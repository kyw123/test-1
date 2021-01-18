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
   * 过滤器实体类
 * @author Administrator
 */
public class LoginFilter implements Filter {
	public void destroy() {
		System.out.println("销毁");
	}
	//重启--先停止.再启动
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;//过滤器,核心拦截方法
		HttpServletResponse response = (HttpServletResponse) arg1;
		String url = request.getRequestURI(); //获取url地址。
		//如果我们的请求是以login.jsp结尾,就不进行拦截,
		//loginServlet 验证用户名与密码的servlet也需要放开
		if(url.endsWith("start.jsp")||url.endsWith("loginServlet")||url.endsWith("AuthImageServlet")||url.endsWith("email")||url.endsWith("active")){
			//继续执行我们请求的url
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
	// 一启动容器就会执行
	public void init(FilterConfig config) throws ServletException {
		System.out.println("初始化: " +config.getInitParameter("name"));
	}
}
