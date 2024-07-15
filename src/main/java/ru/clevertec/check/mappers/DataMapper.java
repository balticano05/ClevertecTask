package ru.clevertec.check.mappers;

import java.util.List;

public interface DataMapper<T> {

    void convert(List<String> data);

    List<T> getConvertedData();

}

