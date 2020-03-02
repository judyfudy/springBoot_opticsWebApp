package com.bubnii.springBoot_opticsWebApp.service.interfaces;

import com.bubnii.springBoot_opticsWebApp.dto.WorkerDTO;
import com.bubnii.springBoot_opticsWebApp.entity.Worker;

import java.util.List;

public interface WorkerService {
    List<WorkerDTO> getAll();
}
