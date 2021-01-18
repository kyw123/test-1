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
 * 用户M层 M层 ：
 * 	用于数据的持久化 职责：将数据 存到数据库中,把数据从数据库中拿出来。
 * 	继承DataBaseDao是调用父类的方法getConn()连接数据库的方法。 
 * 	用户的增删改查。
 */
public class UserDao extends BaseDao {
	/**
	 * 创建一个记录器 
	 * */
	public static Logger logger = Logger.getLogger(UserDao.class);
	/**
	 *  添加
	 * @param user
	 */
	public int add(Users user) {
		logger.info("添加用户");
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
			//将数据传进数据库中。要进行逗号拼接操作。丢进去。
			List<String> star = user.getStar(); // 获取用户明星的值。
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
			ResultSet rs = ps.getGeneratedKeys(); // 获取插入的新数据的主键
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return id ;
	}

	/**
	   * 批量添加
	 * @param list
	 */
	public void addBatch(List<Users> list) {
		logger.info("批量添加用户");
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
				List<String> star = user.getStar(); // 获取用户明星的值。
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
			ps.executeBatch(); // 批量执行
		} catch (SQLException e) {
			logger.error(e);
		}
	}

	/**
	 * 修改
	 * @param user
	 */
	public void update(Users user) {
		logger.info(" 修改用户");
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
			List<String> star = user.getStar(); // 获取用户明星的值。
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
	
	/**修改激活的字段*/
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
	 * 删除
	 * @param id
	 */
	public void delete(Integer id) {
		logger.info("删除用户");
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
	   * 查询
	 * @return List<User>
	 */
	public List<Users> query() {
		logger.info("查询用户");
		List<Users> list = new ArrayList<>();
		Connection con = getCon();
		String sql = "select * from usersJquery";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet st = ps.executeQuery();
			while (st.next()){
				Users user = new Users(); // 创建一个对象装数据
				user.setId(st.getInt("id"));
				user.setName(st.getString("name"));
				user.setAge(st.getInt("age"));
				user.setSex(st.getString("sex"));
				user.setTel(st.getString("tel"));
				//设置地址。
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
				String[] str = star.split(","); // 将值进行拆分。进行数据传进去。
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
	   * 根据ID得到对象
	 * @param id
	 * @return user 没有数据就是null
	 */
	public Users get(Integer id) {
		logger.info("根据id查询用户");
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
				String[] str = star.split(","); // 将值进行拆分。进行数据传进去。
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
	   * 通过list集合进行查询。
	 * @param pageNow 	当前是多少页数。
	 * @param pageSize 	一页显示多少条记录。
	 * @return
	   * 分页：(当前页数-1)*每页显示数据条数
	 */
	public List<Users> query(Integer pageNow,Integer pageSize) {
		logger.info("通过list集合进行查询");
		List<Users> list = new ArrayList<>();
		Connection con = getCon();
		int lnum = (pageNow-1) * pageSize;
		String sql = "select * from usersjquery limit "+lnum+","+pageSize;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet st = ps.executeQuery();
			while (st.next()) {
				Users user = new Users();// 创建一个对象装数据
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
				String[] str = star.split(","); // 将值进行拆分。进行数据传进去。
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
				list.add(user);// 讲对象装到集合中
			}
		}catch(SQLException e){
			logger.error(e);
		}
		return list;
	}
	
	/**
	   *得到全部总数据条数
	 *@return Integer
	 */
	public Integer getTotalCount(){
		logger.info("得到全部总数据条数");
		try {
			Connection con = getCon();
			String sql = "select count(*) from usersJquery";
			PreparedStatement ps = con.prepareStatement(sql);
			//执行查询方法,将结果查出来并返回到了ResultSet对象中(rs)
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e){
			logger.error(e);
		}
		return 0; //如果沒有就是0
	}
}