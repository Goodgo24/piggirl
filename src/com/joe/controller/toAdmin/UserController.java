package com.joe.controller.toAdmin;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.BaseController;
import com.common.BaseExample;
import com.common.BaseFrontController;
import com.joe.pojo.PiggirlUser;
import com.joe.pojo.PiggirlUserExample;
import com.joe.service.PiggirlUserService;

@Controller
@RequestMapping(value="/user")
public class UserController  extends BaseController<PiggirlUser, PiggirlUserExample> implements InitializingBean{	
	@Resource
	private PiggirlUserService piggirlUserService;
	

	@Override
	public BaseExample getExample(HttpServletRequest request) {
		PiggirlUserExample e =new PiggirlUserExample();
		return e;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		service=piggirlUserService;
		pageListKey="/user/list";//分页pagebean放在session里的key
		listPage="toAdmin/userList";//list页面
		editPage="toAdmin/userEdit";//edit页面
		saveUrl="redirect:/user/list";//保存成功之后跳转的页面
		this.defaultPageSize=10;//默认每页数量
		
	}

}
