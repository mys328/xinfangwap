package com.bwzy.company.util;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

public class XmlAndJsonUtil {
	
	public static String xml2JSON(String xml){
		return new XMLSerializer().read(xml).toString();
	}
	
	public static String json2XML(String json){
		JSONObject jobj = JSONObject.fromObject(json);
		String xml =  new XMLSerializer().write(jobj);
		return xml;
	}
	
}
