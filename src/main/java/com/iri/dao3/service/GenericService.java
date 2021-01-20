package com.iri.dao3.service;

import java.util.List;

public interface GenericService<T, I> {
    T create(T t);

    T get(I id);

    List<T> getAll();

    T update(T t);

    boolean delete(I id);
}
