package com.yaetotui.backend_web_site.service;

import com.yaetotui.backend_web_site.domain.dto.response.CabinetResponse;
import com.yaetotui.backend_web_site.domain.dto.response.CampusResponse;

import java.util.List;

public interface CabinetService {

    CampusResponse searchCabinet(String number);

    List<CabinetResponse> searchListCabinets(String value);
}
