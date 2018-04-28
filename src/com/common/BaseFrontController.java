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
public abstract class BaseFrontController{
	protected int defaultPageSize=15;
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Date.class, Configuration.DefaultCustomDateEditor);
	}
	/**
	 * 获取默认列表页面PageBean ，先从缓存获取
	 * @return
	 */
	public PageMySql getDefaultPageBean(Integer current,String uri,boolean isSearch,HttpServletRequest request){
		PageMySql pageBean = null;
		HttpSession session = request.getSession();
		if(current==null || isSearch){//如果传了页码，或者搜索， 就需要刷新pageBean
			session.removeAttribute(uri);
			current=1;
			pageBean= new PageMySql(current,defaultPageSize);
			session.setAttribute(uri, pageBean);
		}else{
			pageBean = (PageMySql)session.getAttribute(uri);//没有刷新，用回原来在session里面的PageBean
			if(pageBean==null){
				pageBean= new PageMySql(current,defaultPageSize);
				session.setAttribute(uri, pageBean);
			}else{
				pageBean.setCurrent(current);
			}
		}
		request.setAttribute(Configuration.SessionAttPageBean, pageBean);
		return pageBean;
	}
	public PageMySql getPageNoCach(int size){
		return new PageMySql(1,size,30);
	}
	/**
	 * 获取登录用户的id
	 * @param request
	 * @return
	 */
	public Integer getLoginUserId(HttpServletRequest request){
		HttpSession session = request.getSession();
		/*String myRightRes = (String)session.getAttribute(Configuration.MyRightRes);
		if(myRightRes==null||myRightRes.equals("")){
			return null;
		}*/
		String Ksdafd8f = (String)session.getAttribute(Configuration.Ksdafd8f);
		//String lsdfnmae = (String)session.getAttribute("lsdfnmae");
		if(Ksdafd8f!=null){
			return SecurityUtil.getInstance().getUncId(Ksdafd8f);
		}
		return null;
	}
	public Integer getLoginUserId(HttpSession session){
		String Ksdafd8f = (String)session.getAttribute(Configuration.Ksdafd8f);
		String lsdfnmae = (String)session.getAttribute("lsdfnmae");
		if(Ksdafd8f!=null&&lsdfnmae!=null){
			return SecurityUtil.getInstance().getUncId(Ksdafd8f);
		}
		return null;
	}
	/**
	 * 获取sellsersid
	 * @param session
	 * @return
	 */
	public Integer getSellersId(HttpSession session){
		String myRightRes = (String)session.getAttribute(Configuration.MyRightRes);
		if(myRightRes==null||myRightRes.equals("")){
			return null;
		}
		return SecurityUtil.getInstance().getUncId(myRightRes);
	}
	/**
	 * 获取我所在的区域
	 */
	public String getMyRegionPath(HttpSession session){
		String myRegionPath = (String)session.getAttribute(Configuration.SessionRegionPathName);
		if(myRegionPath==null||myRegionPath.equals("")){
			myRegionPath = PropertiesConfigUtil.getInstance().getValue("default.regionPath");
			session.setAttribute(Configuration.SessionRegionPathName, myRegionPath);
		}
		return myRegionPath;
	}
	/**
	 * 设置我所在的区域
	 */
	public void setMyRegionPath(HttpSession session,String myRegionPath){
		session.setAttribute(Configuration.SessionRegionPathName, myRegionPath);
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
	/**
	 * 叠加值，用#号隔开值
	 */
	public void addCookieValue(HttpServletRequest request,HttpServletResponse response,String cookieName, String cookieValue){
		NetUtil.addCookieValue(request, response, cookieName, cookieValue);
	}
	/**
	 * 提示信息
	 * @param request
	 */
	protected void initRequestAtt(HttpServletRequest request){
		String saveSuccess = request.getParameter("saveSuccess");
		if(saveSuccess!=null)
			request.setAttribute("saveSuccess", saveSuccess);
		String saveError = request.getParameter("saveError");
		if(saveError!=null)
			request.setAttribute("saveError", saveError);
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
}