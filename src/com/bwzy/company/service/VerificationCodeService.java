package com.bwzy.company.service;

import org.apache.commons.httpclient.HttpClient;

/**
 * Created with IntelliJ IDEA.
 * User: lvyangjun
 * Date: 13-7-24
 * Time: обнГ12:14
 * To change this template use File | Settings | File Templates.
 */
public interface VerificationCodeService {
    public String getVerificationCode(HttpClient httpClient,String sessionid);


}
