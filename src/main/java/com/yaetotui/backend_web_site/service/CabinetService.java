package com.yaetotui.backend_web_site.service;

import com.yaetotui.backend_web_site.domain.dto.response.CabinetResponse;

public interface CabinetService {

    CabinetResponse searchCabinet(String number);
}
