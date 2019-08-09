package com.mf;

import com.mf.service.UtilService;
import com.mf.service.impl.HibernateServiceImpl;

public class UtilFactory {
    private String type;
    public static UtilService getInstance(){

        return new HibernateServiceImpl();
    }
}
