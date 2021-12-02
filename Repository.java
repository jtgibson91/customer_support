package com.company;

public interface Repository<T, ID>{
    ID save(T obj);
    T getById(ID id);
}
