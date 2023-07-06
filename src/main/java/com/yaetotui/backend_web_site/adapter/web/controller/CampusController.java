package com.yaetotui.backend_web_site.adapter.web.controller;

import com.yaetotui.backend_web_site.domain.dto.response.CampusAndCabinetResponse;
import com.yaetotui.backend_web_site.service.CampusService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/campus")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CampusController {

    CampusService campusService;

    @GetMapping("/{nameCampus}")
    public ResponseEntity<CampusAndCabinetResponse> searchCampus(@PathVariable("nameCampus") String campusID) {
        return ResponseEntity.ok()
                .body(campusService.searchCampus(campusID.toLowerCase()));
    }
}
