package com.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.service.UserService;
@SuppressWarnings("serial")
@WebServlet("/userDelete")
public class UserDeleteServlet extends HttpServlet{
	/**
	 * 创建一个记录器 */
	public static Logger logger = Logger.getLogger(UserDeleteServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserService userService = new UserService();
		String id = (String)req.getParameter("id");
		userService.delete(Integer.parseInt(id));
	}
}
