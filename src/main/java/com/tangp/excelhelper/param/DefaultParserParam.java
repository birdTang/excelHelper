package com.tangp.excelhelper.param;

import java.io.InputStream;
import java.util.List;

import com.tangp.excelhelper.IParserParam;



/**
 * 解析参数封装类
 * @ate 2019年9月4日
 * @author Administrator
 */
public class DefaultParserParam implements IParserParam {

	//excel输入流
    private InputStream inputStream;
    //转化后的目标class
    private Class targetClass;
    //excel列数量
    private Integer columnSize;
    //excel表头集合，用作表头是否一致判断
    private List<String> header;
    //数据所在的sheet
    private Integer sheetNum;
    //开始解析的行数
    private Integer startRow = 1; 
    //列表标题头所在行数
    private Integer headerRow = 0; 

    private DefaultParserParam(InputStream inputStream, Class targetClass, Integer columnSize, List<String> header, Integer sheetNum,Integer startColumn,Integer headerColumn) {
        this.inputStream = inputStream;
        this.targetClass = targetClass;
        this.columnSize = columnSize;
        this.sheetNum = sheetNum;
        this.header = header;
        this.startRow = startColumn;
        this.headerRow = headerColumn;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public InputStream getExcelInputStream() {
        return inputStream;
    }

    @Override
    public Class getTargetClass() {
        return targetClass;
    }


    @Override
    public Integer getColumnSize() {
        return columnSize;
    }

    @Override
    public Integer getSheetNum() {
        return sheetNum;
    }

    @Override
    public List<String> getHeader() {
        return header;
    }
    @Override
    public Integer getStartRow() {
        return startRow;
    }
    public Integer getHeaderRow() {
    	return headerRow;
    }

    public static class Builder {

        private InputStream inputStream;
        private Class targetClass;
        private Integer columnSize;
        private List<String> header;
        private Integer sheetNum;
        private Integer startRow = 1; //开始解析的行数
        private Integer headerRow= 0; //列表标题头所在行数
        public Builder excelInputStream(InputStream inputStream) {
            this.inputStream = inputStream;
            return this;
        }

        public Builder targetClass(Class clazz) {
            this.targetClass = clazz;
            return this;
        }


        public Builder columnSize(Integer columnSize) {
            this.columnSize = columnSize;
            return this;
        }

        public Builder header(List<String> header) {
            this.header = header;
            return this;
        }

        public Builder sheetNum(Integer sheetNum) {
            this.sheetNum = sheetNum;
            return this;
        }

        public Builder startRow(Integer startRow) {
        	this.startRow = startRow;
        	return this;
        }
        
        public Builder headerRow(Integer headerRow) {
        	this.headerRow = headerRow;
        	return this;
        }
        public DefaultParserParam build() {
            return new DefaultParserParam(inputStream, targetClass, columnSize, header, sheetNum,startRow,headerRow);
        }
    }
}