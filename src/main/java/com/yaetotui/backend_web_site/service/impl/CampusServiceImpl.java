package com.yaetotui.backend_web_site.service.impl;

import com.yaetotui.backend_web_site.domain.dto.response.CampusResponse;
import com.yaetotui.backend_web_site.service.CampusService;
import com.yaetotui.backend_web_site.service.factory.CampusFactory;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class CampusServiceImpl implements CampusService {

    CampusFactory campusFactory;

    @Override
    public CampusResponse searchCampus(String campusID) {
        return campusFactory.createCampusResponse(campusID);
    }
}
