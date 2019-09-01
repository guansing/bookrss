package dao.Impl;

import dao.BookDao;
import model.BookModel;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao
{
    private static final String ADD_BOOK = "INSERT INTO BOOK (BOOKNAME, BOOKURL, PICURL,WRITER)\n" +
            "    VALUES (?,?,?,?)";
    private static final String FIND_BOOKURL = "SELECT BOOKNAME FROM BOOK WHERE BOOKURL = ?";
    //在数据库中进行查找的sql语句
    private static final String FIND_BOOKNAME = "SELECT BOOKNAME,BOOKURL,PICURL,WRITER FROM BOOK WHERE BOOKNAME like ?";
    //在数据库中去查找全部的书单
    private static final String FIND_BOOKALL = "SELECT BOOKNAME,BOOKURL,PICURL,WRITER FROM BOOK";

    public void addBook(BookModel bookModel)
    {
        try{
            Connection connection = DBUtil.getConnection();
            //获取预处理对象
            PreparedStatement pStatement = connection.prepareStatement(ADD_BOOK);
            pStatement.setString(1, bookModel.getBookname());
            pStatement.setString(2,bookModel.getBookurl());
            pStatement.setString(3,bookModel.getPicurl());
            pStatement.setString(4,bookModel.getWriter());

            pStatement.executeUpdate();

            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<BookModel> findBookAll()
    {
        List<BookModel> bookModelList= new  ArrayList<BookModel>();
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(FIND_BOOKALL);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()){
                BookModel bookModel = new BookModel();
                bookModel.setBookname(resultSet.getString(1));
                bookModel.setBookurl(resultSet.getString(2));
                bookModel.setPicurl(resultSet.getString(3));
                bookModel.setWriter(resultSet.getString(4));

                bookModelList.add(bookModel);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection( connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return bookModelList;
    }

    public List<BookModel> findBookByName(String bookName)
    {
        List<BookModel> bookModelList = new ArrayList<BookModel>();
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(FIND_BOOKNAME);
            pStatement.setString(1,bookName);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next())
            {
                BookModel bookModel = new BookModel();
                bookModel.setBookname(resultSet.getString(1));
                bookModel.setBookurl(resultSet.getString(2));
                bookModel.setPicurl(resultSet.getString(3));
                bookModel.setWriter(resultSet.getString(4));

                bookModelList.add(bookModel);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection( connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return bookModelList;
    }


    public boolean findBookByBookUrl(String bookUrl) {
        boolean flag=false;
        try {

            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(FIND_BOOKURL);
            pStatement.setString(1, bookUrl);
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                flag=true;
             }
            else {
                flag=false;
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
