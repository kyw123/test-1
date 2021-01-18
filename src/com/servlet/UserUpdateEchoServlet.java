package com.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.entry.Users;
import com.service.UserService;
import com.tool.JsonUtils;
@WebServlet("/userUpdateEcho")
@SuppressWarnings("serial")
public class UserUpdateEchoServlet extends HttpServlet{
	UserService userService = new UserService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
		Users user = userService.get(Integer.parseInt(id)); 
		String strJson = JsonUtils.beanToJson(user);
		System.out.println("获取到的值:" + strJson);
		resp.getWriter().write(strJson);  // 将值进行返回到页面。
	}
}
