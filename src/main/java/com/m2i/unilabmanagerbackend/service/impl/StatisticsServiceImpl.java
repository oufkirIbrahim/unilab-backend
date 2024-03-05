package com.m2i.unilabmanagerbackend.service.impl;

import com.m2i.unilabmanagerbackend.DTO.statistics.*;
import com.m2i.unilabmanagerbackend.entity.*;
import com.m2i.unilabmanagerbackend.repository.*;
import com.m2i.unilabmanagerbackend.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private ConsumableRepository consumableRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private RubricRepository rubricRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private LabRepository labRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LabConsumablesOrderRepository labConsumablesOrderRepository;
    @Override
    public ResponseEntity<StatisticsDTO> getStatistics() {
        StatisticsDTO statisticsDTO = new StatisticsDTO();
        statisticsDTO.setGauge(getGaugeStatistics());
        statisticsDTO.setLaboratories(getLaboratoryDetails());
        Consumables consumables = new Consumables();
        consumables.setTrending(getConsumablesTrending());
        consumables.setData(getDistinctConsumableQuantities());
        statisticsDTO.setConsumables(consumables);
        return new ResponseEntity<>(statisticsDTO, HttpStatus.OK);
    }

    private Gauge getGaugeStatistics() throws ArithmeticException {
        Long consumablesTotal = consumableRepository.count();

        Long assignedConsumables = consumableRepository.countByLaboratoryIsNotNull();
        Double consumablesStatistic = consumablesTotal != 0 ? (double) assignedConsumables / consumablesTotal * 100 : 0.0;

        Long materialsTotal = materialRepository.count();
        Long assignedMaterials = materialRepository.countByResponsiblePersonIsNotNull();
        Double materialsStatistic = materialsTotal != 0 ? (double) assignedMaterials / materialsTotal * 100 : 0.0;

        Double totalAllocatedAmount = rubricRepository.sumAllocatedAmount();
        Double totalEngagedAmount = rubricRepository.sumEngagedAmount();
        Double amountStatistic = totalAllocatedAmount != 0 ? totalEngagedAmount / totalAllocatedAmount * 100 : 0.0;

        Long totalSuppliers = supplierRepository.count();
        List<Supplier> materialsSuppliers = materialRepository.findDistinctSuppliers();
        List<Supplier> consumablesSuppliers = consumableRepository.findDistinctSuppliers();
        List<Supplier> suppliers = new ArrayList<>(materialsSuppliers);
        suppliers.addAll(consumablesSuppliers);
        Set<Supplier> distinctSuppliers = new HashSet<>(suppliers);
        Double suppliersStatistic = totalSuppliers != 0 ? (double) distinctSuppliers.size() / totalSuppliers * 100 : 0.0;

        return new Gauge(consumablesStatistic, materialsStatistic, amountStatistic, suppliersStatistic);
    }

    private List<LaboratoryDetails> getLaboratoryDetails(){
        List<Laboratory> laboratories = labRepository.findAll();
        List<LaboratoryDetails> laboratoriesDetails = new ArrayList<>();
        Integer adminsCount = 0;
        Integer usersCount = 0;
        LaboratoryDetails labDetails;
        for (Laboratory lab : laboratories) {
            adminsCount = userRepository.countUsersByLaboratoryIdAndRoleEquals(lab.getLaboratoryId(), Role.RESPONSIBLE);
            usersCount = userRepository.countUsersByLaboratoryIdAndRoleEquals(lab.getLaboratoryId(), Role.USER);

            labDetails = new LaboratoryDetails(lab.getLaboratoryId(), lab.getImage(), lab.getName(), adminsCount, usersCount);
            laboratoriesDetails.add(labDetails);
        }
        return laboratoriesDetails;
    }

    private List<Trending> getConsumablesTrending() {
        Long totalOrdersCount = labConsumablesOrderRepository.count();
        List<Consumable> consumables = consumableRepository.findAll();
        List<Trending> trendingList = new ArrayList<>();

        for (Consumable consumable : consumables) {
            Trending trending = new Trending();
            trending.setName(consumable.getType());
            Integer consumableOrdersCount = labConsumablesOrderRepository.countConsumablesOrderByConsumable(consumable);
            Double consumableOrdersStatistic = totalOrdersCount != 0 ? (double) consumableOrdersCount / totalOrdersCount : 0;
            trending.setAlpha(consumableOrdersStatistic);
            trendingList.add(trending);
        }

        Set<Trending> distinctTrending = new HashSet<>(trendingList);
        trendingList = new ArrayList<>(distinctTrending); // Convert back to ArrayList
        trendingList.sort(Comparator.comparingDouble(Trending::getAlpha).reversed());

        // Return the first 10 elements
        return trendingList.subList(0, Math.min(10, trendingList.size()));
    }

    public List<ConsumablesData> getDistinctConsumableQuantities() {
        List<Object[]> consumableQuantities = consumableRepository.findDistinctConsumableQuantities();
        List<ConsumablesData> consumablesDataList = new ArrayList<>();

        for (Object[] result : consumableQuantities) {
            String type = (String) result[0];
            Integer quantity = ( (Number) result[1]).intValue();
            consumablesDataList.add(new ConsumablesData(type, quantity));
        }

        return consumablesDataList;
    }
}
