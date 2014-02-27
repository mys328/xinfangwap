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
 * Time: 下午1:55
 * To change this template use File | Settings | File Templates.
 */
@Service(value = "0014")
public class ParserFindYouPasswordStep4Impl implements ParserHtmlSourceService{
    @Override
    public Map<String,Object> parserSource(String htmlSource) {
        Map<String,Object> map=new HashMap<String,Object>();
        if(htmlSource.indexOf("密码更新成功，请登录！")!=-1){
            map.put("responseStr","密码更新成功，请登录！");
            map.put("msg", "ElterNewPasswordSUCC");
        }else {
            map.put("responseStr","密码修改失败");
            map.put("msg", "ElterNewPasswordFAIL");
        }
        return map;
          //To change body of implemented methods use File | Settings | File Templates.
    }
}
