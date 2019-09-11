package org.bcos.depot.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {
	private static final String DATE_FORMAT = "yyyyMMdd";
	
	
	private static final String TRANS_DATA_FORMAT = "yyyy/MM/dd hh:mm:ss";
	
	public static String formatSqlDate(String format,java.sql.Date date)
	{
		if(null == date)
		{
			return null;
		}
		if( CommonUtils.isEmpty(format))
		{
			format = DATE_FORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	public static String formatDate(String format,Date date)
	{
		if(null == date)
		{
			return null;
		}
		if( CommonUtils.isEmpty(format))
		{
			format = DATE_FORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	public static  Date format2Date(String format,String time_str)
	{
		Date date = null;
		if(CommonUtils.isEmpty(format))
		{
			format = TRANS_DATA_FORMAT;
		}
		if(CommonUtils.isNotEmpty(time_str))
		{
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			try {
				date = sdf.parse(time_str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}
	
	public static  java.sql.Date format2SqlDate(String format,String time_str)
	{
		java.sql.Date date = null;
		if(CommonUtils.isEmpty(format))
		{
			format = TRANS_DATA_FORMAT;
		}
		if(CommonUtils.isNotEmpty(time_str))
		{
			Date t_date = format2Date(format,time_str);
			if( null != t_date)
			{
				date = new java.sql.Date(t_date.getTime());
			}
		}
		return date;
	}
	
	/*
	 * 获取N天前日期，若要取得N天后则before_days设为负值
	 */
	public static  java.sql.Date format2SqlDateByDay(String format,long l_time,int before_days)
	{
		java.sql.Date date = null;
		if(CommonUtils.isEmpty(format))
		{
			format = TRANS_DATA_FORMAT;
		}
		if(l_time > 0)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(l_time - before_days*24*60*60*1000L);
			date = new java.sql.Date(calendar.getTime().getTime());
		}
		return date;
	}
	
	/*
	 * 获取N小时前日期，若要取得N天后则before_hour设为负值
	 */
	public static  java.sql.Date format2SqlDateByHour(String format,long l_time,int before_hour)
	{
		java.sql.Date date = null;
		if(CommonUtils.isEmpty(format))
		{
			format = TRANS_DATA_FORMAT;
		}
		if(l_time > 0)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(l_time - before_hour*60*60*1000L);
			date = new java.sql.Date(calendar.getTime().getTime());
		}
		return date;
	}
	
	/*
	 * 获取N分钟前日期，若要取得N天后则before_min设为负值
	 */
	public static  java.sql.Date format2SqlDateByMin(String format,long l_time,int before_min)
	{
		java.sql.Date date = null;
		if(CommonUtils.isEmpty(format))
		{
			format = TRANS_DATA_FORMAT;
		}
		if(l_time > 0)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(l_time - before_min*60*1000L);
			date = new java.sql.Date(calendar.getTime().getTime());
		}
		return date;
	}
	
	/*
	 * 获取N天前日期，若要取得N天后则before_days设为负值
	 */
	public static  Date format2DateByDay(String format,long l_time,int before_days)
	{
		Date date = null;
		if(CommonUtils.isEmpty(format))
		{
			format = TRANS_DATA_FORMAT;
		}
		if(l_time > 0)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(l_time - before_days*24*60*60*1000L);
			date = new Date(calendar.getTime().getTime());
		}
		return date;
	}
	
	/*
	 * 获取N小时前日期，若要取得N天后则before_hour设为负值
	 */
	public static  Date format2DateByHour(String format,long l_time,int before_hour)
	{
		Date date = null;
		if(CommonUtils.isEmpty(format))
		{
			format = TRANS_DATA_FORMAT;
		}
		if(l_time > 0)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(l_time - before_hour*60*60*1000L);
			date = new Date(calendar.getTime().getTime());
		}
		return date;
	}
	
	/*
	 * 获取N分钟前日期，若要取得N天后则before_min设为负值
	 */
	public static  Date format2DateByMin(String format,long l_time,int before_min)
	{
		Date date = null;
		if(CommonUtils.isEmpty(format))
		{
			format = TRANS_DATA_FORMAT;
		}
		if(l_time > 0)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(l_time - before_min*60*1000L);
			date = new Date(calendar.getTime().getTime());
		}
		return date;
	}
	
	/**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
       return Integer.parseInt(String.valueOf(between_days));           
    }    
	
	public static  Timestamp format2Timestamp(String format,String time_str)
	{
		Timestamp time = null;
		if(CommonUtils.isEmpty(format))
		{
			format = TRANS_DATA_FORMAT;
		}
		if(CommonUtils.isNotEmpty(time_str))
		{
			Date date = format2Date(format, time_str);
			if( null != date )
			{
				time = new Timestamp(date.getTime());
			}
		}
		return time;
	}
	
	public static void main(String[] args)
	{
		long curr_time = System.currentTimeMillis();
		java.sql.Date curr_date = format2SqlDateByDay("yyyyMMdd", curr_time,-1);
		Date curr_date_1 = format2DateByDay("yyyyMMdd", curr_time,-1);
		System.out.println("-1 : " + curr_date + " | " + curr_date_1);
		curr_date = format2SqlDateByDay("yyyyMMdd", curr_time,0);
		curr_date_1 = format2DateByDay("yyyyMMdd", curr_time,0);
		System.out.println("0 : " + curr_date+ " | " + curr_date_1);
		curr_date = format2SqlDateByDay("yyyyMMdd", curr_time,1);
		curr_date_1 = format2DateByDay("yyyyMMdd", curr_time,1);
		System.out.println("1 : " + curr_date+ " | " + curr_date_1);
		java.sql.Date t_date = format2SqlDate("yyyyMMdd", "20160906");
		System.out.println("t_date : " + t_date);
		System.out.println("========================HOUR==============================");
		Date curr_hour = format2DateByHour("yyyy-MM-dd hh:mm:ss",curr_time,-1);
		java.sql.Date curr_hour_1 = format2SqlDateByHour("yyyy-MM-dd hh:mm:ss", curr_time,-1);
		System.out.println("hour -1 : " + formatDate("yyyy-MM-dd hh:mm:ss",curr_hour) + " | " + curr_hour_1);
		
		curr_hour = format2DateByHour("yyyy-MM-dd hh:mm:ss",curr_time,0);
		curr_hour_1 = format2SqlDateByHour("yyyy-MM-dd hh:mm:ss", curr_time,0);
		System.out.println("hour 0 : " + formatDate("yyyy-MM-dd hh:mm:ss",curr_hour) + " | " + curr_hour_1);
		
		curr_hour = format2DateByHour("yyyy-MM-dd hh:mm:ss",curr_time,1);
		curr_hour_1 = format2SqlDateByHour("yyyy-MM-dd hh:mm:ss", curr_time,1);
		System.out.println("hour 1: " + formatDate("yyyy-MM-dd hh:mm:ss",curr_hour) + " | " + curr_hour_1);
		
		System.out.println("========================MIN==============================");
		Date curr_min = format2DateByMin("yyyy-MM-dd hh:mm:ss",curr_time,-1);
		java.sql.Date curr_min_1 = format2SqlDateByMin("yyyy-MM-dd hh:mm:ss", curr_time,-1);
		System.out.println("min -1 : " + formatDate("yyyy-MM-dd hh:mm:ss",curr_min) + " | " + curr_hour_1);
		
		curr_hour = format2DateByMin("yyyy-MM-dd hh:mm:ss",curr_time,0);
		curr_hour_1 = format2SqlDateByMin("yyyy-MM-dd hh:mm:ss", curr_time,0);
		System.out.println("min 0 : " + formatDate("yyyy-MM-dd hh:mm:ss",curr_min) + " | " + formatDate("yyyy-MM-dd hh:mm:ss",curr_hour_1));
		
		curr_min = format2DateByMin("yyyy-MM-dd hh:mm:ss",curr_time,1);
		curr_min_1 = format2SqlDateByMin("yyyy-MM-dd hh:mm:ss", curr_time,1);
		System.out.println("min 1: " + formatDate("yyyy-MM-dd hh:mm:ss",curr_min) + " | " + curr_hour_1);
		
		System.out.println("=======================CHANGE START TIME===============================");
		String start_time = "2016-10-14 00:00:30";
		curr_time = format2Date("yyyy-MM-dd HH:mm:ss",start_time).getTime();
		System.out.println("======================HOUR================================");
		curr_hour = format2DateByHour("yyyy-MM-dd HH:mm:ss",curr_time,-1);
		curr_hour_1 = format2SqlDateByHour("yyyy-MM-dd HH:mm:ss", curr_time,-1);
		System.out.println("hour -1 : " + formatDate("yyyy-MM-dd HH:mm:ss",curr_hour) + " | " + curr_hour_1);
		
		curr_hour = format2DateByHour("yyyy-MM-dd HH:mm:ss",curr_time,0);
		curr_hour_1 = format2SqlDateByHour("yyyy-MM-dd HH:mm:ss", curr_time,0);
		System.out.println("hour 0 : " + formatDate("yyyy-MM-dd HH:mm:ss",curr_hour) + " | " + curr_hour_1);
		
		curr_hour = format2DateByHour("yyyy-MM-dd HH:mm:ss",curr_time,1);
		curr_hour_1 = format2SqlDateByHour("yyyy-MM-dd HH:mm:ss", curr_time,1);
		System.out.println("hour 1: " + formatDate("yyyy-MM-dd HH:mm:ss",curr_hour) + " | " + curr_hour_1);
		System.out.println("======================MIN================================");
		curr_min = format2DateByMin("yyyy-MM-dd HH:mm:ss",curr_time,-1);
		curr_min_1 = format2SqlDateByMin("yyyy-MM-dd HH:mm:ss", curr_time,-1);
		System.out.println("min -1 : " + formatDate("yyyy-MM-dd HH:mm:ss",curr_min) + " | " + formatDate("yyyy-MM-dd HH:mm:ss",curr_min_1));
		Date check_1 = curr_min;
		curr_min = format2DateByMin("yyyy-MM-dd HH:mm:ss",curr_time,0);
		curr_min_1 = format2SqlDateByMin("yyyy-MM-dd HH:mm:ss", curr_time,0);
		System.out.println("min 0 : " + formatDate("yyyy-MM-dd HH:mm:ss",curr_min) + " | " + formatDate("yyyy-MM-dd HH:mm:ss",curr_min_1));
		
		curr_min = format2DateByMin("yyyy-MM-dd HH:mm:ss",curr_time,1);
		curr_min_1 = format2SqlDateByMin("yyyy-MM-dd HH:mm:ss", curr_time,1);
		System.out.println("min 1: " + formatDate("yyyy-MM-dd HH:mm:ss",curr_min) + " | " + formatDate("yyyy-MM-dd HH:mm:ss",curr_min_1));
		Date check_2 = curr_min;
		try {
			System.out.println(daysBetween(check_2,check_1));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("====================== before ========================");
		curr_time = System.currentTimeMillis();
		Date before_date = format2DateByMin("yyyy-MM-dd HH:mm:ss",curr_time,11);
		Date t_curr_date = format2DateByMin("yyyy-MM-dd HH:mm:ss",curr_time,15);
		System.out.println("before_date : " + before_date + " | t_curr_date : " + t_curr_date);
		Timestamp tp = format2Timestamp("yyyy-MM-dd HH:mm:ss","2016-10-18 13:12:27");
		System.out.println(tp.before(t_curr_date));
	}
}
