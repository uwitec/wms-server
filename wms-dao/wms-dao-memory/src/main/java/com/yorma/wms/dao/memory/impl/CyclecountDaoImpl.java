package com.yorma.wms.dao.memory.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yorma.wms.dao.api.CyclecountDao;
import com.yorma.wms.dao.memory.base.BaseDaoImpl;
import com.yorma.wms.entity.Cyclecount;

import cn.ffcs.memory.BeanHandler;
import cn.ffcs.memory.BeanListHandler;
import cn.ffcs.memory.ColumnHandler;

/**
 * 盘点信息Dao  实现类
 * @author Su
 * 2017年12月13日
 */
public class CyclecountDaoImpl extends BaseDaoImpl<Cyclecount> implements CyclecountDao {

	/**
	 * 根据条件查询盘点纤细
	 * @param parames	参数集合
	 * @return	盘点信息实体集合
	 */
	@Override
	public List<Cyclecount> getCyclecount(Map<String, Object> parames) {
		
		StringBuffer sql = new StringBuffer();
		List<Object> params=new ArrayList<>();
		sql.append("SELECT * FROM cyclecount WHERE 1=1");
		for (String key : parames.keySet()) {
			if (parames.get(key)!= null && !"".equals(parames.get(key))) {
				sql.append(" and " + key+" ? ");
				params.add(parames.get(key));
			}
		}
		
		sql.append(" ORDER BY CREATE_DATE DESC");
		
		return memory.query(sql, new BeanListHandler<Cyclecount>(Cyclecount.class), params);
	}
	
	/**
	 * 根据条件查询盘点信息（分页）
	 * @param parames	参数集合
	 * @param pageNumber	当前页
	 * @param pageSize	每页显示条数
	 * @return 盘点信息实体集合
	 */
	@Override
	public List<Cyclecount> getCyclecount(Map<String, Object> parames, Integer pageNumber, Integer pageSize) {
		StringBuffer sql = new StringBuffer();
		List<Object> params=new ArrayList<>();
		sql.append("SELECT * FROM cyclecount WHERE 1=1");
		for (String key : parames.keySet()) {
			if (parames.get(key)!= null && !"".equals(parames.get(key))) {
				sql.append(" and " + key+" ? ");
				params.add(parames.get(key));
			}
		}
		
		sql.append(" ORDER BY CREATE_DATE DESC");
		params.add((pageNumber-1)*pageSize);
		params.add(pageSize);
		sql.append(" limit ?,? ");
		
		return memory.query(sql, new BeanListHandler<Cyclecount>(Cyclecount.class), params);
	}
	
	/**
	 * 根据条件获取盘点信息参数个数
	 * @param parames	参数集合
	 * @return	盘点信息参数个数
	 */
	@Override
	public Integer getCounts(Map<String, Object> parames) {
		StringBuffer sql = new StringBuffer();
		List<Object> params=new ArrayList<>();
		sql.append("SELECT COUNT(*) FROM cyclecount WHERE 1=1");
		for (String key : parames.keySet()) {
			if (parames.get(key)!= null && !"".equals(parames.get(key))) {
				sql.append(" and " + key+" ? ");
				params.add(parames.get(key));
			}
		}
		return memory.query(sql.toString(), new ColumnHandler<Integer>(Integer.class), params.toArray());
	}
	
	/**
	 * 根据盘点单号查询盘点信息条数
	 * @param cyclecountNo	盘点单号
	 * @return	盘点信息条数
	 */
	@Override
	public int getCountCyclecountByCyclecountNo(String cyclecountNo) {
		String sql="SELECT COUNT(*) FROM cyclecount WHERE CYCLECOUNT_NO=?";
		if (null != connection) {
			return memory.query(connection, sql, new ColumnHandler<Integer>(Integer.class), cyclecountNo);
		}
		return memory.query(sql, new ColumnHandler<Integer>(Integer.class), cyclecountNo);
	}
	
	/**
	 * 根据盘点单号查询盘点单状态
	 * @param cyclecountNo	盘点单号
	 * @return	盘点单状态
	 */
	@Override
	public Integer getStatusByCyclecountNo(String cyclecountNo) {
		String sql="SELECT status FROM cyclecount WHERE CYCLECOUNT_NO=?";
		if (null != connection) {
			return memory.query(connection, sql, new ColumnHandler<Integer>(Integer.class), cyclecountNo);
		}
		return memory.query(sql, new ColumnHandler<Integer>(Integer.class), cyclecountNo);
	}
	
	/**
	 * 根据盘点单号修改盘点单状态
	 * @param cyclecountNo	盘点单号
	 * @param status	盘点单状态
	 * @return	影响行数
	 */
	@Override
	public int updateStatusByCyclecountNo(String cyclecountNo, int status) {
		String sql="UPDATE cyclecount SET STATUS=? WHERE CYCLECOUNT_NO=?";
		if (null != connection) {
			return memory.update(connection, sql, new Object[]{status,cyclecountNo});
		}
		return memory.update(sql, new Object[]{status,cyclecountNo});
	}
	
	/**
	 * 根据盘点单号查询盘点信息
	 * @param cyclecountNo	盘点单号
	 * @return	盘点信息实体
	 */
	@Override
	public Cyclecount getCyclecountByCyclecountNo(String cyclecountNo) {
		String sql="SELECT * FROM cyclecount WHERE CYCLECOUNT_NO=?";
		return memory.query(sql, new BeanHandler<Cyclecount>(Cyclecount.class), cyclecountNo);
	}
	
	/**
	 * 根据盘点单号删除盘点信息
	 * @param cyclecountNo	盘点单号
	 * @return	影响行数
	 */
	@Override
	public int removeCyclecountByCyclecountNo(String cyclecountNo) {
		String sql="DELETE from  cyclecount WHERE CYCLECOUNT_NO=?";
		if (null != connection) {
			return memory.update(connection, sql, cyclecountNo);
		}
		return memory.update(sql, cyclecountNo);
	}
	
}
