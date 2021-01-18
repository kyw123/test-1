package com.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import com.service.AccountService;
import com.tool.EncryptUtils;
@SuppressWarnings("serial")
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	Logger logger = Logger.getLogger(LoginServlet.class);
	/**aes密钥 static final修饰的变量。常量值是不可变的。*/
	public static final String  key= "kuangyaweikuangy";
	AccountService accountService = new AccountService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		try {
			response.setCharacterEncoding("utf-8");
			String username = request.getParameter("username");// 得到用户名
			String password = request.getParameter("password");// 得到密码
			EncryptUtils encryptUtils = new EncryptUtils();
			System.out.println("333"+username +"\t"+"333"+ password);
			String pw = encryptUtils.encryptToAES(key, password);
			System.out.println("加密后的密码：" + pw);
			String randomCode = request.getParameter("randomCode");// 得到验证码
			String hiddenCode = request.getParameter("hiddenCode");// 第一次进来hiddenCode等于空
			if (null == hiddenCode || "".equals(hiddenCode)) {// 等于空的时候
				validation(request, response, accountService, username, pw);
				return;
			}
			if (null == randomCode || "".equals(randomCode)) {
				request.setAttribute("err", 0);// 请输入验证码
				getRequest(request, response);
				return;
			}
			if (randomCode.toUpperCase().equals(request.getSession().getAttribute("rand"))) {
				validation(request, response, accountService, username, pw);
				return;
			}else{
				request.setAttribute("err", 3);// 验证码错误
				getRequest(request, response);
				return;
			}
		} catch (Exception e) {
			logger.error("加密不成功" + e);
		}
	}
	
	/**
	 * 正确进入欢迎页面。不正确则提示。*/
	private void validation(HttpServletRequest request, HttpServletResponse response, AccountService account,
		String username, String password) throws IOException, ServletException{
		int byAccountAndPasswrod = accountService.getByAccountAndPasswrod(username,password);
		if (byAccountAndPasswrod == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			response.sendRedirect("login.jsp");
			return;
		}else if(byAccountAndPasswrod == 0) {				
			request.setAttribute("err", 2);
			getRequest(request, response);
			return;
		}
	}
	
	/**
	 *如果输入错误各种原因跳会登录页面*/
	public void getRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setAttribute("hiddenCode", 1);
		request.getRequestDispatcher("start.jsp").forward(request, response);
		return;
	}
}