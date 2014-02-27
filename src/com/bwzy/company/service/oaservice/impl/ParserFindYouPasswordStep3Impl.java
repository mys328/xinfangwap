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
@Service(value = "0013")
public class ParserFindYouPasswordStep3Impl implements ParserHtmlSourceService{
    @Override
    public Map <String , Object> parserSource(String htmlSource) {
        Map < String , Object > map = new HashMap< String , Object >();
        if (htmlSource.indexOf("请输入新密码") != -1) {
            map.put("key","请输入新密码");
            map.put("msg", "NewPassWord");
        } else {
            map.put("responseStr","证件编号不正确，请重新录入");
            map.put("msg", "CommitCreCodeFail");
        }
        return map;//To change body of implemented methods use File | Settings | File Templates.
    }
}
