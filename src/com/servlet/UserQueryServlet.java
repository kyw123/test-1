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
	 * ����һ����¼��*/
	public static Logger logger = Logger.getLogger(UserQueryServlet.class);
	//�����ǲ�ѯ
	UserService userService = new UserService();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		resp.setCharacterEncoding("utf-8");
		String pageSize = req.getParameter("pageSize");//ÿҳ��ʾ��������
		String pageNow = req.getParameter("pageNow");  //��ǰ�ڼ�ҳ��
		System.out.println("ÿҳ��ʾ������:" + pageSize);
		System.out.println("��ǰ�ڼ�ҳ:" + pageNow);
		List<Users> list = userService.query(Integer.valueOf(pageNow),Integer.valueOf(pageSize));
		Integer pageCount = userService.getPageCount(Integer.valueOf(pageSize));//�õ��ܹ���ҳ����
		Page<Users> page = new Page<Users>();
	  	page.setList(list);
	    page.setPageCount(pageCount);//������Ҫ�����õ���ҳ����
	    page.setPageNow(Integer.valueOf(pageNow));
	    page.setPageSize(Integer.valueOf(pageSize));
	    String jsonStr = JsonUtils.beanToJson(page); // ������ת��Ϊ��Ӧ��json��ʽ��
	    System.out.println("userQuery��ѯ��ֵ:" + jsonStr);
	    resp.getWriter().write(jsonStr);//����JSON�������ڽ�����ʾ
	}
}