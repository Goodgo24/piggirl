package com.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Date Utility Class This is used to convert Strings to Dates and Timestamps
 * 
 * <p>
 * <a href="DateUtil.java.html"><i>View Source</i></a>
 * </p>
 * 
 */
public final class DateUtil {
	private static Log		log			= LogFactory.getLog(DateUtil.class);

	// ~ Static fields/initializers
	// =============================================
	private final static String	shortDatePattern	= "yyMMdd";
//	private final static String	DatePatternForStr	= "yyyyMMdd";
	private final static SimpleDateFormat SimpleDateFormat_yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	private final static SimpleDateFormat SimpleDateFormat_HHmmssSSS = new SimpleDateFormat("HHmmssSSS");
	private final static SimpleDateFormat SimpleDateFormat_yyyyMMddHH = new SimpleDateFormat("yyyyMMddHH");
	private final static SimpleDateFormat SimpleDateFormat_yyyyMMddHHmm = new SimpleDateFormat("yyyyMMddHHmm");
	private final static SimpleDateFormat SimpleDateFormat_yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
	private final static SimpleDateFormat SimpleDateFormat_yyyyMMddHHmmssSSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	private static String defaultDatePattern = "MM/dd/yyyy";
	private final static SimpleDateFormat SimpleDateFormat_Default = new SimpleDateFormat(defaultDatePattern);
	private static String defaultDatePatternHH = "MM/dd/yyyy HH";
	private final static SimpleDateFormat SimpleDateFormat_DefaultHH = new SimpleDateFormat(defaultDatePatternHH);
	private static String	timePattern	= "HH:mm";
	private final static SimpleDateFormat SimpleDateFormat_HHmmss = new SimpleDateFormat("HH:mm:ss");
	private final static SimpleDateFormat DateFormat_yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");
	public final static SimpleDateFormat DateFormat_yyyy_MM_ddHHmm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public final static SimpleDateFormat DateFormat_yyyy_MM_ddHH = new SimpleDateFormat("yyyy-MM-dd HH");

	private static SimpleDateFormat BdDateFormat_dd = null;//ShowDate_dd
	private static SimpleDateFormat BdDateFormat_HH = null;
	private static SimpleDateFormat BdDateFormat_mm = null;
	private static SimpleDateFormat BdDateFormat_ss = null;
	
	/**
	 * change the give date by set year,month,day,hour,minute and second.
	 * 
	 * @param thisDate
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static Calendar changeDate(Date thisDate, int year, int month,
			int day, int hour, int minute, int second) {
		Calendar calendar = Calendar.getInstance();
		if (thisDate != null) {
			calendar.setTime(thisDate);
		}
		calendar.add(Calendar.YEAR, year);
		calendar.add(Calendar.MONTH, month);
		calendar.add(Calendar.DATE, day);
		calendar.add(Calendar.HOUR, hour);
		calendar.add(Calendar.MINUTE, minute);
		calendar.add(Calendar.SECOND, second);
		return calendar;
	}

	// ~ Methods
	// ================================================================
	/**
	 * change now by set year,month,day,hour,minute and second.
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static Calendar changeDate(int year, int month, int day, int hour,
			int minute, int second) {
		return changeDate(null, year, month, day, hour, minute, second);
	}

	/**
	 * combine a end date by give year,month,day the time is 23:59:59
	 * 
	 * @param sYear
	 * @param sMonth
	 * @param sDate
	 * @return
	 */
	public static Date combineDateEnd(String sYear, String sMonth, String sDate) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(sYear));
		cal.set(Calendar.MONTH, Integer.parseInt(sMonth) - 1);
		cal.set(Calendar.DATE, Integer.parseInt(sDate));
		setEndTime(cal);
		return cal.getTime();
	}

	// ************* end day ****************
	/**
	 * combine a start date by give year,month,date, the time is 00:00:00
	 * 
	 * @param sYear
	 * @param sMonth
	 * @param sDate
	 * @return
	 */
	public static Date combineDateStart(String sYear, String sMonth,
			String sDate) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(sYear));
		cal.set(Calendar.MONTH, Integer.parseInt(sMonth) - 1);
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(sDate));
		setStartTime(cal);
		return cal.getTime();
	}

	/**
	 * Sometimes the Datetime is baneful, such as make reporting. This method is
	 * remove time info.And set 0:0:00
	 * 
	 * @param date
	 * @return
	 */
	public static Date convertDateTimeToDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		setStartTime(cal);
		Date d = cal.getTime();
		return d;
	}
	
	/**
	 * This method generates a string representation of a date/time in the
	 * format you specify on input
	 * 
	 * @param aMask
	 *            the date pattern the string is in
	 * @param strDate
	 *            a string representation of a date
	 * @return a converted Date object
	 * @see java.text.SimpleDateFormat
	 * @throws ParseException
	 */
	public static final Date convertStringToDate(String aMask, String strDate)
			throws ParseException {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);

		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '"
					+ aMask + "'");
		}

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			// log.error("ParseException: " + pe);
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return (date);
	}

	public static String fmtTodayToFive() {
		Calendar calendar = Calendar.getInstance();
		String yy = String.valueOf(calendar.get(Calendar.YEAR) % 100);
		if (yy.length() == 1) {
			yy = "0" + yy;
		}
		String mmm = String.valueOf(calendar.get(Calendar.DAY_OF_YEAR));
		if (mmm.length() == 1) {
			mmm = "00" + mmm;
		}
		if (mmm.length() == 2) {
			mmm = "0" + mmm;
		}
		return yy + mmm;
	}

	public static Date getAddDay(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, days);
		Date d = cal.getTime();
		return d;
	}

	/**
	 * This method attempts to convert an Oracle-formatted date in the form
	 * dd-MMM-yyyy to mm/dd/yyyy.
	 * 	 * @param aDate
	 *            date from database as a string
	 * @return formatted string for the ui
	 */
	public static final String getDate(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat("yyyy-MM-dd");
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	public static final String getDateTime(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat();
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * This method generates a string representation of a date's date/time in
	 * the format you specify on input
	 * 
	 * @param aMask
	 *            the date pattern the string is in
	 * @param aDate
	 *            a date object
	 * @return a formatted string representation of the date
	 * 
	 * @see java.text.SimpleDateFormat
	 */
	public static final String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			log.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	public static Date getEndOfAllTime() {
		return getEndOfThisYear();
	}

	public static Date getEndOfLastMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DATE, -1);
		setEndTime(cal);
		return cal.getTime();
	}

	/**
	 * End of last week
	 * 
	 * @return
	 */
	public static Date getEndOfLastWeek() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_YEAR, -1);
		cal.set(Calendar.DAY_OF_WEEK, 7);
		setEndTime(cal);
		return cal.getTime();
	}

	public static Date getEndOfLastYear() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_YEAR, 1);
		cal.add(Calendar.DATE, -1);
		setEndTime(cal);
		return cal.getTime();
	}

	public static Date getEndOfNext30days() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 30);
		setEndTime(cal);
		return cal.getTime();
	}

	public static Date getEndOfNext60days() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 60);
		setEndTime(cal);
		return cal.getTime();
	}

	public static Date getEndOfNext7days() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 7);
		setEndTime(cal);
		return cal.getTime();
	}

	public static Date getEndOfNextMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 2);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DATE, -1);
		setEndTime(cal);
		return cal.getTime();
	}

	public static Date getEndOfNextWeek() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		cal.set(Calendar.DAY_OF_WEEK, 7);
		setEndTime(cal);
		return cal.getTime();
	}

	public static Date getEndOfNextYear() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 2);
		cal.set(Calendar.DAY_OF_YEAR, 1);
		cal.add(Calendar.DATE, -1);
		setEndTime(cal);
		return cal.getTime();
	}

	// ********** end days ***********
	/**
	 * 
	 * @return
	 */
	public static Date getEndOfThisDay() {
		Calendar cal = Calendar.getInstance();
		setEndTime(cal);
		Date d = cal.getTime();
		return d;
	}

	public static Date getEndOfThisDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		setEndTime(cal);
		Date d = cal.getTime();
		return d;
	}

	/**
	 * get the last date of current month
	 * 
	 * @return
	 */
	public static Date getEndOfThisMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DATE, -1);
		setEndTime(cal);
		return cal.getTime();
	}

	public static Date getEndOfThisMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DATE, -1);
		setEndTime(cal);
		return cal.getTime();
	}

	public static Date getEndOfThisQuarter() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, getQuarterEndMonth(cal.getTime()));
		return getEndOfThisMonth(cal.getTime());
	}

	/**
	 * get the last date of current week
	 * 
	 * @version create by yanchaomin on 2006-5-26
	 * @return
	 */
	public static Date getEndOfThisWeek() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, 7);
		setEndTime(cal);
		return cal.getTime();
	}

	public static Date getEndOfThisWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, 7);
		setEndTime(cal);
		return cal.getTime();
	}

	/**
	 * get the last date of current year
	 * 
	 * @return
	 */
	public static Date getEndOfThisYear() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 1);
		cal.set(Calendar.DAY_OF_YEAR, 1);
		cal.add(Calendar.DATE, -1);
		setEndTime(cal);
		return cal.getTime();
	}

	public static Date getNow() {
		return new Date();
	}

	public static int getQuarterEndMonth(Date date) {
		return getQuarterStartMonth(date) + 2;
	}

	public static int getQuarterStartMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int startMonth = 0;
		switch (cal.get(Calendar.MONTH)) {
			case 0:
				startMonth = 0;
				break;
			case 1:
				startMonth = 0;
				break;
			case 2:
				startMonth = 0;
				break;
			case 3:
				startMonth = 3;
				break;
			case 4:
				startMonth = 3;
				break;
			case 5:
				startMonth = 3;
				break;
			case 6:
				startMonth = 6;
				break;
			case 7:
				startMonth = 6;
				break;
			case 8:
				startMonth = 6;
				break;
			case 9:
				startMonth = 9;
				break;
			case 10:
				startMonth = 9;
				break;
			case 11:
				startMonth = 9;
				break;

			default:
				break;
		}

		return startMonth;
	}

	public final static String getShortDateString() {
		SimpleDateFormat sdf = new SimpleDateFormat(shortDatePattern);
		return sdf.format(new Date());
	}
	
	public static Date getStartOfAllTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2005);
		return cal.getTime();
	}

	public static Date getStartOfLast30days() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -30);
		setStartTime(cal);
		return cal.getTime();
	}
	public static Date getStartOfLastday() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		setStartTime(cal);
		return cal.getTime();
	}
	public static Date getStartOfLast60days() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -60);
		setStartTime(cal);
		return cal.getTime();
	}

	public static Date getStartOfLast7days() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		setStartTime(cal);
		return cal.getTime();
	}

	// ***************** end years ****************
	// ***************** start month **************
	public static Date getStartOfLastMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		setStartTime(cal);
		return cal.getTime();
	}

	// ******* end months ***********
	// ******* start weeks **********
	/**
	 * Start of last week
	 * 
	 * @return
	 */
	public static Date getStartOfLastWeek() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_YEAR, -1);
		cal.set(Calendar.DAY_OF_WEEK, 1);
		setStartTime(cal);
		return cal.getTime();
	}

	public static Date getStartOfLastYear() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1);
		cal.set(Calendar.DAY_OF_YEAR, 1);
		setStartTime(cal);
		return cal.getTime();
	}

	public static Date getStartOfNextMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		setStartTime(cal);
		return cal.getTime();
	}

	public static Date getStartOfNextWeek() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		cal.set(Calendar.DAY_OF_WEEK, 1);
		setStartTime(cal);
		return cal.getTime();
	}

	public static Date getStartOfNextYear() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 1);
		cal.set(Calendar.DAY_OF_YEAR, 1);
		setStartTime(cal);
		return cal.getTime();
	}

	/**
	 * get the first datetime of today create by yanchaomin on 2006-5-28
	 * 
	 * @return
	 */
	public static Date getStartOfThisDay() {
		Calendar cal = Calendar.getInstance();
		setStartTime(cal);
		return cal.getTime();
	}

	public static Date getStartOfThisDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		setStartTime(cal);
		return cal.getTime();
	}

	/**
	 * get the first date of current month
	 * 
	 * @return
	 */
	public static Date getStartOfThisMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		setStartTime(cal);
		return cal.getTime();
	}

	public static Date getStartOfThisMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		setStartTime(cal);
		return cal.getTime();
	}

	public static Date getStartOfThisQuarter() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, getQuarterStartMonth(cal.getTime()));
		return getStartOfThisMonth(cal.getTime());
	}

	/**
	 * get the first date of current week
	 * 
	 * @return
	 */
	public static Date getStartOfThisWeek() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, 1);
		setStartTime(cal);
		return cal.getTime();
	}

	public static Date getStartOfThisWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, 1);
		setStartTime(cal);
		return cal.getTime();
	}

	/**
	 * get the first date of current year
	 * 
	 * @return
	 */
	public static Date getStartOfThisYear() {
		Calendar cal = Calendar.getInstance();
		setStartTime(cal);
		cal.set(Calendar.DAY_OF_YEAR, 1);
		return cal.getTime();
	}

	public static final String getTime(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat("yyyy-mm-dd");
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}
	// ************ end weeks *********
	// ************ start day *********
	public static Date getToday() {
		return new Date();
	}
	public static String getTodayStr(){
		return SimpleDateFormat_yyyyMMdd.format(new Date());
	}
	/**
	 * Returns tomorrow
	 * 
	 * @return
	 */
	public static Date getTomorrow() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 1);
		return cal.getTime();
	}

	/**
	 * Returns yesterday
	 * 
	 * @return
	 */
	public static Date getYesterday() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -1);
		return cal.getTime();
	}

	public static void main(String[] args) {
		//System.out.println(DateUtil.fmtTodayToFive());
		
		//System.out.println(DateUtil.getPassDate(1));
		try {
			System.out.println(DateUtil.format_yyyy_MM_ddHHmm(DateUtil.getPassDate(50)));
		} catch (Exception e) {
			e.printStackTrace();
		}
//		Calendar datet = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
//		datet.setTimeInMillis(968601600000L);
//		DateFormat cstFormat = new SimpleDateFormat(); 
//		DateFormat gmtFormat = new SimpleDateFormat(); 
//		TimeZone gmtTime = TimeZone.getTimeZone("GMT+9"); 
//		TimeZone cstTime = TimeZone.getTimeZone("CST");
//		cstFormat.setTimeZone(gmtTime); 
//		gmtFormat.setTimeZone(cstTime);
	}

	/**
	 * Set the calendar time to 23:59:59
	 */
	private static void setEndTime(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
	}

	/**
	 * Set the calendar time to 0:0:00
	 * 
	 * @param cal
	 */
	private static void setStartTime(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
	}
	/**
	 * 获取当前时间字符串，用于文件存储
	 * @return
	 */
	public static String getTimeStr(){
		return SimpleDateFormat_HHmmssSSS.format(new Date());
	}
	public static Date parse_yyyy_MM_dd(String date) throws ParseException{
		return DateFormat_yyyy_MM_dd.parse(date);
	}
	public static String format_yyyy_MM_dd(Date date){
		if(date!=null)
			return DateFormat_yyyy_MM_dd.format(date);
		return null;
	}
	public static String format_yyyyMMddHH(Date date){
		return SimpleDateFormat_yyyyMMddHH.format(date);
	}
	public static String format_yyyyMMdd(Date date){
		return SimpleDateFormat_yyyyMMdd.format(date);
	}
	//刷新日期转字符串 ，前台显示
	public static Date format_yyyyMMddHH(Long date) throws ParseException{
		return SimpleDateFormat_yyyyMMddHH.parse(date+"");
	}
	public static Date parse_yyyyMMddHH(String date) throws ParseException{
		return SimpleDateFormat_yyyyMMddHH.parse(date);
	}
	public static String format_yyyyMMddHHmm(Date date){
		return SimpleDateFormat_yyyyMMddHHmm.format(date);
	}
	public static Date parse_yyyyMMddHHmm(String date) throws ParseException{
		return SimpleDateFormat_yyyyMMddHHmm.parse(date);
	}
	public static String format_yyyyMMddHHmmss(Date date){
		return SimpleDateFormat_yyyyMMddHHmmss.format(date);
	}
	public static Date parse_yyyyMMddHHmmss(String date) throws ParseException{
		return SimpleDateFormat_yyyyMMddHHmmss.parse(date);
	}
	public static String format_yyyyMMddHHmmssSSS(Date date){
		return SimpleDateFormat_yyyyMMddHHmmssSSS.format(date);
	}
	public static Date parse_yyyyMMddHHmmssSSS(String date) throws ParseException{
		return SimpleDateFormat_yyyyMMddHHmmssSSS.parse(date);
	}
	public static String format_HHmmss(Date date){
		return SimpleDateFormat_HHmmss.format(date);
	}
	public static Date parse_HHmmss(String date) throws ParseException{
		return SimpleDateFormat_HHmmss.parse(date);
	}
	
	public static String format_yyyy_MM_ddHHmm(Date date){
		return DateFormat_yyyy_MM_ddHHmm.format(date);
	}
	public static Date parse_yyyy_MM_ddHHmm(String date) throws ParseException{
		return DateFormat_yyyy_MM_ddHHmm.parse(date);
	}
	public static String format_yyyy_MM_ddHH(Date date){
		return DateFormat_yyyy_MM_ddHH.format(date);
	}
	public static Date parse_yyyy_MM_ddHH(String date) throws ParseException{
		return DateFormat_yyyy_MM_ddHH.parse(date);
	}
	
	//默认精确到小时
	public static String format_DefaultHH(Date date){
		return SimpleDateFormat_DefaultHH.format(date);
	}
	//默认精确到小时
	public static Date parse__DefaultHH(String date) throws ParseException{
		return SimpleDateFormat_DefaultHH.parse(date);
	}
	//默认精确到天
	public static String format_Default(Date date){
		return SimpleDateFormat_Default.format(date);
	}
	//默认精确到天
	public static Date parse__Default(String date) throws ParseException{
		return SimpleDateFormat_Default.parse(date);
	}
	
	/**
	 * 获取特定天数之前的日期
	 */
	public static Date getPassDate(int passDay){
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(new Date());//把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, -passDay);  //设置为前一天
		return calendar.getTime();   //得到前一天的时间
	}
	/**
	 * 获取特点分钟之前 或者 之后， passMinute可以是正负数
	 */
	public static Date getPassMinute(int passMinute){
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(new Date());//把当前时间赋给日历
		calendar.add(Calendar.MINUTE, passMinute);  //设置偏移量
		return calendar.getTime();   //得到时间
	}
	public static Date getPassHour(int passHour){
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(new Date());//把当前时间赋给日历
		calendar.add(Calendar.HOUR, passHour);  //设置偏移量
		return calendar.getTime();   //得到时间
	}
	private DateUtil() {
	}
	/**
	 * 计算年龄
	 * @param data 生日
	 * @return
	 */
	public static int getAge(Date data){
		long beginTime = data.getTime();
		long endTime2= new Date().getTime();
		int year = 	(int) (((endTime2 - beginTime) / (1000 * 60 * 60 *24) + 0.5))/365;
		return year;
	}
	/**2
	 * 过去多少天
	 * @param data 日期
	 * @return
	 */
	public static int getPassDay(Date data){
		long beginTime = data.getTime();
		long endTime2= new Date().getTime();
		int day = 	(int) ((endTime2 - beginTime) / (1000 * 60 * 60 *24));
		return day;
	}
	public static int getPassMinute(Date date){
		long beginTime = date.getTime();
		long endTime2= new Date().getTime();
		int mim = 	(int) ((endTime2 - beginTime) / (1000 * 60));
		return mim;
	}
	public static int getPassSeconds(Date date){
		long beginTime = date.getTime();
		long endTime2= new Date().getTime();
		return (int)((endTime2 - beginTime) /1000);
	}
	/**
	 * 获取过期日期
	 * @param startDate 原来日期
	 * @param day 多少天     年365天， 月30天
	 * @return
	 */
	public static Date getDueDate(Date startDate,int day){
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(startDate.getTime());
		c.add(Calendar.DATE, day);//day天后的日期
		return new Date(c.getTimeInMillis()); //将c转换成Date
	}
	//---------------- 本地化日期显示格式 start 
	/**
	 * 刷新时间
	 * @throws ParseException 
	 */
	public static String showRefreshTime(Long time) throws ParseException{
		//initSDF();
		if(time != null){
			Date date = SimpleDateFormat_yyyyMMddHHmm.parse(time+"");
			return BdDateFormat_mm.format(date);
		}
		return null;
	}
	
/*	private static void initSDF(){
		if(BdDateFormat_dd == null){
			SysConfigService ss =null;
			try{
				ss = (SysConfigService)ContextUtil.getBean("sysConfigService");
			}catch(Exception e){}
			String sdf =null;
			if(ss==null)
				sdf=defaultDatePattern;
			else
				sdf = ss.getValue("ShowDate_dd");
			BdDateFormat_dd=new SimpleDateFormat(sdf);
			BdDateFormat_HH=new SimpleDateFormat(sdf+" HH");
			BdDateFormat_mm=new SimpleDateFormat(sdf+" HH:mm");
			BdDateFormat_ss=new SimpleDateFormat(sdf+" HH:mm:ss");
		}
	}*/
	/**
	 * 是不是今天
	 * @return
	 */
	public static boolean isToday(Date date){
		return format_yyyy_MM_dd(new Date()).equals(format_yyyy_MM_dd(date));
	}
	
	/**
	 * 中国版week（周一到周日）
	 */
	public static Date getStartOfThisWeekCn() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, 2);
		setStartTime(cal);
		return cal.getTime();
	}
	/**
	 * 中国版week（周一到周日）
	 * @param date
	 * @return
	 */
	public static Date getStartOfThisWeekCn(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, 2);
		setStartTime(cal);
		return cal.getTime();
	}
	
	/**
	 * Start of last week
	 * 中国版week（周一到周日）
	 * @return
	 */
	public static Date getStartOfLastWeekCn() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_YEAR, -1);
		cal.set(Calendar.DAY_OF_WEEK, 2);
		setStartTime(cal);
		return cal.getTime();
	}
	/**
	 * 中国版week（周一到周日）
	 * @return
	 */
	public static Date getStartOfNextWeekCn() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		cal.set(Calendar.DAY_OF_WEEK, 2);
		setStartTime(cal);
		return cal.getTime();
	}
	/**
	 * 中国版week（周一到周日）
	 * @return
	 */
	public static Date getEndOfThisWeekCn() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		cal.set(Calendar.DAY_OF_WEEK,1);
		setEndTime(cal);
		return cal.getTime();
	}
	/**
	 * 中国版week（周一到周日）
	 * @param date
	 * @return
	 */
	public static Date getEndOfThisWeekCn(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, 1);
		setEndTime(cal);
		return cal.getTime();
	}
	/**中国版week（周一到周日）
	 * End of last week
	 * 
	 * @return
	 */
	public static Date getEndOfLastWeekCn() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_YEAR,0);
		cal.set(Calendar.DAY_OF_WEEK, 1);
		setEndTime(cal);
		return cal.getTime();
	}
	/**
	 * 
	 * @return
	 */
	public static Date getEndOfNextWeekCn() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_YEAR, 2);
		cal.set(Calendar.DAY_OF_WEEK, 1);
		setEndTime(cal);
		return cal.getTime();
	}
//	BigDecimal hs=new BigDecimal(33.333);
//	public int getCm(int chi){
//		BigDecimal chib=new BigDecimal(chi);
//		BigDecimal jss=hs.multiply(chib);
//		return (jss.setScale(0, BigDecimal.ROUND_HALF_UP)).intValue();
////		return (int)Math.rint(jss);
//	}
//	public int getChi(int cm){
//		BigDecimal cmb=new BigDecimal(cm);
//		BigDecimal jss=cmb.divide(cmb);
//		return jss.intValue();
////		return (int)Math.rint(jss);
//	}
}
