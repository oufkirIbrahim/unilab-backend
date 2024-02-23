package com.m2i.unilabmanagerbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ConsumableOrder {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "request_date")
    private Date requestDate;

    private ApprovalStatus approvalStatus;

    private Integer quantity;

    @ManyToOne

    @JoinColumn(
            name = "person_id",
            referencedColumnName = "person_id"
    )
    private User person;


    @ManyToOne
    @JoinColumn(
            name = "consumable_id",
            referencedColumnName = "consumable_id"
    )
    private Consumable consumable;


    @PrePersist
    public void prePersist() {
        this.requestDate = new Date();
    }
}
