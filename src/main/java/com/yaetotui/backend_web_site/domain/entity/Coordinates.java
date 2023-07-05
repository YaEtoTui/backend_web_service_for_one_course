package com.yaetotui.backend_web_site.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "coordinates")
public class Coordinates extends BaseDomainEntity {
    Integer x;
    Integer y;

    @ManyToOne()
    @JoinColumn(name = "cabinet_id")
    Cabinet cabinet;
}
