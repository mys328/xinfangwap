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
 * Time: ����4:13
 * To change this template use File | Settings | File Templates.
 */
@Service(value = "0010")
public class ParserDetailInfoImpl implements ParserHtmlSourceService {
    @Override
    public Map<String, Object> parserSource(String htmlSource) {
        System.out.println(htmlSource);
        Source source = new Source(htmlSource);
        List<Element> allTableElement = source.getAllElements(HTMLElementName.TABLE);

        Map<String, Object> responseMap = new HashMap<String, Object>();

        for (int i = 0; i < allTableElement.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String box=allTableElement.get(i).getAttributeValue("class");
            if(box!=null&&box.equals("box")){

            }
            if (i == 0) {
                List<Element> trElement = allTableElement.get(i).getAllElements(HTMLElementName.TR);
                for (Element element2 : trElement) {
                    List<Element> tdElement = element2.getAllElements(HTMLElementName.TD);
                    for (int j = 0; j < tdElement.size(); j++) {
                        if (j == 0) {

                            map.put("���", tdElement.get(j).getTextExtractor().toString());
                        } else if (j == 1) {
                            map.put("д������", tdElement.get(j).getTextExtractor().toString());
                        } else if (j == 2) {
                            map.put("�������", tdElement.get(j).getTextExtractor().toString());

                        } else if (j == 3) {
                            map.put("����", tdElement.get(j).getTextExtractor().toString());
                        }
                    }
                }

                responseMap.put("����ӳ�������ϸ��Ϣ", map);
            } else if (i == 1) {
                List<Element> tdElement = allTableElement.get(i).getAllElements(HTMLElementName.TD);
                String key = "";
                String value = "";
                for (int j = 0; j < tdElement.size(); j++) {
                    if (j == 0) {
                        key = tdElement.get(j).getTextExtractor().toString();
                    } else if (j == 1) {
                        value = tdElement.get(j).getTextExtractor().toString();
                    }
                }
                map.put(key, value);
                responseMap.put("��ӳ����", map);
            } else if (i == 2) {
                List<Element> TRElement = allTableElement.get(i).getAllElements(HTMLElementName.TR);
                List<Map<String, String>> list = new ArrayList<Map<String, String>>();
                for (Element tr : TRElement) {
                    List<Element> tdElement = tr.getAllElements(HTMLElementName.TD);
                    List<Element> thElement = tr.getAllElements(HTMLElementName.TH);
                    if (thElement.size() == 0) {
                        Map<String, String> map1 = new HashMap<String, String>();
                        for (int j = 0; j < tdElement.size(); j++) {

                            if (j == 0) {
                                map1.put("���", tdElement.get(j).getTextExtractor().toString());
                            } else if (j == 1) {
                                map1.put("����", tdElement.get(j).getTextExtractor().toString());
                            } else if (j == 2) {
                                map1.put("�������", tdElement.get(j).getTextExtractor().toString());

                            }

                        }
                        list.add(map1);
                    }

                }
                responseMap.put("�������", list);
            } else if (i == 3) {
                List<Element> trElement = allTableElement.get(i).getAllElements(HTMLElementName.TR);
                List<Map<String, String>> list = new ArrayList<Map<String, String>>();
                for (Element element2 : trElement) {
                    Map<String, String> map1 = new HashMap<String, String>();
                    List<Element> thElement = element2.getAllElements(HTMLElementName.TH);
                    if (thElement.size() == 0) {
                        List<Element> tdElement = element2.getAllElements(HTMLElementName.TD);
                        for (int j = 0; j < tdElement.size(); j++) {
                            if (j == 0) {
                                map1.put("���", tdElement.get(j).getTextExtractor().toString());
                            } else if (j == 1) {
                                map1.put("���ߵ�λ", tdElement.get(j).getTextExtractor().toString());
                            } else if (j == 2) {
                                map1.put("����ʱ��", tdElement.get(j).getTextExtractor().toString());

                            } else if (j == 3) {
                                map1.put("�ļ�����", tdElement.get(j).getTextExtractor().toString());
                            } else if (j == 4) {
                                map1.put("����", tdElement.get(j).getTextExtractor().toString());
                                String downloadUrl=tdElement.get(j).getChildElements().get(0).getAttributeValue("href").
                                        replace("downloadFile.pfv?","UserInfo_download?businessNUM=0018&");
                                map1.put("download_url",downloadUrl);
                            }
                        }
                        list.add(map1);
                    }

                }
                responseMap.put("������", list);
            } else if (i == 4) {

                List<Element> tdElement = allTableElement.get(i).getAllElements(HTMLElementName.TD);
                for (int j = 0; j < tdElement.size(); j++) {
                    if (j == 0) {
                        map.put("��λ", tdElement.get(j).getTextExtractor().toString());
                    } else if (j == 1) {
                        map.put("����", tdElement.get(j).getTextExtractor().toString());
                    } else if (j == 2) {
                        map.put("���", tdElement.get(j).getTextExtractor().toString());

                    }
                }
                if(isshow(source,"�����:")){
                    responseMap.put("�����", map);
                }
                List<Element> trElement = allTableElement.get(i).getAllElements(HTMLElementName.TR);
                for (int j = 0; j < trElement.size(); j++) {
                    Map inputmap = new HashMap();
                    List<Element> allSelectElement = trElement.get(j).getAllElements(HTMLElementName.SELECT);
                    if (allSelectElement.size() > 0) {
                        for (Element element : allSelectElement) {
                            String name = element.getAttributeValue("name");
                            List list = null;
                            List<Element> option = element.getAllElements(HTMLElementName.OPTION);
                            list = new ArrayList();
                            for (Element optionElement : option) {
                                Map map1 = new HashMap();
                                map1.put("key",optionElement.getAttributeValue("value"));
                                map1.put("name", optionElement.getTextExtractor().toString());
                                list.add(map1);
                            }
                            inputmap.put(name, list);

                        }
                        List<Element> hiddenInput = trElement.get(j).getAllElements(HTMLElementName.INPUT);

                        for (Element hidden : hiddenInput) {
                            String type=hidden.getAttributeValue("type");
                            if(type!=null&&type.equals("hidden")){
                                inputmap.put(hidden.getAttributeValue("name"),hidden.getAttributeValue("value"));
                            }
                        }
                        if(isshow(source,"�ŷ�������̶�")){
                            responseMap.put("����ѡ�������", inputmap);
                        }

                    } else {
                        List<Element> fontElement = trElement.get(j).getAllElements(HTMLElementName.FONT);
                        String fontvalue = "";
                        for (Element font : fontElement) {
                            fontvalue = font.getTextExtractor().toString();

                        }
                        if(isshow(source,"�ŷ�������̶�")&&!fontvalue.equals("")){

                            responseMap.put("�ŷ�������̶�", fontvalue);
                        }

                    }
                }

            } else if (i == 5) {
                System.out.println("++++++++++++++++++====");
                List<Element> trElement = allTableElement.get(i).getAllElements(HTMLElementName.TR);

                for (int j = 0; j < trElement.size(); j++) {
                    Map inputmap = new HashMap();
                    List<Element> allSelectElement = trElement.get(j).getAllElements(HTMLElementName.SELECT);
                    if (allSelectElement.size() > 0) {
                        for (Element element : allSelectElement) {
                            String name = element.getAttributeValue("name");
                            List list = null;
                            List<Element> option = element.getAllElements(HTMLElementName.OPTION);
                            list = new ArrayList();
                            for (Element optionElement : option) {
                                Map map1 = new HashMap();
                                map1.put("key",optionElement.getAttributeValue("value"));
                                map1.put("name", optionElement.getTextExtractor().toString());
                                list.add(map1);
                            }
                            inputmap.put(name, list);

                        }
                        List<Element> hiddenInput = trElement.get(j).getAllElements(HTMLElementName.INPUT);

                        for (Element hidden : hiddenInput) {
                            String type=hidden.getAttributeValue("type");
                            if(type!=null&&type.equals("hidden")){
                               inputmap.put(hidden.getAttributeValue("name"),hidden.getAttributeValue("value"));
                            }
                        }
                        if(isshow(source,"�ŷ�������̶�")){
                            responseMap.put("����ѡ�������", inputmap);
                        }

                    } else {
                        List<Element> fontElement = trElement.get(j).getAllElements(HTMLElementName.FONT);
                        String fontvalue = "";
                        for (Element font : fontElement) {
                            fontvalue = font.getTextExtractor().toString();

                        }
                        if(isshow(source,"�ŷ�������̶�")&&!fontvalue.equals("")){
                            responseMap.put("�ŷ�������̶�", fontvalue);
                        }

                    }
                }
            }
        }

    responseMap.put("msg","biz_result");
        System.out.println(JSONObject.fromObject(responseMap).toString());
        return responseMap;

    //To change body of implemented methods use File | Settings | File Templates.
    }
    private boolean isshow(Source source,String modelname){
        boolean isshow=false;
        List<Element> legendElement = source.getAllElements(HTMLElementName.DIV);
        for (Element lenend:legendElement){
            String classValue=lenend.getAttributeValue("class");
            if("legend".equals(classValue)){
                String modleName=lenend.getTextExtractor().toString();

                if(modleName.indexOf(modelname)!=-1){
                   isshow=true;
                }
            }

        }

        return isshow;
    }
}
