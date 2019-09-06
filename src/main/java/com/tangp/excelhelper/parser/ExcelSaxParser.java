package com.tangp.excelhelper.parser;


import java.io.InputStream;

import org.apache.poi.poifs.filesystem.FileMagic;

import com.tangp.excelhelper.handler.IExcelParseHandler;
import com.tangp.excelhelper.handler.impl.Excel2003ParseHandler;
import com.tangp.excelhelper.handler.impl.Excel2007ParseHandler;

public class ExcelSaxParser<T> extends AbstractExcelParser<T> {

    public IExcelParseHandler<T> createHandler(InputStream excelInputStream) {
        try {
            if (FileMagic.valueOf(excelInputStream) == FileMagic.OLE2) {
                return new Excel2003ParseHandler<>();
            } else if (FileMagic.valueOf(excelInputStream) == FileMagic.OOXML) {
                return new Excel2007ParseHandler<>();
            } else {
                throw new IllegalArgumentException("Your InputStream was neither an OLE2 stream, nor an OOXML stream");
            }
        } catch (Exception e) {
            logger.error("getParserInstance Error!", e);
            throw new RuntimeException(e);
        }
    }

}