package com.yaetotui.backend_web_site.service;

import com.yaetotui.backend_web_site.domain.dto.response.CabinetResponse;
import com.yaetotui.backend_web_site.domain.dto.response.CampusAndCabinetResponse;

import java.util.List;

public interface CabinetService {

    CampusAndCabinetResponse chooseCabinetInDB(Long numberID);

    List<CabinetResponse> searchListCabinets(String value);
}
