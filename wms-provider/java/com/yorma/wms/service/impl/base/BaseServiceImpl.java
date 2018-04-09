package com.yorma.wms.service.impl.base;

import java.lang.reflect.Field;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;

import com.yorma.common.entity.dto.Parameter;
import com.yorma.common.entity.dto.ResponseData;
import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.dao.base.api.BaseDao;
import com.yorma.wms.service.base.api.BaseService;



/**
 * 基本的操作，增、删、改、查
 * 
 * @Description:
 * @author DWL
 * @date 2017年7月4日 上午8:13:35
 * @version V1.0
 */
public class BaseServiceImpl<T> implements BaseService<T> {

	private static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

	private BaseDao<T> baseDao;

	protected BaseServiceImpl() { 
//		if (null == baseDao) {
//			throw new NullPointerException("baseDao is Null");
//		}
	}

	public BaseServiceImpl(BaseDao<T> baseDao) {
		if (null == baseDao) {
			throw new NullPointerException("parameter[baseDao] is Null");
		}
		this.baseDao = baseDao;
	}

	protected void setBaseDao(BaseDao<T> baseDao) {
		if (null == baseDao) {
			throw new NullPointerException("parameter[baseDao] is Null");
		}
		this.baseDao = baseDao;
	}

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
	@Override
	public ResponseMessage save(T entity) {
		try {
			if (entity == null)
				return new ResponseMessage(false, "10004");
			if (baseDao.save(entity) > 0) {
				// 得到Id属性
				Field field = entity.getClass().getDeclaredField("id");
				field.setAccessible(true);// 设置些属性是可以访问的
				Object value = field.get(entity); // 得到此属性的值
				return new ResponseMessage(true, value);
			}
			return new ResponseMessage(false, "10014");
		} catch (Exception e) {
			if (logger.isErrorEnabled())
				logger.error("方法[save]出现异常，异常信息是 :", e);
			return new ResponseMessage(false, "10003", String.format("异常信息:%s", e));
		}
	}

	/**
	 * 批处理添加
	 * 
	 * @param entitys:需要保存的对象集合
	 * @return ResponseMessage对象
	 * 
	 *         <pre>
	 * 		[success:false,status:10004]:参数为null
	 * 		[success:false,status:10003,msg:异常说明信息]
	 * 		[success:true,data:受影响行数集合]:保存成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage createBatch(List<T> entitys) {
		try {
			if (entitys == null)
				return new ResponseMessage(false, "10004");
			return new ResponseMessage(true, baseDao.createBatch(entitys));
		} catch (Exception e) {
			if (logger.isErrorEnabled())
				logger.error("方法[createBatch]出现异常，异常信息是 :", e);
			return new ResponseMessage(false, "10003", String.format("异常信息:%s", e));
		}
	}

	/**
	 * 批处理修改
	 * 
	 * @param entitys:需要保存的对象集合
	 * @return ResponseMessage对象
	 * 
	 *         <pre>
	 * 		[success:false,status:10004]:参数为null
	 * 		[success:false,status:10003,msg:异常说明信息]
	 * 		[success:true,data:受影响行数集合]:修改成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage updateBatch(List<T> entitys) {
		try {
			if (entitys == null)
				return new ResponseMessage(false, "10004");
			return new ResponseMessage(true, baseDao.updateBatch(entitys));
		} catch (Exception e) {
			if (logger.isErrorEnabled())
				logger.error("方法[updateBatch]出现异常，异常信息是 :", e);
			return new ResponseMessage(false, "10003", String.format("异常信息:%s", e));
		}
	}

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
	@Override
	public ResponseMessage removeById(Long id) {
		try {
			if (id == null)
				return new ResponseMessage(false, "10004");
			return new ResponseMessage(true, baseDao.removeById(id));
		} catch (Exception e) {
			if (logger.isErrorEnabled())
				logger.error("方法[removeById]出现异常，异常信息是 :", e);
			return new ResponseMessage(false, "10003", String.format("异常信息:%s", e));
		}
	}

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
	@Override
	public ResponseMessage find(Long id) {
		try {
			if (id == null)
				return new ResponseMessage(false, "10004");
			return new ResponseMessage(true, baseDao.find(id));
		} catch (Exception e) {
			if (logger.isErrorEnabled())
				logger.error("方法[find]出现异常，异常信息是 :", e);
			return new ResponseMessage(false, "10003", String.format("异常信息:%s", e));
		}
	}

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
	@Override
	public ResponseMessage searchParameter(List<Parameter> parameters) {
		try {
			return new ResponseMessage(true, baseDao.searchParameter(parameters));
		} catch (Exception e) {
			if (logger.isErrorEnabled())
				logger.error("方法[searchParameter]出现异常，异常信息是 :", e);
			return new ResponseMessage(false, "10003", String.format("异常信息:%s", e));
		}
	}

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
	@Override
	public ResponseMessage searchParameter(List<Parameter> parameters, Integer pageNumber, Integer pageSize) {
		try {
			if (pageSize == null)
				return new ResponseMessage(false, "10004", "查询的条数不能为空");
			if (pageNumber == null)
				return new ResponseMessage(false, "10004", "页码不能为空");
			return new ResponseMessage(true, baseDao.searchParameter(parameters, pageNumber, pageSize));
		} catch (Exception e) {
			if (logger.isErrorEnabled())
				logger.error("方法[searchParameter][分页]出现异常，异常信息是 :", e);
			return new ResponseMessage(false, "10003", String.format("异常信息:%s", e));
		}
	}

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
	@Override
	public ResponseMessage search(ISearch search) {
		try {
			// 进行判断是否是分页操作
			if (null != search && search.getMaxResults() > 0 && search.getPage() >= 0) {
				SearchResult<T> results = baseDao.searchAndCount(search);
				// info(String.format("通用查询方法分页search查询条数为:%s", results.getTotalCount()));
				return new ResponseMessage(true,
						new ResponseData<T>(search.getPage(), getTotal(results.getTotalCount(), search.getMaxResults()),
								results.getTotalCount(), results.getResult()));
			}
			List<T> results = baseDao.search(search);
			// if (null != results)
			// info(String.format("通用查询方法不分页查询条数为:%s", results.size()));
			return new ResponseMessage(true, results);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMessage(false, "10003", String.format("异常信息:%s", e));
		}
	}

	/**
	 * 获得总页数
	 * 
	 * @param totalCount：总行数
	 * @param maxResults：被分页的行数
	 * @return：总页数
	 */
	protected int getTotal(int totalCount, int maxResults) {
		return totalCount % maxResults == 0 ? totalCount / maxResults : totalCount / maxResults + 1;
	}

	/**
	 * <p>
	 * Checks if a String is not empty (""), not null and not whitespace only.
	 * </p>
	 *
	 * <pre>
	 * isNotBlank(null)      = false
	 * isNotBlank("")        = false
	 * isNotBlank(" ")       = false
	 * isNotBlank("bob")     = true
	 * isNotBlank("  bob  ") = true
	 * </pre>
	 *
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if the String is not empty and not null and not
	 *         whitespace
	 */
	protected boolean isNotBlank(String str) {
		return !this.isNotBlank(str);
	}

	/**
	 * <p>
	 * Checks if a String is whitespace, empty ("") or null.
	 * </p>
	 *
	 * <pre>
	 * isBlank(null)      = true
	 * isBlank("")        = true
	 * isBlank(" ")       = true
	 * isBlank("bob")     = false
	 * isBlank("  bob  ") = false
	 * </pre>
	 *
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if the String is null, empty or whitespace
	 */
	protected boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}
}
