package com.bwzy.company.service.oaservice.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
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
@Service(value = "0012")
public class ParserFindYouPasswordStep2Impl implements ParserHtmlSourceService{
    @Override
    public Map<String,Object> parserSource(String htmlSource) {
        Map<String,Object> map=new HashMap<String,Object>();

        if(htmlSource.indexOf("取回密码问题")!=-1){
            map.put("responseStr","答案不正确，请输入正确答案");
            map.put("msg", "AnswerError");
            ParserHtmlSourceService parserHtmlSourceService=new ParserFindYouPasswordIpml();
            Map<String,Object> returnMap=parserHtmlSourceService.parserSource(htmlSource);
            map.put("key", returnMap.get("key"));
        }else {
            Source source = new Source(htmlSource);
            List<Element> allTableElement = source.getAllElements(HTMLElementName.TABLE);
            for (Element e: allTableElement){
                Element td=e.getFirstElement(HTMLElementName.TH);
                List<Element> input=e.getAllElements(HTMLElementName.INPUT);
                for (Element element :input){
                    if("zjlx".equals(element.getAttributeValue("name"))){
                        map.put("zjlx",element.getAttributeValue("value"));
                    }
                }
                map.put("key",td.getTextExtractor().toString());
            }
            map.put("msg", "AnswerRight");
        }
        return map;
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
