package com.m2i.unilabmanagerbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rubric_budget")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties("rubric")
public class RubricBudget {

    @Id
    @Column(name = "rubric_budget_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rubricBudgetId;

    private Integer year;

    private Float allocated_amount;
    private Float engaged_amount;
    private Float remaining_amount;

    @OneToOne(
            mappedBy = "rubricBudget",
            cascade = CascadeType.ALL
    )
    private Rubric rubric;

}
