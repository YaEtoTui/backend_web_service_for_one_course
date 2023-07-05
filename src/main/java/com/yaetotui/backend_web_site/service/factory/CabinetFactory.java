package com.yaetotui.backend_web_site.service.factory;

import com.yaetotui.backend_web_site.adapter.repository.CabinetRepository;
import com.yaetotui.backend_web_site.adapter.repository.CampusRepository;
import com.yaetotui.backend_web_site.common.exception.NotFoundCabinetException;
import com.yaetotui.backend_web_site.common.exception.StartWrongCabinetException;
import com.yaetotui.backend_web_site.domain.dto.response.CabinetResponse;
import com.yaetotui.backend_web_site.domain.entity.Cabinet;
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
public class CabinetFactory {

    CabinetRepository cabinetRepository;

    public CabinetResponse createCabinetResponse(String numberCabinet) {

        //сюда доходит номера типа р-217, надо получить только число типа Integer
        if (!numberCabinet.startsWith("р-")) {
            throw new StartWrongCabinetException("Номер кабинета должен начинаться с символа, обозначающий кампус. Например, р-243");
        }

        Cabinet cabinet = cabinetRepository.findCabinetByNumber(Integer.parseInt(numberCabinet.split("-")[1]));

        if (cabinet == null) {
            throw new NotFoundCabinetException("Нет такого кабинета!");
        }

        return new CabinetResponse(
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
