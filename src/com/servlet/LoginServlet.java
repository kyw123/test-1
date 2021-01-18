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
	/**aes��Կ static final���εı���������ֵ�ǲ��ɱ�ġ�*/
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
			String username = request.getParameter("username");// �õ��û���
			String password = request.getParameter("password");// �õ�����
			EncryptUtils encryptUtils = new EncryptUtils();
			System.out.println("333"+username +"\t"+"333"+ password);
			String pw = encryptUtils.encryptToAES(key, password);
			System.out.println("���ܺ�����룺" + pw);
			String randomCode = request.getParameter("randomCode");// �õ���֤��
			String hiddenCode = request.getParameter("hiddenCode");// ��һ�ν���hiddenCode���ڿ�
			if (null == hiddenCode || "".equals(hiddenCode)) {// ���ڿյ�ʱ��
				validation(request, response, accountService, username, pw);
				return;
			}
			if (null == randomCode || "".equals(randomCode)) {
				request.setAttribute("err", 0);// ��������֤��
				getRequest(request, response);
				return;
			}
			if (randomCode.toUpperCase().equals(request.getSession().getAttribute("rand"))) {
				validation(request, response, accountService, username, pw);
				return;
			}else{
				request.setAttribute("err", 3);// ��֤�����
				getRequest(request, response);
				return;
			}
		} catch (Exception e) {
			logger.error("���ܲ��ɹ�" + e);
		}
	}
	
	/**
	 * ��ȷ���뻶ӭҳ�档����ȷ����ʾ��*/
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
	 *�������������ԭ�������¼ҳ��*/
	public void getRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setAttribute("hiddenCode", 1);
		request.getRequestDispatcher("start.jsp").forward(request, response);
		return;
	}
}