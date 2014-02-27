package com.bwzy.company.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.bwzy.company.service.ParamsService;
import com.bwzy.company.util.ParamsKeyUtil;
/**
 * Created with IntelliJ IDEA.
 * User: lvyangjun
 * Date: 13-4-19
 * Time: 上午11:13
 * To change this template use File | Settings | File Templates.
*/
public class ParamsServiceImpl implements ParamsService {
	private String paramsStr="";
    /**
     * 将参数拼接起来
     * @param req
     *
     * @return String 返回拼接好的字符串
     */
    @Override
    public String putAllParamsToStr(HttpServletRequest req) {
        Enumeration<?> enum1 =req.getParameterNames();
        while (enum1.hasMoreElements()) {
            String paramName = (String) enum1.nextElement();
            if(!paramName.equals(ParamsKeyUtil.URL_PARAMS_KEY_BUSINESSNUM)){
            	String paramValue = req.getParameter(paramName);
            	try {
	                if("".equals(paramsStr)){
						
						paramsStr=paramsStr+paramName+"="+URLEncoder.encode(paramValue,"utf-8");
						
	                }else{
	                	paramsStr=paramsStr+"&"+paramName+"="+URLEncoder.encode(paramValue,"utf-8");
	                }
            	} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}    
            }  
        }

        return paramsStr;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
