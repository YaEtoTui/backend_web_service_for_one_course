package com.yaetotui.backend_web_site.adapter.repository;

import com.yaetotui.backend_web_site.domain.entity.Campus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampusRepository extends JpaRepository<Campus, String> {
    Campus findByName(String nameCampus);
}
