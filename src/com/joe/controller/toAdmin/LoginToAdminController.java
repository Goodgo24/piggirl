package com.joe.controller.toAdmin;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.BaseFrontController;

@Controller
@RequestMapping("/toAdmin")
public class LoginToAdminController  extends BaseFrontController{
/*	@Resource
	private SysUserService sysUserService;
	@Resource
	private CompanyService companyService;*/
	
	@RequestMapping(value = {"/login" })
	public String login(HttpServletRequest req) {
		//req.setAttribute("sysUserLoginName",getLocaleFromCookie(req, "sysUserLoginName"));
		return "toAdmin/login";
	}
	@RequestMapping(value = { "/index"})
	public String index(HttpServletRequest req) {
		return "toAdmin/index";
	}
	@RequestMapping(value = {"/logout" })
	public String logout(HttpSession session) {
		session.invalidate();
		return "toAdmin/login";
	}
	/*@RequestMapping(value = "/loginDo", method = {RequestMethod.POST,RequestMethod.GET}) //
	public String login( HttpServletRequest request ,HttpServletResponse response) {
		String loginName = request.getParameter("loginName");
		String pwd = request.getParameter("pwd");
		pwd = EncodeUtil.getEncode(pwd);
		SysUser sysUser = sysUserService.selectSysUser(loginName, pwd);
		HttpSession session = request.getSession();
		if(sysUser != null){
			if(sysUser.getStatus()!=0){
				if(sysUser.getCompanyId() !=null){
					Company company = companyService.selectByPrimaryKey(sysUser.getCompanyId());
					if(company!=null){
						session.setAttribute(Configuration.MyCampanysdf, company.getCompanyId());
						session.setAttribute(Configuration.MyCampanylgg, company.getLanguageType());
					}
				}
				if(sysUser.getLoginName()!=null && !sysUser.getLoginName().equals("")){
					session.setAttribute(Configuration.SecurityUidName, sysUser.getLoginName());
					session.setAttribute(Configuration.MyRightRes, sysUser.getLoginName());
				}else{
					session.setAttribute(Configuration.SecurityUidName, sysUser.getLoginName());
					session.setAttribute(Configuration.MyRightRes, sysUser.getLoginName());
				}
				setCookie(response, "sysUserLoginName", loginName);
				
//				setCookie(response, "UserHistoryId", SecurityUtil.getInstance().getEncId(sysUser.getSysUserId())); //客户浏览历史记录id
				session.setAttribute(Configuration.Ksdafd8f, SecurityUtil.getInstance().getEncId(sysUser.getSysUserId()));
				session.setAttribute("lsdfnmae", sysUser.getLoginName());//登录者用户id ${Configuration.Ksdafd8f}
				//刷新登陆时间
				sysUserService.updateByPrimaryKeySelective(sysUser);
			}else{
				this.addShowError(request,"login.zhangHaoXx");
				return "toAdmin/login";
			}
		}else {
			this.addShowError(request,"login.yhHmmcw");
			return "toAdmin/login";
		}
		return "redirect:/toAdmin/index";
	}*/
}
