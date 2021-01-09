/**
 * @Author       : Rinwhisper
 * @Date         : 2021-01-07 15:11:06
 * @LastEditTime : 2021-01-09 20:42:13
 * @LastEditors  : Rinwhisper
 * @FilePath     : \ScreenBroadcast\src\main\java\ScreenBroadcast\Tools\Path.java
 * @Description  : 用于提供一些 path 相关的工具
 */
package ScreenBroadcast.Tools;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import ScreenBroadcast.App;



public class Path {
    /**
     * @description： 	返回项目的根路径，vs code 中为项目根目录，jar 包为 jar 包的运行目录（无论是以何种
     * 					方式运行 jar 文件，皆返回 jar 文件所在目录）
     * @param 
     * @return 			返回项目的根目录或 jar 包的运行目录
     * @exception 		UnsupportedEncodingException	解码失败
     */
    public static String getProjectOrRunRootPath() throws UnsupportedEncodingException{
		String file_path =  URLDecoder.decode(
		App.class.getProtectionDomain().getCodeSource().getLocation().getPath(), "UTF-8");

		if(file_path.endsWith(".jar")){
			// jar 文件运行，去除路径中的jar包名
			file_path = file_path.substring(0, file_path.lastIndexOf("/") + 1);
		}
		else{
			// 在 vs code 中运行，定位到项目根目录
			file_path = file_path.substring(0, file_path.indexOf("target"));
		}
		return (new File(file_path)).getAbsolutePath();
    }
}
