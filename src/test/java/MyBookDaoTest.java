import dao.MyBookDao;
import dao.Impl.MyBookDaoImpl;
import model.MyBookModel;

import java.util.List;

public class MyBookDaoTest {
    public static void main(String[] args)
    {
        //1.测试添加到我的书架
        //addMyBook();
        //2.测试移出我的书架
        //deleteMyBook();
        //3.测试查找我的书籍
        //findMyBook();
        //4.测试书籍是否存在于书架中
        existMyBook();
    }

    static void addMyBook()
    {
        MyBookDao myBookDao = new MyBookDaoImpl();
        MyBookModel myBookModel = new MyBookModel();
        myBookModel.setUsername("lgx");
        myBookModel.setBookname("西游记111");
        myBookModel.setBookurl("www.baidu.com111");
        myBookModel.setPicurl("www.334455.cn111");
        myBookModel.setWriter("曹雪芹111");


        boolean flag = myBookDao.addMyBook(myBookModel);
        System.out.println(flag);
    }

    static void deleteMyBook(){
        MyBookDao myBookDao = new MyBookDaoImpl();
        boolean flag =  myBookDao.deleteMyBook("lgx","www.baidu.com");
        System.out.println(flag);
    }

    static void findMyBook(){
        MyBookDao myBookDao = new MyBookDaoImpl();
        List<MyBookModel> myBookModelList = myBookDao.findMyBook();
        for (MyBookModel myBook : myBookModelList){
            System.out.println(myBook.getUsername() + myBook.getBookname() + myBook.getBookurl() + myBook.getPicurl() + myBook.getWriter());
        }
    }

    static void existMyBook(){
        MyBookDao myBookDao = new MyBookDaoImpl();
        boolean flag = myBookDao.existMyBook("lgx","www.baidu.com");
        System.out.println(flag);
    }
}
