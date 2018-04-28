package com.common;
import java.util.List;
import org.apache.ibatis.annotations.Param;
public interface BaseMapperWithBLOBs<T,E> extends BaseMapper<T,E>{
	public List<T> selectByExampleWithBLOBs(E example);
	public int updateByExampleWithBLOBs(@Param("record") T record,@Param("example") E example);
	public int updateByPrimaryKeyWithBLOBs(T record);
}