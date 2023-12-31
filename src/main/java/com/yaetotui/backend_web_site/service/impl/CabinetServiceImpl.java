package com.yaetotui.backend_web_site.service.impl;

import com.yaetotui.backend_web_site.domain.dto.response.CabinetResponse;
import com.yaetotui.backend_web_site.domain.dto.response.CampusAndCabinetResponse;
import com.yaetotui.backend_web_site.service.CabinetService;
import com.yaetotui.backend_web_site.service.factory.CabinetFactory;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class CabinetServiceImpl implements CabinetService {

    CabinetFactory cabinetFactory;

    @Override
    public CampusAndCabinetResponse chooseCabinetInDB(Long numberID) {
        return cabinetFactory.createCabinetResponseWithCampus(numberID);
    }

    @Override
    public List<CabinetResponse> searchListCabinets(String value) {
        return cabinetFactory.createListCabinetResponse(value);
    }
}
