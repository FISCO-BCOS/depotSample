package org.bcos.depot.utils;

import java.math.BigInteger;

public class CommonUtils {

	public static boolean isEmpty(String str)
	{
		boolean flag = true;
		if(str != null && str.length() > 0)
		{
			flag = false;
		}
		return flag;
	}
	
	public static boolean isNotEmpty(String str)
	{
		return !isEmpty(str);
	}
	
	public static String[] split(String strKey,String regex)
	{
		String[] ret_arr = new String[]{""};
		if(isNotEmpty(strKey) && isNotEmpty(regex))
		{
			try{
				ret_arr = strKey.split(regex);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return ret_arr;
	}
	
	public static int convertInt(String param, int def_val)
	{
		int iRet = def_val;
		if(isNotEmpty(param))
		{
			try{
				iRet = Integer.parseInt(param);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return iRet;
	}
	
	public static double convertDouble(String param, double def_val)
	{
		double dRet = def_val;
		if(isNotEmpty(param))
		{
			try{
				dRet = Double.parseDouble(param);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return dRet;
	}
	
	public static long convertLong(String param, long def_val)
	{
		long lRet = def_val;
		if(isNotEmpty(param))
		{
			try{
				lRet = Long.parseLong(param);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return lRet;
	}
	
	public static BigInteger trans2BigInt(String key_id)
	{
		BigInteger b_int = BigInteger.ZERO;
		if(isNotEmpty(key_id))
		{
			int max_index = 32;
			int str_size = key_id.length();
			int for_index = Math.min(max_index, str_size);
			for(int i = 0; i < for_index;++i )
			{
				long c_int = (long)key_id.charAt(i);
				int bytes = (for_index - i - 1) * 8;
				b_int = b_int.add(BigInteger.valueOf(c_int).shiftLeft(bytes));
			}
		}
		return b_int;
	}
}
