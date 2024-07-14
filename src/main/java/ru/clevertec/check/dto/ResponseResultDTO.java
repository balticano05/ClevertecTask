package ru.clevertec.check.dto;

import java.util.List;

public class ResponseResultDTO {

    private List<String> data;

    public ResponseResultDTO() {
    }

    public ResponseResultDTO(List<String> data) {
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
