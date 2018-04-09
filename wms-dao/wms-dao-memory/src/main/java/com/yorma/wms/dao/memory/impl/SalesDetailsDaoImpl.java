package com.yorma.wms.dao.memory.impl;

import java.util.List;

import com.yorma.wms.dao.api.SalesDetailsDao;
import com.yorma.wms.dao.memory.base.BaseDaoImpl;
import com.yorma.wms.entity.SalesDetails;

import cn.ffcs.memory.BeanListHandler;

/**
 * 销售单明细DaoImpl
 * 
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author su 2017年11月15日
 * @version 1.00.00
 * @date 2017年11月15日
 * @version V1.0
 */
public class SalesDetailsDaoImpl extends BaseDaoImpl<SalesDetails> implements SalesDetailsDao {

	/**
	 * 根据订单号删除出库销售订单明细
	 * @param so	订单号
	 * @return	影响行数
	 */
	@Override
	public int removeSalesDetailsByOrderSO(String orderSo) {
		String sql="delete from sales_details where ORDER_SO=?";
		if (null != connection) {
			return memory.update(connection, sql, orderSo);
		}
		return memory.update(sql, orderSo);
	}
	
	/**
	 * 根据订单号查询出库销售订单明细
	 * @param so	订单号
	 * @return	List<出库销售订单明细实体>
	 */
	@Override
	public List<SalesDetails> getSalesDetailsByOrderSO(String orderSo) {
		String sql="select * from sales_details where ORDER_SO=? ";
		return memory.query(sql, new BeanListHandler<SalesDetails>(SalesDetails.class), orderSo);
	}
	
	
	
}
