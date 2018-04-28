package com.common.util;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dingdang.portal.pojo.SysUser;
import com.dingdang.portal.service.SysConfigService;
import com.dingdang.portal.service.SysUserService;


/**
 * 安全校验
 * @author DingDang
 */
public class SecurityUtil {
	private Random random= new Random();
	private int pass=20;//该值可以根据时间  实时改变，以加大破解难道
	private final long PassNoChange=103212765849137L;//该值不允许改变，改变将无法解密
	private static SecurityUtil instance = new SecurityUtil();
	private SysConfigService sysConfigService = null;
	private SysUserService sysUserService = null;
	public static SecurityUtil getInstance(){
		instance.init();
		return instance;
	}
	private void init(){
		if(sysUserService==null){
			sysUserService = (SysUserService)ContextUtil.getBean("sysUserService");
			sysConfigService=(SysConfigService)ContextUtil.getBean("sysConfigService");
		}
	}
	/**
	 * 简单加密
	 * @param userId
	 * @return
	 */
	public String getEncId(Integer userId){
		int to = random.nextInt(9)+1;//前 长度随机
		int ei = random.nextInt(9)+1;//后
		return getStrI(to)+""+(userId-pass)+getStrE(ei);
	}
	
	/**
	 * 解密
	 * @param userIdStr
	 * @return
	 */
	public int getUncId(String userIdStr){
		int length = userIdStr.length();
		int to = Integer.parseInt(userIdStr.substring(0, 1));
		int ei = Integer.parseInt(userIdStr.substring(length-1, length));
		userIdStr = userIdStr.substring(to+1,length-ei-1);
		return Integer.parseInt(userIdStr)+pass;
	}
	private String getStrE(int dos){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<dos;i++){
			sb.append(random.nextInt(10));
		}
		return sb.toString()+dos;
	}
	private String getStrI(int dos){
		StringBuffer sb = new StringBuffer(dos+"");
		for(int i=0;i<dos;i++){
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}
	
	
	public boolean checkSecurityUtil(HttpServletRequest request){
		try{
			HttpSession session = request.getSession();
			String myRightRes = (String)session.getAttribute(Configuration.MyRightRes);
			if(myRightRes==null||myRightRes.equals("")){
				return false;
			}
			String Ksdafd8f = (String)session.getAttribute(Configuration.Ksdafd8f);
			String lsdfnmae = (String)session.getAttribute("lsdfnmae");
			
			Integer id = getUncId(Ksdafd8f);
			SysUser user = sysUserService.selectByPrimaryKey(id);
			if(user!=null&&user.getLoginName().equals(lsdfnmae)){
				return true;
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public static void main(String[] args) {
//		String en = instance.getEncTel(DateUtil.format_yyyyMMddHH(new Date()));
//		System.out.println(en+"");
//		System.out.println(en.length()+"");
		System.out.println(instance.getEncId(10235)+"");
//		System.out.println(instance.getUncId("583906-2643225")+"");
//		String rightKey="JobPublish.BuyYear4000";
//		if(rightKey.contains(".")){
//			rightKey=rightKey.substring(rightKey.indexOf("."));
//		}
//		String ret=((int)((Math.random()*9+1)*1000))+"";
//		System.out.println(ret);
	}
	
	int lastCd=5,indexCd=3;
	/**
	 * 递减值不改变 解密
	 * @param edIdStr
	 * @return
	 */
	public String getUncTel(String edIdStr){
		int length = edIdStr.length();
		edIdStr = edIdStr.substring(indexCd, length-lastCd);
		length = edIdStr.length();
		int cd = Integer.parseInt(edIdStr.substring(length-2, length));
		edIdStr = edIdStr.substring(0, length-2);
		long dhlen = new Long(edIdStr)+PassNoChange;
		edIdStr = dhlen+"";
		length = edIdStr.length();
		edIdStr = edIdStr.substring(length-cd, length);
		return edIdStr;
	}
	private String de0="0";
	/**
	 * 5位前后加密 递减值不改变
	 * @param edId
	 * @return
	 */
	public String getEncTel(String edId){
		String CountryRegionId = "35";//sysConfigService.getCountryRegionId().toString();
		StringBuffer nstr = new StringBuffer(CountryRegionId);
		int lenght = CountryRegionId.length();
		long theLong=0;
		if(lenght<16){
			for(int i=0;i<16-lenght;i++){
				nstr.append(de0);
			}
			theLong = new Long(nstr.toString());
			theLong = theLong+Long.parseLong(edId)-PassNoChange;
		}
		int sz = edId.length();
		if(sz<10)
			return theLong+"0"+sz+getTelEnd(lastCd);
		return getTelEnd(indexCd)+theLong+sz+getTelEnd(lastCd);
	}
	
	private String getTelEnd(int ws){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<ws;i++){
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}
}