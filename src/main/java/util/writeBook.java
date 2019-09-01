package util;

import java.io.File;
import java.io.PrintWriter;

public class writeBook {
    writeBook()
    {
        nowChapter = new File("nowChapter.txt");
        try {
             output = new PrintWriter(nowChapter);
        } catch (Exception e) {
            System.out.println("写文件错误");
        }
    }




    public     File nowChapter;
    public PrintWriter output;

    public void write(String s){
        try {

            output.println(s);

        } catch (Exception e) {
            System.out.println("写文件错误");
        }
    }

    public static void main(String[] args) {

        writeBook a = new writeBook();
        a.write("adfasdf");
        a.write("adfasdf");


    }

    public void close()
    {
        try {

            output.close();

        } catch (Exception e) {
            System.out.println("写文件错误");
        }

    }
}