package com.bwzy.company.util;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.bwzy.company.service.BaseService;
import com.bwzy.company.service.oaservice.ParserHtmlSourceService;

/**
 * Created with IntelliJ IDEA.
 * User: LVYANGJUN
 * Date: 12-9-3
 * Time: 下午4:37
 * To change this template use File | Settings | File Templates.
 */
public class ServiceUtil {

    AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext();
    
    //自定义业务    
    public BaseService getService(String businessNUM){
        applicationContext.scan("com.bwzy.company.service.*");
        applicationContext.refresh();
        BaseService baseService= (BaseService) applicationContext.
                getBean(businessNUM);
        return baseService;
    }
    //规定业务
    public ParserHtmlSourceService getPraserService(String businessNUM){
        applicationContext.scan("com.bwzy.company.service.oaservice.*");
        applicationContext.refresh();
        ParserHtmlSourceService parser=
        	(ParserHtmlSourceService) applicationContext.getBean(businessNUM);
        return parser;
    }

}
