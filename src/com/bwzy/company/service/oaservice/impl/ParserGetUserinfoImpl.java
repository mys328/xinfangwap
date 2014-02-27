package com.bwzy.company.service.oaservice.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bwzy.company.util.RemoteUtil;
import net.htmlparser.jericho.Attribute;
import net.htmlparser.jericho.Attributes;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import org.springframework.stereotype.Service;

import com.bwzy.company.service.oaservice.ParserHtmlSourceService;

/**
 * Created with IntelliJ IDEA.
 * User: lvyangjun
 * Date: 13-7-29
 * Time: ÏÂÎç5:36
 * To change this template use File | Settings | File Templates.
 */
@Service(value = "0002")
public class ParserGetUserinfoImpl implements ParserHtmlSourceService{
    
    public Map<String,Object> parserSource(String htmlSource) {
        Map<String,Object> map = new HashMap<String,Object>();

            Source source = new Source(htmlSource);
            List<Element> allSelectElement = source.getAllElements(HTMLElementName.SELECT);

            for (Element element : allSelectElement) {
                String name = element.getAttributeValue("name");
                List<Object> list=null;
                if ("zy".equals(name) || "mz".equals(name)) {
                    List<Element> option = element.getAllElements(HTMLElementName.OPTION);
                    list=new ArrayList<Object>();
                    for (Element optionElement : option) {

                        Map<String,Object> map1 = new HashMap<String,Object>();
                        Attributes attribute=optionElement.getAttributes();
                        for(Attribute a :attribute){
                            if(a.getName().equals("selected")){
                                if(name.equals("zy")){
                                    map.put("zyseleced",optionElement.getAttributeValue("value"));

                                }else{
                                    map.put("mzseleced",optionElement.getAttributeValue("value"));

                                }

                            }
                        }
                        map1.put("key",optionElement.getAttributeValue("value"));
                        map1.put("name", optionElement.getTextExtractor().toString());

                        list.add(map1);
                    }
                    map.put(name,list);
                }

            }
            List<Element> allTrElement = source.getAllElements(HTMLElementName.TR);
            for(Element element: allTrElement){
                Element thElement=element.getFirstElement(HTMLElementName.TH);
                Element tdElement=element.getFirstElement(HTMLElementName.TD);
                List<Element> elements=tdElement.getChildElements();
                if(elements.size()==0){
                    map.put(thElement.getTextExtractor().toString(),tdElement.getTextExtractor().toString());
                }else{
                    for (Element element1:elements){
                        if(element1.getName().equals(HTMLElementName.INPUT)&&element1.getAttributeValue("name")!=null
                                &&element1.getAttributeValue("type").equals("text")){
                            map.put(thElement.getTextExtractor().toString(),element1.getAttributeValue("value"));
                        }
                    }
                }


            }
            map.put("msg", "getuserinfo");


        return map;
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
