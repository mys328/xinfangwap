package com.bwzy.company.service.oaservice.impl;

import java.util.ArrayList;
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
 * Time: 上午10:27
 * To change this template use File | Settings | File Templates.
 */
@Service(value = "0008")
public class                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        ParserLettersListImpl implements ParserHtmlSourceService{
    @Override
    public Map<String,Object> parserSource(String htmlSource) {
        Source source=new Source(htmlSource);
        List<Object> list=new ArrayList<Object>();
        List<Element> allTRElement = source.getAllElements(HTMLElementName.TR);
        Map<String,Object> map1=null;
        for (Element element : allTRElement) {
            List<Element> td = element.getAllElements(HTMLElementName.TD);

            if(td.size()==5){
                map1=new HashMap<String,Object>();
                for(int i=0;i<td.size();i++){
                    if(i==0){
                        map1.put("bt",td.get(i).getTextExtractor().toString());
                        List<Element> aElementList=td.get(i).getAllElements();
                        for(Element element1:aElementList){
                            if(element1.getName().equals(HTMLElementName.A)){
                                map1.put("bt_href",element1.getAttributeValue("href").replace("biz-xfjView-pre.pfv?", "UserInfo_get?businessNUM=0009&"));
                            }
                        }

                    }else if(i==1){
                        map1.put("time",td.get(i).getTextExtractor().toString());
                    }else if(i==2){
                        map1.put("wtlx",td.get(i).getTextExtractor().toString());
                    }else if(i==3){
                        map1.put("zt",td.get(i).getTextExtractor().toString());
                    }else if(i==4){
                        map1.put("blqk",td.get(i).getTextExtractor().toString());
                        List<Element> aElementList=td.get(i).getAllElements();
                        for(Element element1:aElementList){
                            if(element1.getName().equals(HTMLElementName.A)){
                                map1.put("blqk_href",element1.getAttributeValue("href").replace("biz-resultViewByCxm-pre.pfv?", "UserInfo_get?businessNUM=0010&"));
                            }
                        }
                    }

                }
                list.add(map1);
            }



        }
        //分页功能
        Map<String,Object> map2=new HashMap<String,Object>();
        List<Element> allformElement = source.getAllElements(HTMLElementName.FORM);
        int i=0;
        for(Element element:allformElement){

            List<Element> allElement = element.getAllElements();

            for (Element element1 :allElement){
                if(element1.getName().equals(HTMLElementName.FONT)){

                    if(i==0){
                        map2.put("listscount",element1.getTextExtractor().toString());
                    }else {
                        map2.put("nowpage",element1.getTextExtractor().toString());
                    }


                    i++;
                }else if(element1.getName().equals(HTMLElementName.A)){
                    map2.put(element1.getTextExtractor().toString(),element1.getAttributeValue("href").replace("biz-xfjList.pfv?", "UserInfo_get?businessNUM=0008&"));
                }else if(element1.getName().equals(HTMLElementName.SELECT)){
                    List<Element> optionElement=element1.getAllElements(HTMLElementName.OPTION);
                    List<Object> pagelist=new ArrayList<Object>();
                    if(optionElement.size()>0){
                    	
                        for(Element element2:optionElement){
                            Map<String,Object> map3=new HashMap<String,Object>();
                            map3.put("key",element2.getTextExtractor().toString());
                            map3.put("value", element2.getAttributeValue("value").replace("biz-xfjList.pfv?", "UserInfo_get?businessNUM=0008&"));
                            pagelist.add(map3);
                            map2.put("pagelist",pagelist);
                            map2.put("pagescount",optionElement.size()+"");
                        }
                    }else{
                    	 map2.put("pagelist",pagelist);
                         map2.put("pagescount",optionElement.size()+"");
                    }
                    

                }
            }
        }
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("lists",list);
        map.put("pages",map2);
        map.put("msg","getLetterList");
        return map;
          //To change body of implemented methods use File | Settings | File Templates.
    }

	
}
