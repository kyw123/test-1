package com.entry;
import java.util.ArrayList;
import java.util.List;
/**
   * 分页实体类
 * @author Administrator
 * @param <T>
 */
public class Page<T>{
	/**
	   *每页显示多少条数据*/
	private Integer pageSize;
	/**
	   *当前第几页*/
	private Integer pageNow;
	/**
	   *总数据*/
	private Integer pageCount;
	/**
	   * 一共有多少页*/
	private Integer sum;
	/**
	   * 获取对象的数据*/
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