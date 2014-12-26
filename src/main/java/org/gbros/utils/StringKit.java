package org.gbros.utils;

import java.text.DecimalFormat;
import java.util.List;

public class StringKit {
	public static String toString(Object obj) {

//		System.out.println("obj:"+obj);
		return obj == null
				|| obj.toString().trim().toUpperCase().equals("NULL") ? ""
				: obj.toString();
	}
	public static String toString(Object obj,boolean istrim) {
		if(istrim){
			return obj == null
					|| obj.toString().trim().toUpperCase().equals("NULL") ? ""
					: obj.toString().trim();
		}else{
			return obj == null
			|| obj.toString().trim().toUpperCase().equals("NULL") ? ""
			: obj.toString();
		}
	}

	public static boolean isEmpty(String str) {
		return ((str == null) || (str.length() == 0));
	}

	public static boolean isNotEmpty(String str) {
		if(str != null){
			str = str.trim();
		}
		return (!(isEmpty(str)));
	}

	public static boolean isBlank(String str) {
		int strLen;
		if ((str == null) || ((strLen = str.length()) == 0)) {
			return true;
		}
		for (int i = 0; i < strLen; ++i) {
			if (!(Character.isWhitespace(str.charAt(i)))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(String str) {
		return (!(isBlank(str)));
	}

	public static boolean equals(String str1, String str2) {
		return ((str1 == null) ? false : (str2 == null) ? true : str1
				.equals(str2));
	}

	/**
	 * 0,1字符转布尔型
	 * @param value
	 * @return boolean
	 */
	public static Boolean toBoolean(String value){
		if (isBlank(value)) {
			return null;
		} else {
			if ("1".equals(value) || "true".equals(value)){
				return Boolean.TRUE;
			} else if ("0".equals(value) || "false".equals(value)){
				return Boolean.FALSE;
			} else {
				throw new RuntimeException("Can not parse the parameter \"" + value + "\" to boolean value.");
			}
		}
	}
	
	/**
	 * object 转 String
	 * 
	 * @param obj
	 * @return String
	 */
	public static String convertToString(Object obj) {
		if (obj == null)
			return "";
		String str = obj.toString().trim();
		if (str.equals("null") || str.equals("NULL"))
			return "";
		return str;
	}

	/**
	 * Object转double
	 * 
	 * @param obj
	 * @return double
	 */
	public static double convertToDouble(Object obj) {
		if (obj == null)
			return 0d;
		try {
			double d = Double.parseDouble(obj.toString().trim());
			if (d == 0.0 || d == -0.0 || (d < 1E-9 && d > -1E-9))
				return 0d;
			return d;
		} catch (NumberFormatException e) {
			return 0d;
		}
	}

	public static double convertToDouble2(Object obj) {
		if (obj == null)
			return 0d;
		try {
			double d = Double.parseDouble(obj.toString().trim());
			if (d == 0.0 || d == -0.0 || (d < 1E-9 && d > -1E-9))
				return 0d;
			DecimalFormat df = new DecimalFormat("#.00");
			String temp = df.format(d);
			double result = Double.parseDouble(temp);
			return result;
		} catch (NumberFormatException e) {
			return 0d;
		}
	}

	/**
	 * 格式化double
	 * 
	 * @param obj
	 * @return
	 */
	public static String convertDoubleToString(Object obj) {
		if (obj == null)
			return "0";
		try {
			double d = Double.parseDouble(obj.toString().trim());
			if (d == 0.0 || d == -0.0 || (d < 1E-9 && d > -1E-9))
				return "0";
			DecimalFormat df = new DecimalFormat("0.00");
			String result = df.format(d);
			return result;
		} catch (RuntimeException e) {
			return "0";
		}
	}
	
	/**
	 * double转换为字符串，保留非零小数
	 * @param value
	 * @return
	 */
	public static String doubleToString(Double value){
		if (value == null)
			return "0";
		try {
			if (value == 0.0 || value == -0.0 || (value < 1E-9 && value > -1E-9)){
				return "0";
			}
			String tmpValue = String.valueOf(value);
			String subValue = tmpValue.substring(tmpValue.indexOf(".") + 1,tmpValue.length());
			if(StringKit.isNotBlank(subValue)){
				Double tmpDouble = Double.parseDouble("0." + subValue);
				if(0.0 == tmpDouble){
					return tmpValue.substring(0, tmpValue.indexOf("."));
				}
			}
			return tmpValue;
		} catch (RuntimeException e) {
			return "0";
		}
	}
	
	/**
	 * double转换double，保留非零小数
	 * @param value
	 * @return
	 */
	public static Object doubleToObject(Double value){
		if (value == null)
			return 0;
		try {
			String tmpValue = String.valueOf(value);
			String subValue = tmpValue.substring(tmpValue.indexOf(".") + 1,tmpValue.length());
			if(StringKit.isNotBlank(subValue)){
				Double tmpDouble = Double.parseDouble("0." + subValue);
				if(tmpDouble == 0){
					return Integer.parseInt(tmpValue.substring(0, tmpValue.indexOf(".")));
				}
			}
			return value;
		} catch (RuntimeException e) {
			return 0;
		}
	}

	/**
	 * 将金额转换为万元
	 * 
	 * @param obj
	 * @return
	 */
	public static String convertMoneyToWan(Object obj) {
		if (obj == null)
			return "0";
		try {
			double d = Double.parseDouble(obj.toString().trim()) / 10000;
			if (d == 0.0 || d == -0.0 || (d < 1E-9 && d > -1E-9))
				return "0";
			DecimalFormat df = new DecimalFormat("0.00");
			String result = df.format(d);
			return result;
		} catch (RuntimeException e) {
			return "0";
		}
	}

	public static boolean convertToBoolean(Object obj) {
		if (obj == null)
			return false;
		try {
			return Boolean.parseBoolean(obj.toString());
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Object转int
	 * 
	 * @param obj
	 * @return int
	 */
	public static int convertToInt(Object obj) {
		if (obj == null)
			return 0;
		try {
			int result = Integer.parseInt(obj.toString().trim());
			return result;
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	/**
	 * Object转long
	 * 
	 * @param obj
	 * @return
	 */
	public static long convertToLong(Object obj) {
		if (obj == null)
			return 0L;
		try {
			long result = Long.parseLong(obj.toString().trim());
			return result;
		} catch (NumberFormatException e) {
			return 0L;
		}
	}

	/**
	 * Object转bytes[]
	 * 
	 * @param obj
	 * @return
	 */
	public static byte[] convertToBytes(Object obj) {
		if (obj == null)
			return null;
		try {
			byte[] result = obj.toString().trim().getBytes();
			return result;
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * 将时间转换为日期
	 * 
	 * @param obj
	 * @return String
	 */
	public static String convertTimeToDate(Object obj) {
		if (obj == null)
			return "";
		String str = obj.toString().trim();
		if (str.equals("null") || str.equals("NULL"))
			return "";
		if (str.length() > 10)
			return str.substring(0, 10);
		else
			return str;
	}

	/**
	 * 将字符串的特殊字符转换为html的转义字符
	 * 
	 * @param obj
	 * @return String
	 */
	public static String convertStrToHtml(Object obj) {
		String str = convertToString(obj);
		str = str.replaceAll(" ", "&nbsp;");
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll("\'", "&#x27;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\t",
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		str = str.replaceAll("\r\n", "<br/>");
		str = str.replaceAll("\n", "<br/>");
		return str;
	}

	/**
	 * 将字符串转换为sql的in格式
	 * 
	 * @param str
	 * @return
	 */
	public static String convertStrToSqlIn(Object obj) {
		String str = convertToString(obj);
		if (str.equals(""))
			return "''";
		StringBuilder sb = new StringBuilder();
		sb.append("'");
		sb.append(str.replaceAll(",", "','"));
		sb.append("'");
		return sb.toString();
	}

	/**
	 * 将数组转成字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String convertArrayToStr(Object[] obj) {
		if (obj == null || obj.length == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		for (Object s : obj) {
			if (s == null || s.equals(""))
				continue;
			if (sb.length() > 0)
				sb.append(",");
			sb.append("'").append(s).append("'");
		}
		return sb.toString();
	}

	/**
	 * 将字符串数组转换为sql的in格式
	 * 
	 * @param ss
	 * @return
	 */
	public static String convertArrayToSqlIn(String[] ss) {
		if (ss == null || ss.length == 0)
			return "''";
		StringBuilder sb = new StringBuilder();
		for (String s : ss) {
			if (s == null || s.equals(""))
				continue;
			if (sb.length() > 0)
				sb.append(",");
			sb.append("'").append(s).append("'");
		}
		return sb.toString();
	}

	/**
	 * 将List数组转换为sql的in格式
	 * 
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String convertListToSqlIn(List list) {
		if (list == null || list.isEmpty())
			return "''";
		StringBuilder sb = new StringBuilder();
		for (Object o : list) {
			if (o == null || o.toString().trim().equals(""))
				continue;
			if (sb.length() > 0)
				sb.append(",");
			sb.append("'").append(o).append("'");
		}
		return sb.toString();
	}

//	/**
//	 * 将list,object[]转化为要求类型的数组
//	 * 
//	 * @param list
//	 * @param classType
//	 * @return
//	 */
//	public static Object[] convertObjectToArray(Object obj, Class<?> classType) {
//		if (obj == null)
//			return new Object[] { null };
//		if (obj instanceof List) {
//			List list = (List) obj;
//			if (list.size() == 0)
//				return new Object[] { null };
//			Object[] arr = new Object[list.size()];
//			for (int i = 0; i < arr.length; i++) {
//				arr[i] = ConvertUtils.convert(list.get(i), classType);
//			}
//			return arr;
//		}
//		if (obj instanceof Object[]) {
//			Object[] arr = (Object[]) obj;
//			if (arr.length == 0)
//				return new Object[] { null };
//			Object[] result = new Object[arr.length];
//			for (int i = 0; i < arr.length; i++) {
//				result[i] = ConvertUtils.convert(arr[i], classType);
//			}
//			return result;
//		}
//		if (obj instanceof String) {
//			Object[] arr = ((String) obj).split(",");
//			if (arr.length == 0)
//				return new Object[] { null };
//			Object[] result = new Object[arr.length];
//			for (int i = 0; i < arr.length; i++) {
//				result[i] = ConvertUtils.convert(arr[i], classType);
//			}
//			return result;
//		}
//		return null;
//	}

	/**
	 * 从json字符串查找值
	 * 
	 * @param jsonStr
	 * @param name
	 * @return value
	 */
	public static String queryJson(String json, String name) {
		json = json.replaceAll("[{}]", "");
		int b = json.indexOf(name);
		if (b == -1)
			return "";
		int e = json.indexOf(",", b);
		if (e == -1)
			e = json.length();
		return json.substring(json.indexOf(":", b) + 1, e);
	}
	
	/**
	 * 字符串转换为驼峰写法
	 * 如：auto-auth，转换为autoAuth
	 * 如：auto_auth，转换为autoAuth
	 */
	public static String toCamelString(String str){
		if(isNotBlank(str)){
			String tmp = "";
			int lenth = str.length();
			for(int i = 0; i < lenth; i++){
				char ch = str.charAt(i);
				if(ch == '-' || ch == '_'){
					if(i != lenth-1 && i != 0){
						char next = str.charAt(i+1);
						tmp = tmp + Character.toUpperCase(next);
						i++;
					}
				}else{
					tmp = tmp + ch;
				}
			}
			return tmp;
		}else{
			return null;
		}
	}
	
	/**
	 * 字符串转换为驼峰写法
	 * @param camelString 驼峰写法的字符串
	 * @param upperCaseFlag 是否转换为大些，默认为小写
	 * 如：autoAuth，转换为auto_auth
	 */
	public static String camelToTbFieldString(String camelString,boolean upperCaseFlag){
		if(isNotBlank(camelString)){
			String tmp = "";
			int lenth = camelString.length();
			for(int i = 0; i < lenth; i++){
				char ch = camelString.charAt(i);
				if(Character.isUpperCase(ch)){
					tmp = tmp + "_" + ch;
				}else{
					tmp = tmp + ch;
				}
			}
			if(upperCaseFlag){
				return tmp.toUpperCase();
			}else{
				return tmp.toLowerCase();
			}
		}else{
			return null;
		}
	}
	
	public static void main(String args[]){
		/*String str = "AUTO_AuTh";
		System.out.println(toCamelString(str));
		String str1 = "autoAuthThis";
		System.out.println(camelToTbFieldString(str1,false));
		System.out.println(camelToTbFieldString(str1,true));*/
		
		System.out.println(doubleToObject(125.000001));
	}
}
