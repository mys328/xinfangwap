package com.bwzy.company.util;

import java.util.Properties;

/**
 * Created with MyEclipse
 * @author lvyangjun
 *date:12-12-29 
 *time:обнГ 14:47
 */

public class FilePathConfigUtil {
	private static Properties pro = new Properties();
	private static FilePathConfigUtil dbConfig;
	static{
		try{
			pro.load(FilePathConfigUtil.class.getClassLoader().getResourceAsStream("mailconfig.properties"));
		}catch(Exception e){
			
		}
	}
	
	public static FilePathConfigUtil getInstance(){
		if(dbConfig==null){
			dbConfig = new FilePathConfigUtil();
		}
		return dbConfig;
	}
	
	public static String getValue(String key){
		return pro.getProperty(key);
	}
}
