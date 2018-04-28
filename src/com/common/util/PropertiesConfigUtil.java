package com.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;

//读取配置文件（属性文件）的工具类
public class PropertiesConfigUtil {
	private static PropertiesConfigUtil configManager;
	//properties.load(InputStream);读取属性文件
	private Properties properties; 
	private static HashMap<String,String> KeyValue=new HashMap<String,String>();
	private static HashSet<String> FilterWords=new HashSet<String>();
	private static HashSet<String> UrlReqType=new HashSet<String>();
	private PropertiesConfigUtil(){
		String configFile="myconfig.properties";
		properties=new Properties();
		InputStream in=PropertiesConfigUtil.class.getClassLoader().getResourceAsStream(configFile);
		try {
			properties.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static PropertiesConfigUtil getInstance(){
		if(configManager==null){
			configManager=new PropertiesConfigUtil();
		}
		return configManager;
	}
	public String getValue(String key){
		if(KeyValue.get(key)==null){
			KeyValue.put(key, properties.getProperty(key));
		}
		return KeyValue.get(key);
	}
	public String getValueNoCache(String key){
		return properties.getProperty(key);
	}
	public HashSet<String> getFilterWords(){
		if(FilterWords.size()<1){
			InputStreamReader read=null;
			InputStream in=null;
	        try{
	        	in=PropertiesConfigUtil.class.getClassLoader().getResourceAsStream("filterWord.properties");
	        	read = new InputStreamReader(in,"UTF-8");//考虑到编码格式
	        	BufferedReader bufferedReader = new BufferedReader(read);
	        	String lineTxt = null;
	        	while((lineTxt = bufferedReader.readLine()) != null)
	        		FilterWords.add(lineTxt);
	        	read.close();
	        	in.close();
	        }catch(Exception e){
	            System.out.println("读取文件内容出错");e.printStackTrace();
				try {
					if(read!=null) read.close();
					if(in!=null) in.close();
				} catch (IOException e1) {e1.printStackTrace();}
	        }
		}
        return FilterWords;
    }
	public HashSet<String> getUrlReqType(){
		if(UrlReqType.size()<1){
			InputStreamReader read=null;
			InputStream in=null;
	        try{
	        	in=PropertiesConfigUtil.class.getClassLoader().getResourceAsStream("urlReqType.properties");
	        	read = new InputStreamReader(in,"UTF-8");//考虑到编码格式
	        	BufferedReader bufferedReader = new BufferedReader(read);
	        	String lineTxt = null;
	        	while((lineTxt = bufferedReader.readLine()) != null)
	        		UrlReqType.add(lineTxt);
	        	read.close();
	        	in.close();
	        }catch(Exception e){
	            System.out.println("读取文件内容出错"); e.printStackTrace();
				try {
					if(read!=null) read.close();
					if(in!=null) in.close();
				} catch (IOException e1) { e1.printStackTrace();}
	        }
		}
        return UrlReqType;
    }
	//文件上传后，访问路径
	public String getMediaUrl(String ctxPath){
		String pz=getValue("front.media.uri"),hj=getValue("is.debug");
		if(hj.equals("1"))return pz+"/upload/";//开发环境
		return ctxPath+"/upload/";//正式环境
	}
}
