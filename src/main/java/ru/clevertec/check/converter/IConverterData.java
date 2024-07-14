package ru.clevertec.check.converter;

import java.util.List;

public interface IConverterData<T> {

    void convert(List<String> data);

    List<T> getConvertedData();

}

