package dao;

import model.AccountModel;

public interface AccountDao {
    //1.登录时用于匹配
    boolean signon(String username,String password);
    //2.注册账号功能：将用户的username,password,email插入到数据库中
    boolean register(String username,String password,String email);
    //3.将用户补充输入的用户信息更新到数据库中
    boolean updateAccount(AccountModel accountModel);
    //4.修改用户密码的信息
    boolean updatePassword(String username,String newPassword);
    //5.通过username和email找回用户密码
    String findPassword(String username,String email);
    //6.判断用户是否存在
    boolean existUsername(String username);
}
