package com.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.common.util.PageMySql;

public interface BaseService<T,E> {
	/**
	 * 根据注解删掉记录
	 * @param id
	 * @return
	 */
	public int deleteByPrimaryKey(Integer id);
	public int deleteByPrimaryKey(Long id);
	/**
	 * 插入一条记录
	 * @param record
	 * @return
	 */
	public int insert(T record);
	/**
	 * 插入一条记录 ，会进行非空判断
	 * @param record
	 * @return
	 */
	public int insertSelective(T record);
	/**
	 * 根据主键查找对象
	 * @param id
	 * @return
	 */
	public T selectByPrimaryKey(Integer id);//getById
	public T selectByPrimaryKey(Long id);//面对长整形主键
	/**
	 * 会判断record的属性是否为空，如果为空则不进行更新操作
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeySelective(T record);// update .. set... where id=56;
	/**
	 * 不判断record的属性是否为空，全部属性更新
	 */
	public int updateByPrimaryKey(T record);
	/**
	 * 默认分页查询
	 * @param start 开始记录
	 * @param count 总记录
	 * @return
	 */
	public List<T> getListDef(PageMySql pageBean);
	
	
	
	/**
	 * 默认分页查询
	 * @param pageBean
	 * @return
	 */
	public List<T> getListDefByExample(PageMySql pageBean,E example);
	/**
	 * 获取总记录数
	 * @param example
	 * @return
	 */
	public int countByExample(E example);
	/**
	 * 根据Example删除
	 * @param example
	 * @return
	 */
	public int deleteByExample(E example);
	/**
	 * 查询 没有分页
	 * @param example
	 * @return
	 */
	public List<T> selectByExample(E example);
	/**
	 * 返回全部，慎用
	 * @return
	 */
	public List<T> selectAll();
//	public T selectFirst(String column,String operator,String value);
//	public List<T> selectAll(String column,String operator,String value);
	/**
	 * 根据Example做更新操作，如果为空则不进行更新操作
	 * @param record
	 * @param example
	 * @return
	 */
	public int updateByExampleSelective(@Param("record") T record, @Param("example") E example);
	/**
	 * 根据Example做更新操作，全部属性更新
	 * @param record
	 * @param example
	 * @return
	 */
	public int updateByExample(@Param("record") T record, @Param("example") E example);
	/**
	 * 获取唯一对象
	 * @param example
	 * @return
	 */
	public T getSingle(E example);
	
	public T getObjectbyTaskId(String taskId);
	/**
	 * 同步工作流状态到数据库
	 * @param flowState
	 * @param bosid
	 */
	public void saveFlowState(String flowState, Long bosid,Short flowIsBak);
}