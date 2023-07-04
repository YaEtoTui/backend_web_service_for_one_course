package com.yaetotui.backend_web_site.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@MappedSuperclass
public class BaseDomainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", allocationSize = 1)
    Long id;

    public int hashCode() {
        return id.hashCode();
    }
}
