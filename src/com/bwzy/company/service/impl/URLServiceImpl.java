package com.bwzy.company.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;

import com.bwzy.company.service.ParamsService;
import com.bwzy.company.service.URLService;
import com.bwzy.company.util.UrlConfigUtil;

public class URLServiceImpl implements URLService {
	private String URL="";
	/**
	 * 拼接URL
	 */
	@Override
	public String getURL(HttpServletRequest request,String businessNUM) {
		
		//封装所有请求参数
        ParamsService paramsService = new ParamsServiceImpl();
        String params = paramsService.putAllParamsToStr(request);
        //没有参数
        if(!params.equals("")){

    			URL=UrlConfigUtil.getValue(businessNUM)+params;


        }else{
        	URL=UrlConfigUtil.getValue(businessNUM);
        }

		return URL;
	}

}
