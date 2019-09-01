import dao.BookDao;
import dao.Impl.BookDaoImpl;
import model.BookModel;
import util.test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.String;
public class Main {
    public static test1 book;
    public String html;
    BookDao bookDao ;

    Queue<BookModel> queue ;

//    class books{
//        String url;
//        String pic;//图片
//        String writer;
//        String name;
//
//        public void tostring()
//        {
//            System.out.println(url + " " + pic + " " + name + "  " + writer);
//        }
//
//    }

    Main()
    {
        bookDao = new BookDaoImpl();
        html = "";
        int count=0;
        try {

            queue = new LinkedList<BookModel>();


            //建立连接
            URL url = new URL("https://www.lingdiankanshu.co/class/1_1.html");
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
            String REGEX = "<a href=\"/[0-9]+/\"><img src=\"[^0-9]+\" alt=\"[^a-z]+\"";
            Pattern p = Pattern.compile(REGEX);

            String REGEX2 = "</span><span class=\"s2\"><a href=\"[^a-z]+\">[^a-z]+</a></span><span class=\"s5\">[^a-z]+</span></li>";
            Pattern p2 = Pattern.compile(REGEX2);


            while(data!=null)  {
                //System.out.println(data);

                data=br.readLine();
                //System.out.println(count++);
                if(count++ >=27)
                {
                    html+=data;
                }
            }
            //System.out.println(html);

            Matcher m = p.matcher(html); // 获取 matcher 对象
            Matcher m2 = p2.matcher(html); // 获取 matcher 对象


            while (m.find())
            {
                //System.out.println(m.group(0));
                String s = m.group(0);
                String[] sArray=s.split("\"") ;
                BookModel b = new BookModel();
                b.setBookurl("https://www.lingdiankanshu.co"+ sArray[1]);
                b.setPicurl("https://www.lingdiankanshu.co"+ sArray[3]);
                b.setBookname(sArray[5]);
                queue.add(b);

            }

            while (m2.find())
            {
                //System.out.println(m2.group(0));

                String s = m2.group(0);
                s= s.substring(32);
                s= s.replaceAll("<" , "\"");
                s=s.replaceAll(">", "");
             //   System.out.println(s);

                String[] sArray=s.split("\"") ;
//                for(int i =0; i < sArray.length; i++)
//                {
//                    System.out.println(i + "  " + sArray[i]);
//                }

                BookModel b = new BookModel();
                b.setBookurl("https://www.lingdiankanshu.co"+ sArray[1]);
                b.setBookname(sArray[2]);
                b.setWriter(sArray[7]);
                queue.add(b);
            }



            for (BookModel b:queue)
            {
                b.tostring();
                if (bookDao.findBookByBookUrl(b.getBookurl()) == false){
                    bookDao.addBook(b);
                }
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

    public static void main(String [] a)
    {
        Main m = new Main();

        //book.turnLast();
    }


}
