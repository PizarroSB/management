package com.event.management.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "tb_event")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String date;
    @Column(nullable = false)
    private String location;

    @ManyToMany(mappedBy = "events",cascade = CascadeType.ALL)
    private List<UserEntity> users = new ArrayList<>();


}
