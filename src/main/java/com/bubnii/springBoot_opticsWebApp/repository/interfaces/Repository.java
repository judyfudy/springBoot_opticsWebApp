package com.bubnii.springBoot_opticsWebApp.repository.interfaces;

import com.bubnii.springBoot_opticsWebApp.entity.User;

import java.util.List;

public interface Repository<T> {
    List<T> getAll();

    void add(final T t);

    T get(final int id);

    void update(final T user);

    void changeUserRole(final String role, final int id);

    void delete(final int id);
}
