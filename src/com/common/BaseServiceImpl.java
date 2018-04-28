package com.common;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.InitializingBean;

import com.common.util.PageMySql;

public abstract class BaseServiceImpl<T,E> implements BaseService<T,E>,InitializingBean{
	public BaseMapper<T,E> baseMapper=null;
	protected final transient Log logger=LogFactory.getLog(getClass());
	public abstract void initService();
	private static final PageMySql OneItemPage=new PageMySql(1,1,1);
	public int deleteByPrimaryKey(Integer id){
		return baseMapper.deleteByPrimaryKey(id);
	}
	public int deleteByPrimaryKey(Long id){
		return baseMapper.deleteByPrimaryKey(id);
	}
	public int insert(T record){
		return baseMapper.insert(record);
	}
	public int insertSelective(T record){
		return baseMapper.insertSelective(record);
	}
	public T selectByPrimaryKey(Integer id){
		return baseMapper.selectByPrimaryKey(id);
	}
	public T selectByPrimaryKey(Long id){
		return baseMapper.selectByPrimaryKey(id);
	}
	public int updateByPrimaryKeySelective(T record){
		return baseMapper.updateByPrimaryKeySelective(record);
	}
	public int updateByPrimaryKey(T record){
		return baseMapper.updateByPrimaryKey(record);
	}
	@Override
	public List<T> getListDef(PageMySql pageBean) {
		return baseMapper.getListDef(pageBean.getStartIndex(), pageBean.getLength());
	}
	public List<T> getListDefByExample(PageMySql pageBean,E example){
		if(pageBean.getCount()<1){
			pageBean.setCount(countByExample(example));
		}
//		((BaseExample)example).setPage(pageBean);//设置pageBean
		return selectByExample(example);
	}
	public int countByExample(E example){
		return baseMapper.countByExample(example);
	}
	public int deleteByExample(E example){
		return baseMapper.deleteByExample(example);
	}
	public List<T> selectByExample(E example){
		return baseMapper.selectByExample(example);
	}
	public List<T> selectAll(){
		return baseMapper.selectByExample(null);
	}
//	public T selectFirst(String column,String operator,String value){
//		
//	}
//	public List<T> selectAll(String column,String operator,String value){
//		
//	}
	public int updateByExampleSelective(@Param("record") T record, @Param("example") E example){
		return baseMapper.updateByExampleSelective(record, example);
	}
	public int updateByExample(@Param("record") T record, @Param("example") E example){
		return baseMapper.updateByExample(record, example);
	}
	/**
	 * 单条数据，使用单条数据分页，加快搜索速度
	 * @return
	 */
	public PageMySql getOneItemPage(){
		return OneItemPage;
	}
	public T getSingle(E example){
		List<T> list = baseMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		initService();
		if(baseMapper==null)
			logger.warn("Generic dao is not set in BaseServiceImpl's child class! initService");
	}
	public T getObjectbyTaskId(String taskId){
		return null;
	}
	@Override
	public void saveFlowState(String flowState, Long bosid,Short flowIsBak){
		//设计工作流的service重写该方法
	}
}