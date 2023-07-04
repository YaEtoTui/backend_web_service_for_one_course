package com.yaetotui.backend_web_site.service.impl;

import com.yaetotui.backend_web_site.domain.dto.response.CampusResponse;
import com.yaetotui.backend_web_site.service.CabinetService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CabinetServiceImpl implements CabinetService {

    @Override
    public CampusResponse searchCabinet(String number) {

        return null;
    }
}
