package com.yaetotui.backend_web_site.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cabinet")
public class Cabinet extends BaseDomainEntity {

    String number;
    Long floor;

    Integer x;
    Integer y;

    @ManyToOne()
    @JoinColumn(name = "campus_id")
    Campus campus;
}
