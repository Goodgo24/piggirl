package com.common.util;

import java.math.BigDecimal;

/**
 * 经纬度计算距离
 * 附近的店铺 经纬度上下 加0.3
 * @author Administrator
 *
 */
public class DistanceUtil {
	private static double EARTH_RADIUS = 6378.137;
	public static void main(String[] args) {
		double jl = DistanceUtil.GetDistance(22.5352162727,113.9102685713, 22.8352301328,113.9100849538);
		System.out.println(jl);
		
		BigDecimal b = new BigDecimal(9.655 );
        //double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        System.out.println("f1=" + f1);//f1=9.65
        BigDecimal mData = new BigDecimal("9.655").setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println("mData=" + mData);//mData=9.66
	}
	
	private static double rad(double d){
	   return d * Math.PI / 180.0;
	}
	/**
	 * 计算距离 千米--公里
	 * @param lat1  纬度
	 * @param lng1  经度
	 * @param latitude2  纬度
	 * @param longitude2  经度
	 * @return
	 */
	public static double GetDistance(double latitude1, double longitude1, double latitude2, double longitude2){
	   double radLat1 = rad(latitude1), radLat2 = rad(latitude2);
	   double a = radLat1 - radLat2, b = rad(longitude1) - rad(longitude2);
	   double s = (2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
			   Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2))))* EARTH_RADIUS;
	   s = new BigDecimal(s).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();//取3位小数点，四舍五入
	   return s;
	}
}