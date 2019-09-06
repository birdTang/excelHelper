package com.tangp.excelhelper;

import java.util.List;

public interface IExcelParser<T> {
    List<T> parse(IParserParam parserParam);
}