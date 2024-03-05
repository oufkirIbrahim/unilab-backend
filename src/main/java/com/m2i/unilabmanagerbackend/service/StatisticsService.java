package com.m2i.unilabmanagerbackend.service;

import com.m2i.unilabmanagerbackend.DTO.statistics.Gauge;
import com.m2i.unilabmanagerbackend.DTO.statistics.LaboratoryDetails;
import com.m2i.unilabmanagerbackend.DTO.statistics.StatisticsDTO;
import com.m2i.unilabmanagerbackend.DTO.statistics.Trending;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StatisticsService {
    ResponseEntity<StatisticsDTO> getStatistics();
}
