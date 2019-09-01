package dao.Impl;


import dao.MyBookDao;
import model.MyBookModel;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MyBookDaoImpl implements MyBookDao {
    private static final String ADD_MYBOOK = "INSERT INTO MYBOOK (USERNAME,BOOKURL,BOOKNAME,PICURL,WRITER) VALUES (?,?,?,?,?)";
    private static final String DELETE_MYBOOK = "DELETE FROM MYBOOK WHERE USERNAME = ? AND BOOKURL = ?";
    private static final String FIND_MYBOOK = "SELECT * FROM MYBOOK";
    private static final String EXIST_MYBOOK = "SELECT USERNAME FROM MYBOOK WHERE USERNAME = ? AND BOOKURL = ?";


    public boolean addMyBook(MyBookModel myBookModel) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_MYBOOK);
            preparedStatement.setString(1,myBookModel.getUsername());
            preparedStatement.setString(2,myBookModel.getBookurl());
            preparedStatement.setString(3,myBookModel.getBookname());
            preparedStatement.setString(4,myBookModel.getPicurl());
            preparedStatement.setString(5,myBookModel.getWriter());
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteMyBook(String userName, String bookUrl) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MYBOOK);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,bookUrl);

            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<MyBookModel> findMyBook() {
        List<MyBookModel> myBookModelList = new ArrayList<MyBookModel>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_MYBOOK);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                MyBookModel myBookModel = new MyBookModel();
                myBookModel.setUsername(resultSet.getString(1));
                myBookModel.setBookurl(resultSet.getString(2));
                myBookModel.setBookname(resultSet.getString(3));
                myBookModel.setPicurl(resultSet.getString(4));
                myBookModel.setWriter(resultSet.getString(5));
                myBookModelList.add(myBookModel);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return myBookModelList;
    }

    public boolean existMyBook(String userName, String bookUrl) {
        try{
            boolean flag=false;
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(EXIST_MYBOOK);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,bookUrl);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                flag = true;
            }
            else
            {
                flag = false;
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
            return flag;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
