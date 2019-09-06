package com.tangp.excelhelper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import com.tangp.excelhelper.param.DefaultParserParam;
import com.tangp.excelhelper.parser.ExcelDomParser;
import com.tangp.excelhelper.parser.ExcelSaxParser;

public class Test {
	private static IExcelParser<User> parser;
	public static void main(String[] args) throws FileNotFoundException {
		testSaxXls();
	}
	
	
	public static void testDomXlsx() throws FileNotFoundException {

        parser = new ExcelDomParser<>();

        IParserParam parserParam = DefaultParserParam.builder()
                .excelInputStream(new FileInputStream(new File("C:\\Users\\Administrator\\Downloads\\test01.xlsx")))
                .columnSize(4)
                .sheetNum(IParserParam.FIRST_SHEET)
                .targetClass(User.class)
                .header(User.getHeader())
                .build();

        List<User> user = parser.parse(parserParam);
        System.out.println(user);
    }
	
	public static void testDomXls() throws FileNotFoundException {

        parser = new ExcelDomParser<>();

        IParserParam parserParam = DefaultParserParam.builder()
                .excelInputStream(new FileInputStream(new File("C:\\Users\\Administrator\\Downloads\\test01.xls")))
                .columnSize(4)
                .sheetNum(IParserParam.FIRST_SHEET)
                .targetClass(User.class)
                .header(User.getHeader())
                .build();

        List<User> user = parser.parse(parserParam);
        System.out.println(user);
    }
	
	public static void testSaxXlsx() throws FileNotFoundException {
        parser = new ExcelSaxParser<>();

        IParserParam parserParam = DefaultParserParam.builder()
                .excelInputStream(new FileInputStream(new File("C:\\Users\\Administrator\\Downloads\\test01.xlsx")))
                .columnSize(4)
                .sheetNum(IParserParam.FIRST_SHEET)
                .targetClass(User.class)
                .header(User.getHeader())
                .build();

        List<User> user = parser.parse(parserParam);
        System.out.println(user);
    }
	
	public static void testSaxXls() throws FileNotFoundException {
        parser = new ExcelSaxParser<>();

        IParserParam parserParam = DefaultParserParam.builder()
                .excelInputStream(new BufferedInputStream(new FileInputStream(new File("C:\\Users\\Administrator\\Downloads\\test01.xls"))))
                .columnSize(4)
                .sheetNum(IParserParam.FIRST_SHEET)
                .targetClass(User.class)
                .header(User.getHeader())
                .build();

        List<User> user = parser.parse(parserParam);
        System.out.println(user);
    }
}
