package com.yaetotui.backend_web_site.domain.dto.response;

import com.yaetotui.backend_web_site.domain.Vector;
import lombok.Value;

import java.awt.*;
import java.util.List;


@Value
public class CampusResponse {
    String campusID;
    String campusName;
    Vector address;
    String description;
    List<CabinetInfo> cabinets;

    @Value
    public static class CabinetInfo {
        String number;
        Long floor;
        List<Point> vectors;
    }
}
