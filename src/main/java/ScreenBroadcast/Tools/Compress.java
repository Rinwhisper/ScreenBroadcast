/**
 * @Author       : Rinwhisper
 * @Date         : 2021-01-09 20:57:31
 * @LastEditTime : 2021-01-09 21:23:39
 * @LastEditors  : Rinwhisper
 * @FilePath     : \ScreenBroadcast\src\main\java\ScreenBroadcast\Tools\Compress.java
 * @Description  : Compress 主要提供压缩和解压缩功能
 *                 使用了 Snappy 库，因为它有较快的压缩速度、解压缩速度和较低的 CPU 使用率
 */
package ScreenBroadcast.Tools;

import java.io.IOException;

import org.xerial.snappy.Snappy;

import ScreenBroadcast.Log.Log;

public class Compress {
    /**
     * @description：       压缩
     * @param               src  待压缩的源数据
     * @return              byte[]格式，压缩之后的数据；
     *                      若压缩失败，返回空数组     
     * @exception: 
     */
    public static byte[] compress(byte[] src) {
        try {
            return Snappy.compress(src);
        } catch (IOException e) {
            Log.info(new Exception("图像压缩失败", e));
            return new byte[0];
        }
    }

    /**
     * @description：       解压缩
     * @param               src 待解压的数据
     * @return              byte[]格式，解压之后的数据；
     *                      若解压失败，返回空数组
     * @exception: 
     */
    public static byte[] uncompress(byte[] src){
        try{
            return Snappy.uncompress(src);
        }catch(IOException e){
            Log.info(new Exception("图像解压缩失败", e));
            return new byte[0];
        }
    }
}

