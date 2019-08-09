package com.mf.service.impl;

import com.mf.SqlInfo;
import com.mf.SqlParam;
import com.mf.service.UtilService;
import com.mf.util.Utils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author fei
 */
public class HibernateServiceImpl implements UtilService {
    @Override
    public SqlInfo analyse(String string) {
        String sqlPre = "Hibernate:";
        String paramPre = "binding parameter";
        SqlInfo sqlInfo = new SqlInfo();
        String sql = "";
        List<String> params = new ArrayList<>();
        String[] split = string.split("\n");
        for (String s : split) {
            String sqlMatchStr = Utils.getMatchStr(s, sqlPre);
            if (StringUtils.isNotEmpty(sqlMatchStr)) {
                sql = sqlMatchStr;
            }
            String paramMatchStr = Utils.getMatchStr(s, paramPre);
            if (StringUtils.isNotEmpty(paramMatchStr)) {
                params.add(paramMatchStr);
            }
        }
        //如果有注释,换行
        sql = sql.replace("*/", "*/\n");
        //解析参数内容
        String regex = "\\[(\\d)] as \\[(\\w*)] - \\[(.*)]";
        Pattern compile = Pattern.compile(regex);
        List<SqlParam> paramList = new ArrayList<>();
        params.stream().peek(param -> {
            Matcher matcher = compile.matcher(param);
            if (matcher.find()) {
                SqlParam sqlParam = new SqlParam();
                paramList.add(sqlParam);
                for (int i = 0; i <= matcher.groupCount(); i++) {
                    String group = matcher.group(i);
                    if (i == 1) {
                        sqlParam.setIndex(Integer.valueOf(group));
                    }
                    if (i == 2) {
                        sqlParam.setType(group);
                    }
                    if (i == 3) {
                        sqlParam.setContent(group);
                    }
                }
            }
        }).collect(Collectors.toList());
        sqlInfo.setSql(sql);
        sqlInfo.setParams(paramList);
        return sqlInfo;

    }

    /**
     * 合并sql和参数
     *
     * @param sql    sql
     * @param params 参数
     * @return 合并内容
     */
    @Override
    public String merge(String sql, List<SqlParam> params) {
        for (SqlParam param : params) {
            int i = sql.indexOf("?");
            String substring = sql.substring(0, i);
            String substring1 = sql.substring(i + 1);
            sql = substring + param.getContent() + substring1;
        }
        return sql;
    }

}
