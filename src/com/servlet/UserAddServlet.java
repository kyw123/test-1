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
	 * 创建一个记录器 */
	public static Logger logger = Logger.getLogger(UserAddServlet.class);
	/**密钥*/
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
			System.out.println("pawdDes进行加密:"+decryptByDES);
			String confirm_password = req.getParameter("addConfirm_password");
			decryptByDES = en.encryptToDES(key, confirm_password);
			System.out.println("confirm_passwordDes进行加密:"+decryptByDES);
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
			//页面传进来的值进行逗号拆分。一个个将值存进list集合中。
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
			String strJson = JsonUtils.beanToJson(user);  //  将java对象List集合转换成json字符串
			System.out.println("userAdd格式："+strJson);
			resp.getWriter().write(strJson);	          //  2、response.getWriter().write(str)（页面/json）
			System.out.println("用户的值： "+id+" " + name+ " "+age +" "+ sex +" "+ shengao +" "+ tel+" "+addr+" "+ sfz+" "+ star+" "+ aihao+" " +pawd + " "+confirm_password+""+email);
		} catch (Exception e) {
			logger.error("转换不成功的异常:"+e);
		}
	}
	
	/**
	 * 邮箱方法
	 * @param email
	 * @param id
	 */
	public void sendMail(String email,int id){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = simpleDateFormat.format(new Date());
		 com.tool.MailTest mail = new com.tool.MailTest();  
        mail.setSubject("这个是标题1111111111111111");  
        String content="请点击下面的超连接进行激活<br>"
        		+ "http://localhost:8080/wcrudjquery/active?id=" +id+"&date="+date;
        mail.setContent(content);  
        //收件人 可以发给其他邮箱(163等) 下同  
        mail.setTo(new String[] {email});  
        //抄送  
       // mail.setCc(new String[] {"ljj_java@126.com"});  
        //密送  
       // mail.setBcc(new String[] {"xxx@qq.com","xxx@qq.com"});  
        //发送附件列表 可以写绝对路径 也可以写相对路径(起点是项目根目录)  
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