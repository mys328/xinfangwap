package com.bwzy.company.service.oaservice.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import org.springframework.stereotype.Service;

import com.bwzy.company.service.oaservice.ParserHtmlSourceService;

/**
 * Created with IntelliJ IDEA.
 * User: lvyangjun
 * Date: 13-7-26
 * Time: ????12:11
 * To change this template use File | Settings | File Templates.
 */
@Service(value = "0007")
public class ParserCommitLettersImpl implements ParserHtmlSourceService {
    @Override
    public Map<String,Object> parserSource(String htmlSource) {

          Map<String,Object> map=new HashMap<String,Object>();
         if(htmlSource.indexOf("写信成功")!=-1){
             map.put("responseStr","写信成功");
             map.put("msg", "commitLetterSuccess");
         }else {
        	 Source source = new Source(htmlSource);
        	 if(htmlSource.indexOf("tishi_cuowu")!=-1){
        	      List<Element> tdElement = source.getAllElements(HTMLElementName.TD);
        	      for (Element element : tdElement) {
					if(element.getAttributeValue("class")==null){
						map.put("responseStr",element.getTextExtractor().toString());
			            map.put("msg", "commitLetterFail");
					}
				}
        	 }else{
        		 int index=htmlSource.indexOf("var msg = \"");
        		 int end=htmlSource.indexOf("！\"");
        		 String dd=htmlSource.substring(index+12,end);
        		 map.put("responseStr",dd);
		         map.put("msg", "commitLetterFail");
        	 }


         }
        return map;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
