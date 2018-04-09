package com.yorma.wms.dao.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yorma.wms.dao.base.api.BaseDao;
import com.yorma.wms.entity.DeliveryPackingList;
import com.yorma.wms.entity.dto.DeliveryPackingListDto;

/**
 * 出库货物信息Dao
 * 
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author su 2017年8月25日
 * @version 1.00.00
 * @date 2017年8月28日
 * @version V1.0
 */
public interface DeliveryPackingListDao extends BaseDao<DeliveryPackingList> {

	/**
	 * 查询出库货物信息
	 * 
	 * @param deliveryNoteNo
	 *            出货单号
	 * @return 将查询（所有/符合条件）的数据放入的list集合
	 */
	List<DeliveryPackingList> getDeliveryPackingListByNoteNo(String deliveryNoteNo);

	/**
	 * 查询出库货物信息
	 * 
	 * @param deliveryNoteNo
	 *            出货单号
	 * @param status
	 *            状态
	 * @return 将查询（所有/符合条件）的数据放入的list集合
	 */
	List<DeliveryPackingList> getDeliveryPackingListByNoteNo(String deliveryNoteNo, Integer status, String deliveryLotNo);

	/**
	 * 根据明细Uuid查询拣货信息
	 * 
	 * @param asnUuid
	 *            明细UUID
	 * @return List<拣货实体>
	 */
	List<DeliveryPackingList> getDeliveryPackingListByAsnUUID(String asnUuid);
	
	/**
	 * 根据明细、是否拣货查询拣货信息
	 * @param asnUuid
	 * @param isOrderPack
	 * @return
	 */
	List<DeliveryPackingList> getDeliveryPackingListByAsnUUID(String asnUuid,Integer status);

	/**
	 * 根据库存UUID和明细UUID查询拣货信息
	 * 
	 * @param stockUuid
	 *            库存UUID
	 * @param asnUuid
	 *            明细UUID
	 * @return 拣货实体
	 */
	DeliveryPackingList getDeliveryPackingListByStockUUID(String stockUuid, String asnUuid);

	/**
	 * 根据出货单号删除出库货物信息
	 * 
	 * @param deliveryNoteNo
	 *            出货单号
	 * @return 影响行数
	 */
	int deleteDeliveryPackingList(String deliveryNoteNo);

	/**
	 * 根据明细UUID删除出库货物信息
	 * 
	 * @param asnUuid
	 *            明细UUID
	 * @return 影响行数
	 */
	int deleteDeliveryPackingListByAsnUUID(String asnUuid);

	/**
	 * 根据UUID查询拣货信息
	 * 
	 * @param uuid
	 *            拣货UUID
	 * @return 拣货信息
	 */
	DeliveryPackingList getdiveryPackingListByUUID(String uuid);

	
	/**
	 * 根据拣货信息UUID修改修改拣货单为出库状态、出库批次
	 * 
	 * @param status
	 *            状态
	 * @param deliveryLotNo
	 *            出库批次号
	 * @param uuid
	 *            拣货UUID
	 * @return 影响行数
	 */
	int updateStatusByUUID(Integer status, String deliveryLotNo, String uuid, Date deliveryDate);

	/**
	 * 根据拣货信息UUID修改拣货状态为拣货
	 * 
	 * @param status
	 *            状态
	 * @param uuid
	 *            拣货UUID
	 * @return 影响行数
	 */
	int updateIsOrderPickingByUUID(Integer status, String uuid);

	/**
	 * 根据库存UUID查询拣货信息
	 * 
	 * @param stockUuid
	 *            库存UUID
	 * @return List<拣货实体>
	 */
	List<DeliveryPackingList> getDeliveryPackingListByStockUUID(String stockUuid);
	
	/**
	 * 查询拣货信息
	 * @param parameters
	 * @return
	 */
	List<DeliveryPackingListDto> getDeliveryPackingListDto( Map<String, Object> parameters);
	
	/**
	 * 根据运送单号修改拣货单为出库状态
	 * @param transportNoteNo
	 * @param status
	 * @return
	 */
	int updateStatusByTransportNoteNo(String transportNoteNo,Integer status,Date deliveryDate);
	
	/**
	 * 根据UUID修改运送单号
	 * @param transportNotrNo
	 * @param uuid
	 * @return
	 */
	int updateTranspotyNoteNoByUUID(String transportNoteNo,String uuid);
	
	/**
	 * 根据出货单号、库存UUID查询拣货信息
	 * @param deliveryNoteNo
	 * @param stockUuid
	 * @return
	 */
	DeliveryPackingList getDeliveryPackingListByNoteNoAndStockUUID(String deliveryNoteNo,String stockUuid);
	
	/**
	 * 根据运送单号查询拣货信息
	 * @param transportNotrNo	运送单号
	 * @return
	 */
	List<DeliveryPackingList> getDeliveryPackingListByTransportNoteNo(String transportNoteNo);

	/**
	 * 根据合并批次号和是否拣货查询拣货货物信息
	 * @param mergeLotNo	合并批次号
	 * @param status	是否拣货(null则不作为筛选条件)
	 * @return		List<拣货信息>
	 */
	List<DeliveryPackingList> getDeliveryPackingListByMergeLotNo(String mergeLotNo,Integer status);
	
	/**
	 * 根据单号查询拣货信息条数
	 * @param deliveryNoteNo	出库单号
	 * @return	拣货信息条数
	 */
	int getDeliveryPackingListCountsByNoteNo(String deliveryNoteNo);
	
	/**
	 * 根据条件统计已出库的拣货信息条数
	 * @param params	参数集合
	 * @return	已出库的拣货信息条数
	 */
	int countDeliveryPackingList(Map<String, Object> params);
	
	/**
	 * 根据条件查询已出库的拣货信息
	 * @param parames
	 * @param pageNumber
	 * @param pageSize
	 * @return 已出库的拣货信息Dto集合
	 */
	List<DeliveryPackingListDto> listDeliveryPackingListDto(Map<String, Object> parames,int pageNumber,int pageSize);
	
	/**
	 * 根据运送单号删除拣货信息中的运送单号
	 * @param transportNoteNo	运送单号
	 * @return	影响行数
	 */
	int removeTransportNoteNoByTransportNoteNo(String transportNoteNo);
	
	/**
	 * 根据拣货UUID移除拣货信息中的配送单号
	 * @param uuid	拣货UUID
	 * @return	影响行数
	 */
	int removeTransportNoteNoByUUID(String uuid);
	
	/**
	 * 根据合并批次号删除拣货信息
	 * @param mergeLotNo	合并批次号
	 * @return
	 */
	int removeByMergeLotNo(String mergeLotNo);
	
	
}
