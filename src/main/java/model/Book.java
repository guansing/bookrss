package model;

import util.test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.LinkedList;
import java.util.Queue;


class point{
    public String sUrlleaf; //章节网址
    public String name; //章节名字
}

public class Book {
    public String Burl ;//本书bookurl
    public String pic;//图片picurl

    public test1 chapter;//小说

    public String sUrlroot;//开始阅读参数
     public String sUrlleaf;
    Queue<point> queue ;
    Book(String s)
    {
        Burl =s;
    }

    public void start()
    {

    }

    //进入章节
    public void enter(String s1, String s2)
    {
        chapter = new test1(s1, s2);
    } //新建test，s1代表书   s2代表章节


    public void getCatalog()
    {
        queue = new LinkedList<point>();
        try {
            //建立连接
            URL url = new URL(Burl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoInput(true);
            //httpUrlConn.setRequestMethod("GET");
            httpUrlConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //获取输入流
            InputStream input = httpUrlConn.getInputStream();
            //将字节输入流转换为字符输入流
            InputStreamReader read = new InputStreamReader(input, "utf-8");
            //为字符输入流添加缓冲
            BufferedReader br = new BufferedReader(read);
            // 读取返回结果
            String data = br.readLine();

            //开始匹配
            String REGEX = "<dd> <a style=\"\" href=\"[0-9]+";
            Pattern p = Pattern.compile(REGEX);
            String REGEX2 = "第[^a-b]+章[^a-z]+";
            Pattern p2= Pattern.compile(REGEX2);
            int count=0;


            while(data!=null) {


                //进行判断
                if (count++ >= 87) {
                    // System.out.println(data);
                    Matcher m = p.matcher(data); // 获取 matcher 对象
                    Matcher m2 = p2.matcher(data); // 获取 matcher 对象

                    if (m.find()) {

                        point ptemp = new point();

                        //System.out.println(m.group(0));
                        //替换
                        //进行多余字符替换
                        Pattern p3 = Pattern.compile("<dd> <a style=\"\" href=\"");
                        // get a matcher object
                        Matcher m3 = p3.matcher(m.group(0));
                        String s1 = m3.replaceAll("");
                        ptemp.sUrlleaf = s1;


                        //输出
                        System.out.println(s1);


                        if (m2.find()) {
                            //ystem.out.println(m2.group(0));
//进行多余字符替换
                            Pattern p4 = Pattern.compile("</");
                            // get a matcher object
                            Matcher m4 = p4.matcher(m2.group(0));
                             s1 = m4.replaceAll("");
                             ptemp.name =s1;

                             queue.add(ptemp);


                            //输出
                           System.out.println(s1);
                           // System.out.println(queue.size());
                        }

                    }



                }
                    data = br.readLine();
                }
                // 释放资源
                br.close();
                read.close();
                input.close();
                httpUrlConn.disconnect();




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    public static void main(String[]a)
    {
        Book b = new Book("https://www.lingdiankanshu.co/397048/");
        b.getCatalog();
        b.sUrlleaf = b.queue.element().sUrlleaf+".html";
        b.sUrlroot = b.Burl;
        b.enter(b.sUrlroot,b.sUrlleaf);
       b.chapter.turnLast();


    }
}
