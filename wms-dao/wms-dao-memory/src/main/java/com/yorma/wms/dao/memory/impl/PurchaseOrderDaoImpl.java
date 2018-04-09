package com.yorma.wms.dao.memory.impl;

import java.util.ArrayList;
import java.util.List;

import com.yorma.common.entity.dto.Parameter;
import com.yorma.wms.dao.api.PurchaseOrderDao;
import com.yorma.wms.dao.memory.base.BaseDaoImpl;
import com.yorma.wms.entity.PurchaseOrder;
import com.yorma.wms.entity.dto.PurchaseOrderDto;

import cn.ffcs.memory.BeanHandler;
import cn.ffcs.memory.BeanListHandler;
import cn.ffcs.memory.ColumnHandler;

public class PurchaseOrderDaoImpl extends BaseDaoImpl<PurchaseOrder> implements PurchaseOrderDao {

	
	@Override
	public List<PurchaseOrderDto> getPurchaseOrderDto(List<Parameter> parameters) {
		String sql="SELECT purchase_order.*, ( SELECT DEPART_NAME FROM sys_depart WHERE	purchase_order.OWNER_CODE = DEPART_CODE ) AS departName,( SELECT DEPART_NAME FROM sys_depart WHERE purchase_order.SUPPLIER_CODE = DEPART_CODE ) AS supplierName, 	( SELECT DEPART_NAME FROM sys_depart WHERE	purchase_order.PAYMENT_CODE = DEPART_CODE ) as paymentName FROM 	purchase_order  ";
		List<Object> params = new ArrayList<Object>();
		sql=this.generalSelectSQL(sql, parameters, params);
		sql+=" ORDER BY purchase_order.CREATE_DATE desc ";
		return memory.query(sql, new BeanListHandler<PurchaseOrderDto>(PurchaseOrderDto.class), params.toArray());
	}
	
	/**
	 * 根据订单号查询入库采购订单信息
	 * @param po	订单号
	 * @return	入库采购订单实体
	 */
	@Override
	public PurchaseOrderDto getPurchaseOrderByPo(String po) {
		String sql="SELECT purchaseOrder.*, ( SELECT DEPART_NAME FROM sys_depart WHERE	purchaseOrder.OWNER_CODE = DEPART_CODE ) AS departName,( SELECT DEPART_NAME FROM sys_depart WHERE purchaseOrder.SUPPLIER_CODE = DEPART_CODE ) AS supplierName, 	( SELECT DEPART_NAME FROM sys_depart WHERE	purchaseOrder.PAYMENT_CODE = DEPART_CODE ) as paymentName FROM 	purchase_order purchaseOrder WHERE 	po =?";
		return memory.query(sql, new BeanHandler<PurchaseOrderDto>(PurchaseOrderDto.class), po);
	}
	
	/**
	 * 根据订单号删除入库采购订单信息
	 * @param po	订单号
	 * @return	影响行数
	 */
	@Override
	public int reomovePurchaseOrderByUUID(String uuid) {
		String sql="delete from purchase_order where uuid=?";
		if (null != connection) {
			return memory.update(connection, sql, uuid);
		}
		return memory.update(sql, uuid);
	}
	
	/**
	 * 根据订单号修改审核状态
	 * @param po	订单号
	 * @param isAudit	是否审核
	 * @return	影响行数
	 */
	@Override
	public int updateIsAuditByPo(String po, boolean isAudit) {
		String sql="update purchase_order set IS_AUDIT=? where po=?";
		return memory.update(sql, new Object[]{isAudit,po});
	}
	
	/**
	 * 根据订单号修改是否生成入库单
	 * @param po
	 * @return	影响行数
	 */
	@Override
	public int updateReceivingByPo(String po,boolean isReceiving) {
		String sql="update purchase_order set IS_RECEIVING=?	where po=?";
		if (null != connection) {
			return memory.update(connection,sql, new Object[]{isReceiving,po});
		}
		return memory.update(sql, new Object[]{isReceiving,po});
	}
	
	/**
	 * 根据订单号删除订单表头
	 * @param po	订单号
	 * @return	影响行数
	 */
	@Override
	public int removePurchaseOrderByPO(String po) {
		String sql="delete from purchase_order where po=?";
		if (null != connection) {
			return memory.update(connection,sql, po);
		}
		return memory.update(sql, po);
	}
	
	/**
	 * 根据订单号查询订单是否的审核状态
	 * @param po	订单号
	 * @return	true：已审核，false：未审核
	 */
	@Override
	public boolean getPurchaseOrderAuditByPO(String po) {
		String sql = "select IS_AUDIT from purchase_order where  po=?";
		if (null != connection) {
			return memory.query(connection, sql, new ColumnHandler<Boolean>(Boolean.class), po);
		}
		return memory.query(sql, new ColumnHandler<Boolean>(Boolean.class), po);
	}
	
	/**
	 * 根据客户端编号查询订单数量
	 * @param customsCode 客户端编号
	 * @return 订单数量
	 */
	@Override
	public int getPurchaseOrderCountByCustomsCode(String customsCode) {
		String sql = "SELECT COUNT(*) FROM purchase_order WHERE CUSTOMS_CODE = ?";
		return memory.query(sql, new ColumnHandler<Integer>(Integer.class), new Object[]{customsCode});
	}
	
}
