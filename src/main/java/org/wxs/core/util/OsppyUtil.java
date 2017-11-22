package org.wxs.core.util;

import java.io.File;

/**。。1510101432
 *	操作系统工具类，
 *		获取操作系统名称，
 *		获取当前操作系统下的文件分隔符
 */
public class OsppyUtil {
	/**
	 * 获取当前操作系统的名称：Linux or Windows Or Unilx
	 */
	public static String osName(){
		return System.getProperty("os.name");
	}
	/**
	 * 获取当前操作系统下文件分隔符
	 * 	附:linux 和 unix 使用文件分隔符是相同的
	 */
	public static String osSeparator(){
		return File.separator;
	}
}
