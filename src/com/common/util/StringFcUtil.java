package com.common.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//分词
public class StringFcUtil {
	private static final StringFcUtil instance=new StringFcUtil();
	public static StringFcUtil getInstance(){
		return instance;
	}
	public static void main(String[] args) {
		StringFcUtil sfu=new StringFcUtil();
		ArrayList<String> ss=sfu.cf("ab");
//		System.out.println(sfu.isContainChinese("hiudsfd545lkj45"));
//		System.out.println(sfu.isContainChinese("၀င္ၿပိဳင္မည"));
//		System.out.println(sfu.isZmSz("dfgfd56QQQ56ErtrU"));
		for(int i=0;i<ss.size();i++){
			System.out.print(ss.get(i)+",");
		}
	}
	/**
	 * 客户已经分词 直接获取
	 * @param msg
	 * @return
	 */
	public ArrayList<String> getFcStrs(String msg){
		ArrayList<String> keys=new ArrayList<String>();
		addStr(msg," ",keys);
		addStr(msg,",",keys);
		addStr(msg,"，",keys);
		/*if(keys.size()<15){//还未拆分够 15个关键字 去辅助关键字表，匹配关键字
		}*/
		return keys;
	}
	/**
	 * 字符串拆分 组合
	 * @param msg
	 * @return
	 */
	public ArrayList<String> cf(String msg){
		ArrayList<String> rts=new ArrayList<String>(), dxrt=new ArrayList<String>();
		int sl=msg.length();
		// abc  a b c ab bc abc             abcd  a b c d ab bc cd abc bcd
		for(int i=1;i<sl;i++){
			for(int j=0;j<sl;j++){
				if(i+j>sl) break;
				rts.add(msg.substring(j, i+j));
			}
		}
		for(int i=rts.size()-1;i>=0;i--){
			dxrt.add(rts.get(i));
		}
		return dxrt;
	}
	private void addStr(String str,String split,ArrayList<String> keys){
		if(str.contains(split)){
			String[] strs=str.split(split);
			if(strs!=null&&strs.length>0){
				for(int i=0;i<strs.length;i++){
					keys.add(strs[i]);
				}
			}
		}
	}
	/**
	 * 是否包含中文
	 * @return
	 */
	public boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
	/**
	 * 是否全部字母 数字
	 * @param text
	 * @return
	 */
	public boolean isZmSz(String text){
		//String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
		return text.matches("[A-Za-z0-9]*");//[0-9A-Za-z] 
	}
}
