package com.bwzy.company.service.impl;

import java.io.IOException;
import java.net.SocketTimeoutException;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import com.bwzy.company.util.ResponseStrUtil;

/**
 * Created with IntelliJ IDEA.
 * User: LVYANGJUN
 * Date: 12-9-7
 * Time: ����12:03
 * To change this template use File | Settings | File Templates.
 */
public class LoginServicesImpl{
	private String htmlSource="";
    //OAϵͳ��֤
    public String OAlogin(HttpClient client,String url){
        int statusCode=0;
        PostMethod method = new PostMethod(url);
        try {
            statusCode=client.executeMethod(method);
            //��½�󷵻ص�HTMl
            htmlSource = method.getResponseBodyAsString();

        }catch (ConnectTimeoutException e) {
            e.printStackTrace();
            htmlSource= ResponseStrUtil.RESPONSE_RETURN_HTML_TIMEOUT;
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            htmlSource= ResponseStrUtil.RESPONSE_RETURN_HTML_READTIMEOUT;
        } catch (IOException e) {
            e.printStackTrace();
            htmlSource= ResponseStrUtil.RESPONSE_RETURN_HTML_FAILURE;
        } finally {
            method.releaseConnection();
        } 
        //���ݷ���״̬�ж�
        if(statusCode==302){
            htmlSource= ResponseStrUtil.RESPONSE_RETURN_LOGIN_SUCCESS;
        }else if(statusCode==200) {
        	htmlSource=ResponseStrUtil.RESPONSE_RETURN_LOGIN_FAILURE;
        }else {
        	htmlSource=ResponseStrUtil.RESPONSE_RETURN_LOGIN_SYSTEMERROR;
        }
        return htmlSource;

    }
}
