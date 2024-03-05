package com.m2i.unilabmanagerbackend.service;

import com.m2i.unilabmanagerbackend.entity.Rubric;
import com.m2i.unilabmanagerbackend.error.RubricNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RubricService {
    Rubric saveRubric(Rubric rubric);

    List<Rubric> getAllRubric();

    Rubric getRubricById(Integer id) throws RubricNotFoundException;

    Rubric getRubricByCode(String code) throws RubricNotFoundException;

    ResponseEntity<?> updateRubric(Integer id , Rubric rubric) throws RubricNotFoundException;

    ResponseEntity<?> deleteRubricById(Integer id);
}
