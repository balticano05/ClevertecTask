package ru.clevertec.check.services;

import ru.clevertec.check.dto.ResponseParsedArgsDTO;

public interface ParserArgsService {

    public ResponseParsedArgsDTO parseArgs(String args);

}
