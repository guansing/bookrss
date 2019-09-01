package dao;

import model.BookModel;

import java.util.List;

public interface BookDao {
    //对书籍进行添加到数据库
    void addBook(BookModel bookModel);
    //查询所有书籍
    List<BookModel> findBookAll();
    //通过书的名称模糊查询
    List<BookModel> findBookByName(String bookName);
    //用来防止数据库中的书籍数据重复，防止程序因主键的唯一性而崩溃
    boolean findBookByBookUrl(String bookUrl);
}
