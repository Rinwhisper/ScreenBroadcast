/**
 * @Author       : Rinwhisper
 * @Date         : 2021-01-06 22:56:57
 * @LastEditTime : 2021-01-09 21:58:12
 * @LastEditors  : Rinwhisper
 * @FilePath     : \ScreenBroadcast\src\main\java\ScreenBroadcast\App.java
 * @Description  : 
 */

package ScreenBroadcast;

import java.io.File;
import java.net.URLDecoder;

import ScreenBroadcast.CaptureScreen.CaptureScreen;
import ScreenBroadcast.Tools.Compress;
import ScreenBroadcast.Tools.Path;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        byte[] img = CaptureScreen.capture();
        System.out.println(img.length);
        System.out.println(Compress.compress(img).length);
    }
}
