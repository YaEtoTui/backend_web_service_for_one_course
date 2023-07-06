package com.yaetotui.backend_web_site.service.factory;

import com.yaetotui.backend_web_site.adapter.repository.CabinetRepository;
import com.yaetotui.backend_web_site.common.exception.NotFoundCabinetException;
import com.yaetotui.backend_web_site.domain.Vector;
import com.yaetotui.backend_web_site.domain.dto.response.CabinetResponse;
import com.yaetotui.backend_web_site.domain.dto.response.CampusResponse;
import com.yaetotui.backend_web_site.domain.entity.Cabinet;
import com.yaetotui.backend_web_site.domain.entity.Campus;
import com.yaetotui.backend_web_site.domain.entity.Coordinates;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class CabinetFactory {

    CabinetRepository cabinetRepository;

    //Переделать
    public List<CabinetResponse> createListCabinetResponse(String value) {
        List<Cabinet> cabinetList;
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
        return new CabinetResponse(
                cabinet.getId(),
                String.format("%s-%s",cabinet.getCampus().getSymbol(), cabinet.getNumber())
        );
    }

    public CampusResponse createCabinetResponseWithCampus(Long numberID) {

        Cabinet cabinet = cabinetRepository.findById(numberID)
                .orElseThrow(() -> new NotFoundCabinetException(
                        String.format("Не правильно введен id Кабинета. Был: '%s'", numberID)
                        )
                );

        return new CampusResponse(
                cabinet.getCampus().getId(),
                cabinet.getCampus().getName(),
                new Vector(cabinet.getCampus().getX(), cabinet.getCampus().getY()),
                cabinet.getCampus().getDescription(),
                cabinet.getCampus().getCabinets().stream()
                        .map(this::createCabinetInfo)
                        .collect(Collectors.toList())

        );
    }

    private CampusResponse.CabinetInfo createCabinetInfo(Cabinet cabinet) {
        return new CampusResponse.CabinetInfo(
                cabinet.getNumber(),
                cabinet.getFloor(),
                cabinet.getCoordinates().stream()
                        .map(this::createPoint)
                        .collect(Collectors.toList())
        );
    }

    private Point createPoint(Coordinates coordinates) {
        return new Point(
                coordinates.getX(),
                coordinates.getY()
        );
    }
}
