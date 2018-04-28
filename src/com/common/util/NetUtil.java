package com.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NetUtil {
	/**
	 * 设置cookie
	 * @param request
	 * @param response
	 * @param cookieName
	 * @param LocaleValue
	 */
	public static void setCookie(HttpServletResponse response,String cookieName, String LocaleValue) {
		Cookie cookie = new Cookie(cookieName, LocaleValue);
		cookie.setMaxAge(312345678);//永不过期
		// 设置路径，这个路径即该工程下都可以访问该cookie 如果不设置路径，那么只有设置该cookie路径及其子路径可以访问
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	/**
	 * 追加值  叠加值，用#号隔开值
	 * @param request
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 */
	public static void addCookieValue(HttpServletRequest request,HttpServletResponse response,String cookieName, String cookieValue){
		String value=getLocaleFromCookie(request, cookieName);
		if(value!=null&&value.length()>0){
			if(value.contains(cookieValue)){
				value=value.replace(cookieValue, "");
				value=value.replace("##", "#");
			}
			setCookie(response, cookieName, cookieValue+"#"+value);
		}else
			setCookie(response, cookieName, cookieValue);
	}
	/**
	 * 获取cookie值
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static String getLocaleFromCookie(HttpServletRequest request,String cookieName) {
		Cookie[] cookies = request.getCookies();// 这样便可以获取一个cookie数组
		if(cookies!=null){
			for (Cookie cookie : cookies) {
				if (cookieName.equals(cookie.getName())) {// get the cookie name
					return cookie.getValue(); // get the cookie value
				}
			}
		}
		return null;
	}
	/**
	 * 获取ip地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = null;
		// ipAddress = request.getRemoteAddr();
		ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if(ipAddress != null && ipAddress.equals("0:0:0:0:0:0:0:1")){
				ipAddress="127.0.0.1";
			}
			/*if (ipAddress != null && ipAddress.equals("127.0.0.1")) {// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
					ipAddress = inet.getHostAddress();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}*/
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length() = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}
}
