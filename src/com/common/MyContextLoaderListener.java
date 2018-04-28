package com.common;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import com.common.util.Configuration;
import com.common.util.ContextUtil;
import com.common.util.PropertiesConfigUtil;

public class MyContextLoaderListener extends ContextLoaderListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		ServletContext servletContext=event.getServletContext();
		String isDebug=PropertiesConfigUtil.getInstance().getValueNoCache("is.debug");
		servletContext.setAttribute("basePath", PropertiesConfigUtil.getInstance().getValueNoCache("my.basePath"));
		servletContext.setAttribute("ctxPath",servletContext.getContextPath());
		if(isDebug.equals("1")) servletContext.setAttribute("mediaPath",PropertiesConfigUtil.getInstance().getValueNoCache("front.media.uri"));
		else servletContext.setAttribute("mediaPath",servletContext.getContextPath());
		Configuration.CountryRegionIdValue=new Integer(PropertiesConfigUtil.getInstance().getValueNoCache("system.countryId"));
		servletContext.setAttribute("CountryRegionId",Configuration.CountryRegionIdValue);//默认国家
		servletContext.setAttribute("defaultRegionPath",
				PropertiesConfigUtil.getInstance().getValue("default.regionPath"));//默认城市
		
		/*servletContext.setAttribute(Configuration.ApplicationRegionList
				,regionService.selectPublishCity(Configuration.CountryRegionIdValue+"."));//List<Region> 区域选择
		servletContext.setAttribute(Configuration.ApplicationRegionListHw
				,regionService.selectByParentId(new Integer(0)));//List<Region> 区域选择 海外
		servletContext.setAttribute(Configuration.AllYdFrontCategorys,categoryService.getAllYdFrontCategorys());*/
	}
}