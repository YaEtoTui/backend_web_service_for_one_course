package com.yaetotui.backend_web_site.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "campus")
public class Campus extends BaseDomainEntity {
    String name;
    float x;
    float y;
    String description;
    @OneToMany(mappedBy = "campus", fetch = FetchType.LAZY)
    List<Cabinet> cabinets = new LinkedList<>();
}
