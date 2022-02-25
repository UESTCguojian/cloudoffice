package com.guojian.server.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author guojian
 * @since 2022-02-24-2022/2/24
 */
public class DateConverter implements Converter<String, LocalDate>
{
    @Override
    public LocalDate convert(String source)
    {
        try {
            return LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
