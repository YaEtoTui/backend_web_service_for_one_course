package com.yaetotui.backend_web_site.service.factory;

import com.yaetotui.backend_web_site.adapter.repository.CabinetRepository;
import com.yaetotui.backend_web_site.domain.dto.response.CabinetResponse;
import com.yaetotui.backend_web_site.domain.dto.response.CampusResponse;
import com.yaetotui.backend_web_site.domain.entity.Cabinet;
import com.yaetotui.backend_web_site.domain.entity.Coordinates;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class CabinetFactory {

    CabinetRepository cabinetRepository;

    public List<CabinetResponse> createListCabinetResponse(String value) {
        List<Cabinet> cabinetList = new LinkedList<>();
        if (value.startsWith("р-")) {
            cabinetList = cabinetRepository.findCabinetsByNumberContainingIgnoreCase(value.split("-")[1]);
        } else if (value.startsWith("р")) {
            System.out.println(value.substring(1));
            cabinetList = cabinetRepository.findCabinetsByNumberContainingIgnoreCase(value.substring(1));

        } else {
            cabinetList = cabinetRepository.findCabinetsByNumberContainingIgnoreCase(value);
        }

        List<CabinetResponse> cabinetResponseList = cabinetList.stream()
                .map(this::createCabinetResponse)
                .toList();

        return cabinetResponseList;
    }

    public CabinetResponse createCabinetResponse(Cabinet cabinet) {
        return new CabinetResponse(cabinet.getNumber());
    }

    public CampusResponse createCabinetResponsee(String numberCabinet) {

        //сюда доходит номера типа р-217, надо получить только число типа Integer
//        if (!numberCabinet.startsWith("р-")) {
//            throw new StartWrongCabinetException("Номер кабинета должен начинаться с символа, обозначающий кампус. Например, р-243");
//        }
//
//        Cabinet cabinet = cabinetRepository.findCabinetByNumber(Integer.parseInt(numberCabinet.split("-")[1]));
//
//        if (cabinet == null) {
//            throw new NotFoundCabinetException("Нет такого кабинета!");
//        }
//
//        return new CabinetResponse(
//                cabinet.getNumber(),
//                cabinet.getCoordinates().stream()
//                        .map(this::createListVector)
//                        .collect(Collectors.toList())
//        );
        return null;
    }

    private Point createListVector(Coordinates coordinates) {
        return new Point(
                coordinates.getX(),
                coordinates.getY()
        );
    }
}
