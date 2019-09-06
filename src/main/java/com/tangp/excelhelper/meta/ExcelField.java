package com.tangp.excelhelper.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * excel字段映射注解
 * @ate 2019年9月6日
 * @author tangp
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExcelField {

    Logger logger = LoggerFactory.getLogger(ExcelField.class);

    int index();

    ExcelFieldType type() default ExcelFieldType.STR;


    enum ExcelFieldType {
        STR {
            @Override
            public String buildSetString(String in) {
                return in;
            }
        }, DATE {
            @Override
            public String buildSetString(String in) {

                if (in.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    return in;
                }
                if (in.matches("\\d{4}/\\d{2}/\\d{2}")) {
                    return in.replaceAll("/", "-");
                }
                if (in.matches("\\d{1,2}/\\d{1,2}/\\d{2}")) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        return simpleDateFormat2.format(simpleDateFormat.parse(in));
                    } catch (Exception e) {
                        logger.error(String.format("未知的日期格式,日期为:%s", in));
                    }
                }
                logger.error(String.format("未知的日期格式,日期为:%s", in));
                return in;
            }
        };

        public abstract String buildSetString(String str);
    }
}
