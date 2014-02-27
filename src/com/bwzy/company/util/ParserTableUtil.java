package com.bwzy.company.util;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: lvyangjun
 * Date: 13-9-6
 * Time: 上午11:10
 * To change this template use File | Settings | File Templates.
 */
public class ParserTableUtil {

    /**
     * 解析HTML页面中Table
     *
     * @param htmlSource     html代码
     * @return        以Map类型返回table数据
     */
    public Map<String,Object> parserTable(String htmlSource) {
        Map<String,Object> tableMap=new HashMap<String, Object>();
        Source source = new Source(htmlSource);
        List<Element> allTableslement = source.getAllElements(HTMLElementName.TABLE);
        Iterator<Element> iterator=allTableslement.iterator();
        while (iterator.hasNext()){
            Element tableElement=iterator.next();
            List tableList = new ArrayList();
            List<Element> allTRElement = tableElement.getAllElements(HTMLElementName.TR);

            String tablesName=tableElement.getAttributeValue("class");
            List thList = new ArrayList();
            for (Element TR : allTRElement) {
                List<Element> Th=TR.getAllElements(HTMLElementName.TH);
                for(Element th: Th){
                    thList.add(th.getTextExtractor().toString());
                }
                Map map=null;
                if(Th.size()==0){
                    map=new HashMap();
                    List<Element> td =TR.getAllElements(HTMLElementName.TD);
                    for(int i=0;i<td.size();i++){
                        map.put(thList.get(i), td.get(i).getTextExtractor().toString());
                    }
                }
                if(map!=null){
                    tableList.add(map);
                }
            }
            tableMap.put(tablesName,tableList);

        }
        return tableMap;
    }
   /* public static void main(String[] args){
        String html="<table name=\"table1\">\n" +
                "  <tr>\n" +
                "    <th>Month</th>\n" +
                "    <th>Savings</th>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>January</td>\n" +
                "    <td>$100</td>\n" +
                "  </tr>\n" +
                "</table>\n"+
                "<table name=\"table2\">\n" +
                "  <tr>\n" +
                "    <th>Month</th>\n" +
                "    <th>Savings</th>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>January</td>\n" +
                "    <td>$100</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>January</td>\n" +
                "    <td>$100</td>\n" +
                "  </tr>\n" +
                "</table>\n";
        ParserTableUtil parserTableUtil=new ParserTableUtil();
        Map<String,Object> map=parserTableUtil.parserTable(html);
        String json= JSONObject.fromObject(map).toString();
        System.out.println(json);
    }*/


}
