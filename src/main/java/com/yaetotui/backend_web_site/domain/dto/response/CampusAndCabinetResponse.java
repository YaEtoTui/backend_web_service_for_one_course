package com.yaetotui.backend_web_site.domain.dto.response;

import com.yaetotui.backend_web_site.domain.Vector;
import lombok.Value;

import java.awt.*;
import java.util.List;


@Value
public class CampusAndCabinetResponse {
    String campusID;
    String campusName;
    Vector addressCampus;
    String descriptionCampus;
    CabinetInfo cabinet;

    @Value
    public static class CabinetInfo {
        String numberCabinet;
        String descriptionCabinet;
        List<FloorsInfo> floors;

        @Value
        public static class FloorsInfo {
            Integer floor;
            String descriptionStep;
            List<Point> vectors;
        }

    }
}
