package com.bwzy.company.service.oaservice.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bwzy.company.service.oaservice.ParserHtmlSourceService;


/**
 * Created with IntelliJ IDEA.
 * User: lvyangjun
 * Date: 13-7-30
 * Time: ����9:29
 * To change this template use File | Settings | File Templates.
 */
@Service(value="0003")
public class ParserCommitUserInfoImpl implements ParserHtmlSourceService{
	String responseStr="";
    public Map<String, Object> parserSource(String htmlSource) {
    	Map<String, Object> map=new HashMap<String, Object>();
    	String msg="elterfail";
    	responseStr="�޸��û���Ϣʧ��";
        if(htmlSource.indexOf("�ĵ��Ѿ�ת�Ƶ�")!=-1){
        responseStr="�޸��û���Ϣ�ɹ�";
        msg="eltersuccess";
        }
        map.put("msg", msg);
        map.put("responseStr", responseStr);

        return map;
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
