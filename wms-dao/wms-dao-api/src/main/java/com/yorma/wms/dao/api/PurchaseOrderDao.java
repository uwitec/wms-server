package com.yorma.wms.dao.api;

import java.util.List;

import com.yorma.common.entity.dto.Parameter;
import com.yorma.wms.dao.base.api.BaseDao;
import com.yorma.wms.entity.PurchaseOrder;
import com.yorma.wms.entity.dto.PurchaseOrderDto;

/**
 * 采购订单明细Dao 接口类
 * 
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author su 
 * @version 1.00.00
 * @date 2017年11月15日
 * @version V1.0
 */
public interface PurchaseOrderDao extends BaseDao<PurchaseOrder> {

	/**
	 * 根据条件查询采购订单明细
	 * @param parameters	参数集合
	 * @return	采购明细Dto实体集合
	 */
	List<PurchaseOrderDto> getPurchaseOrderDto(List<Parameter> parameters);
	
	/**
	 * 根据订单号查询入库采购订单信息
	 * @param po	订单号
	 * @return	入库采购订单实体
	 */
	PurchaseOrderDto getPurchaseOrderByPo(String po);
	
	/**
	 * 根据订单号删除入库采购订单信息
	 * @param po	订单号
	 * @return	影响行数
	 */
	int reomovePurchaseOrderByUUID(String uuid);
	
	/**
	 * 根据订单号修改审核状态
	 * @param po	订单号
	 * @param isAudit	是否审核
	 * @return	影响行数
	 */
	int updateIsAuditByPo(String po,boolean isAudit);
	
	/**
	 * 根据订单号修改是否生成入库单
	 * @param po
	 * @param isReceiving
	 * @return	影响行数
	 */
	int updateReceivingByPo(String po,boolean isReceiving);
	
	/**
	 * 根据订单号删除订单表头
	 * @param po	订单号
	 * @return	影响行数
	 */
	int removePurchaseOrderByPO(String po);
	
	/**
	 * 根据订单号查询订单是否的审核状态
	 * @param po	订单号
	 * @return	true：已审核，false：未审核
	 */
	boolean getPurchaseOrderAuditByPO(String po);
	
	/**
	 * 根据客户端编号查询订单数量
	 * @param customsCode 客户端编号
	 * @return 订单数量
	 */
	int getPurchaseOrderCountByCustomsCode(String customsCode);
	
}
