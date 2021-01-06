package ScreenBroadcast.Log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Log {
    private static String default_log_file = "ScreenBroadcastling/log.txt";

    //这个日志文件不关闭，等待程序结束由操作系统释放
    private static BufferedWriter fw;

    private static Calendar calendar = Calendar.getInstance();
    private static SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static{
        try{
            fw = new BufferedWriter(new FileWriter(default_log_file, true));
        }catch(IOException e){
            System.err.println(String.format("创建或打开日志文件 %s 失败!", default_log_file));
            e.printStackTrace();
            System.exit(-1);
        }
    }

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

    //一旦调用这个方法，程序在写入错误日志以后就要关闭了
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

    static String getNowDatetime(){
        return date_format.format(calendar.getTime());
    }

    

}
