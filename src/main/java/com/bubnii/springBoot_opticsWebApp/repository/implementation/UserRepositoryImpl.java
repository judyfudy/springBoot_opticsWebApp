package com.bubnii.springBoot_opticsWebApp.repository.implementation;

import com.bubnii.springBoot_opticsWebApp.entity.User;
import com.bubnii.springBoot_opticsWebApp.repository.interfaces.UserRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getPersonByCredentials(final String username) {
        return jdbcTemplate.queryForObject("SELECT * FROM person WHERE person.username = ?",
                new BeanPropertyRowMapper<>(User.class), username);
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void add(final User user) {
        jdbcTemplate.update("INSERT INTO person(username, pass, first_name, last_name, email) "
                        + "VALUES(?,?,?,?,?)", user.getUsername(), user.getPass(), user.getFirstName(),
                user.getLastName(), user.getEmail());
    }

    @Override
    public User get(final int id) {
       return jdbcTemplate.queryForObject("SELECT * FROM person WHERE person.id = ?",
               new BeanPropertyRowMapper<>(User.class), id);
    }

    @Override
    public boolean update(final User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(final int id) {
        throw new UnsupportedOperationException();
    }
}
