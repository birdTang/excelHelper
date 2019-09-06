package com.tangp.excelhelper.parser;

import java.io.InputStream;

import com.tangp.excelhelper.handler.IExcelParseHandler;
import com.tangp.excelhelper.handler.impl.ExcelDomParseHandler;


public class ExcelDomParser<T> extends AbstractExcelParser<T> {

    private IExcelParseHandler<T> excelParseHandler;

    public ExcelDomParser() {
        this.excelParseHandler = new ExcelDomParseHandler<>();
    }

    @Override
    protected IExcelParseHandler<T> createHandler(InputStream excelInputStream) {
        return this.excelParseHandler;
    }
}