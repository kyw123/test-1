package com.servlet;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tool.MailTest;
@SuppressWarnings("serial")
@WebServlet("/email")
public class EmailServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {
			doPost(req,resp);
	};
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String id = req.getParameter("id");
		sendMail(email, Integer.valueOf(id));
	}
	public void sendMail(String email,int id){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = simpleDateFormat.format(new Date());
		 MailTest mail = new MailTest();  
        mail.setSubject("测试项目账号激活邮件");  
        String content="请点击下面的超连接进行激活<br>"
        		+ "http://localhost:8080/wcrudjquerywz/active?id=" +id+"&date="+date;
        mail.setContent(content);  
        // 收件人 可以发给其他邮箱(163等) 下同  
        mail.setTo(new String[] {email});  
        // 抄送  
        // mail.setCc(new String[] {"ljj_java@126.com"});  
        // 密送  
        // mail.setBcc(new String[] {"xxx@qq.com","xxx@qq.com"});  
        // 发送附件列表 可以写绝对路径 也可以写相对路径(起点是项目根目录)  
        // mail.setFileList(new String[] {"file\\附件1.txt","file\\附件2.txt"});  
        //发送邮件  
        try {  
            mail.sendMessage();  
            System.out.println("发送邮件成功！");  
        } catch (Exception e) {  
            System.out.println("发送邮件失败！");  
            e.printStackTrace();  
        }  
	}
}