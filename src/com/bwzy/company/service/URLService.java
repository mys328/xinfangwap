package com.bwzy.company.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;

public interface URLService {
	
	public String getURL(HttpServletRequest request,String businessNUM);
	
}
