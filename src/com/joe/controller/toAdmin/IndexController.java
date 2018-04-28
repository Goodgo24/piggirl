package com.joe.controller.toAdmin;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.BaseFrontController;

@Controller
public class IndexController  extends BaseFrontController{	
	@RequestMapping(value = {"/index"})
	public String index(HttpServletRequest req) {
		return "toAdmin/index";
	}

}
