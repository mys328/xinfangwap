package com.bwzy.company.util;

import java.util.Properties;

/**
 * Created with MyEclipse
 * @author lvyangjun
 *date:12-12-29 
 *time:обнГ 14:47
 */

public class UrlConfigUtil {
	private static Properties pro = new Properties();
	private static UrlConfigUtil dbConfig;
	static{
		try{
			pro.load(UrlConfigUtil.class.getClassLoader().getResourceAsStream("UrlsConfig.properties"));
		}catch(Exception e){
			
		}
	}
	
	public static UrlConfigUtil getInstance(){
		if(dbConfig==null){
			dbConfig = new UrlConfigUtil();
		}
		return dbConfig;
	}
	
	public static String getValue(String key){
		return pro.getProperty(key);
	}
}
