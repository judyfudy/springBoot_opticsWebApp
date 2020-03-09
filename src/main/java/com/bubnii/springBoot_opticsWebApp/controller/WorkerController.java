package com.bubnii.springBoot_opticsWebApp.controller;

import com.bubnii.springBoot_opticsWebApp.dto.WorkerDTO;
import com.bubnii.springBoot_opticsWebApp.service.interfaces.WorkerService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/worker")
public class WorkerController {

    private final WorkerService workerService;

    public WorkerController(final WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping("/all")
    public List<WorkerDTO> getAllWorkers() {
        return workerService.getAll();
    }
}
