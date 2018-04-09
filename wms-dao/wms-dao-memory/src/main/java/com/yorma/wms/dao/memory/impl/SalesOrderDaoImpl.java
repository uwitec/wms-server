package com.yorma.wms.dao.memory.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yorma.common.entity.dto.Parameter;
import com.yorma.wms.dao.api.SalesOrderDao;
import com.yorma.wms.dao.memory.base.BaseDaoImpl;
import com.yorma.wms.entity.SalesOrder;
import com.yorma.wms.entity.dto.SalesOrderDto;

import cn.ffcs.memory.BeanHandler;
import cn.ffcs.memory.BeanListHandler;
import cn.ffcs.memory.ColumnHandler;


/**
 * 销售单DaoImpl
 * 
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author su 2017年11月15日
 * @version 1.00.00
 * @date 2017年11月15日
 * @version V1.0
 */
public class SalesOrderDaoImpl extends BaseDaoImpl<SalesOrder> implements SalesOrderDao {

	/**
	 * 根据订单号查询出库销售订单
	 * @param so
	 * @return	出库销售订单Dto实体
	 */
	@Override
	public SalesOrderDto getSalesOrderDtoBySo(String so) {
		String sql="SELECT salesOrder.*, ( SELECT DEPART_NAME FROM 	sys_depart WHERE salesOrder.OWNER_CODE = DEPART_CODE ) AS departName, (SELECT DEPART_NAME FROM sys_depart WHERE salesOrder.CONSIGNEE_CODE = DEPART_CODE ) AS consigneeName, ( SELECT DEPART_NAME FROM sys_depart 	WHERE salesOrder.PAYMENT_CODE = DEPART_CODE ) AS paymentName FROM sales_order salesOrder where so=? ";
		return memory.query(sql, new BeanHandler<SalesOrderDto>(SalesOrderDto.class), so);
	}
	
	/**
	 * 根据条件查询出库销售订单
	 * @param parameters
	 * @return	List<出库销售订单Dto实体>
	 */
	@Override
	public List<SalesOrderDto> getSalesOrderDto(List<Parameter> parameters) {
		String sql="SELECT sales_order.*, ( SELECT DEPART_NAME FROM 	sys_depart WHERE sales_order.OWNER_CODE = DEPART_CODE ) AS departName, (SELECT DEPART_NAME FROM sys_depart WHERE sales_order.CONSIGNEE_CODE = DEPART_CODE ) AS consigneeName, ( SELECT DEPART_NAME FROM sys_depart WHERE sales_order.PAYMENT_CODE = DEPART_CODE ) AS paymentName FROM sales_order  ";
		List<Object> params = new ArrayList<>();
		sql=this.generalSelectSQL(sql, parameters, params);
		sql+=" ORDER BY sales_order.CREATE_DATE DESC ";
		return memory.query(sql, new BeanListHandler<SalesOrderDto>(SalesOrderDto.class), params.toArray());
	}
	
	/**
	 * 根据单号删除出库销售订单及出库销售订单明细
	 * @param so
	 * @return	影响行数
	 */
	@Override
	public int removeSalesOrderByOrderUUID(String orderUuid) {
		String sql="delete from sales_order where order_uuid=?";
		if (null != connection) {
			return memory.update(connection, sql, orderUuid);
		}
		return memory.update(sql, orderUuid);
	}
	
	@Override
	public List<SalesOrderDto> getSalesOrderDto(Map<String, Object> parameters) {
		Integer pageNumber=null;
		Integer pageSize=null;
		List<Object> params = new ArrayList<>();
		String sql="SELECT salesOrder.*, ( SELECT DEPART_NAME FROM 	sys_depart WHERE salesOrder.OWNER_CODE = DEPART_CODE ) AS departName, (SELECT DEPART_NAME FROM sys_depart WHERE salesOrder.CONSIGNEE_CODE = DEPART_CODE ) AS consigneeName, ( SELECT DEPART_NAME FROM sys_depart 	WHERE salesOrder.PAYMENT_CODE = DEPART_CODE ) AS paymentName FROM sales_order salesOrder where 1=1 ";
		for (String  key : parameters.keySet()) {
			if ("pageSize".equals(key)) {
				pageSize=(Integer) parameters.get(key);
				parameters.put(key, null);
			}
			if ("pageNumber".equals(key)) {
				pageNumber=(Integer) parameters.get(key);
				parameters.put(key, null);
			}
			if (parameters.get(key) != null && !"".equals(parameters.get(key)) && !"%%".equals(parameters.get(key))
						&& !"% %".equals(parameters.get(key))) {
				sql+=" and "+key+" ? ";
				params.add(parameters.get(key));
			}
		}
		sql+=" ORDER BY salesOrder.CREATE_DATE desc ";
		if (pageNumber != null && pageSize != null) {
			sql+=" limit ?,?";
			params.add(pageNumber*pageSize);
			params.add(pageSize);
		}
		return memory.query(sql, new BeanListHandler<SalesOrderDto>(SalesOrderDto.class), params.toArray());
	}
	
	/**
	 * 根据订单号修改审核状态
	 * @param so	订单号
	 * @param isAudit	是否审核
	 * @return	影响行数
	 */
	@Override
	public int updateIsAuditBySo(String so, boolean isAudit) {
		String sql="update sales_order set IS_AUDIT=?	where so=?";
		return memory.update(sql, new Object[]{isAudit,so});
	}
	
	/**
	 * 根据订单号修改是否生成入库单
	 * @param so	订单号
	 * @param isDelivery	是否生成入库单
	 * @return	影响行数
	 */
	@Override
	public int updateIsDelivery(String so, boolean isDelivery) {
		String sql="update sales_order set IS_DELIVERY=?	where so=?";
		if (null != connection) {
			return memory.update(connection,sql, new Object[]{isDelivery,so});
		}
		return memory.update(sql, new Object[]{isDelivery,so});
	}
	
	/**
	 * 根据订单号删除出库销售订单
	 * @param so	订单号
	 * @return 影响行数
	 */
	@Override
	public int removeSalesOrderBySO(String so) {
		String sql="delete from sales_order where so=?";
		if (null != connection) {
			return memory.update(connection,sql, so);
		}
		return memory.update(sql, so);
	}
	
	/**
	 * 根据订单号查询销售订单是否审核
	 * @param so	订单号
	 * @return	true：已审核，false：未审核
	 */
	@Override
	public boolean getSalesOrderAuditBySO(String so) {
		String sql = "select IS_AUDIT from sales_order where  SO=?";
		if (null != connection) {
			return memory.query(connection, sql, new ColumnHandler<Boolean>(Boolean.class), so);
		}
		return memory.query(sql, new ColumnHandler<Boolean>(Boolean.class), so);
	}
	
	/**
	 * 根据客户端编号查询出库销售订单的信息数量
	 * @param customsCode	客户端编号
	 * @return	出库销售订单的信息数量
	 */
	@Override
	public int getSalesOrderCountByCustomsCode(String customsCode) {
		String sql = "SELECT COUNT(*) FROM sales_order WHERE CUSTOMS_CODE =?";
		return memory.query(sql, new ColumnHandler<Integer>(Integer.class), new Object[]{customsCode});
	}
	
}
