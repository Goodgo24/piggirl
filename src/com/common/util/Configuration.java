package com.common.util;

import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;
//languageType 1中文 2英文 3本地语言
public class Configuration {
	//权限资源
	public static final String SecurityResource="_SecurityResource_";
	public static final String SecurityUidKey="_SecurityUidKey_";
	public static final String SecurityUidName="_SecurityUidName_";
	public static final String SessionAttPageBean = "_PageBean";
	public static final String SecurityKey = "SHA";
	public static final String EncodingSet = "UTF-8";
	public static final String MyRightRes = "myRightRes";
	public static final String IsDebug = "is.debug";//是否开发状态 标识
	public static final String CountryRegionId = "CountryRegionId";
	public static Integer CountryRegionIdValue = null;
	public static final String UploadFileMaxSize = "UploadFileMaxSize";
	public static final String CountryRegionPath = "2.";
	public static final String String1 = "1";//
	public static final String String0 = "0";//
	public static final Short Short2 = new Short("2");//状态2
	public static final Short Short1 = new Short(String1);//激活状态
	public static final Short Short0 = new Short(String0);//状态失效
	public static final String Ksdafd8f = "Ksdafd8f";
	public static final String MyCampanysdf = "MyCampanysdf";//企业 存储id
	public static final String MyCampanylgg = "MyCampanylgg";//企业 存储语言权限
	public static final String AcceptLanguage = "AcceptLanguage";//zh_CN  en_US my_MM 对应 1 2 3
	public static final String Locale="locale";// &locale=zh_CN   &locale=en_US &locale=my_MM
	public static final String SessionRegionPathName="region_path";//区域保存到session的key
	public static final String SessionRegionPathObject="region_path_object";//区域对象保存到session的key
	public static final String SessionRegionName="region_path_name";//区域名称保存到session的key
	public static final String ApplicationRegionList="ApplicationRegionList";//区域保存到缓存，减少数据库查询
	public static final String ApplicationRegionListHw="ApplicationRegionListHw";//区域保存到缓存，减少数据库查询
	public static final String UserReqHistoryName="UserReqHistory_Name";
	public static final String oldClient="oldClient";//老客户为1 新客户为2
	public static final String UploadPath="/upload/";
	public static final String JobPublishKey="JobPublish.";//JobPublish
	public static final String ResumeDownKey="ResumeDown.";
	public static final String AllYdFrontCategorys="AllYdFrontCategorys"; //栏目
	
	
	public static final CustomDateEditor DefaultCustomDateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),true);//true:允许输入空值，false:不能为空值
	public static final CustomDateEditor LongCustomDateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),true);//true:允许输入空值，false:不能为空值
	public static final CustomDateEditor CustomDateEditor_mm=new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm"),true);
}