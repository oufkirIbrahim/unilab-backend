package com.m2i.unilabmanagerbackend.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "consumable")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consumable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consumable_id")
    private Integer consumableId;

    private String type;

    private Integer quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "supplier_id",
            referencedColumnName = "supplier_id"
    )
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(
            name = "laboratory_id",
            referencedColumnName = "laboratory_id"
    )
    private Laboratory laboratory;

    @Column(name = "assignment_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date assignmentDate;

    @PrePersist
    public void prePersist() {
        this.assignmentDate = new Date();
    }
}
