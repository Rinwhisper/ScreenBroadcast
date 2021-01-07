/**
 * @Author       : Rinwhisper
 * @Date         : 2021-01-06 23:23:53
 * @LastEditTime : 2021-01-07 22:03:44
 * @LastEditors  : Rinwhisper
 * @FilePath     : \ScreenBroadcast\src\main\java\ScreenBroadcast\Log\Log.java
 * @Description  : Log.java 实现日志功能
 */
package ScreenBroadcast.Log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import ScreenBroadcast.Tools.Tools;

public class Log {
    private static String log_filename = "log.txt";

    //这个日志文件不关闭，等待程序结束由操作系统释放
    private static BufferedWriter fw;

    private static Calendar calendar = Calendar.getInstance();
    private static SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static{
        try{
            fw = new BufferedWriter(new FileWriter(
                Tools.getProjectOrRunRootPath() + "\\" + log_filename, true));
        }catch(UnsupportedEncodingException e){
                System.err.println("获得项目路径或 jar 包运行路径失败（UTF-8 解码失败）");
                e.printStackTrace();
                System.exit(-1);
        }catch(IOException e){
            System.err.println(String.format("创建或打开日志文件 %s 失败!", log_filename));
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * @description：   记录一般日志信息
     * @param           e   简单封装了一下抛出的异常
     *                      使用 e.getMessage() 获得捕获异常时确认的信息
     *                      使用 e.getCause() 获得系统抛出的原始异常
     * @return 
     * @exception：
     */
    static public synchronized void info(Exception e){
        try{
            fw.write(String.format("[%s] [info]  %s", getNowDatetime(), e.getMessage()));
            fw.newLine();
            fw.flush();
        }catch(IOException e1){
            //不知道该怎么处理...
            error(new Exception("写入日志失败", e1));
        }
    }


    /**
     * @description：   记录错误信息，一旦调用这个方法，程序在写入错误日志以后就要关闭了
     * @param           e   简单封装了一下抛出的异常
     *                      使用 e.getMessage() 获得捕获异常时确认的信息
     *                      使用 e.getCause() 获得系统抛出的原始异常
     * @return
     * @exception: 
     */
    static public synchronized void error(Exception e){
        try {
            fw.write(String.format("[%s] [error] %s", getNowDatetime(), e.getMessage()));
            fw.newLine();
            fw.write(e.getCause().toString());
            fw.newLine();
            for (StackTraceElement traceElement : e.getCause().getStackTrace()) {
                fw.write("\tat " + traceElement);
                fw.newLine();
            }
            fw.flush();
        } catch (IOException e1) {
            // 不管 fw ，让操作系统关闭， 写入失败我也没办法ヽ(ー_ー)ノ
            System.err.println("写入日志失败，请尝试清空日志文件并重启程序!");
        } finally {
            System.exit(-1);
        }
    }

    /**
     * @description：   获得当前的时间，经过格式化
     * @param {*}
     * @return          String 类型经过格式化的时间
     * @exception: 
     */
    static String getNowDatetime(){
        return date_format.format(calendar.getTime());
    }

    

}
