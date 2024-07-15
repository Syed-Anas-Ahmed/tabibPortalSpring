package com.fynuls.dao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface CrudRepository<T> {
    List<T> getAll();
    T getById(Integer id);
    void deleteById(Integer id);
    void deleteAll();
    boolean save(T entity);
    Integer saveAndGetId(T entity);
}

