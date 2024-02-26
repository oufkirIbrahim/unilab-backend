package com.m2i.unilabmanagerbackend.controller;

import com.m2i.unilabmanagerbackend.service.ConsumableService;
import com.m2i.unilabmanagerbackend.service.LaboratoryService;
import com.m2i.unilabmanagerbackend.service.MaterialService;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class ExportPdfController {

    @Autowired
    private MaterialService materialService;

    @Autowired
    private ConsumableService consumableService;

    @Autowired
    private LaboratoryService laboratoryService;
    @GetMapping("/admin/export/pdf/assignments")
    public void exportAssignmentsPdf(HttpServletResponse response) throws IOException, JRException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        materialService.exportAssignmentsPdf(response);
    }

    @GetMapping("/admin/export/pdf/labAssignments/consumables/{labId}/{year}")
    public void exportLabConsumables(HttpServletResponse response, @PathVariable Integer labId, @PathVariable Integer year) throws IOException, JRException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        consumableService.exportLabConsumables(response, labId, year);
    }

    @GetMapping("/admin/export/pdf/labAssignments/materials/{labId}/{year}")
    public void exportLabMaterials(HttpServletResponse response, @PathVariable Integer labId, @PathVariable Integer year) throws IOException, JRException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        materialService.exportLabMaterials(response, labId, year);
    }


    @GetMapping("/admin/export/pdf/labDetails/{labId}")
    public void exportLabDetails(HttpServletResponse response, @PathVariable Integer labId) throws IOException, JRException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        laboratoryService.exportLabDetails(response, labId);
    }

}
