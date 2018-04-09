package com.yorma.wms.dao.memory.impl;

import java.util.List;

import com.yorma.wms.dao.api.PurchaseDetailsDao;
import com.yorma.wms.dao.memory.base.BaseDaoImpl;
import com.yorma.wms.entity.PurchaseDetails;

import cn.ffcs.memory.BeanListHandler;
import cn.ffcs.memory.ColumnHandler;

public class PurchaseDetailsDaoImpl extends BaseDaoImpl<PurchaseDetails> implements PurchaseDetailsDao {

	/**
	 * 根据订单号删除入库采购订单明细
	 * @param po
	 * @return
	 */
	@Override
	public int removePurchaseDetailsByOrderPo(String orderPo) {
		String sql="delete from purchase_details where ORDER_PO=? ";
		if (null != connection) {
			return memory.update(connection, sql, orderPo);
		}
		return memory.update(connection, sql, orderPo);
	}
	
	/**
	 * 根据订单号查询入库采购订单明细
	 * @param orderUuid	
	 * @return	list<入库采购订单明细实体>
	 */
	@Override
	public List<PurchaseDetails> getPurchaseDetailsByOrderPo(String orderPo) {
		String sql="select * from purchase_details where ORDER_PO=?";
		return memory.query(sql, new BeanListHandler<PurchaseDetails>(PurchaseDetails.class), orderPo);
	}
	
	
	
}
