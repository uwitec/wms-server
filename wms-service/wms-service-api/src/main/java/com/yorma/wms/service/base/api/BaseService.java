package com.yorma.wms.service.base.api;

import java.util.List;

import com.googlecode.genericdao.search.ISearch;
import com.yorma.common.entity.dto.Parameter;
import com.yorma.common.entity.dto.ResponseMessage;

/**
 * 基本的操作，增、删、改、查
 * 
 * @Description:
 * @author DWL
 * @date 2016年8月25日 上午11:37:18
 * @version V1.0
 */
public interface BaseService<T> {
	/**
	 * <p>
	 * 保存操作
	 * </p>
	 * <p>
	 * 若主键[Id]有值,进行修改操作;否则,进行添加操作
	 * </p>
	 * 
	 * @param entity：需要保存的对象
	 * @return ResponseMessage对象
	 * 
	 *         <pre>
	 * 		[success:false,status:10004]:参数为null
	 * 		[success:false,status:10003,msg:异常说明信息]
	 *     	[success:false,status:10014]:保存失败
	 * 		[success:true,data:Id值]:保存成功
	 *         </pre>
	 */
	ResponseMessage save(T entity);

	/**
	 * 批处理添加
	 * 
	 * @param entitys:需要保存的对象集合
	 * @return ResponseMessage对象
	 * 
	 *         <pre>
	 * 		[success:false,status:10004]:参数为null
	 * 		[success:false,status:10003,msg:异常说明信息]
	 * 		[success:true,data:受影响行数集合(int[])]:添加成功
	 *         </pre>
	 */
	ResponseMessage createBatch(List<T> entitys);

	/**
	 * 批处理修改
	 * 
	 * @param entitys:需要保存的对象集合
	 * @return ResponseMessage对象
	 * 
	 *         <pre>
	 * 		[success:false,status:10004]:参数为null
	 * 		[success:false,status:10003,msg:异常说明信息]
	 * 		[success:true,data:受影响行数集合(int[])]:修改成功
	 *         </pre>
	 */
	ResponseMessage updateBatch(List<T> entitys);

	/**
	 * 根据主键(Id)删除对象
	 * 
	 * @param id:主键(Id)
	 * @return ResponseMessage对象
	 * 
	 *         <pre>
	 * 		[success:false,status:10004]:参数为null
	 *		[success:false,status:10003,msg:异常说明信息]
	 * 		[success:true,data:受影响行数]:删除成功
	 *         </pre>
	 */
	ResponseMessage removeById(Long id);

	/**
	 * 根据主键(Id)查询对象
	 * 
	 * @param id:主键(Id)
	 * @return ResponseMessage对象
	 * 
	 *         <pre>
	 * 		[success:false,status:10004]:参数为null
	 * 		[success:false,status:10003,msg:异常说明信息]
	 * 		[success:true,data:查询对象]
	 *         </pre>
	 */
	ResponseMessage find(Long id);

	/**
	 * 通过查询参数查询当前对象集合
	 * 
	 * @param parameters查询参数
	 * @return ResponseMessage对象
	 * 
	 *         <pre>
	 * 		[success:false,status:10003,msg:异常说明信息]
	 * 		[success:true,data:查询对象集合]
	 *         </pre>
	 */
	ResponseMessage searchParameter(List<Parameter> parameters);

	/**
	 * 通过查询参数查询当前对象集合,支持分页查询
	 * 
	 * <p>
	 * 1.不设置参数默认查询全部<br/>
	 * 2.{@link Parameter}:不设置operator属性时，默认是=，不设置connect属性时，默认是and
	 * </p>
	 * 
	 * @param parameters:查询参数
	 * @param pageNumber:页码[需要传入的是当前页-1]
	 * @param pageSize:查询的条数
	 * @return ResponseMessage对象
	 * 
	 *         <pre>
	 *         [success:false,status:10004]:参数为null
	 * 			[success:false,status:10003,msg:异常说明信息]
	 * 			[success:true,data:查询对象集合]
	 *         </pre>
	 */
	ResponseMessage searchParameter(List<Parameter> parameters, Integer pageNumber, Integer pageSize);

	/**
	 * 根据Search对象查询<br/>
	 * 
	 * @param search：Search对象
	 * @return ResponseMessage对象
	 * 
	 *         <pre>
	 * 		[success:false,status:10004]:参数为null
	 * 		[success:false,status:10003,msg:异常说明信息]
	 * 		[success:true,data:List集合]若传入的Search对象为空或maxResults的值不大于0或page的值大于等于0，则不需要分页查询、只是在单存查询;
	 *     [success:true,data:ResponseData对象]若传入的Search对象不为空、maxResults的值大于0、page的值大于等于0,则需要分页查询;
	 *         </pre>
	 */
	ResponseMessage search(ISearch search);

}
