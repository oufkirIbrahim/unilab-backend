package com.m2i.unilabmanagerbackend.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "laboratory")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Laboratory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "laboratory_id")
    private Integer labId;
    private String name;

    @JsonManagedReference(value = "responsiblePerson")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "responsible_person_id",
            referencedColumnName = "person_id"
    )
    private User responsiblePerson;

    @JsonManagedReference(value = "deputyPerson")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "deputy_person_id",
            referencedColumnName = "person_id"
    )
    private User deputyPerson;


    @OneToMany(mappedBy = "laboratory")
    @JsonIgnore
    private List<ConsumableAssignment> consumableAssignments;
}
