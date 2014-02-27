package com.bwzy.company.service.oaservice.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.bwzy.company.service.oaservice.ParserHtmlSourceService;

/**
 * Created with IntelliJ IDEA.
 * User: lvyangjun
 * Date: 13-7-30
 * Time: 上午10:05
 * To change this template use File | Settings | File Templates.
 */
@Service(value = "0005")
public class ParserUpdatePasswordImpl implements ParserHtmlSourceService{
    @Override
    public Map<String, Object> parserSource(String htmlSource) {
        String msg="";
        Map<String, Object> map=new HashMap<String, Object>();
        String responseStr="";
        if(htmlSource.indexOf("更新登录密码成功")!=-1){
            responseStr="密码更新成功";
            msg="updatePasswordSuccess";
        }else {
            responseStr="密码修改失败";
            msg="updatePasswordFail";
        }
        map.put("responseStr", responseStr);
        map.put("msg", msg);
        return map;
         //To change body of implemented methods use File | Settings | File Templates.
    }
}
