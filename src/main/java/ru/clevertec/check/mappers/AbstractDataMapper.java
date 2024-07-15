package ru.clevertec.check.mappers;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDataMapper<T> implements DataMapper<T> {

    protected List<T> convertedData = new ArrayList<>();

    @Override
    public void convert(List<String> data) {

        String[] values;

        List<T> convertedData = new ArrayList<>();

        for (int i = 1; i < data.size(); i++) {

            values = divideLineBySemicolon(data.get(i));
            convertedData.add(convertLineToEntity(values));
        }

        this.convertedData = convertedData;
    }

    @Override
    public List<T> getConvertedData() {

        return convertedData;

    }

    protected String[] divideLineBySemicolon(String line) {

        return line.split(";");

    }

    protected abstract T convertLineToEntity(String[] values);
}
