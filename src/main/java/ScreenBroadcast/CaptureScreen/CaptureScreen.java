/**
 * @Author       : Rinwhisper
 * @Date         : 2021-01-06 23:25:29
 * @LastEditTime : 2021-01-07 22:05:58
 * @LastEditors  : Rinwhisper
 * @FilePath     : \ScreenBroadcast\src\main\java\ScreenBroadcast\CaptureScreen\CaptureScreen.java
 * @Description  : 
 */
package ScreenBroadcast.CaptureScreen;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import ScreenBroadcast.Log.Log;

import java.awt.Robot;
import java.awt.AWTException;

public class CaptureScreen {
    // 获取默认屏幕尺寸
    private static Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();

    // 获取要截取的屏幕范围（默认整个屏幕）
    private static Rectangle rect = new Rectangle(screen_size);

    // 创建一个 Robot 实例
    private static Robot robot;

    private static ByteArrayOutputStream image = new ByteArrayOutputStream();

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            Log.error(new Exception("创建 Robot 实例失败!", e));
        }
    }

    /**
     * @description：   获得字节数组格式的截屏图像
     * @param {*}
     * @return          byte[] 格式
     * @exception: 
     */
    static private byte[] getByteArrayImage() {
        try {
            image.reset();
            ImageIO.write(robot.createScreenCapture(rect), "png", image);
            return image.toByteArray();
        } catch (IOException e) {
            Log.info(new Exception("截屏或填充字节数组错误!", e));
            return getByteArrayImage();//重新尝试
        }
    }

    static public byte[] capture(){
        return getByteArrayImage();
    }

     
}

