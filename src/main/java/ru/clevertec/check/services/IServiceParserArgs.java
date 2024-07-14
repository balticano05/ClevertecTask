package ru.clevertec.check.services;

import ru.clevertec.check.dto.ResponseParsedArgsDTO;

public interface IServiceParserArgs {

    public ResponseParsedArgsDTO parseArgs(String args);

}
