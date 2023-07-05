package com.yaetotui.backend_web_site.domain.dto.response;

import com.yaetotui.backend_web_site.domain.Vector;
import lombok.Value;

import java.awt.*;
import java.util.List;


@Value
public class CampusResponse {
    Long campusID;
    String campusName;
    Vector address;
    String description;
    List<CabinetInfo> cabinets;

    @Value
    public static class CabinetInfo {
        Integer number;
        List<Point> vectors;
    }
}
