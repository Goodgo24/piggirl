package com.common;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.common.util.Configuration;
import com.common.util.FileUtilMy;
import com.common.util.PageMySql;
import com.common.util.SecurityUtil;
//return redirect:/
public abstract class BaseController<T,E> {
	protected BaseService service=null;//service层
	protected String pageListKey=null;//分页key，用于存储在session里
	protected BaseExample example=null;//查询支持
	protected String listPage=null;
	protected String editPage=null;
	protected String saveUrl=null;
	protected int defaultPageSize=20;
	
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
	/**
	 * 列表页面，自定义查询功能，请重写getExample()
	 * @param current
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Integer current,HttpServletRequest request, Model model) {
		BaseExample example = getExample(request);
		PageMySql pageBean= this.getDefaultPageBean(current,pageListKey,false,request);
		example.setPage(pageBean);
		model.addAttribute("list", service.getListDefByExample(pageBean, example));
		initRequestAtt(request);
		return listPage;
	}
	/**
	 * 列表页面 获取查询条件
	 * @param request
	 * @return
	 */
	public abstract BaseExample getExample(HttpServletRequest request);
	/**
	 * 批量删除功能，高度适用
	 * @param ch_id
	 * @param current
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
	public String batchDelete(Integer[] ch_id,Integer current,HttpServletRequest request, Model model){
		for(int delId:ch_id){
			service.deleteByPrimaryKey(delId);
		}
		request.getSession().removeAttribute(pageListKey);//列表页面执行删除操作后，清除PageMySql对象
		model.addAttribute("saveSuccess", "1");
		return list(current, request, model);
	}
	/**
	 * 长整形 主键的对象  删除
	 * @return
	 */
	@RequestMapping(value = "/batchDeleteLong", method = RequestMethod.POST)
	public String batchDelete(Long[] ch_id,Integer current,HttpServletRequest request, Model model){
		for(Long delId:ch_id){
			service.deleteByPrimaryKey(delId);
		}
		request.getSession().removeAttribute(pageListKey);//列表页面执行删除操作后，清除PageMySql对象
		model.addAttribute("saveSuccess", "1");
		return list(current, request, model);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(Integer do_id,Integer current,HttpServletRequest request, Model model){
		service.deleteByPrimaryKey(do_id);
		request.getSession().removeAttribute(pageListKey);//列表页面执行删除操作后，清除PageMySql对象
		model.addAttribute("saveSuccess", "1");
		return list(current, request, model);
	}
	/**
	 * 长整形 主键 删除操作
	 */
	@RequestMapping(value = "/deleteLong", method = RequestMethod.POST)
	public String delete(Long do_id,Integer current,HttpServletRequest request, Model model){
		service.deleteByPrimaryKey(do_id);
		request.getSession().removeAttribute(pageListKey);//列表页面执行删除操作后，清除PageMySql对象
		model.addAttribute("saveSuccess", "1");
		return list(current, request, model);
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Integer id,Integer current,HttpServletRequest request,Model model) {// 紧跟validate之后写验证结果
		Object editObject=null;
		if(id!=null){
			editObject = service.selectByPrimaryKey(id);
			model.addAttribute("pojo",editObject);
		}
		beforeEdit(request,editObject);
		if(current!=null)
			model.addAttribute("_current",current);
		return editPage;
	}
	/**
	 * 长整形 主键 编辑
	 */
	@RequestMapping(value = "/editLong", method = RequestMethod.GET)
	public String edit(Long id,Integer current,HttpServletRequest request,Model model){// 紧跟validate之后写验证结果
		Object editObject=null;
		if(id!=null){
			editObject = service.selectByPrimaryKey(id);
			model.addAttribute("pojo",editObject);
		}
		beforeEdit(request,editObject);
		if(current!=null){
			model.addAttribute("_current",current);
		}
		return editPage;
	}
	/**
	 * 保存功能  添加用户时，为post请求，访问以下代码
	 * @param pojo
	 * @param bindingResult
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Validated T pojo, BindingResult bindingResult, HttpServletRequest request, Model model) {// 紧跟validate之后写验证结果
		return this.saveTy(pojo, bindingResult, request, model);
	}
	
	/**
	 * 单文件上传表单 通用方法   默认不打水印
	 * @param pojo
	 * @param bindingResult
	 * @param uploadFile
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveFile", method = RequestMethod.POST)
	public String saveFile(@Validated T pojo, BindingResult bindingResult,
			@RequestParam("uploadFile") MultipartFile uploadFile, HttpServletRequest request,Model model) {
		if (uploadFile!=null&& !bindingResult.hasErrors()) {
			String name=uploadFile.getOriginalFilename(),txurl = null;
			if(name.endsWith(".png")||name.endsWith(".jpg")||name.endsWith("jpeg")
					||name.endsWith("gif")||name.endsWith("bmp")){
				txurl = FileUtilMy.getInstance().saveFile(uploadFile);
			}
			if (txurl!=null) {
				setUploadFileValue(pojo,txurl);
			}
		}
		return this.saveTy(pojo, bindingResult, request, model);
	}
	
	/**
	 * 单文件上传表单 通用方法  打水印
	 * @param pojo
	 * @param bindingResult
	 * @param uploadFile
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveFileImg", method = RequestMethod.POST)
	public String saveFileImg(@Validated T pojo, BindingResult bindingResult,
			@RequestParam("uploadFile") MultipartFile uploadFile, HttpServletRequest request,Model model) {
		if (uploadFile!=null&& !bindingResult.hasErrors()) {
			String name=uploadFile.getOriginalFilename(),txurl = null;
			if(name.endsWith(".png")||name.endsWith(".jpg")||name.endsWith("jpeg")
					||name.endsWith("gif")||name.endsWith("bmp")){
				txurl = FileUtilMy.getInstance().saveFileImg(uploadFile);
			}else{
				txurl = FileUtilMy.getInstance().saveFile(uploadFile);
			}
			if (txurl!=null) {
				setUploadFileValue(pojo,txurl);
			}
		}
		return this.saveTy(pojo, bindingResult, request, model);
	}
	
	
	//保存通用代码
	protected String saveTy(T pojo, BindingResult bindingResult, HttpServletRequest request, Model model){
		if (bindingResult.hasErrors()) {
			// 若有错误，直接掉转到add 视图
			List<FieldError>  err=bindingResult.getFieldErrors();
		    for (FieldError fe:err) {//得到那个字段验证出错  得到错误消息
		    	System.out.println("错误字段消息："+fe.getField() +" : "+fe.getDefaultMessage());
		    }
			request.setAttribute("pojo", pojo);
			request.setAttribute("fieldErrors", err);
			request.setAttribute("saveError", "1");
			return editPage;
		}
		request.getSession().removeAttribute(pageListKey);//页面执行保存操作后，清除PageMySql对象
		String _current = "";
		if(request.getParameter("pojoId")==null||request.getParameter("pojoId").equals("")){
			beforeInsert(pojo,request);
			service.insertSelective(pojo);
		}else{
			beforeUpdate(pojo,request);
			service.updateByPrimaryKeySelective(pojo);
			_current = request.getParameter("_current");
		}
		if(saveUrl.contains("?"))
			return saveUrl+"&saveSuccess=1";//"redirect:/system/sysRole/sysRoleList";
		else
			return saveUrl+"?saveSuccess=1&current="+_current;
	}
	protected void initRequestAtt(HttpServletRequest request){
		String saveSuccess = request.getParameter("saveSuccess");
		if(saveSuccess!=null)
			request.setAttribute("saveSuccess", saveSuccess);
		String saveError = request.getParameter("saveError");
		if(saveError!=null)
			request.setAttribute("saveError", saveError);
	}
	/**
	 * 获取登录用户的id
	 * @param request
	 * @return
	 */
	public Integer getLoginUserId(HttpServletRequest request){
		HttpSession session = request.getSession();
		String myRightRes = (String)session.getAttribute(Configuration.MyRightRes);
		if(myRightRes==null||myRightRes.equals("")){
			return null;
		}
		String Ksdafd8f = (String)session.getAttribute(Configuration.Ksdafd8f);
		String lsdfnmae = (String)session.getAttribute("lsdfnmae");
		if(Ksdafd8f!=null&&lsdfnmae!=null){
			return SecurityUtil.getInstance().getUncId(Ksdafd8f);
		}
		return null;
	}
	/**
	 * 如果有只上传一个文件的 表单，重写该方法，实现文件上传后，地址的注入
	 * 参考 Advertisement广告案例
	 * @param txurl
	 */
	protected void setUploadFileValue(T pojo,String txurl){
	}
	/**
	 * 插入之前的处理工作， 需要做处理的，请重写该方法     如数据初始化
	 */
	protected void beforeInsert(T pojo,HttpServletRequest request){
	}
	/**
	 * 修改保存之前的处理工作， 需要做处理的，请重写该方法    如数据初始化
	 */
	protected void beforeUpdate(T pojo,HttpServletRequest request){
	}
	/**
	 * 进入编辑页面之前的自定义准备工作，请重写该方法
	 */
	protected void beforeEdit(HttpServletRequest request,Object editObject){
	}
	/**
	 * 成功提示信息
	 */
	public void addSuccessMsg(HttpServletRequest request,String successMsg){
		request.setAttribute("successMsg", successMsg);
	}
	/**
	 * 错误提示信息
	 */
	public void addErrorMsg(HttpServletRequest request,String errorMsg){
		request.setAttribute("errorMsg", errorMsg);
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
	public Integer getMyCampanyId(HttpSession session){
		Object ob=session.getAttribute(Configuration.MyCampanysdf);
		if(ob!=null)return (Integer)ob;
		return null;
	}
	public Short getMyLanguageType(HttpSession session){
		Object ob=session.getAttribute(Configuration.MyCampanylgg);
		if(ob!=null)return (Short)ob;
		return null;
	}
}