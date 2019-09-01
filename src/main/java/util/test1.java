package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


//已经进入小说内的某一章 进行该章节的文本爬取


public class test1 {

    public void turnNext()
    {
        getNextUrl();
        write();

    }

    public void turnLast()
    {
        getLastUrl();
        write();
    }


    public void getLastUrl()//改变surlleaf的值
    {
        //获取上一章网页
        String regex1 = "<a id=\"pager_prev\" href=\"[^a-z]+[a-z]+";
        Pattern p1 = Pattern.compile(regex1);
        Matcher m1 = p1.matcher(html); // 获取 matcher 对象
        if (m1.find()) {
            System.out.println("zhaodao");
            //进行多余字符替换
            Pattern ptemp = Pattern.compile("<a id=\"pager_prev\" href=\"");
            // get a matcher object
            Matcher mtemp = ptemp.matcher(m1.group(0));
            String stemp = mtemp.replaceAll("");
            System.out.println(stemp);
            sUrlleaf = stemp;
        }
    }

    public void getNextUrl()
    {
        //获取下一章
        String regex1 = "<a id=\"pager_next\" href=\"[^a-z]+[a-z]+";
        Pattern p1 = Pattern.compile(regex1);
        Matcher m1 = p1.matcher(html); // 获取 matcher 对象
        if (m1.find()) {
            System.out.println("zhaodao");
            //进行多余字符替换
            Pattern ptemp = Pattern.compile("<a id=\"pager_next\" href=\"");
            // get a matcher object
            Matcher mtemp = ptemp.matcher(m1.group(0));
            String stemp =    mtemp.replaceAll("");
            System.out.println(stemp);
            sUrlleaf = stemp;


        }
    }


            String html;
             String sUrlroot;
             String sUrlleaf;
             String last;
             String next;

            public test1(String s1, String s2) {


                sUrlroot = "https://www.lingdiankanshu.co/388851/";
                sUrlleaf = "2260821.html";

                sUrlroot = s1;
                sUrlleaf = s2;
                write();



            }


            public void write()
            {
                 writeBook nowC = new writeBook();




                try {
                    //建立连接


                    String sUrl = sUrlroot + sUrlleaf;

                    URL url = new URL(sUrl);
                    HttpURLConnection httpUrlConn1 = (HttpURLConnection) url.openConnection();
                    httpUrlConn1.setDoInput(true);



                    httpUrlConn1.setRequestMethod("GET");


                    httpUrlConn1.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

                    //获取输入流
                    InputStream input = httpUrlConn1.getInputStream();
                    //将字节输入流转换为字符输入流
                    InputStreamReader read = new InputStreamReader(input, "utf-8");
                    //为字符输入流添加缓冲
                    BufferedReader br = new BufferedReader(read);
                    // 读取返回结果
                    String data = br.readLine();

                    String REGEX = "br />[^a-b]+<";
                    String REGEX2 = "br />[^a-b]+$";
                    Pattern p = Pattern.compile(REGEX);
                    Pattern p2 = Pattern.compile(REGEX2);

                    while(data!=null)  {
                        //System.out.println(data);
                        html+=data;

                        int count=0;
                        Matcher m = p.matcher(data); // 获取 matcher 对象
                        Matcher m2 = p2.matcher(data); // 获取 matcher 对象

                        try {




                            while (m.find()) {
                                // System.out.println(m.group(0));

                                //进行多余字符替换
                                Pattern p3 = Pattern.compile("br />|<");
                                // get a matcher object
                                Matcher m3 = p3.matcher(m.group(0));
                                String s1 = m3.replaceAll("");


                                //输出
//                                System.out.println(m.group(0));
//
//                                System.out.println(s1);
                                nowC.write(s1);

                                 //System.out.println(count++);
                            }


                            //System.out.println(m2.group(0));
                            Pattern p4 = Pattern.compile("br />");
                            // get a matcher object
                            while (m2.find()) {
                                Matcher m3 = p4.matcher(m2.group(0));

                                String s1 = m3.replaceAll("");
                                //输出
                                //System.out.println(s1);
                                nowC.write(s1);



                            }



                        }
                        catch (Exception e) {
                            System.out.println("写文件错误");
                        }







                        data=br.readLine();
                    }

                    nowC.close();
                    // 释放资源
                    br.close();
                    read.close();
                    input.close();
                    httpUrlConn1.disconnect();


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


}