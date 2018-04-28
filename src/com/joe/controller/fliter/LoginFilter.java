package com.joe.controller.fliter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.filter.OncePerRequestFilter;
import com.common.util.Configuration;
import com.common.util.NetUtil;
/**
 * 过滤器
 * @author
 *
 */
public class LoginFilter extends OncePerRequestFilter {
	//@Resource
	
	private final String CyXg="/", TAdmin="/toAdmin/",CyAnd="&" ;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {
		HttpSession session = request.getSession();
//		String userHistoryId = NetUtil.getLocaleFromCookie(request, "UserHistoryId");
		String url = request.getRequestURI();
//		String fullURL =request.getRequestURL().toString();
		//System.out.println("$$$$$$$$$$"+fullURL+"$$$$$$$$$$$$$$");//就这个
		
		if(url.equals("")||url.equals(CyXg)){
			if(url.endsWith(CyXg)) response.sendRedirect(url + "index");
			else response.sendRedirect(url + "/index");
			return;
		}
		try{
			//语言设置start  zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3   ?locale=zh_CN
/*			if (request.getParameter(Configuration.Locale) != null) {
				String getLanguage = request.getParameter(Configuration.Locale);
				if (getLanguage.startsWith("zh_CN")) {
					session.setAttribute(Configuration.AcceptLanguage, new Integer(1));
				} else if (getLanguage.startsWith("en_US")) {
					session.setAttribute(Configuration.AcceptLanguage, new Integer(2));
				} else {
					session.setAttribute(Configuration.AcceptLanguage, new Integer(3));
				}
				NetUtil.setCookie(response,Configuration.AcceptLanguage, getLanguage);
			} else if (session.getAttribute(Configuration.AcceptLanguage) == null) {
				String query=request.getQueryString();
				if(query!=null&&query.length()>0) url+="?"+query;
				else url+="?doLocalCh=1";
				String getLanguage = NetUtil.getLocaleFromCookie(request,Configuration.AcceptLanguage);
				if (getLanguage != null && getLanguage.length() > 0) {
					response.sendRedirect(url + CyAnd + Configuration.Locale + "="+getLanguage);
					return;
				} else if(!url.contains("/phoneIndex")){//不安全，屏蔽微信端{
					
				}
				if(!url.contains("/phoneIndex")){//不安全，屏蔽微信端
					//不安全 getLanguage=request.getHeader("Accept-Language"); 
					if(getLanguage==null){response.sendRedirect(url+CyAnd+Configuration.Locale+"=zh_CN");
					}else if(getLanguage.startsWith("zh-CN")){response.sendRedirect(url+CyAnd+Configuration.Locale+"=zh_CN");
					}else if(getLanguage.startsWith("en-US")){response.sendRedirect(url+CyAnd+Configuration.Locale+"=en_US");
					}else if(getLanguage.startsWith("my-MM")){response.sendRedirect(url+CyAnd+Configuration.Locale+"=my_MM");
					}else{response.sendRedirect(url+CyAnd+Configuration.Locale+"=en_US");}
				}
				filterChain.doFilter(request, response);
				return;
			}*/
			//语言设置end
			
			//权限控制start
			if (!url.contains(TAdmin)||url.contains("/login")){
				filterChain.doFilter(request, response);
				return;
			}
			Object obj = request.getSession().getAttribute(Configuration.Ksdafd8f);
			if (null == obj) {
				response.sendRedirect(request.getContextPath() + "/toAdmin/login");
			} else {
				filterChain.doFilter(request, response);
			}
			//权限控制end
			
		}catch(Exception e){
			e.printStackTrace();
			filterChain.doFilter(request, response);
		}
	}
	private String getFullUrl(HttpServletRequest request){
		String strBackUrl = "http://" + request.getServerName() //服务器地址  
        + ":"   
        + request.getServerPort()           //端口号  
        + request.getContextPath()      //项目名称  
        + request.getServletPath()      //请求页面或其他地址  
        + "?" + (request.getQueryString()); //参数 
		return strBackUrl;
	}
}
