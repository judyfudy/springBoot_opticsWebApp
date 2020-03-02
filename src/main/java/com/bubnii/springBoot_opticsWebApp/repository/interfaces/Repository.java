package com.bubnii.springBoot_opticsWebApp.repository.interfaces;

import java.util.List;

public interface Repository<T> {
    List<T> getAll();

    void add(final T t);

    T get(final int id);

    boolean update(final T t);

    boolean delete(final int id);
}
