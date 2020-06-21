package com.bubnii.springBoot_opticsWebApp.repository.implementation;

import com.bubnii.springBoot_opticsWebApp.entity.User;
import com.bubnii.springBoot_opticsWebApp.enums.UserType;
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
        return jdbcTemplate.queryForObject("SELECT * FROM user WHERE user.username = ?",
                new BeanPropertyRowMapper<>(User.class), username);
    }

    @Override
    public boolean existsByUsername(String username) {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user WHERE username=?", Integer.class, username);
        return count != null && count > 0;
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM user", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void add(final User user) {
        jdbcTemplate.update("INSERT INTO user(username, pass, first_name, last_name, email, userType) "
                        + "VALUES(?,?,?,?,?,?)", user.getUsername(), user.getPass(), user.getFirstName(),
                user.getLastName(), user.getEmail(), UserType.USER.toString());
    }

    @Override
    public User get(final int id) {
       return jdbcTemplate.queryForObject("SELECT * FROM user WHERE user.id = ?",
               new BeanPropertyRowMapper<>(User.class), id);
    }

    @Override
    public void update(final User user) {
        jdbcTemplate.update("UPDATE user SET username=?, first_name=?,last_name=?," +
                "email=?, userType=?,pass=?", user.getUsername(), user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getUserType().toString(), user.getPass());
    }

    @Override
    public void changeUserRole(final String role, final int id) {
        jdbcTemplate.update("UPDATE user SET user.userType = ? WHERE user.id = ?", role, id);
    }

    @Override
    public void delete(final int id) {
        throw new UnsupportedOperationException();
    }
}
