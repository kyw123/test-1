package com.filter;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
   * ������ʵ����
 * @author Administrator
 */
public class LoginFilter implements Filter {
	public void destroy() {
		System.out.println("����");
	}
	//����--��ֹͣ.������
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;//������,�������ط���
		HttpServletResponse response = (HttpServletResponse) arg1;
		String url = request.getRequestURI(); //��ȡurl��ַ��
		//������ǵ���������login.jsp��β,�Ͳ���������,
		//loginServlet ��֤�û����������servletҲ��Ҫ�ſ�
		if(url.endsWith("start.jsp")||url.endsWith("loginServlet")||url.endsWith("AuthImageServlet")||url.endsWith("email")||url.endsWith("active")){
			//����ִ�����������url
			chain.doFilter(request, response);
		}else{
			HttpSession session = request.getSession();
			String userName = (String) session.getAttribute("username");
			if(userName == null || userName.equals("")){
				if(url.endsWith(".jpg") || url.endsWith(".js") || url.endsWith(".css") || url.endsWith(".gif")||url.endsWith(".jsp")){
					chain.doFilter(request, response);
				}else{
					response.sendRedirect("start.jsp");
				}
			}else{
				chain.doFilter(request, response);
			}
		}
	}
	// һ���������ͻ�ִ��
	public void init(FilterConfig config) throws ServletException {
		System.out.println("��ʼ��: " +config.getInitParameter("name"));
	}
}
