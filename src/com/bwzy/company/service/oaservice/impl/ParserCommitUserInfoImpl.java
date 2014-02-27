package com.bwzy.company.service.oaservice.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bwzy.company.service.oaservice.ParserHtmlSourceService;


/**
 * Created with IntelliJ IDEA.
 * User: lvyangjun
 * Date: 13-7-30
 * Time: 上午9:29
 * To change this template use File | Settings | File Templates.
 */
@Service(value="0003")
public class ParserCommitUserInfoImpl implements ParserHtmlSourceService{
	String responseStr="";
    public Map<String, Object> parserSource(String htmlSource) {
    	Map<String, Object> map=new HashMap<String, Object>();
    	String msg="elterfail";
    	responseStr="修改用户信息失败";
        if(htmlSource.indexOf("文档已经转移到")!=-1){
        responseStr="修改用户信息成功";
        msg="eltersuccess";
        }
        map.put("msg", msg);
        map.put("responseStr", responseStr);

        return map;
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
