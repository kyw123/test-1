package com.entry;
import java.util.List;
/***
 * �û�ʵ����
* @author Administrator
*/
public class Users {
	/**�û�id*/
	private Integer id;
	/**�û�����*/
	private String name;
	/**�û��Ա�*/
	private String sex;
	/**�û�����*/
	private Integer age;
	/**�û����*/
	private Integer shengao;
	/**�û���ַ*/
	private List<String> addr;
	/**�û��绰*/
	private String tel;
	/**�û����֤*/
	private String sfz;
	/**ϲ������*/
	private List<String> star;
	/**����*/
	private String aihao;
	/**��������*/
	private String birthday;
	/**
	 * ����
	 */
	private String pawd;
	/**
	 * ȷ������
	 */
	private String confirm_password;
	/**
	 * ����
	 * @return
	 */
	private String email;
	/**
	 * �Ƿ񼤻�
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
