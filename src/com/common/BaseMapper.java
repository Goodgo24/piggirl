package com.common;
import java.util.List;
import org.apache.ibatis.annotations.Param;
public interface BaseMapper<T,E>{
	public int deleteByPrimaryKey(Integer id);
	public int deleteByPrimaryKey(Long id);//面对长整形主键
	public int insert(T record);

    public int insertSelective(T record);

    public T selectByPrimaryKey(Integer id);
    public T selectByPrimaryKey(Long id);//面对长整形主键

    public int updateByPrimaryKeySelective(T record);

    public int updateByPrimaryKey(T record);
	/**
	 * 默认分页查询
	 * @param start 开始记录
	 * @param count 总记录
	 * @return
	 */
	public List<T> getListDef(Integer start,Integer count);
	public int countByExample(E example);
	public int deleteByExample(E example);
	public List<T> selectByExample(E example);
	public int updateByExampleSelective(@Param("record") T record, @Param("example") E example);
	public int updateByExample(@Param("record") T record, @Param("example") E example);

}