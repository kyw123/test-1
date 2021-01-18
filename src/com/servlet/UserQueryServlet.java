package com.servlet;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.entry.Page;
import com.entry.Users;
import com.service.UserService;
import com.tool.JsonUtils;
@WebServlet("/userQuery")
@SuppressWarnings("serial")
public class UserQueryServlet extends HttpServlet{
	/**
	 * 创建一个记录器*/
	public static Logger logger = Logger.getLogger(UserQueryServlet.class);
	//这里是查询
	UserService userService = new UserService();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		resp.setCharacterEncoding("utf-8");
		String pageSize = req.getParameter("pageSize");//每页显示的条数。
		String pageNow = req.getParameter("pageNow");  //当前第几页。
		System.out.println("每页显示的条数:" + pageSize);
		System.out.println("当前第几页:" + pageNow);
		List<Users> list = userService.query(Integer.valueOf(pageNow),Integer.valueOf(pageSize));
		Integer pageCount = userService.getPageCount(Integer.valueOf(pageSize));//得到总共的页数。
		Page<Users> page = new Page<Users>();
	  	page.setList(list);
	    page.setPageCount(pageCount);//这里需要计算拿到总页数。
	    page.setPageNow(Integer.valueOf(pageNow));
	    page.setPageSize(Integer.valueOf(pageSize));
	    String jsonStr = JsonUtils.beanToJson(page); // 将对象转换为对应的json格式。
	    System.out.println("userQuery查询的值:" + jsonStr);
	    resp.getWriter().write(jsonStr);//返回JSON数据用于界面显示
	}
}