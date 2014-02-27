package com.bwzy.company.service.oaservice.impl;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.bwzy.company.service.oaservice.ParserHtmlSourceService;

/**
 * Created with IntelliJ IDEA.
 * User: lvyangjun
 * Date: 13-7-24
 * Time: 上午10:50
 * To change this template use File | Settings | File Templates.
 */
@Service(value = "0015")
public class ParaserCommitRegisterImpl implements ParserHtmlSourceService{
    @Override
    public Map<String,Object> parserSource(String htmlSource) {
        String responseStr="";
        Map<String,Object> map=new HashMap<String,Object>();
        if(htmlSource.indexOf("注册成功")!=-1){

            map.put("responseStr", "注册成功");
            map.put("msg", "registerSUCC");
        }else {
        	int index=htmlSource.indexOf("var msg = \"");
   		 	int end=htmlSource.indexOf("！\"");
   		 	String dd=htmlSource.substring(index+11,end);
   		 	map.put("responseStr",dd);
            map.put("msg", "registerFail");
        }
        responseStr= JSONObject.fromObject(map).toString();

        return map;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
