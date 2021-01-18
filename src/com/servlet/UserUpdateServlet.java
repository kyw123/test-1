package com.servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.entry.Users;
import com.service.UserService;
import com.tool.EncryptUtils;
import com.tool.JsonUtils;
@SuppressWarnings("serial")
@WebServlet("/userUpdate")
public class UserUpdateServlet extends HttpServlet{
	/**
	 * 创建一个记录器 
	 */
	public static Logger logger = Logger.getLogger(UserUpdateServlet.class);
	/**密钥*/
	public static final String key = "kuangyaw";
	UserService userService = new UserService();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EncryptUtils en = new EncryptUtils();
		String encryptToDES = null;
		try {
			resp.setCharacterEncoding("utf-8");
			String id = req.getParameter("updateId");
			String name = req.getParameter("updateName");
			String age = req.getParameter("updateAge");
			String sex = req.getParameter("updateSex");
			String tel = req.getParameter("updateTel");
			String addr = req.getParameter("updateAddr");
			String sfz = req.getParameter("updateSfz");
			String shengao = req.getParameter("updateShengao");
			String star = req.getParameter("updateMx");
			String aihao = req.getParameter("updateAh");
			String birthday = req.getParameter("updateBirth");
			String pawd = req.getParameter("updatePassword");
			encryptToDES = en.encryptToDES(key, pawd);
			System.out.println(encryptToDES);
			String confirm_password = req.getParameter("updateConfirm_password");
			encryptToDES = en.encryptToDES(key, confirm_password);
			System.out.println(encryptToDES);
			String email = req.getParameter("updateEmail");
			Users user = new Users();
			user.setName(name);
			user.setAge(Integer.valueOf(age));
			user.setSex(sex);
			user.setShengao(Integer.parseInt(shengao));
			String[] str1 = addr.split(",");
			List<String> list1 = new ArrayList<String>();
			for (int j = 0; j < str1.length; j++) {
				list1.add(str1[j]);
			}
			user.setAddr(list1);
			user.setTel(tel);
			user.setSfz(sfz);
			//页面传进来的值进行逗号拆分。一个个将值存进list集合中。
			String[] str = star.split(",");
			List<String>list=new ArrayList<>();
			for (int i = 0; i < str.length; i++) {
				list.add(str[i]);
			}
			user.setStar(list);
			user.setAihao(aihao);
			user.setId(Integer.valueOf(id));
			user.setBirthday(birthday);
			user.setPawd(encryptToDES);
			user.setConfirm_password(encryptToDES);
			user.setEmail(email);
			userService.update(user);
			String strJson =JsonUtils.beanToJson(user);
			resp.getWriter().write(strJson);
			System.out.println("UserUpdate修改:"+strJson);
		}catch(Exception e) {
			logger.error(e);
		}
	}
}
