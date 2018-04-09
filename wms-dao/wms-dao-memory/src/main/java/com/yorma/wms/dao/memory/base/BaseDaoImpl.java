package com.yorma.wms.dao.memory.base;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yorma.wms.dao.base.api.BaseDao;
import com.yorma.wms.dao.memory.util.MemoryFactory;
import com.yorma.memory.utils.StringHumpConvert;
import com.yorma.common.entity.dto.Parameter;
import com.yorma.common.enumeration.Connect;
import com.yorma.common.enumeration.Operator;
import com.yorma.memory.dao.GenericDAOImpl;

import cn.ffcs.memory.BeanListHandler;
import cn.ffcs.memory.Memory;

/**
 * 
 * @Description:基本增、删、改、查Dao层实现
 * @Copyright: Copyright (c) 2017 FFCS All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author DWL 2017年8月7日
 * @version 1.00.00
 * @history:
 *
 */
@SuppressWarnings("all")
public class BaseDaoImpl<T> extends GenericDAOImpl<T, Long> implements BaseDao<T> {

	private static final Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

	protected Memory memory = MemoryFactory.getInstance();

	// 数据库连接对象
	protected Connection connection;

	private Class<T> clazz;

	public BaseDaoImpl() {
		// 通过反射得到T的真实类型
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class) pt.getActualTypeArguments()[0];
		// 设置父类的Memory
		super.setMemory(memory);
	}

	/**
	 * 保存操作
	 * <p>
	 * (id!=null):update,(id==null):create
	 * </p>
	 * 
	 * @param entity:需要保存的对象
	 * @return 受影响行数
	 */
	@Override
	public int save(T entity) {
		try {
			// 得到Id属性
			Field field = entity.getClass().getDeclaredField("id");
			field.setAccessible(true);// 设置些属性是可以访问的
			Object value = field.get(entity); // 得到此属性的值
			if (value == null) {
				if (connection == null)
					return memory.create(clazz, entity);
				else
					return memory.create(connection, clazz, entity);
			} else {
				if (connection == null)
					return memory.update(clazz, entity);
				else
					return memory.update(connection, clazz, entity);

			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 批处理添加
	 * 
	 * @param entitys:需要保存的对象集合
	 * @return 受影响行数集合
	 */
	@Override
	public int[] createBatch(List<T> entitys) {
		if (connection == null)
			return memory.create(clazz, entitys);
		else
			return memory.create(connection, clazz, entitys);
	}

	/**
	 * 批处理修改
	 * 
	 * @param entitys:需要保存的对象集合
	 * @return 受影响行数集合
	 */
	@Override
	public int[] updateBatch(List<T> entitys) {
		if (connection == null)
			return memory.update(clazz, entitys);
		else
			return memory.update(connection, clazz, entitys);
	}

	/**
	 * 根据(主键)Id查询
	 * 
	 * @param id:(主键)Id
	 * @return：查询单个对象
	 */
	@Override
	public T find(Long id) {
		return memory.read(clazz, id);
	}

	/**
	 * 根据(主键)Id删除
	 * 
	 * @param id:(主键)Id
	 * @return 删除影响行数
	 */
	@Override
	public int removeById(Long id) {
		if (connection == null)
			return memory.delete(clazz, id);
		else
			return memory.delete(connection, clazz, id);
	}

	/**
	 * 设置连接对象
	 */
	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

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
	@Override
	public List<T> searchParameter(List<Parameter> parameters) {
		// 创建参数集合
		List<Object> params = new ArrayList<Object>();
		return memory.query(this.generalSelectSQL(generalFromSql(), parameters, params), new BeanListHandler<>(clazz),
				params.toArray());
	}

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
	@Override
	public List<T> searchParameter(List<Parameter> parameters, Integer pageNumber, Integer pageSize) {
		// 创建参数集合
		List<Object> params = new ArrayList<Object>();
		// 添加分页SQL
		StringBuffer sql = new StringBuffer(this.generalSelectSQL(generalFromSql(), parameters, params))
				.append(" limit ").append("?,?");
		// 设置分页参数
		params.add(pageNumber * pageSize);
		params.add(pageSize);
		return memory.query(sql.toString(), new BeanListHandler<>(clazz), params.toArray());
	}

	/**
	 * 生成获得查询所有数据的SQL
	 * 
	 * @return 生成的SQL
	 */
	protected String generalFromSql() {
		// 获得类名,并将驼峰命名法转换成下划线
		String tableName = StringHumpConvert.humpToLine2(clazz.getSimpleName());
		// 生成查询所有数据的SQL语句
		StringBuffer sql = new StringBuffer("SELECT * FROM ").append(tableName.toLowerCase());
		return sql.toString();
	}

	/**
	 * 生成查询SQL
	 * 
	 * @param sql:查询SQL，不包含WHERE条件
	 * @param parameters:参数对象集合
	 * @param params:输出参数:参数集合
	 * @return:查询SQL语句
	 */
	protected String generalSelectSQL(String sql, List<Parameter> parameters, List<Object> params) {
		StringBuffer generalSql = new StringBuffer(sql);
		if (null != parameters && parameters.size() > 0) {
			// 验证传入的参数集合
			if (null == params)
				throw new NullPointerException("参数集合为空");
			// 设置Where条件
			generalSql.append(" WHERE 1=1 ");
			// 处理条件查询
			for (Parameter parameter : parameters) {
				// 追加连接符
				generalSql.append(getConnect(parameter.getConnect()));
				// 转换实体属性到数据库字段
				String field = parameter.getField();
				if (-1 != field.indexOf(".")) {
					generalSql
							.append(StringHumpConvert.humpToLine2(field.substring(0, field.indexOf("."))).toLowerCase())
							.append(".").append(StringHumpConvert
									.humpToLine2(field.substring(field.indexOf(".") + 1, field.length())))
							.append(" ");
				} else {
					generalSql.append(StringHumpConvert.humpToLine2(field)).append(" ");
				}
				// 生成操作符
				generalSql.append(getOperator(parameter.getOperator())).append(" ");
				// 设置参数
				if (null != parameter.getValue()) {
					// 处理in或not in
					if (parameter.getOperator() != null && (parameter.getOperator().equals(Operator.OP_IN)
							|| parameter.getOperator().equals(Operator.OP_NOT_IN)))
						generalSql.append("(");
					// 设置占位符
					generalSql.append("?");
					// 处理in或not in
					if (parameter.getOperator() != null && (parameter.getOperator().equals(Operator.OP_IN)
							|| parameter.getOperator().equals(Operator.OP_NOT_IN)))
						generalSql.append(")");
					params.add(parameter.getValue());
				}
			}
		}
		if (logger.isDebugEnabled())
			logger.debug("Method [generalSelectSQL] execution ,sql:{}", generalSql);
		return generalSql.toString();
	}

	/**
	 * 生成连接符，若不指定连接符，默认使用and
	 * 
	 * @param connect:连接符对象
	 * @return 连接符字符串
	 */
	private static String getConnect(Connect connect) {
		if (connect == null)
			return " AND ";
		switch (connect) {
		case OP_AND:
			return " AND ";
		case OP_OR:
			return " OR ";
		default:
			return " AND ";
		}
	}

	/**
	 * 获得操作符,若不指定操作符，默认使用=
	 * 
	 * @param operator:操作符对象
	 * @return:操作符字符串
	 */
	private static String getOperator(Operator operator) {
		if (operator == null)
			return " = ";
		switch (operator) {
		case OP_NULL:
			return " IS NULL";
		case OP_NOT_NULL:
			return " IS NOT NULL";
		case OP_IN:
			return " IN ";
		case OP_NOT_IN:
			return " NOT IN ";
		case OP_EQUAL:
			return " = ";
		case OP_NOT_EQUAL:
			return " != ";
		case OP_GREATER_THAN:
			return " > ";
		case OP_LESS_THAN:
			return " < ";
		case OP_GREATER_OR_EQUAL:
			return " >= ";
		case OP_LESS_OR_EQUAL:
			return " <= ";
		case OP_LIKE:
			return " LIKE ";
		default:
			return " = ";
		}
	}
}
