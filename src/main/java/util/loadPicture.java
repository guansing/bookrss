package util;

import java.io.IOException;
import java.io.*;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.*;


public class loadPicture {
    public static void main(String[] a) {
        //构造URL
        try {
            URL url = new URL("http://ww4.sinaimg.cn/mw1024/005vbOHfgw1eylg2gnnrlj30ia0s87ac.jpg");

            //构造连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

//这个网站要模拟浏览器才行
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko");

//打开连接
            conn.connect();

            //打开这个网站的输入流
            InputStream inStream = conn.getInputStream();

            //用这个做中转站 ，把图片数据都放在了这里，再调用toByteArray()即可获得数据的byte数组
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//用这个是很好的，不用一次就把图片读到了文件中
//要是需要把图片用作其他用途呢？所以直接把图片的数据弄成一个变量，十分有用
//相当于操作这个变量就能操作图片了

            byte[] buf = new byte[1024];
            //为什么是1024？
//1024Byte=1KB，分配1KB的缓存
//这个就是循环读取，是一个临时空间，多大都没关系
//这没有什么大的关系，你就是用999这样的数字也没有问题，就是每次读取的最大字节数。
//byte[]的大小，说明你一次操作最大字节是多少
//虽然读的是9M的文件，其实你的内存只用1M来处理，节省了很多空间．
//当然，设得小，说明I/O操作会比较频繁，I/O操作耗时比较长，
//这多少会有点性能上的影响．这看你是想用空间换时间，还是想用时间换空间了．
//时间慢总比内存溢出程序崩溃强．如果内存足够的话，我会考虑设大点．
            int len = 0;

//读取图片数据
            while ((len = inStream.read(buf)) != -1) {
                System.out.println(len);
                outStream.write(buf, 0, len);
            }
            inStream.close();
            outStream.close();

            //把图片数据填入文件中
            File file = new File("t.jpg");

            FileOutputStream op = new FileOutputStream(file);

            op.write(outStream.toByteArray());

            op.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

