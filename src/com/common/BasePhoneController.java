package com.common;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.common.util.Configuration;
import com.common.util.NetUtil;
import com.common.util.PageMySql;
import com.common.util.PropertiesConfigUtil;
import com.common.util.SecurityUtil;

//前台 controller
public abstract class BasePhoneController{
	protected int defaultPageSize=15;
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Date.class, Configuration.DefaultCustomDateEditor);
	}
	/**
	 * 获取默认列表页面PageBean ，先从缓存获取
	 * 不需要计算 总页数  记录数
	 * @return
	 */
	public PageMySql getDefaultPageBean(Integer current){
		if(current==null){//如果传了页码，或者搜索， 就需要刷新pageBean
			current=1;
		}
		return new PageMySql(current,defaultPageSize,2000);
	}
	/**
	 * 获取登录用户的id
	 * @param request
	 * @return
	 */
	public Integer getLoginUserId(String uidStr){
		if(uidStr==null||uidStr.equals("")){
			return null;
		}
		return SecurityUtil.getInstance().getUncId(uidStr);
	}
	/**
	 * 获取sellsersid
	 * @param session
	 * @return
	 */
	public Integer getSellersId(String uidStr){
		if(uidStr==null||uidStr.equals("")){
			return null;
		}
		return SecurityUtil.getInstance().getUncId(uidStr);
	}
	
	public Integer getLanguage(String language){
		if(language==null){
			return 2;
		}else if(language.startsWith("zh")){//zh_CN
			return 1;
		}else if(language.startsWith("en")){//en_US  
			return 2;
		}else if(language.startsWith("my")){//my_MM
			return 3;
		}else{
			return 2;
		}
	}
	/**
	 * 添加前台页面提示内容
	 * @param key 国际化的关键字
	 */
	public void addShowMsg(HttpServletRequest request,String key){
		request.setAttribute("ShowMsgKey", key);
	}
	public void addShowError(HttpServletRequest request,String key){
		request.setAttribute("ShowErrorMsgKey", key);
	}
	/**
	 * 设置cookie值
	 */
	public void setCookie(HttpServletResponse response,String cookieName, String cookieValue) {
		NetUtil.setCookie(response, cookieName, cookieValue);
	}
	/**
	 * 获取cookie值
	 */
	public String getLocaleFromCookie(HttpServletRequest request,String cookieName) {
		return NetUtil.getLocaleFromCookie(request, cookieName);
	}
}