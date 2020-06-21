package com.bubnii.springBoot_opticsWebApp.service.interfaces;

import com.bubnii.springBoot_opticsWebApp.dto.UserDTO;
import com.bubnii.springBoot_opticsWebApp.entity.User;

import java.util.List;

public interface UserService {

    List<UserDTO> getAll();

    void add(User user);

    UserDTO get(int id);

    void update(final User user);

    void changeUserRole(final String role, final int id);

    void delete(int id);

    UserDTO getPersonByCredentials(String username);

    boolean existsByUsername(String username);
}
