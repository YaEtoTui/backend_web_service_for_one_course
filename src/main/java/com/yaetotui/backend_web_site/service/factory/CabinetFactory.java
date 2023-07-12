package com.yaetotui.backend_web_site.service.factory;

import com.yaetotui.backend_web_site.adapter.repository.CabinetRepository;
import com.yaetotui.backend_web_site.adapter.repository.CoordinatesRepository;
import com.yaetotui.backend_web_site.common.exception.NotFoundCabinetException;
import com.yaetotui.backend_web_site.common.exception.NotFoundCoordinatesException;
import com.yaetotui.backend_web_site.domain.Vector;
import com.yaetotui.backend_web_site.domain.dto.response.CabinetResponse;
import com.yaetotui.backend_web_site.domain.dto.response.CampusAndCabinetResponse;
import com.yaetotui.backend_web_site.domain.entity.Cabinet;
import com.yaetotui.backend_web_site.domain.entity.Coordinates;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class CabinetFactory {

    @NonFinal
    Integer CountFloors; /* Нужна глобальная переменная для перечисления этажей по порядку */

    CabinetRepository cabinetRepository;
    CoordinatesRepository coordinatesRepository;

    List<String> symbolsList = Stream
            .of("р", "м", "ф", "гук")
            .toList();

    /* Ищем список кабинетов, которые содержат входящий элемент. При этом мы его фильтруем. Например, 'р', 'р-' и само значение */

    public List<CabinetResponse> createListCabinetResponse(String value) {

        String symbol = (symbolsList.stream().anyMatch(value::startsWith)) ?
                symbolsList.stream().filter(value::startsWith).findAny().get() : null;

        return findListCabinetsByValue(value, symbol).stream()
                .map(this::createCabinetResponse)
                .toList();
    }

    /* Поисковик. Находим сам список, приэтом фильтруем данные, чтобы получить более точный ответ */

    private List<Cabinet> findListCabinetsByValue(String value, String symbol) {

        List<Cabinet> cabinetList;
        if (value.startsWith(String.format("%s-", symbol)) && isInteger(value.substring(symbol.length() + 1))) {
            cabinetList = cabinetRepository.findCabinetsByNumberStartingWithIgnoreCaseAndCampusSymbol(value.substring(symbol.length() + 1), symbol);
        } else if (value.startsWith(String.format("%s", symbol)) && isInteger(value.substring(symbol.length()))) {
            cabinetList = cabinetRepository.findCabinetsByNumberStartingWithIgnoreCaseAndCampusSymbol(value.substring(symbol.length()), symbol);
        } else {
            cabinetList = cabinetRepository.findCabinetsByNumberStartingWithOrDescriptionContainingIgnoreCase(value, value);
        }

        return cabinetList;
    }

    /* Проверяем на то что строка состоит полностью из цифр */

    private static boolean isInteger(String str) {

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    /* Создаем объект CabinetResponse */

    public CabinetResponse createCabinetResponse(Cabinet cabinet) {
        return new CabinetResponse(
                cabinet.getId(),
                String.format("%s-%s",cabinet.getCampus().getSymbol(), cabinet.getNumber().toLowerCase()),
                cabinet.getDescription()
        );
    }

    public CampusAndCabinetResponse createCabinetResponseWithCampus(Long numberID) {

        Cabinet cabinet = cabinetRepository.findById(numberID)
                .orElseThrow(() -> new NotFoundCabinetException(
                        String.format("Не правильно введен id Кабинета. Был: '%s'", numberID)
                        )
                );

        return new CampusAndCabinetResponse(
                cabinet.getCampus().getId(),
                cabinet.getCampus().getName(),
                new Vector(cabinet.getCampus().getX(), cabinet.getCampus().getY()),
                cabinet.getCampus().getDescription(),
                createCabinetInfo(cabinet, searchPathToCabinet(cabinet))
        );
    }

    private CampusAndCabinetResponse.CabinetInfo createCabinetInfo(Cabinet cabinet, List<List<Coordinates>> coordinatesList) {
        CountFloors = 0; //init global variable

        List<String> listDescription = Stream.of(
                cabinet.getDescriptionStep1(),
                cabinet.getDescriptionStep2(),
                cabinet.getDescriptionStep3()
                )
                        .collect(Collectors.toList());

        listDescription.removeIf(Objects::isNull); //Удаляем все null элементы

        return new CampusAndCabinetResponse.CabinetInfo(
                cabinet.getNumber(),
                cabinet.getDescription(),
                listDescription,
                coordinatesList.stream()
                        .map(list -> createFloorsInfo(cabinet, list))
                        .collect(Collectors.toList())
        );
    }

    private CampusAndCabinetResponse.CabinetInfo.FloorsInfo createFloorsInfo(Cabinet cabinet, List<Coordinates> coordinatesList) {

        if (CountFloors == 1 && (cabinet.getFloor() >= 3 || cabinet.getFloor() <= 0)) {
            CountFloors = cabinet.getFloor();
        }
        else {
            CountFloors = 1;
        }

        return new CampusAndCabinetResponse.CabinetInfo.FloorsInfo(
                CountFloors,
                coordinatesList.stream()
                        .map(this::createPoint)
                        .collect(Collectors.toList())
        );
    }

    /* Создаем точку */

    public Point createPoint(Coordinates coordinates) {
        return new Point(
                coordinates.getX(),
                coordinates.getY()
        );
    }

    /* Метод, который ищет список координат до нужного кабинета */

    private List<List<Coordinates>> searchPathToCabinet(Cabinet cabinet) {

        Coordinates coordinates = coordinatesRepository.findCoordinatesByXAndYAndFloor(cabinet.getX(), cabinet.getY(), cabinet.getFloor());

        if (coordinates == null) {
            throw new NotFoundCoordinatesException(
                    String.format("Не найдены координаты x: %s y: %s на этаже %s", cabinet.getX(), cabinet.getY(), cabinet.getFloor())
            );
        }

        List<Coordinates> listPath = new LinkedList<>();
        while(coordinates != null) {
            listPath.add(coordinates);
            coordinates = coordinates.getCoordinates();
        }

        Collections.reverse(listPath); //меняем местами координаты

        List<List<Coordinates>> listXCoordinates = new LinkedList<>();
        for (int i = 0; i < cabinet.getFloor(); i++)
            listXCoordinates.add(new LinkedList<>());

        if ((cabinet.getFloor() == 1) || (cabinet.getFloor() == 2)) {
            listPath.forEach(coordinatesPath -> listXCoordinates.get(coordinatesPath.getFloor() - 1).add(coordinatesPath)); //заполняем координаты по этажам
        } else {
            listPath.stream()
                    .filter(coordinatesPath -> coordinatesPath.getFloor() != 2)
                    .forEach(coordinatesPath -> listXCoordinates.get(coordinatesPath.getFloor() - 1).add(coordinatesPath));
        }

        listXCoordinates.removeIf(List::isEmpty);

        return listXCoordinates;
    }
}