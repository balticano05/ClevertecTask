package ru.clevertec.check.services;

public interface IServiceGeneratingCheck <T,R>{

    R execute(T requestOrder);

}
