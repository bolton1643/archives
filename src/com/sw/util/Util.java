package com.sw.util;
public class Util {
	  // Create an 24 byte UUID
	  protected static int count = 0;
	  /**
	   * 生成24位UUID
	   * @return UUID 24bit string
	   */
	  public static synchronized String getUUID() {
		count++;
		long time = System.currentTimeMillis();

		String uuid = "G" + Long.toHexString(time) + Integer.toHexString(count)
				+ Long.toHexString(Double.doubleToLongBits(Math.random()));

		uuid = uuid.substring(0, 24).toUpperCase();

		return uuid;
	}

	
	/**
	 * null值的改变
	 * @param in 指定字符串
	 * @return 如果指定字符串为null,返回"",否则返回本身
	 */
    public static String chgNull(String in) {
        String out = null;
        out = in;
        if (out == null || (out.trim()).equals("null")) {
            return "";
        } else {
            return out.trim();
        }
    }
    /**
     * double类型取小数点后面几位
     * @param val 指定double型数字
     * @param precision 取前几位
     * @return 转换后的double数字
     */
    public static Double roundDouble(double val, int precision) {   
        Double ret = null;   
        try {   
            double factor = Math.pow(10, precision);   
            ret = Math.floor(val * factor + 0.5) / factor;   
        } catch (Exception e) {   
            e.printStackTrace();   
        }   
  
        return ret;   
    }  
}
