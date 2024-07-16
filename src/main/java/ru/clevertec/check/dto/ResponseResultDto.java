package ru.clevertec.check.dto;

import java.util.List;

public class ResponseResultDto {

    private List<String> data;

    public ResponseResultDto() {
    }

    public ResponseResultDto(List<String> data) {
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
