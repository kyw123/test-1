package com.servlet;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.entry.Users;
import com.service.UserService;
import com.tool.EncryptUtils;
import com.tool.JsonUtils;
@SuppressWarnings("serial")
@WebServlet("/userAdd")
public class UserAddServlet extends HttpServlet{
	/** 
	 * ����һ����¼�� */
	public static Logger logger = Logger.getLogger(UserAddServlet.class);
	/**��Կ*/
	public static final String key = "kuangyaw";
	UserService userService = new UserService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		doPost(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		resp.setCharacterEncoding("utf-8");
		EncryptUtils en = new  EncryptUtils();
		String decryptByDES = null;
		try {
			String name = req.getParameter("addName");
			String age = req.getParameter("addAge");
			String sex = req.getParameter("addSex");
			String shengao = req.getParameter("addShengao");
			String tel = req.getParameter("addTel");
			String addr = req.getParameter("addAddr");
			String sfz = req.getParameter("addSfz");
			String star = req.getParameter("addMx");
			String aihao = req.getParameter("addAihao");
			String birthday = req.getParameter("addBirth");
			String pawd = req.getParameter("addPassword");
			decryptByDES = en.encryptToDES(key, pawd);
			System.out.println("pawdDes���м���:"+decryptByDES);
			String confirm_password = req.getParameter("addConfirm_password");
			decryptByDES = en.encryptToDES(key, confirm_password);
			System.out.println("confirm_passwordDes���м���:"+decryptByDES);
			String email = req.getParameter("addEmail");
			Users user = new Users();
			user.setName(name);
			user.setAge(Integer.valueOf(age));
			user.setSex(sex);
			user.setShengao(Integer.valueOf(shengao));
			String[] str1 = addr.split(",");
			List<String> list1 = new ArrayList<String>();
			for (int j = 0; j < str1.length; j++) {
				list1.add(str1[j]);
			}
			user.setAddr(list1);
			user.setTel(tel);
			user.setSfz(sfz);
			//ҳ�洫������ֵ���ж��Ų�֡�һ������ֵ���list�����С�
			String[] str = star.split(",");
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < str.length; i++) {
				list.add(str[i]);
			}
			user.setStar(list);
			user.setAihao(aihao);
			user.setBirthday(birthday);
			user.setPawd(decryptByDES);
			user.setConfirm_password(decryptByDES);
			user.setEmail(email);
			int id = userService.add(user);
			user.setId(id);
			sendMail(email,id);
			String strJson = JsonUtils.beanToJson(user);  //  ��java����List����ת����json�ַ���
			System.out.println("userAdd��ʽ��"+strJson);
			resp.getWriter().write(strJson);	          //  2��response.getWriter().write(str)��ҳ��/json��
			System.out.println("�û���ֵ�� "+id+" " + name+ " "+age +" "+ sex +" "+ shengao +" "+ tel+" "+addr+" "+ sfz+" "+ star+" "+ aihao+" " +pawd + " "+confirm_password+""+email);
		} catch (Exception e) {
			logger.error("ת�����ɹ����쳣:"+e);
		}
	}
	
	/**
	 * ���䷽��
	 * @param email
	 * @param id
	 */
	public void sendMail(String email,int id){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = simpleDateFormat.format(new Date());
		 com.tool.MailTest mail = new com.tool.MailTest();  
        mail.setSubject("����Ǳ���1111111111111111");  
        String content="��������ĳ����ӽ��м���<br>"
        		+ "http://localhost:8080/wcrudjquery/active?id=" +id+"&date="+date;
        mail.setContent(content);  
        //�ռ��� ���Է�����������(163��) ��ͬ  
        mail.setTo(new String[] {email});  
        //����  
       // mail.setCc(new String[] {"ljj_java@126.com"});  
        //����  
       // mail.setBcc(new String[] {"xxx@qq.com","xxx@qq.com"});  
        //���͸����б� ����д����·�� Ҳ����д���·��(�������Ŀ��Ŀ¼)  
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