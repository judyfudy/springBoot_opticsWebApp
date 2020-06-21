package com.bubnii.springBoot_opticsWebApp.controller;

import com.bubnii.springBoot_opticsWebApp.dto.WorkerDTO;
import com.bubnii.springBoot_opticsWebApp.security.jwt.JwtAuthEntryPoint;
import com.bubnii.springBoot_opticsWebApp.service.interfaces.WorkerService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/worker")
public class WorkerController {
    private static final Logger LOGGER = Logger.getLogger(WorkerController.class);
    private final WorkerService workerService;

    public WorkerController(final WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping("/all")
    public List<WorkerDTO> getAllWorkers() {
        LOGGER.info("email.is.working");
        return workerService.getAll();
    }
}
