package com.tangp.excelhelper.handler;

import java.util.List;

import com.tangp.excelhelper.IParserParam;



public interface IExcelParseHandler<T> {

    List<T> process(IParserParam parserParam) throws Exception;

}