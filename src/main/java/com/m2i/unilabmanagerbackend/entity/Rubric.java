package com.m2i.unilabmanagerbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rubric")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties("rubric")
public class Rubric {

    @Id
    @Column(name = "rubric_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rubricId;

    private String code;

    private String designation;

    @Column(name = "allocated_amount")
    private Float allocatedAmount;

    @Column(name = "engaged_amount")
    private Float engagedAmount;
}
