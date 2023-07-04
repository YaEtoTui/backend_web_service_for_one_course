package com.yaetotui.backend_web_site.domain.dto.response;

import com.yaetotui.backend_web_site.domain.Vector;
import lombok.Value;

import java.util.List;


@Value
public class CampusResponse {
    Long campusID;
    String campusName;
    Vector address;
    String description;
    List<CabinetInfo> cabinet;

    @Value
    public static class CabinetInfo {
        Integer number;
        List<Vector> vector;
    }
}
