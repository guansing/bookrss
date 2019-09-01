package dao.Impl;

import dao.AccountDao;
import model.AccountModel;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class AccountDaoImpl implements AccountDao {


    private static final String REGISTER = "INSERT INTO ACCOUNT (USERNAME,PASSWORD,EMAIL)\n" +
        "    VALUES (?,?,?)";
    private static final String EXIST_USERNAME = "SELECT USERNAME FROM ACCOUNT WHERE USERNAME = ?";
    private static final String SIGNON = "SELECT USERNAME,PASSWORD FROM ACCOUNT WHERE USERNAME = ? AND PASSWORD = ?";

    private static final String UPDATE_ACCOUNT = "UPDATE ACCOUNT SET NICKNAME = ?,AGE = ?,SEX = ? WHERE USERNAME = ?";

    private static final String UPDATE_PASSWORD = "UPDATE ACCOUNT SET PASSWORD = ? WHERE USERNAME = ?";

    private static final String FIND_PASSWORD = "SELECT PASSWORD FROM ACCOUNT WHERE USERNAME = ? AND EMAIL = ?";

    public boolean signon(String username, String password)
    {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SIGNON);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean register(String username, String password, String email) {

        try{
             Connection connection = DBUtil.getConnection();

            PreparedStatement pStatement = connection.prepareStatement(REGISTER);
            pStatement.setString(1, username);
            pStatement.setString(2,password);
            pStatement.setString(3,email);


            pStatement.executeUpdate();

            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean existUsername(String username)
    {
        try {
            boolean flag=false;

            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(EXIST_USERNAME);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                flag=true;
            }else {
                  flag=false;
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
            return  flag;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAccount(AccountModel accountModel) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT);
            preparedStatement.setString(1,accountModel.getNickname());
            preparedStatement.setInt(2,accountModel.getAge());
            preparedStatement.setString(3,accountModel.getSex());
            preparedStatement.setString(4,accountModel.getUsername());
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePassword(String username, String newPassword) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD);
            preparedStatement.setString(1,newPassword);
            preparedStatement.setString(2,username);
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public String findPassword(String username, String email) {
        try {

            String result;
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_PASSWORD);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result=resultSet.getString(1);
            }else {
                result= null;
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
            return result;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
