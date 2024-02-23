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

    @EmbeddedId
    private ConsumableAssignmentKey id;

    private Integer quantity;

    @Column(name = "assignment_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date assignmentDate;

    @ManyToOne
    @MapsId("consumableId")
    @JoinColumn(name = "consumable_id")
    private Consumable consumable;

    @ManyToOne
    @MapsId("labId")
    @JoinColumn(name = "laboratory_id")
    private Laboratory laboratory;

    @PrePersist
    public void prePersist() {
        this.assignmentDate = new Date();
    }

}
