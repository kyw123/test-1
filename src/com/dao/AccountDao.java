package com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.entry.Account;
public class AccountDao extends BaseDao{
	/**
	 * getLogger( String name) 获取日志记录器，这个记录器将负责控制日志信息 创建一个新的记录器
	 */
	public static Logger logger = Logger.getLogger(AccountDao.class);
	/**
	 * 获取账户类的账号和密码
	 * @param account
	 * @param pswd
	 * @return
	 */
	public int getByAccountAndPasswrod(String account,String pswd) {
		System.out.println(account +"\t" + pswd);
		logger.info("获取账户名和密码");
		logger.debug("account and pswd");
		Account acc = null;
		int i =0;
		try{
			logger.debug("判断是否连接数据库！"); 
			Connection con = getCon();  // 连接数据库
			String sql ="select count(*),id,account,password from account1 where account = ? and password = ?GROUP BY id,account,password";
			PreparedStatement ps = con.prepareStatement(sql); // 动态预编译对象
			ps.setString(1, account);//将传进来的值设置给ps设置值 
			ps.setString(2, pswd);
			ResultSet rs = ps.executeQuery();//执行语句获取值以ResultSet对象的形式获取当前结果.executeQuery();执行sql语句。
			i = 1;
			while (rs.next()) { 
				if(i == 1) {
					acc = new Account();
					i = rs.getInt("id");
					acc.setId(Integer.valueOf(i)); // 将获取到的值设置到Account账户对象中。
					String string = rs.getString("account");
					acc.setAccount(string);
					acc.setPassword(rs.getString("password"));
					return 1;
				}
			}
		} catch (SQLException e) {
			logger.error("连接数据驱动失败：" + e);  
		}
		return 0;
	}
}