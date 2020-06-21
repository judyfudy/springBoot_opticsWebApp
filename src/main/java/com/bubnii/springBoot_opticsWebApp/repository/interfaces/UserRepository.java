package com.bubnii.springBoot_opticsWebApp.repository.interfaces;

import com.bubnii.springBoot_opticsWebApp.entity.User;

public interface UserRepository extends Repository<User> {
    User getPersonByCredentials(final String username);

    boolean existsByUsername(String username);

}
