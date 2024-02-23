package com.m2i.unilabmanagerbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumableAssignmentKey implements Serializable {

    @Column(name = "consumable_id")
    private Integer consumableId;

    @Column(name = "laboratory_id")
    private Integer laboratoryId;
}
