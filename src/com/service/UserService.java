package com.service;
import java.util.List;
import org.apache.log4j.Logger;
import com.dao.UserDao;
import com.entry.Users;
public class UserService extends UserDao{
	/**����һ���µļ�¼��*/
	public static Logger logger = Logger.getLogger(UserService.class);
	UserDao userDao = new UserDao();
	public int add(Users user) {
		logger.debug("��֤add����");
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
	 * ��ѯ��ǰҳ����ÿҳ��ʾ������ ��
	 */
	public List<Users> query(Integer pageNow,Integer pageSize) {
		return userDao.query(pageNow, pageSize);
	}
	/**
	 * �õ���ҳ��
	 * @param pageSize ÿҳ��ʾ����
	 * @return Integer 
	 * ���磺20������/ÿҳ��ʾ3����һ����6ҳ+1ҳ����7ҳ��
	 */
	public Integer getPageCount(Integer pageSize) {
		logger.info("�õ���ҳ��");
		//�õ�ȫ������������
		int totalCount = userDao.getTotalCount();
		//������ҳ��
		int num = totalCount % pageSize; 
		if (num == 0){ //�����˽���
			return totalCount / pageSize;
		}
		return (totalCount / pageSize)+1;
	}
}
