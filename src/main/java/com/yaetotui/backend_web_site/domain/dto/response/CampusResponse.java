package com.yaetotui.backend_web_site.domain.dto.response;

import com.yaetotui.backend_web_site.domain.Vector;
import lombok.Value;

import java.awt.*;
import java.util.List;


@Value
public class CampusResponse {
    String campusID;
    String campusName;
    Vector addressCampus;
    String descriptionCampus;
    List<CabinetInfo> cabinets;

    @Value
    public static class CabinetInfo {
        String numberCabinet;
        Long floor;
        List<Point> vectors;
    }
}
