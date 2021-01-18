package com.service;
import java.util.List;
import org.apache.log4j.Logger;
import com.dao.UserDao;
import com.entry.Users;
public class UserService extends UserDao{
	/**创建一个新的记录器*/
	public static Logger logger = Logger.getLogger(UserService.class);
	UserDao userDao = new UserDao();
	public int add(Users user) {
		logger.debug("验证add方法");
		return userDao.add(user);
	}
	public void addBatch(List<Users> list) {
		userDao.addBatch(list);
	}
	public void update(Users user) {
		userDao.update(user);
	}
	
	public void updateMark(Users user) {
		userDao.updateMark(user);
	}
	public void delete(Integer id) {
		userDao.delete(id);
	}
	
	public List<Users> query() {
		return userDao.query();
	}
	
	public Users get(Integer id) {
		return userDao.get(id);
	}
	
	/***
	 * 查询当前页数和每页显示的条数 。
	 */
	public List<Users> query(Integer pageNow,Integer pageSize) {
		return userDao.query(pageNow, pageSize);
	}
	/**
	 * 得到总页数
	 * @param pageSize 每页显示条数
	 * @return Integer 
	 * 比如：20条数据/每页显示3条。一共有6页+1页就是7页。
	 */
	public Integer getPageCount(Integer pageSize) {
		logger.info("得到总页数");
		//得到全部总数据条数
		int totalCount = userDao.getTotalCount();
		//计算总页数
		int num = totalCount % pageSize; 
		if (num == 0){ //除断了进来
			return totalCount / pageSize;
		}
		return (totalCount / pageSize)+1;
	}
}
