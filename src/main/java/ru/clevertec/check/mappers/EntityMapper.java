package ru.clevertec.check.mappers;

import java.util.List;

public interface EntityMapper<T> {

    void convert(T t);

    List<String> getConvertedEntity();

}
