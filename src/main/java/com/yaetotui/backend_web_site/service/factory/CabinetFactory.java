package com.yaetotui.backend_web_site.service.factory;

import com.yaetotui.backend_web_site.adapter.repository.CampusRepository;
import com.yaetotui.backend_web_site.domain.entity.Campus;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CabinetFactory {

    CampusRepository campusRepository;

//    public String faire(String nameCabinet) {
//        String nameCampus;
//        if (nameCabinet.toLowerCase().startsWith("р")) {
//            nameCampus = "ИРИТ-РТФ";
//        }
//
//        Campus campus = campusRepository.findAllByName(nameCampus)
//    }
}
