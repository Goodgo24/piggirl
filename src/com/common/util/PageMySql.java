package com.common.util;
/**
 * 
 * @author DingDang
 */
public class PageMySql {
	private int current=1;//当前页码  用户点击时候决定的
	private int total;//总页数 1 2 3
	private int count;//总记录数     数据库查询的
	private int length=10;//每页数量 5          我们定义的
	private int begin=0;//
	private int pageShow=5;//分页显示 默认显示页面数量
	private String orderBySql=null;
	public PageMySql(){}
	public PageMySql(int current, int length) {
		super();
		this.current = current;
		this.length = length;
	}
	//用于只返回第一页的情况
	public PageMySql(Integer current, int length,int count) {
		if(current==null)current=1;
		this.current = current;
		this.length = length;
		this.setCount(count);
	}
	public int getBegin() {
		begin = getStartIndex();
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	/**
	 * 获取开始 的下标  0,5   5,10  10,15  从0开始 mysql
	 * @return
	 */
	public int getStartIndex(){
		if(current<1){
			current = 1;
		}
		return (current-1) * length;
	}
	/**
	 * 获取结束 的下标
	 * @return
	 */
	public int getEndIndex(){
		return current * length;
	}
	
	public int getPageShow() {
		return pageShow;
	}
	public void setPageShow(int pageShow) {
		this.pageShow = pageShow;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int pageItem) {
		this.length = pageItem;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
		/*
		 * 获取总页数
		 */
		int pageC = (int)(count/length);
		total = (count % length)==0?(pageC):(pageC +1);
	}
	
	public String getOrderBySql() {
		return orderBySql;
	}
	public void setOrderBySql(String orderBySql) {
		this.orderBySql = orderBySql;
	}
	/* pagebean.jsp 辅助代码 start */
	/**
	 * 获取上一批开始 id
	 * @return
	 */
	public int getUpPage(){
		int qm = current - (pageShow/2);
		if(qm<=1)
			return -1;
		qm = qm - 2;
		if(qm<1)
			return 1;
		return qm;
	}
	/**
	 * 获取下一批开始 id
	 * @return
	 */
	public int getNextPage(){
		int qm = current + (pageShow/2);
		if(qm>=total)
			return -1;
		qm = qm + 2;
		if(pageShow>=qm)
			qm = pageShow+2;
		if(qm>total)
			return total;
		return qm;
	}
	/**
	 * 获取批次的开始id
	 * @return int[0]=UpPage  1=NextPage  2=start
	 */
	public int[] getPage(){
		int[] rt = new int[3];
		int upPage = getUpPage(); 
		int nextPage = getNextPage(); 
		int start = current - (pageShow/2);
		if(upPage==-1){
			start=1;
			nextPage = pageShow+2;
			if(nextPage>total)
				nextPage=total;
		}else if(nextPage==-1){
			start=total-pageShow+1;
			upPage = start-2;
			if(upPage<1)
				upPage=1;
		}
		rt[0]=upPage;
		rt[1]=nextPage;
		rt[2]=start;
		return rt;
	}
	/* pagebean.jsp 辅助代码 end */
	
	
	@Override
	public String toString() {
		return "PageMySql [current=" + current + ", total=" + total + ", count=" + count +
				", length=" + length + "]";
	}
}