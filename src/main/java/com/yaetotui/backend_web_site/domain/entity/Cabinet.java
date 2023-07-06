package com.yaetotui.backend_web_site.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cabinet")
public class Cabinet extends BaseDomainEntity {

    String number;
    Integer floor;

    Integer x;
    Integer y;

    @ManyToOne()
    @JoinColumn(name = "campus_id")
    Campus campus;
}
