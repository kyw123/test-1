package com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.entry.Account;
public class AccountDao extends BaseDao{
	/**
	 * getLogger( String name) ��ȡ��־��¼���������¼�������������־��Ϣ ����һ���µļ�¼��
	 */
	public static Logger logger = Logger.getLogger(AccountDao.class);
	/**
	 * ��ȡ�˻�����˺ź�����
	 * @param account
	 * @param pswd
	 * @return
	 */
	public int getByAccountAndPasswrod(String account,String pswd) {
		System.out.println(account +"\t" + pswd);
		logger.info("��ȡ�˻���������");
		logger.debug("account and pswd");
		Account acc = null;
		int i =0;
		try{
			logger.debug("�ж��Ƿ��������ݿ⣡"); 
			Connection con = getCon();  // �������ݿ�
			String sql ="select count(*),id,account,password from account1 where account = ? and password = ?GROUP BY id,account,password";
			PreparedStatement ps = con.prepareStatement(sql); // ��̬Ԥ�������
			ps.setString(1, account);//����������ֵ���ø�ps����ֵ 
			ps.setString(2, pswd);
			ResultSet rs = ps.executeQuery();//ִ������ȡֵ��ResultSet�������ʽ��ȡ��ǰ���.executeQuery();ִ��sql��䡣
			i = 1;
			while (rs.next()) { 
				if(i == 1) {
					acc = new Account();
					i = rs.getInt("id");
					acc.setId(Integer.valueOf(i)); // ����ȡ����ֵ���õ�Account�˻������С�
					String string = rs.getString("account");
					acc.setAccount(string);
					acc.setPassword(rs.getString("password"));
					return 1;
				}
			}
		} catch (SQLException e) {
			logger.error("������������ʧ�ܣ�" + e);  
		}
		return 0;
	}
}