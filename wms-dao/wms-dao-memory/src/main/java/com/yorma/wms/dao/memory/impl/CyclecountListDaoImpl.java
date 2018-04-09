package com.yorma.wms.dao.memory.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yorma.wms.dao.api.CyclecountListDao;
import com.yorma.wms.dao.memory.base.BaseDaoImpl;
import com.yorma.wms.entity.CyclecountList;

import cn.ffcs.memory.BeanListHandler;

/**
 * 盘点明细信息Dao 实现类
 * @author dell
 * 2017年12月13日
 */
public class CyclecountListDaoImpl extends BaseDaoImpl<CyclecountList> implements CyclecountListDao {

	/**
	 * 根据条件查询盘点明细信息
	 * @param parames	参数聚合
	 * @return	List<盘点明细信息>
	 */
	@Override
	public List<CyclecountList> getCyclecountList(Map<String, Object> parames) {
		//当前页、每页显示条数
		Integer pageNumber=null;
		Integer pageSize=null;
		StringBuffer sql= new StringBuffer();
		List<Object> params=new ArrayList<>();
		sql.append("SELECT * FROM cyclecount_list WHERE 1=1 ");
		for (String key : parames.keySet()) {
			if ("pageSize".equals(key)) {
				pageSize=(Integer) parames.get(key);
				parames.put(key, null);
			}
			if ("pageNumber".equals(key)) {
				pageNumber=(Integer) parames.get(key);
				parames.put(key, null);
			}
			if (parames.get(key) == null && !"".equals(parames.get(key))) {
				sql.append(" and "+key+" ? ");
				params.add(parames.get(key));
			}
		}
		if (pageNumber != null && pageSize != null) {
			sql.append(" limit ?,?");
			params.add(pageNumber*pageSize);
			params.add(pageSize);
		}
		return memory.query(sql, new BeanListHandler<CyclecountList>(CyclecountList.class), params);
	}
	
	/**
	 * 根据盘点单号删除盘点明细信息
	 * @param cyclecountNo	盘点单号
	 * @return	影响行数
	 */
	@Override
	public int removeCyclecountListByCyclecountNo(String cyclecountNo) {
		String sql="DELETE FROM cyclecount_list WHERE CYCLECOUNT_NO=?";
		if (null != connection) {
			return memory.update(connection, sql, cyclecountNo);
		}
		return memory.update(sql, cyclecountNo);
	}
	
	/**
	 * 根据盘点单号查询盘点明细信息
	 * @param cyclecountNo	盘点单号
	 * @return	List<盘点明细信息>
	 */
	@Override
	public List<CyclecountList> getCyclecountListByCyclecountNo(String cyclecountNo) {
		String sql="SELECT * FROM cyclecount_list WHERE CYCLECOUNT_NO=?";
		if (null != connection) {
			return memory.query(connection, sql, new BeanListHandler<CyclecountList>(CyclecountList.class), cyclecountNo); 
		}
		return memory.query(sql, new BeanListHandler<CyclecountList>(CyclecountList.class), cyclecountNo);
	}
	
	/**
	 * 根据id修改盘点明细的入库单号
	 * @param receivingNoteNo	入库单号
	 * @param id	盘点明细ID
	 * @return	影响行数
	 */
	@Override
	public int updateReceivingNoteNoById(String receivingNoteNo, Long id) {
		String sql="UPDATE cyclecount_list SET RECEIVING_NOTE_NO=? WHERE ID=?";
		if (null != connection) {
			return memory.update(connection, sql, new Object[]{receivingNoteNo,id});
		}
		return memory.update(sql, new Object[]{receivingNoteNo,id});
	}
	
	/**
	 * 根据id修改盘点明细的出库单号
	 * @param deliveryNoteNo	出库单号
	 * @param id	盘点明细ID
	 * @return	影响行数
	 */
	@Override
	public int updateDeliveryNoteNoById(String deliveryNoteNo, Long id) {
		String sql="UPDATE cyclecount_list SET DELIVERY_NOTE_NO=? WHERE ID=?";
		if (null != connection) {
			return memory.update(connection, sql, new Object[]{deliveryNoteNo,id});
		}
		return memory.update(sql, new Object[]{deliveryNoteNo,id});
	}
	
}