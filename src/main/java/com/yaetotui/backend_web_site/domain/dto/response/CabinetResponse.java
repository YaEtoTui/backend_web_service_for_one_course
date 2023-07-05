package com.yaetotui.backend_web_site.domain.dto.response;

import lombok.Value;

import java.awt.*;
import java.util.List;

@Value
public class CabinetResponse {
    Integer number;
    List<Point> vectors;
}
