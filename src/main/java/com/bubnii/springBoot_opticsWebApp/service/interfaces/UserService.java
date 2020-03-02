package com.bubnii.springBoot_opticsWebApp.service.interfaces;

import com.bubnii.springBoot_opticsWebApp.dto.UserDTO;
import com.bubnii.springBoot_opticsWebApp.entity.User;

import java.util.List;

public interface UserService {

    List<UserDTO> getAll();

    void add(User user);

    UserDTO get(int id);

    boolean update(User user);

    boolean delete(int id);

    UserDTO getPersonByCredentials(String username);
}
