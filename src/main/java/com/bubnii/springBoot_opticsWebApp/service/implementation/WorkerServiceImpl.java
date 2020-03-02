package com.bubnii.springBoot_opticsWebApp.service.implementation;

import com.bubnii.springBoot_opticsWebApp.dto.WorkerDTO;
import com.bubnii.springBoot_opticsWebApp.repository.interfaces.WorkerRepository;
import com.bubnii.springBoot_opticsWebApp.service.interfaces.WorkerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;
    private final ModelMapper modelMapper;

    public WorkerServiceImpl(final WorkerRepository workerRepository, final ModelMapper modelMapper) {
        this.workerRepository = workerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<WorkerDTO> getAll() {
        return workerRepository.getAll()
                .stream()
                .map(worker -> modelMapper.map(worker, WorkerDTO.class))
                .collect(Collectors.toList());
    }
}
