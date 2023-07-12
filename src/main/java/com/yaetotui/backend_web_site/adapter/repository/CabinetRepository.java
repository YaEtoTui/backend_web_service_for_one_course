package com.yaetotui.backend_web_site.adapter.repository;

import com.yaetotui.backend_web_site.domain.entity.Cabinet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CabinetRepository extends JpaRepository<Cabinet, Long> {
    List<Cabinet> findCabinetsByNumberStartingWithOrDescriptionContainingIgnoreCase(String number, String description);

    List<Cabinet> findCabinetsByNumberStartingWithIgnoreCaseAndCampusSymbol(String value, String symbol);
}
