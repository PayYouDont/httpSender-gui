package com.payudon.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils{
	private static String[] parsePatterns = {  
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",  
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",  
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};  

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） 
	 */
	public static String getDate() {  
		return getDate("yyyy-MM-dd");  
	}  

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E" 
	 */
	public static String getDate(String pattern) {  
		return DateFormatUtils.format(new Date(), pattern);
	}  

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E" 
	 */
	public static String formatDate(Date date, Object... pattern) {  
		if (date == null) {  
			return null;  
		}  
		String formatDate = null;  
		if (pattern != null && pattern.length > 0) {  
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {  
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}  
		return formatDate;  
	}  

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss） 
	 */
	public static String formatDateTime(Date date) {  
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");  
	}  

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss） 
	 */
	public static String getTime() {  
		return formatDate(new Date(), "HH:mm:ss");  
	}  

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss） 
	 */
	public static String getDateTime() {  
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");  
	}  

	/**
	 * 得到当前年份字符串 格式（yyyy） 
	 */
	public static String getYear() {  
		return formatDate(new Date(), "yyyy");  
	}  

	/**
	 * 得到当前月份字符串 格式（MM） 
	 */
	public static String getMonth() {  
		return formatDate(new Date(), "MM");  
	}  

	/**
	 * 得到当天字符串 格式（dd） 
	 */
	public static String getDay() {  
		return formatDate(new Date(), "dd");  
	}  

	/**
	 * 得到当前星期字符串 格式（E）星期几 
	 */
	public static String getWeek() {  
		return formatDate(new Date(), "E");  
	}  

	/**
	 * 日期型字符串转化为日期 格式 
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", 
	 * "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" } 
	 */
	public static Date parseDate(Object str) {  
		if (str == null) {  
			return null;  
		}  
		try {  
			return parseDate(str.toString(), parsePatterns);  
		} catch (ParseException e) {  
			return null;  
		}  
	}  

	/**
	 * 获取过去的天数 
	 * 
	 * @param date 
	 * @return 
	 */
	public static long pastDays(Date date) {  
		long t = new Date().getTime() - date.getTime();  
		return t / (24 * 60 * 60 * 1000);  
	}  

	/**
	 * 获取过去的小时 
	 * 
	 * @param date 
	 * @return 
	 */
	public static long pastHour(Date date) {  
		long t = new Date().getTime() - date.getTime();  
		return t / (60 * 60 * 1000);  
	}  

	/**
	 * 获取过去的分钟 
	 * 
	 * @param date 
	 * @return 
	 */
	public static long pastMinutes(Date date) {  
		long t = new Date().getTime() - date.getTime();  
		return t / (60 * 1000);  
	}  

	/**
	 * 转换为时间（天,时:分:秒.毫秒） 
	 * 
	 * @param timeMillis 
	 * @return 
	 */
	public static String formatDateTime(long timeMillis) {  
		long day = timeMillis / (24 * 60 * 60 * 1000);  
		long hour = (timeMillis / (60 * 60 * 1000) - day * 24);  
		long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);  
		long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);  
		long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);  
		return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;  
	}  

	/**
	 * 获取两个日期之间的天数 
	 * 
	 * @param before 
	 * @param after 
	 * @return 
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {  
		long beforeTime = before.getTime();  
		long afterTime = after.getTime();  
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);  
	}
	/**
	 * 获取两个日期之间的分钟数
	 *
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoMinute(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60);
	}

	/**
	 * Java 8 获取两个时间之间的天数，月份数，年数
	 * @param before
	 * @param after
	 */
	public void period(LocalDate before, LocalDate after) {
		Period between = Period.between(before, after);
		between.getDays();
		between.getMonths();
		between.getYears();
	}
	/**
	 * 获取当月第一天
	 * 
	 * @return
	 */
	public static String getFirstDayOfMonth(int month) {  
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		//获取当前月第一天：  
		Calendar c = Calendar.getInstance();  
		c.set(Calendar.MONTH, month);  
		c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天  
		String first = format.format(c.getTime());  
		return first;  
	}
	
	/**
	 * 获取当月的最后一天
	 * @param month
	 * @return
	 */
	public static String getLastDayOfMonth(int month){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, month+1);
		c.set(Calendar.DAY_OF_MONTH,0);
		String last = sdf.format(c.getTime());
		return last;
	}
	

	
	/**
	 * @Description 获得一天的开始时间
	 * @return 
	 */
	public static Date getBeginTimeOfToday(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * @Description 获得一天的结束时间
	 * @return
	 */
	public static Date getEndTimeOfToday(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 *  java 8 字符串格式日期转Date
	 * @param strDate
	 * @param pattern
	 * @return
	 */
	public static LocalDate formatDate(String strDate, String pattern) {

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);

		return LocalDate.parse(strDate,dateTimeFormatter);

	}
	/**
	* @Author peiyongdong
	* @Description ( 计算2个日期是否为同一天 )
	* @Date 10:39 2019/4/11
	* @Param [date1, date2]
	* @return boolean
	**/
    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                .get(Calendar.YEAR);
        boolean isSameMonth = isSameYear
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                .get(Calendar.DAY_OF_MONTH);

        return isSameDate;
    }
}
