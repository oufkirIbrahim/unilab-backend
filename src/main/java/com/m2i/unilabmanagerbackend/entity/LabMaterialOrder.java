package com.m2i.unilabmanagerbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabMaterialOrder {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "request_date")
    private Date requestDate;

    private ApprovalStatus approvalStatus;

    @ManyToOne
    @JoinColumn(
            name = "laboratory_id",
            referencedColumnName = "laboratory_id"
    )
    private Laboratory laboratory;


    @ManyToOne
    @JoinColumn(
            name = "material_id",
            referencedColumnName = "material_id"
    )
    private Material material;


    @PrePersist
    public void prePersist() {
        this.requestDate = new Date();
    }
}
