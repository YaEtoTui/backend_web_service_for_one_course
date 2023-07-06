package com.yaetotui.backend_web_site.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "coordinates")
public class Coordinates extends BaseDomainEntity {
    Integer x;
    Integer y;
    Integer floor;
    @OneToOne()
    @JoinColumn(name = "coordinates_parent_id")
    Coordinates coordinates;
}
