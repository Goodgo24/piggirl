package com.common.util;

import java.security.MessageDigest;

public class EncodeUtil {
	
	  /*** 
	   * MD5加密 生成32位md5码
	   * SHA加密 生成40位SHA码
	   * @param 待加密字符串
	   * @return 返回40位SHA码
	  */
    public static String getEncode(String inStr){
    	 try {
	        MessageDigest sha = MessageDigest.getInstance(Configuration.SecurityKey);
	        byte[] byteArray = inStr.getBytes(Configuration.EncodingSet);
	        byte[] md5Bytes = sha.digest(byteArray);
	        StringBuffer hexValue = new StringBuffer();
	        for (int i = 0; i < md5Bytes.length; i++) {
	            int val = ((int) md5Bytes[i]) & 0xff;
	            if (val < 16) { 
	                hexValue.append("0");
	            }
	            hexValue.append(Integer.toHexString(val));
	        }
	        return hexValue.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return inStr;
        }
    }

    /**
     * 测试主函数
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        String str = new String("115307517188");
        System.out.println("原始：" + str);
        System.out.println("SHA后：" + getEncode(str));
    }
}
