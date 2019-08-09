package com.mf.service;

import com.mf.SqlInfo;
import com.mf.SqlParam;

import java.util.List;

public interface UtilService {
    SqlInfo analyse(String string);

    String merge(String sql, List<SqlParam> params);
}
