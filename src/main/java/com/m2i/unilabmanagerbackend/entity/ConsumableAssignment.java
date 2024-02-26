package com.m2i.unilabmanagerbackend.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "consumable_assignment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsumableAssignment implements Serializable {

    @Id
    @Column(name = "assign_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quantity;

    @Column(name = "assignment_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date assignmentDate;

    @ManyToOne
    @JoinColumn(
            name = "consumable_id",
            referencedColumnName = "consumable_id"
    )
    private Consumable consumable;

    @ManyToOne
    @JoinColumn(
            name = "laboratory_id",
            referencedColumnName = "laboratory_id"
    )
    private Laboratory laboratory;

//    @ManyToOne
//    @JoinColumn(
//            name = "person_id",
//            referencedColumnName = "person_id"
//    )
//    private User person;

    @PrePersist
    public void prePersist() {
        this.assignmentDate = new Date();
    }

}
