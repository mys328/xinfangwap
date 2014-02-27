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
 * Date: 13-7-29
 * Time: ÏÂÎç3:00
 * To change this template use File | Settings | File Templates.
 */
@Service(value = "0009")
public class PraserLettersContentImpl implements ParserHtmlSourceService {
    @Override
    public Map<String,Object> parserSource(String htmlSource) {
        Source source = new Source(htmlSource);
        List<Element> allTRElement = source.getAllElements(HTMLElementName.TR);
        Map<String,Object> map1=new HashMap<String,Object>();

        for (Element element : allTRElement) {
            Element th=element.getFirstElement(HTMLElementName.TH);
            Element td=element.getFirstElement(HTMLElementName.TD);
            if(td.getChildElements().size()==0){
                map1.put(th.getTextExtractor().toString(),td.getTextExtractor().toString());
            }else {
                List<Element> list1=td.getChildElements();
                for(Element element1:list1){
                    if(!element1.getName().equals(HTMLElementName.INPUT)){

                        map1.put(th.getTextExtractor().toString(),td.getTextExtractor().toString());
                    }
                }
            }

        }
        map1.put("msg", "lettercontent");
        return map1;
         //To change body of implemented methods use File | Settings | File Templates.
    }
}
