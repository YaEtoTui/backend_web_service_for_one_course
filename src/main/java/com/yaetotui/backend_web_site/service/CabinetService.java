package com.yaetotui.backend_web_site.service;

import com.yaetotui.backend_web_site.domain.dto.response.CampusResponse;

public interface CabinetService {

    CampusResponse searchCabinet(String number);
}
