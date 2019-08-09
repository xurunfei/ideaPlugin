package com.mf;


import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class SqlParam {
    private Integer index;
    private String type;
    private String content;


    public String getContent() {
        if (ParamType.VARCHAR.name().equals(type)) {
            return "'" + content + "'";
        }
        if (ParamType.TIMESTAMP.name().equals(type)) {
            LocalDateTime parse = LocalDateTime.parse(content);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return "'" + parse.format(dateTimeFormatter) + "'";
        }

        return content;
    }
}
