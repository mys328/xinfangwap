package com.bwzy.company.service.oaservice;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;

/**
 * Created with IntelliJ IDEA.
 * User: lvyangjun
 * Date: 13-4-19
 * Time: ÏÂÎç5:10
 * To change this template use File | Settings | File Templates.
 */
public interface HttpRequestSender {
    public HttpClient getHttpClient(HttpServletRequest request);
    public String postMethodRequest(HttpClient httpClient, String url,HttpServletRequest req);
    public String getMethodResuest(HttpClient httpclient, String url);
    public String uploadFileToRemote(HttpClient httpClient,String url,String filepath,String filename,String index);
    public  boolean fromRemoteDownloadFile(HttpClient httpclient, String url,String filePath);
}