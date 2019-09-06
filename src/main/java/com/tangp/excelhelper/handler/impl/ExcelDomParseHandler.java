package com.tangp.excelhelper.handler.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.tangp.excelhelper.IParserParam;




public class ExcelDomParseHandler<T> extends BaseExcelParseHandler<T> {

    @Override
    public List<T> process(IParserParam parserParam) throws Exception {
        Workbook workbook = generateWorkBook(parserParam);
        Sheet sheet = workbook.getSheetAt(parserParam.getSheetNum());
        Iterator<Row> rowIterator = sheet.rowIterator();
        if (parserParam.getHeader() != null && !parserParam.getHeader().isEmpty()) {
            checkHeader(rowIterator, parserParam);
        }
        return parseRowToTargetList(rowIterator, parserParam);
    }

    private void checkHeader(Iterator<Row> rowIterator, IParserParam parserParam) {
        while (true) {
            Row row = rowIterator.next();
            if(row.getRowNum()!=parserParam.getHeaderRow()) continue; 
            List<String> rowData = parseRowToList(row, parserParam.getColumnSize());
            boolean empty = isRowDataEmpty(rowData);
            if (!empty) {
                validHeader(parserParam, rowData);
                break;
            }
        }
    }


    private Workbook generateWorkBook(IParserParam parserParam) throws IOException {
        return WorkbookFactory.create(parserParam.getExcelInputStream());
    }

    private List<T> parseRowToTargetList(Iterator<Row> rowIterator, IParserParam parserParam) throws IllegalAccessException {
        List<T> result = new ArrayList<>();
        for (; rowIterator.hasNext(); ) {
            Row row = rowIterator.next();
            if(row.getRowNum()<parserParam.getStartRow()) continue;
            List<String> rowData = parseRowToList(row, parserParam.getColumnSize());
            Optional<T> d = parseRowToTarget(parserParam, rowData);
            d.ifPresent(result::add);
        }
        return result;
    }

    private List<String> parseRowToList(Row row, int size) {
        List<String> dataRow = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            if (row.getCell(i) != null) {
                DataFormatter formatter = new DataFormatter();
                String formattedCellValue = formatter.formatCellValue(row.getCell(i));
                dataRow.add(formattedCellValue.trim());
            } else {
                dataRow.add("");
            }
        }
        return dataRow;
    }
}