package dao;

import model.MyBookModel;

import java.util.List;


public interface MyBookDao {
    //添加到我的书架
    boolean addMyBook(MyBookModel myBookModel);
    //移出我的书架
    boolean deleteMyBook(String userName,String bookUrl);
    //查询我的书架
    List<MyBookModel> findMyBook();
    //判断书架中是否存在已有收藏
    boolean existMyBook(String userName,String bookUrl);
}
