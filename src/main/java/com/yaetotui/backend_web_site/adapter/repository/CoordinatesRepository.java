package com.yaetotui.backend_web_site.adapter.repository;

import com.yaetotui.backend_web_site.domain.entity.Coordinates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinatesRepository extends JpaRepository<Coordinates, Long> {
    Coordinates findCoordinatesByXAndYAndFloor(Integer x, Integer y,Integer floor);
}
