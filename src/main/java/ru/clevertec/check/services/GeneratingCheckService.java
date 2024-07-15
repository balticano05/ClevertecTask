package ru.clevertec.check.services;

public interface GeneratingCheckService<T,R>{

    R execute(T requestOrder);

}
