package com.service;
import org.apache.log4j.Logger;
import com.dao.AccountDao;
import com.entry.Account;
/**
   *账户逻辑层。
 * @author Administrator
 */
public class AccountService {
	/**
	 * getLogger( String name) 获取日志记录器，这个记录器将负责控制日志信息
	 *   创建一个新的记录器
	 * */
	public static Logger logger = Logger.getLogger(AccountService.class);
	AccountDao accountDao = new AccountDao();
	public int getByAccountAndPasswrod(String account,String password) {
		Account acc = new Account();
		acc.setAccount(account);
		acc.setPassword(password);
		System.out.println(acc.getAccount() + "\t" + acc.getPassword());
		if(account.equals(acc.getAccount()) && password.equals(acc.getPassword())) {
			return accountDao.getByAccountAndPasswrod(account, password);
		}
		return 0;
	}
}