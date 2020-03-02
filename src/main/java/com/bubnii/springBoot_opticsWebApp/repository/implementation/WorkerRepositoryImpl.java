package com.bubnii.springBoot_opticsWebApp.repository.implementation;

import com.bubnii.springBoot_opticsWebApp.entity.Worker;
import com.bubnii.springBoot_opticsWebApp.repository.interfaces.WorkerRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkerRepositoryImpl implements WorkerRepository {

    private final JdbcTemplate jdbcTemplate;

    public WorkerRepositoryImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Worker> getAll() {
        return jdbcTemplate.query("SELECT * FROM worker", new BeanPropertyRowMapper<>(Worker.class));
    }

    @Override
    public void add(final Worker worker) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Worker get(final int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(final Worker worker) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(final int id) {
        throw new UnsupportedOperationException();
    }
}
