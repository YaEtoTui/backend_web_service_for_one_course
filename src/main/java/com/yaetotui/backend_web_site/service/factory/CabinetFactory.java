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

    List<String> stringList = Stream
            .of("р", "м", "ф")
            .toList();

    /* Ищем список кабинетов, которые содержат входящий элемент*/

    public List<CabinetResponse> createListCabinetResponse(String value) {
        //Ниже 4 строчки сделать короче
        char symbol = '@';
        if (stringList.stream().anyMatch(value::startsWith)) {
            symbol = stringList.stream().filter(value::startsWith).findAny().get().charAt(0);
        }

        //Ниже 8 строчек сделать короче
        List<Cabinet> cabinetList;
        if (value.startsWith(String.format("%s-", symbol))) {
            cabinetList = cabinetRepository.findCabinetsByNumberStartingWithIgnoreCaseAndCampusSymbol(value.substring(2), symbol);
        } else if (value.startsWith(String.format("%s", symbol))) {
            cabinetList = cabinetRepository.findCabinetsByNumberStartingWithIgnoreCaseAndCampusSymbol(value.substring(1), symbol);
        } else {
            cabinetList = cabinetRepository.findCabinetsByNumberStartingWith(value);
        }

        return cabinetList.stream()
                .map(this::createCabinetResponse)
                .toList();
    }

    public CabinetResponse createCabinetResponse(Cabinet cabinet) {
        return new CabinetResponse(
                cabinet.getId(),
                String.format("%s-%s",cabinet.getCampus().getSymbol(), cabinet.getNumber())
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
                        .map(list -> createFloorsInfo(list, listDescription))
                        .collect(Collectors.toList())
        );
    }

    private CampusAndCabinetResponse.CabinetInfo.FloorsInfo createFloorsInfo(List<Coordinates> coordinatesList,
                                                                             List<String> listDescription) {
        CountFloors++;
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

        listPath.forEach(coordinatesPath -> listXCoordinates.get(coordinatesPath.getFloor() - 1).add(coordinatesPath)); //заполняем координаты по этажам

        return listXCoordinates;
    }
}