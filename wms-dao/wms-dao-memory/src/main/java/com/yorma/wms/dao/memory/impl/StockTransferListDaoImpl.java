package com.yorma.wms.dao.memory.impl;

import java.util.List;

import com.yorma.wms.dao.api.StockTransferListDao;
import com.yorma.wms.dao.memory.base.BaseDaoImpl;
import com.yorma.wms.entity.StockTransferList;

import cn.ffcs.memory.BeanListHandler;

public class StockTransferListDaoImpl extends BaseDaoImpl<StockTransferList> implements StockTransferListDao {

	/**
	 * 根据移库单号删除移库明细
	 * @param transferNo	移库单号
	 * @return	影响行数
	 */
	@Override
	public int removeStockTransferListByTransferNo(String transferNo) {
		String sql="delete from stock_transfer_list where transfer_no=?";
		if (null != connection) {
			return memory.update(connection, sql, transferNo);
		}
		return memory.update(sql, transferNo);
	}
	
	/**
	 * 根据移库单号查询移库明细信息
	 * @param transFerNo	移库单号
	 * @return	移库明细信息实体集合
	 */
	@Override
	public List<StockTransferList> getStockTransferListByTransferNo(String transferNo) {
		String sql="select * from stock_transfer_list where TRANSFER_NO=?";
		if (null != connection) {
			return memory.query(connection, sql, new BeanListHandler<StockTransferList>(StockTransferList.class), transferNo); 
		}
		return memory.query(sql, new BeanListHandler<StockTransferList>(StockTransferList.class), transferNo);
	}
	
}
