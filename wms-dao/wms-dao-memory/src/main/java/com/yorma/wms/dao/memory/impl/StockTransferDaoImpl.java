package com.yorma.wms.dao.memory.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yorma.wms.dao.api.StockTransferDao;
import com.yorma.wms.dao.memory.base.BaseDaoImpl;
import com.yorma.wms.entity.StockTransfer;

import cn.ffcs.memory.BeanHandler;
import cn.ffcs.memory.BeanListHandler;
import cn.ffcs.memory.ColumnHandler;

/**
 * 移库单表头DaoImpl
 * 
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author su 2017年12月13日
 * @version 1.00.00
 * @date 2017年12月13日
 * @version V1.0
 */
public class StockTransferDaoImpl extends BaseDaoImpl<StockTransfer> implements StockTransferDao {

	private Logger logger = LoggerFactory.getLogger(StockTransferDaoImpl.class);
	
	/**
	 * 根据条件查询移库单表头
	 * @param parames	参数集合
	 * @return	移库单表头实体集合
	 */
	@Override
	public List<StockTransfer> getStockTransfer(Map<String, Object> parames) {
		StringBuffer sql=new StringBuffer();
		List<Object> params=new ArrayList<>();
		sql.append("SELECT * FROM stock_transfer where 1=1");
		for (String key : parames.keySet()) {
			if (parames.get(key) != null && !"".equals(parames.get(key)) && !"%%".equals(parames.get(key))
					&& !"% %".equals(parames.get(key))) {
				sql.append(" and "+key+"?");
				params.add(parames.get(key));
			}
		}
		sql.append(" ORDER BY CREATE_DATE DESC ");
		
		if (logger.isDebugEnabled()) {
			logger.debug("Dao:execute SQL sentence : {}", sql);
		}
		return memory.query(sql, new BeanListHandler<StockTransfer>(StockTransfer.class), params);
	}
	
	/**
	 * 根据条件查询移库单表头(分页)
	 * @param parames 	参数集合
	 * @param pageNumber	当前页
	 * @param pageSize	每页显示条数
	 * @return	移库单表头实体集合
	 */
	@Override
	public List<StockTransfer> getStockTransfer(Map<String, Object> parames, Integer pageNumber, Integer pageSize) {
		StringBuffer sql=new StringBuffer();
		List<Object> params=new ArrayList<>();
		sql.append("SELECT * FROM stock_transfer where 1=1");
		for (String key : parames.keySet()) {
			if (parames.get(key) != null && !"".equals(parames.get(key)) && !"%%".equals(parames.get(key))
					&& !"% %".equals(parames.get(key))) {
				sql.append(" and "+key+"?");
				params.add(parames.get(key));
			}
		}
		sql.append(" ORDER BY CREATE_DATE DESC ");
		
		params.add((pageNumber-1)*pageSize);
		params.add(pageSize);
		sql.append(" limit ?,?");
		
		if (logger.isDebugEnabled()) {
			logger.debug("Dao:execute SQL sentence : {}", sql);
		}
		return memory.query(sql.toString(), new BeanListHandler<StockTransfer>(StockTransfer.class), params.toArray());
	}
	
	/**
	 * 根据条件查询移库单表头信息条数
	 * @param parames	参数集合
	 * @return	移库单表头信息条数
	 */
	@Override
	public Integer getcounts(Map<String, Object> parames) {
		StringBuffer sql=new StringBuffer();
		List<Object> params=new ArrayList<>();
		sql.append("SELECT COUNT(*) FROM stock_transfer where 1=1");
		for (String key : parames.keySet()) {
			if (parames.get(key) != null && !"".equals(parames.get(key)) && !"%%".equals(parames.get(key))
					&& !"% %".equals(parames.get(key))) {
				sql.append(" and "+key+"?");
				params.add(parames.get(key));
			}
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("Dao:execute SQL sentence : {}", sql);
		}
		return memory.query(sql.toString(), new ColumnHandler<Integer>(Integer.class), params.toArray());
	}
	
	/**
	 * 根据移库单号查询移库表头信息
	 * @param transferNo	移库单号
	 * @return	移库表头实体
	 */
	@Override
	public StockTransfer getStockTransferByTransferNo(String transferNo) {
		String sql="select * from stock_transfer where TRANSFER_NO=?";
		if (null != connection) {
			return memory.query(connection, sql, new BeanHandler<StockTransfer>(StockTransfer.class), transferNo);
		}
		return memory.query(sql, new BeanHandler<StockTransfer>(StockTransfer.class), transferNo);
	}
	
	/**
	 * 根据移库单号删除移库单表头新西
	 * @param transferNo	移库单号
	 * @return	影响行数
	 */
	@Override
	public int removeStockTransferByTransferNo(String transferNo) {
		String sql="delete from stock_transfer where transfer_no=?";
		if (null != connection) {
			return memory.update(connection, sql, transferNo);
		}
		return memory.update(connection, sql, transferNo);
	}
	
}
