package model;

public class BookModel {
    private String bookurl;
    private String picurl;//图片
    private String bookname;
    private String writer;

    public String getBookurl() {
        return bookurl;
    }

    public void setBookurl(String bookurl) {
        this.bookurl = bookurl;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void tostring()
    {
        System.out.println(this.bookurl + " " + this.picurl + " " + this.bookname + "  " + this.writer);
    }
}
