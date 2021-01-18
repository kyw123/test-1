package com.entry;
import java.util.List;
/***
 * 用户实体类
* @author Administrator
*/
public class Users {
	/**用户id*/
	private Integer id;
	/**用户姓名*/
	private String name;
	/**用户性别*/
	private String sex;
	/**用户年龄*/
	private Integer age;
	/**用户身高*/
	private Integer shengao;
	/**用户地址*/
	private List<String> addr;
	/**用户电话*/
	private String tel;
	/**用户身份证*/
	private String sfz;
	/**喜爱明星*/
	private List<String> star;
	/**爱好*/
	private String aihao;
	/**出生日期*/
	private String birthday;
	/**
	 * 密码
	 */
	private String pawd;
	/**
	 * 确定密码
	 */
	private String confirm_password;
	/**
	 * 邮箱
	 * @return
	 */
	private String email;
	/**
	 * 是否激活
	 */
	private int mark;
	
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPawd() {
		return pawd;
	}
	public void setPawd(String pawd) {
		this.pawd = pawd;
	}
	public String getConfirm_password() {
		return confirm_password;
	}
	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public List<String> getAddr() {
		return addr;
	}
	public void setAddr(List<String> addr) {
		this.addr = addr;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getSfz() {
		return sfz;
	}
	public void setSfz(String sfz) {
		this.sfz = sfz;
	}
	public Integer getShengao() {
		return shengao;
	}
	public void setShengao(Integer shengao) {
		this.shengao = shengao;
	}
	public List<String> getStar() {
		return star;
	}
	public void setStar(List<String> star) {
		this.star = star;
	}
	public String getAihao() {
		return aihao;
	}
	public void setAihao(String aihao) {
		this.aihao = aihao;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
}
