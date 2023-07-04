package com.yaetotui.backend_web_site.adapter.web.controller;

import com.yaetotui.backend_web_site.service.CabinetService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/campus/cabinets")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CabinetController {

    CabinetService cabinetService;

    @GetMapping("/{number}")
    public ResponseEntity<?> searchCabinet(@PathVariable("number") String number ) {
        return ResponseEntity.ok()
                .body(cabinetService.searchCabinet(number));
    }
}
