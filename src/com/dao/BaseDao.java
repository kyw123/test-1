package com.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.tool.PropertiesUtil;
/**
 * MVC����: 
 * 	ģ�ͣ�Model�� ��ͼ��View�� ����̨��Controller�� 
   *     ���MVC MVCӦ�ó��򱻷�Ϊ3�����Ĳ����� ģ��(M) ��ͼ
 *  (V) ����̨ (C). M�㣺��Ҫ�����ݳ־û��㣨Dao��
 *  @author Administrator ְ��
 *  	�����ݴ浽���ݿ��У������ݴ����ݿ����ó������������κεĴ�����
 */
public class BaseDao {
	/**
	 * ����һ����¼�� 
	 * */
	public static Logger logger = Logger.getLogger(BaseDao.class);
	/**
	  * ����ģʽ��
	  * ֻ����һ�����ݿ���� ������󲻴����򴴽����� �����������򲻴�����
	 */
	static Connection con = null;
	public static Connection getCon() {
		if (null == con) {
			try {
				con = getCons();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return con;
	}
	/**
	  *�������ݿ�
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException           
	 * 1�������������ǵõ�����jar���� 2���������ݿ� 3�� ˽�еķ�����������ʹ�á�
	 */
	private static Connection getCons() throws ClassNotFoundException, SQLException {
		logger.info("�������ݿ⣡");
		logger.debug("�������ݿ��ֵ�Ƿ���ȷ��"); 
		try {
			String className =PropertiesUtil.getValue("className") ;
			String url =PropertiesUtil.getValue("url") ;
			String user = PropertiesUtil.getValue("user");
			String password =PropertiesUtil.getValue("password");
			Class.forName(className); //���ӵ�������
			Connection conn = (Connection) DriverManager.getConnection(url,user,password);
			return conn;
		}catch (Exception e){
			logger.error("������������ʧ�ܣ�"+e);
		}
		return null;
	}
}
