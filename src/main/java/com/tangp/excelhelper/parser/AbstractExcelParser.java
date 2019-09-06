package com.tangp.excelhelper.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tangp.excelhelper.IExcelParser;
import com.tangp.excelhelper.IParserParam;
import com.tangp.excelhelper.handler.IExcelParseHandler;


abstract class AbstractExcelParser<T> implements IExcelParser<T> {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<T> parse(IParserParam parserParam) {
        checkParserParam(parserParam);
        IExcelParseHandler<T> handler = this.createHandler(parserParam.getExcelInputStream());
        try {
            return handler.process(parserParam);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (parserParam.getExcelInputStream() != null) {
                    parserParam.getExcelInputStream().close();
                }
            } catch (IOException e) {
                // doNothing
            }
        }
    }

    protected abstract IExcelParseHandler<T> createHandler(InputStream excelInputStream);

    private void checkParserParam(IParserParam parserParam) {
        if (parserParam == null || parserParam.getExcelInputStream() == null
                || parserParam.getColumnSize() == null || parserParam.getTargetClass() == null
                || parserParam.getSheetNum() == null || parserParam.getStartRow()==null || parserParam.getHeaderRow()==null) {
            throw new IllegalArgumentException(String.format("ParserParam has null value,ParserParam value is %s",
                    Objects.toString(parserParam)));
        }
    }
}