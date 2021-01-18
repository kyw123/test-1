package com.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.tool.PropertiesUtil;
/**
 * MVC三层: 
 * 	模型（Model） 视图（View） 控制台（Controller） 
   *     简称MVC MVC应用程序被分为3个核心部件： 模型(M) 视图
 *  (V) 控制台 (C). M层：主要是数据持久化层（Dao）
 *  @author Administrator 职责：
 *  	将数据存到数据库中，把数据从数据库中拿出来。（不做任何的处理）。
 */
public class BaseDao {
	/**
	 * 创建一个记录器 
	 * */
	public static Logger logger = Logger.getLogger(BaseDao.class);
	/**
	  * 单例模式：
	  * 只创建一个数据库对象 如果对象不存在则创建对象。 如果对象存在则不创建。
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
	  *连接数据库
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException           
	 * 1、加载驱动。记得导驱动jar包。 2、连接数据库 3、 私有的方法。仅本类使用。
	 */
	private static Connection getCons() throws ClassNotFoundException, SQLException {
		logger.info("连接数据库！");
		logger.debug("连接数据库的值是否正确！"); 
		try {
			String className =PropertiesUtil.getValue("className") ;
			String url =PropertiesUtil.getValue("url") ;
			String user = PropertiesUtil.getValue("user");
			String password =PropertiesUtil.getValue("password");
			Class.forName(className); //连接的驱动。
			Connection conn = (Connection) DriverManager.getConnection(url,user,password);
			return conn;
		}catch (Exception e){
			logger.error("连接数据驱动失败："+e);
		}
		return null;
	}
}
