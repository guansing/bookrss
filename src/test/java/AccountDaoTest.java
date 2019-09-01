import dao.AccountDao;
import dao.Impl.AccountDaoImpl;
import model.AccountModel;

public class AccountDaoTest {
    public static void main(String[] args)
    {
        //测试是否可以注册
        //register();
        //测试注册时用户名是否存在
        //existUsername();
        //测试用户是否登录
        //signon();
        //测试用户更改个人资料
        //updateAccount();
        //测试修改密码
        //updatePassword();
        //测试找回密码
        findPassword();
    }

    static void register()
    {
        AccountDao accountDao = new AccountDaoImpl();
        boolean flag = accountDao.register("lgx","csu666","1349621303@qq.com");
        System.out.println(flag);
    }

    static void existUsername(){
        AccountDao accountDao = new AccountDaoImpl();
        boolean flag = accountDao.existUsername("lgx");
        System.out.println(flag);
    }

    static void signon(){
        AccountDao accountDao = new AccountDaoImpl();
        boolean flag = accountDao.signon("lgx","csu6666");
        System.out.println(flag);
    }
    static void updateAccount(){
        AccountDao accountDao = new AccountDaoImpl();
        AccountModel accountModel = new AccountModel();
        accountModel.setNickname("李观星");
        accountModel.setAge(19);
        accountModel.setSex("男");
        accountModel.setUsername("lgx");

        boolean flag = accountDao.updateAccount(accountModel);
        System.out.println(flag);
    }

    static void updatePassword(){
        AccountDao accountDao = new AccountDaoImpl();

        boolean flag = accountDao.updatePassword("lgx","csu666555");
        System.out.println(flag);
    }

    static void findPassword(){
        AccountDao accountDao = new AccountDaoImpl();

        String newPassword = accountDao.findPassword("lgx","1349621303@qq.com");
        System.out.println(newPassword);
    }
}
