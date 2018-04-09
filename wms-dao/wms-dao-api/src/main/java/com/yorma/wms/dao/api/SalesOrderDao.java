package com.yorma.wms.dao.api;

import java.util.List;
import java.util.Map;

import com.yorma.common.entity.dto.Parameter;
import com.yorma.wms.dao.base.api.BaseDao;
import com.yorma.wms.entity.SalesOrder;
import com.yorma.wms.entity.dto.SalesOrderDto;

/**
 * 销售单Dao
 * 
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author su 2017年11月15日
 * @version 1.00.00
 * @date 2017年11月15日
 * @version V1.0
 */
public interface SalesOrderDao extends BaseDao<SalesOrder> {

	/**
	 * 根据订单号查询出库销售订单
	 * @param so
	 * @return	出库销售订单Dto实体
	 */
	SalesOrderDto getSalesOrderDtoBySo(String so);
	
	/**
	 * 根据条件查询出库销售订单
	 * @param parameters
	 * @return	List<出库销售订单Dto实体>
	 */
	List<SalesOrderDto> getSalesOrderDto(List<Parameter> parameters);
	
	/**
	 * 根据单号删除出库销售订单及出库销售订单明细
	 * @param so
	 * @return  影响行数
	 */
	int removeSalesOrderByOrderUUID(String orderUuid);
	
	/**
	 * 根据条件查询出库销售订单
	 * @param parameters
	 * @return	List<出库销售订单Dto实体>
	 */
	List<SalesOrderDto> getSalesOrderDto(Map<String, Object> parameters);
	
	/**
	 * 根据订单号修改审核状态
	 * @param so	订单号
	 * @param isAudit	是否审核
	 * @return	影响行数
	 */
	int updateIsAuditBySo(String so,boolean isAudit);
	
	/**
	 * 根据订单号修改是否生成入库单
	 * @param so	订单号
	 * @param isDelivery	是否生成入库单
	 * @return	影响行数
	 */
	int updateIsDelivery(String so,boolean isDelivery);
	
	/**
	 * 根据订单号删除出库销售订单
	 * @param so	订单号
	 * @return 影响行数
	 */
	int removeSalesOrderBySO(String so);
	
	/**
	 * 根据订单号查询销售订单是否审核
	 * @param so	订单号
	 * @return	true：已审核，false：未审核
	 */
	boolean getSalesOrderAuditBySO(String so);
	
	/**
	 * 根据客户端编号查询出库销售订单的信息数量
	 * @param customsCode	客户端编号
	 * @return	出库销售订单的信息数量
	 */
	int getSalesOrderCountByCustomsCode(String customsCode);
	
}
