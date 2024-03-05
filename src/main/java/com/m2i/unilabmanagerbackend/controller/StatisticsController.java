package com.m2i.unilabmanagerbackend.controller;

import com.m2i.unilabmanagerbackend.DTO.statistics.Gauge;
import com.m2i.unilabmanagerbackend.DTO.statistics.LaboratoryDetails;
import com.m2i.unilabmanagerbackend.DTO.statistics.StatisticsDTO;
import com.m2i.unilabmanagerbackend.DTO.statistics.Trending;
import com.m2i.unilabmanagerbackend.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/admin/statistics")
    public ResponseEntity<StatisticsDTO> getStatistics(){
        return statisticsService.getStatistics();
    }
}
