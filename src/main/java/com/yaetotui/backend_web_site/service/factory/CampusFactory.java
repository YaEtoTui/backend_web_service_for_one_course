package com.yaetotui.backend_web_site.service.factory;

import com.yaetotui.backend_web_site.adapter.repository.CampusRepository;
import com.yaetotui.backend_web_site.common.exception.NotFoundCampusException;
import com.yaetotui.backend_web_site.domain.Vector;
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
import java.util.stream.Collectors;


@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class CampusFactory {

    CampusRepository campusRepository;

    public CampusResponse createCampusResponse(String nameCampus) {

        Campus campus = campusRepository.findAllByName(nameCampus);

        if (campus == null) {
            throw new NotFoundCampusException("Не правильно веден кампус!");
        }

        return new CampusResponse(
                campus.getId(),
                campus.getName(),
                new Vector(campus.getX(), campus.getY()),
                campus.getDescription(),
                campus.getCabinets().stream()
                        .map(this::createCabinetInfo)
                        .collect(Collectors.toList())
        );
    }

    private CampusResponse.CabinetInfo createCabinetInfo(Cabinet cabinet) {
        return new CampusResponse.CabinetInfo(
                cabinet.getNumber(),
                cabinet.getCoordinates().stream()
                        .map(this::createListVector)
                        .collect(Collectors.toList())
        );
    }
     private Point createListVector(Coordinates coordinates) {
        return new Point(
                coordinates.getX(),
                coordinates.getY()
        );
     }
}
