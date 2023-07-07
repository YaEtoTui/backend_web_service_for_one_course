package com.yaetotui.backend_web_site.adapter.web.controller;

import com.yaetotui.backend_web_site.domain.dto.response.CabinetResponse;
import com.yaetotui.backend_web_site.domain.dto.response.CampusAndCabinetResponse;
import com.yaetotui.backend_web_site.service.CabinetService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campus/cabinets")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@CrossOrigin
public class CabinetController {

    CabinetService cabinetService;

    @GetMapping("/cabinet")
    public ResponseEntity<CampusAndCabinetResponse> chooseCabinetInDB(@RequestParam("numberID") Long numberID ) {
        return ResponseEntity.ok()
                .body(cabinetService.chooseCabinetInDB(numberID));
    }

    @GetMapping("/list")
    public ResponseEntity<List<CabinetResponse>> searchListCabinets(@RequestParam("value") String number ) {
        return ResponseEntity.ok()
                .body(cabinetService.searchListCabinets(number.toLowerCase()));
    }
}
