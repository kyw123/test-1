package com.entry;
import java.util.ArrayList;
import java.util.List;
/**
   * ��ҳʵ����
 * @author Administrator
 * @param <T>
 */
public class Page<T>{
	/**
	   *ÿҳ��ʾ����������*/
	private Integer pageSize;
	/**
	   *��ǰ�ڼ�ҳ*/
	private Integer pageNow;
	/**
	   *������*/
	private Integer pageCount;
	/**
	   * һ���ж���ҳ*/
	private Integer sum;
	/**
	   * ��ȡ���������*/
	private List<T> list = new ArrayList<T>();
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNow() {
		return pageNow;
	}
	public void setPageNow(Integer pageNow) {
		this.pageNow = pageNow;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getSum() {
		return sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
}