package com.common.util;

public class IpUtil {
	/*
	 * 验证IP是否属于某个IP段
	 * ipSection IP段（以'-'分隔）
	 * 
	 * ip 所验证的IP号码
	 */
	public static boolean ipExistsInRange(String ip, String ipSection) {
		int idx = ipSection.indexOf('-');
		String beginIP = ipSection.substring(0, idx);
		String endIP = ipSection.substring(idx + 1);
		long ipLong = getIp2long2(ip);
		return (getIp2long2(beginIP) <= ipLong) && (ipLong <= getIp2long2(endIP));
	}
	public static boolean ipExistsInRange(String ip, String beginIP,String endIP) {
		long ipLong = getIp2long2(ip);
		return (getIp2long2(beginIP) <= ipLong) && (ipLong <= getIp2long2(endIP));
	}
	public static long getIp2long(String ip) {
		ip = ip.trim();
		String[] ips = ip.split("\\.");
		long ip2long = 0L;
		for (int i = 0; i < 4; ++i) {
			ip2long = ip2long << 8 | Integer.parseInt(ips[i]);
		}
		return ip2long;
	}
	public static long getIp2long2(String ip) {
		String[] ips = ip.trim().split("\\.");
//		long ip1 = Integer.parseInt(ips[0]);
//		long ip2 = Integer.parseInt(ips[1]);
//		long ip3 = Integer.parseInt(ips[2]);
//		long ip4 = Integer.parseInt(ips[3]);
//		long ip2long = 1L * ip1 * 256 * 256 * 256 + ip2 * 256 * 256 + ip3 * 256 + ip4;
//		return ip2long;
		return 1L * Long.parseLong(ips[0]) * 256 * 256 * 256 
				+ Long.parseLong(ips[1]) * 256 * 256 + Long.parseLong(ips[2]) * 256 + Long.parseLong(ips[3]);
	}

	public static void main(String[] args) {
		// 10.10.10.116 是否属于固定格式的IP段10.10.1.00-10.10.255.255
		String ip = "113.191.56.48";
		String ipSection = "113.160.0.0-113.191.255.255";
		boolean exists = ipExistsInRange(ip, ipSection);
		System.out.println(exists);
		System.out.println(getIp2long(ip));
		System.out.println(getIp2long2(ip));
	}
}
