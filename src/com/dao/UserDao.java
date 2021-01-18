package com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.entry.Users;
/**
 * �û�M�� M�� ��
 * 	�������ݵĳ־û� ְ�𣺽����� �浽���ݿ���,�����ݴ����ݿ����ó�����
 * 	�̳�DataBaseDao�ǵ��ø���ķ���getConn()�������ݿ�ķ����� 
 * 	�û�����ɾ�Ĳ顣
 */
public class UserDao extends BaseDao {
	/**
	 * ����һ����¼�� 
	 * */
	public static Logger logger = Logger.getLogger(UserDao.class);
	/**
	 *  ���
	 * @param user
	 */
	public int add(Users user) {
		logger.info("����û�");
		Connection con = getCon();
		String sql = "insert into usersJquery(name,age,sex,shengao,tel,addr,sfz,star,aihao,birthday,pawd,confirm_password,email) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int id = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getName());
			ps.setInt(2, user.getAge());
			ps.setString(3, user.getSex());
			ps.setInt(4,user.getShengao());
			ps.setString(5, user.getTel());
			List<String> addr = user.getAddr();
			String join ="";
			for(int j = 0; j < addr.size(); j++) {
				join += addr.get(j)+",";
			}
			ps.setString(6, join);
			ps.setString(7, user.getSfz());
			//�����ݴ������ݿ��С�Ҫ���ж���ƴ�Ӳ���������ȥ��
			List<String> star = user.getStar(); // ��ȡ�û����ǵ�ֵ��
			String str="";
			for (int i = 0; i < star.size(); i++) {
				str += star.get(i)+",";
			}
			ps.setString(8,str);
			ps.setString(9,user.getAihao());
			ps.setString(10,user.getBirthday());
			ps.setString(11, user.getPawd());
			ps.setString(12, user.getConfirm_password());
			ps.setString(13, user.getEmail());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys(); // ��ȡ����������ݵ�����
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return id ;
	}

	/**
	   * �������
	 * @param list
	 */
	public void addBatch(List<Users> list) {
		logger.info("��������û�");
		Connection con = getCon();
		String sql = "insert into usersJquery(name,age,sex,shengao,tel,addr,sfz,star,aihao,birthday,pawd,confirm_password,email) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			for (Users user : list) {
				ps.setString(1, user.getName());
				ps.setInt(2, user.getAge());
				ps.setString(3, user.getSex());
				ps.setInt(4,user.getShengao());
				ps.setString(5, user.getTel());
				List<String> addr = user.getAddr();
				String join ="";
				for(int j = 0; j < addr.size(); j++) {
					join += addr.get(j)+",";
				}
				ps.setString(6, join);
				ps.setString(7, user.getSfz());
				List<String> star = user.getStar(); // ��ȡ�û����ǵ�ֵ��
				String str="";
				for (int i = 0; i < star.size(); i++) {
					str += star.get(i)+",";
				}
				ps.setString(8,str);
				ps.setString(9,user.getAihao());
				ps.setString(10,user.getBirthday());
				ps.setString(11, user.getPawd());
				ps.setString(12, user.getConfirm_password());
				ps.setString(13, user.getEmail());
				// ps.execute();
			}
			ps.executeBatch(); // ����ִ��
		} catch (SQLException e) {
			logger.error(e);
		}
	}

	/**
	 * �޸�
	 * @param user
	 */
	public void update(Users user) {
		logger.info(" �޸��û�");
		Connection con = getCon();
		String sql = "update usersJquery set name=?,age=?,sex=?,shengao=?,tel=?,addr=?,sfz =?,star=?,aihao=?,birthday=?,pawd=?,confirm_password=?,email=? where id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setInt(2, user.getAge());
			ps.setString(3, user.getSex());
			ps.setInt(4, user.getShengao());
			ps.setString(5, user.getTel());
			List<String> addr = user.getAddr();
			String join ="";
			for(int j = 0; j < addr.size(); j++) {
				join += addr.get(j)+",";
			}
			ps.setString(6, join);
			ps.setString(7, user.getSfz());
			List<String> star = user.getStar(); // ��ȡ�û����ǵ�ֵ��
			String str="";
			for (int i = 0; i < star.size(); i++) {
				str += star.get(i)+",";
			}
			ps.setString(8,str);
			ps.setString(9,user.getAihao());
			ps.setString(10,user.getBirthday());
			ps.setString(11, user.getPawd());
			ps.setString(12, user.getConfirm_password());
			ps.setString(13, user.getEmail());
			ps.setInt(14, user.getId());
			ps.execute();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
	
	/**�޸ļ�����ֶ�*/
	public void updateMark(Users user) {
		Connection con = getCon();
		String sql = "update usersJquery set mark = 0 where id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, user.getId());
			ps.execute();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
	
	
//	String sql = "update users set mark = 0 where id = ?";
//	Connection conn = BaseDao.getCon();
//	try {
//		PreparedStatement ps = conn.prepareStatement(sql);
//		ps.setInt(1, Integer.valueOf(id));
//		ResultSet rs = ps.executeQuery();
//		Users users = new Users();
//		while(rs.next()){
//			users.setMark(1);
//		}
//		String strJson = JsonUtils.beanToJson(users);
//		resp.getWriter().write(strJson);
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	
	/**
	 * ɾ��
	 * @param id
	 */
	public void delete(Integer id) {
		logger.info("ɾ���û�");
		Connection con = getCon();
		String sql = "delete from usersJquery where id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
	/**
	   * ��ѯ
	 * @return List<User>
	 */
	public List<Users> query() {
		logger.info("��ѯ�û�");
		List<Users> list = new ArrayList<>();
		Connection con = getCon();
		String sql = "select * from usersJquery";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet st = ps.executeQuery();
			while (st.next()){
				Users user = new Users(); // ����һ������װ����
				user.setId(st.getInt("id"));
				user.setName(st.getString("name"));
				user.setAge(st.getInt("age"));
				user.setSex(st.getString("sex"));
				user.setTel(st.getString("tel"));
				//���õ�ַ��
				String string = st.getString("addr");
				String[] str1 = string.split(",");
				List<String> listadd = new ArrayList<String>();
				for(int j = 0; j<str1.length; j++) {
					listadd.add(str1[j]);
				}
				user.setAddr(listadd);
				user.setSfz(st.getString("sfz"));
				user.setShengao(st.getInt("shengao"));
				String star = st.getString("star");
				String[] str = star.split(","); // ��ֵ���в�֡��������ݴ���ȥ��
				List<String> list2 = new ArrayList<>();
				for (int i = 0; i < str.length; i++) {
					list2.add(str[i]);
				}
				user.setStar(list2);
				user.setAihao(st.getString("aiaho"));
				user.setBirthday(st.getString("birthday"));
				user.setPawd(st.getString("pawd"));
				user.setConfirm_password(st.getString("confirm_password"));
				user.setEmail(st.getString("email"));
				list.add(user);
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return list;
	}

	/**
	   * ����ID�õ�����
	 * @param id
	 * @return user û�����ݾ���null
	 */
	public Users get(Integer id) {
		logger.info("����id��ѯ�û�");
		Users user = null;
		try {
			Connection con = getCon();
			String sql = "select * from usersJquery where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet st = ps.executeQuery();
			while (st.next()) {
				user = new Users();
				user.setId(st.getInt("id"));
				user.setName(st.getString("name"));
				user.setAge(st.getInt("age"));
				user.setSex(st.getString("sex"));
				user.setTel(st.getString("tel"));
				String string = st.getString("addr");
				String[] str1 = string.split(",");
				List<String> listadd = new ArrayList<String>();
				for(int j = 0; j<str1.length; j++) {
					listadd.add(str1[j]);
				}
				user.setAddr(listadd);
				user.setSfz(st.getString("sfz"));
				user.setShengao(st.getInt("shengao"));
				String star = st.getString("star");
				String[] str = star.split(","); // ��ֵ���в�֡��������ݴ���ȥ��
				List<String> list2 = new ArrayList<>();
				for (int i = 0; i < str.length; i++) {
					list2.add(str[i]);
				}
				user.setStar(list2);
				user.setAihao(st.getString("aihao"));
				user.setBirthday(st.getString("birthday"));
				user.setPawd(st.getString("pawd"));
				user.setConfirm_password(st.getString("confirm_password"));
				user.setEmail(st.getString("email"));
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return user;
	}
	
	/**
	   * ͨ��list���Ͻ��в�ѯ��
	 * @param pageNow 	��ǰ�Ƕ���ҳ����
	 * @param pageSize 	һҳ��ʾ��������¼��
	 * @return
	   * ��ҳ��(��ǰҳ��-1)*ÿҳ��ʾ��������
	 */
	public List<Users> query(Integer pageNow,Integer pageSize) {
		logger.info("ͨ��list���Ͻ��в�ѯ");
		List<Users> list = new ArrayList<>();
		Connection con = getCon();
		int lnum = (pageNow-1) * pageSize;
		String sql = "select * from usersjquery limit "+lnum+","+pageSize;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet st = ps.executeQuery();
			while (st.next()) {
				Users user = new Users();// ����һ������װ����
				user.setId(st.getInt("id"));
				user.setName(st.getString("name"));
				user.setAge(st.getInt("age"));
				user.setSex(st.getString("sex"));
				user.setTel(st.getString("tel"));
				String string = st.getString("addr");
				String[] str1 = string.split(",");
				List<String> listadd = new ArrayList<String>();
				for(int j = 0; j<str1.length; j++) {
					listadd.add(str1[j]);
				}
				user.setAddr(listadd);
				user.setSfz(st.getString("sfz"));
				user.setShengao(st.getInt("shengao"));
				String star = st.getString("star");
				String[] str = star.split(","); // ��ֵ���в�֡��������ݴ���ȥ��
				List<String> list2 = new ArrayList<>();
				for (int i = 0; i < str.length; i++) {
					list2.add(str[i]);
				}
				user.setStar(list2);
				user.setAihao(st.getString("aihao"));
				user.setBirthday(st.getString("birthday"));
				user.setPawd(st.getString("pawd"));
				user.setConfirm_password(st.getString("confirm_password"));
				user.setEmail(st.getString("email"));
				user.setMark(st.getInt("mark"));
				list.add(user);// ������װ��������
			}
		}catch(SQLException e){
			logger.error(e);
		}
		return list;
	}
	
	/**
	   *�õ�ȫ������������
	 *@return Integer
	 */
	public Integer getTotalCount(){
		logger.info("�õ�ȫ������������");
		try {
			Connection con = getCon();
			String sql = "select count(*) from usersJquery";
			PreparedStatement ps = con.prepareStatement(sql);
			//ִ�в�ѯ����,���������������ص���ResultSet������(rs)
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e){
			logger.error(e);
		}
		return 0; //����]�о���0
	}
}