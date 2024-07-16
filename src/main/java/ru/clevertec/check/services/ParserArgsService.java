package ru.clevertec.check.services;

import ru.clevertec.check.dto.ResponseParsedArgsDto;

public interface ParserArgsService {

    public ResponseParsedArgsDto parseArgs(String args);

}
