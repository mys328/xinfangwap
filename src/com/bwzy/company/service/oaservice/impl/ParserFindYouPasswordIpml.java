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
 * Time: ����1:27
 * To change this template use File | Settings | File Templates.
 */
@Service(value = "0011")
public class ParserFindYouPasswordIpml implements ParserHtmlSourceService{
    @Override
    public Map<String,Object> parserSource(String htmlSource) {

        Map<String,Object> map=new HashMap<String,Object>();
        if(htmlSource.indexOf("�������û���")!=-1){
            map.put("responseStr","���û������ڣ����������룡");
            map.put("msg","noTheUser");
        }else if(htmlSource.indexOf("�û���ֻ�ܰ���[��ĸ]��[����]")!=-1){
            map.put("responseStr","�û���ֻ�ܰ���[��ĸ]��[����]");
            map.put("msg","noTheUser");
        }else {
            Source source = new Source(htmlSource);

            List<Element> allTableElement = source.getAllElements(HTMLElementName.TABLE);
            for (Element e: allTableElement){
                Element td=e.getFirstElement(HTMLElementName.TD);

                map.put("key",td.getTextExtractor().toString());
                map.put("msg", "hasTheUser");
            }
        }
        return map;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
