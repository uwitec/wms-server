package com.yorma.wms.dao.base.api;

import java.sql.Connection;
import java.util.List;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;
import com.yorma.common.entity.dto.Parameter;

/**
 * 
 * @Description:基本的增、删、改、查的操作
 * @Copyright: Copyright (c) 2017 FFCS All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author DWL 2017年8月3日
 * @version 1.00.00
 * @history:
 *
 */
public interface BaseDao<T> {

	/**
	 * 保存操作
	 * <p>
	 * (id!=null):update,(id==null):create
	 * </p>
	 * 
	 * @param entity:需要保存的对象
	 * @return 受影响行数
	 */
	int save(T entity);

	/**
	 * 批处理添加
	 * 
	 * @param entitys:需要保存的对象集合
	 * @return 受影响行数集合
	 */
	int[] createBatch(List<T> entitys);

	/**
	 * 批处理修改
	 * 
	 * @param entitys:需要保存的对象集合
	 * @return 受影响行数集合
	 */
	int[] updateBatch(List<T> entitys);

	/**
	 * 根据(主键)Id查询
	 * 
	 * @param id:(主键)Id
	 * @return：查询单个对象
	 */
	T find(Long id);

	/**
	 * 根据(主键)Id删除
	 * 
	 * @param id:(主键)Id
	 * @return 删除影响行数
	 */
	int removeById(Long id);

	/**
	 * 设置连接对象
	 * 
	 * @param connection:连接对象
	 */
	void setConnection(Connection connection);

	/**
	 * 通过查询参数查询当前对象集合
	 * 
	 * <p>
	 * 使用此方法说明: <br/>
	 * 1.类名必须是驼峰命名法<br/>
	 * 2.数据库表名必须是小写加下划线
	 * </p>
	 * <p>
	 * 1.不设置参数默认查询全部<br/>
	 * 2.{@link Parameter}:不设置operator属性时，默认是=，不设置connect属性时，默认是and
	 * </p>
	 * 
	 * @param parameters:查询参数
	 * @return:当前对象集合
	 */
	List<T> searchParameter(List<Parameter> parameters);

	/**
	 * 通过查询参数查询当前对象集合,支持分页查询
	 * 
	 * <p>
	 * 使用此方法说明: <br/>
	 * 1.类名必须是驼峰命名法<br/>
	 * 2.数据库表名必须是小写加下划线
	 * </p>
	 * <p>
	 * 1.不设置参数默认查询全部<br/>
	 * 2.{@link Parameter}:不设置operator属性时，默认是=，不设置connect属性时，默认是and
	 * </p>
	 * 
	 * @param parameters:查询参数
	 * @param pageNumber:页码[需要传入的是当前页-1]
	 * @param pageSize:查询的条数
	 * @return 当前对象集合
	 */
	List<T> searchParameter(List<Parameter> parameters, Integer pageNumber, Integer pageSize);

	/**
	 * Search for objects of this type given the search parameters in the specified
	 * <code>ISearch</code> object.
	 */
	public List<T> search(ISearch search);

	/**
	 * Returns a <code>SearchResult</code> object that includes the list of results
	 * like <code>search()</code> and the total length like
	 * <code>searchLength</code>.
	 */
	public SearchResult<T> searchAndCount(ISearch search);

}
