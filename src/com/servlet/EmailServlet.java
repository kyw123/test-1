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
        mail.setSubject("������Ŀ�˺ż����ʼ�");  
        String content="��������ĳ����ӽ��м���<br>"
        		+ "http://localhost:8080/wcrudjquerywz/active?id=" +id+"&date="+date;
        mail.setContent(content);  
        // �ռ��� ���Է�����������(163��) ��ͬ  
        mail.setTo(new String[] {email});  
        // ����  
        // mail.setCc(new String[] {"ljj_java@126.com"});  
        // ����  
        // mail.setBcc(new String[] {"xxx@qq.com","xxx@qq.com"});  
        // ���͸����б� ����д����·�� Ҳ����д���·��(�������Ŀ��Ŀ¼)  
        // mail.setFileList(new String[] {"file\\����1.txt","file\\����2.txt"});  
        //�����ʼ�  
        try {  
            mail.sendMessage();  
            System.out.println("�����ʼ��ɹ���");  
        } catch (Exception e) {  
            System.out.println("�����ʼ�ʧ�ܣ�");  
            e.printStackTrace();  
        }  
	}
}