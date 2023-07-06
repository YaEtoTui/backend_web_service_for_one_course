package com.yaetotui.backend_web_site.service;

import com.yaetotui.backend_web_site.domain.dto.response.CampusAndCabinetResponse;

public interface CampusService {

    CampusAndCabinetResponse searchCampus(String campusID);
}
