package com.mf;

import lombok.Data;

import java.util.List;
/**
 * @author fei
 */
@Data
public class SqlInfo {
    /**
     * sql语句
     */
    private String sql;
    /**
     * 参数
     */
    private List<SqlParam> params;
}
