package com.mf.util;

import org.apache.commons.lang3.StringUtils;

public class Utils {

    /**
     * 如果匹配到前缀内容,就返回对应前缀后面部分
     *
     * @param source 内容
     * @param target 目标前缀内容
     * @return
     */
    public static String getMatchStr(String source, String target) {
        if (StringUtils.isNotEmpty(target)) {
            if (source.contains(target)) {
                return source.substring(source.indexOf(target) + target.length());
            }
        }
        return null;
    }
}
