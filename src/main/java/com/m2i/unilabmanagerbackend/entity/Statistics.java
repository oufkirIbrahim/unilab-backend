package com.m2i.unilabmanagerbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Entity
@Table(name = "statistics")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statistics_id")
    private Integer statisticsId;

    private Integer year = Year.now().getValue();;
    @Column(
            name = "usage_count",
            columnDefinition = "integer default 0"
    )
    private Integer usageCount = 0;



}
