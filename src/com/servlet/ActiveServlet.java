package com.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.entry.Users;
import com.service.UserService;
@SuppressWarnings("serial")
@WebServlet("/active")
public class ActiveServlet extends HttpServlet {
	UserService userService = new UserService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		try {
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html; charset=UTF-8");
			//�Ƚ�ʱ�䣬��������涨ʱ�䣬�Ͳ��ܽ��м������Ҫ���·����ʼ�
			String id = req.getParameter("id");
			System.out.println(id);
//			DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");  
			String date1 = req.getParameter("date");//2019-09-07 19:38:20
			System.out.println(date1);
//			String format = simpleDateFormat.format(date1);
//			System.out.println(format);
//			Date newDNEate = new Date();
//			long time = newDNEate.getTime();  // ��ȡ��ǰ��ʱ�䡣
//			Date newDate = new Date();
//			newDate = simpleDateFormat.parse(format);
//			long time2 = newDate.getTime();
//			System.out.println(time + "\t"+time2);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
//		Users user = new Users();
		Users user = new Users();
		user.setId(Integer.valueOf(id));
		userService.updateMark(user);
		resp.getWriter().write("����ɹ�");
	}
}
