package com.m2i.unilabmanagerbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "material_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialOrder {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "request_date")
    private Date requestDate;

    private ApprovalStatus approvalStatus;

    @ManyToOne

    @JoinColumn(
            name = "person_id",
            referencedColumnName = "person_id"
    )
    private User person;


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
