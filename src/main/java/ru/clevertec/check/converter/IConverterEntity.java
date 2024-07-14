package ru.clevertec.check.converter;

import java.util.List;

public interface IConverterEntity<T> {

    void convert(T t);

    List<String> getConvertedEntity();

}
