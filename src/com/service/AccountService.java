package com.service;
import org.apache.log4j.Logger;
import com.dao.AccountDao;
import com.entry.Account;
/**
   *�˻��߼��㡣
 * @author Administrator
 */
public class AccountService {
	/**
	 * getLogger( String name) ��ȡ��־��¼���������¼�������������־��Ϣ
	 *   ����һ���µļ�¼��
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