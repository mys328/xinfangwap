package com.bwzy.company.util;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yangjun
 * Date: 14-2-13
 * Time: ÉÏÎç10:24
 * To change this template use File | Settings | File Templates.
 */
public class RemoteUtil {
    public boolean isRemoteSucc(String htmlSource){
        boolean isRemote=true;
        if(htmlSource.equals(ResponseStrUtil.RESPONSE_RETURN_HTML_READTIMEOUT)){
            isRemote=false;

        }else if(htmlSource.equals(ResponseStrUtil.RESPONSE_RETURN_HTML_TIMEOUT)) {
            isRemote=false;

        }else if(htmlSource.equals(ResponseStrUtil.RESPONSE_RETURN_HTML_FAILURE)){
            isRemote=false;

        }
        return isRemote;
    }
}
