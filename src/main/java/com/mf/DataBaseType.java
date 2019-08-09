package com.mf;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author fei
 */

@AllArgsConstructor
@Getter
public enum DataBaseType {
    //
    MYSQL("mysql"),
    SQL_SERVER("sqlserver"),
    ORACLE("ORACLE")
    ;
    private String desc;

    public Boolean equals(String desc) {
        return this.desc.equals(desc);
    }
}
