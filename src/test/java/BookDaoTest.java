import dao.BookDao;
import dao.Impl.BookDaoImpl;
import model.BookModel;

import java.util.List;

public class BookDaoTest {
    public static void main(String[] args)
    {
        //1.测试findBookAll()
        //findBookAll();

        //2.测试findBookByName()
        //findBookByName();
    }

    static void findBookByName(){
        BookDao bookDao = new BookDaoImpl();
        List<BookModel> bookModelList = bookDao.findBookByName("武%");
        for (BookModel book:bookModelList){
            book.tostring();
        }
    }

    static void findBookAll()
    {
        //1.实例化DAO层，以便可以调用数据库中写好的方法
        BookDao bookDao = new BookDaoImpl();
        //2.调用数据库的方法，并将获取的值赋值到队列中
        List<BookModel> bookModelList= bookDao.findBookAll();
        //3.foreach遍历输出队列中存储的数据
        for (BookModel book:bookModelList)
        {
            book.tostring();
        }
    }
}
