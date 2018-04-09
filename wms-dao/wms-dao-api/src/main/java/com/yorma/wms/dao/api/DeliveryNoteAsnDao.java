package com.yorma.wms.dao.api;

import java.util.List;
import java.util.Map;

import com.yorma.wms.dao.base.api.BaseDao;
import com.yorma.wms.entity.DeliveryNoteAsn;
import com.yorma.wms.entity.dto.DeliveryNoteAsnDto;

/**
 * 出货明细Dao
 * 
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author su 2017年8月25日
 * @version 1.00.00
 * @date 2017年8月28日
 * @version V1.0
 */
public interface DeliveryNoteAsnDao extends BaseDao<DeliveryNoteAsn> {

	/**
	 * 查询出货明细及明细的分配总量
	 * 
	 * @param deliveryNoteNo
	 *            出库单号
	 * @return 将查询（所有/符合条件）的数据放入的list集合
	 */
	List<DeliveryNoteAsnDto> getDeliveryNoteAsnDtoByNoteNo(String deliveryNoteNo);
	
	/**
	 * 根据出货单号查询出库明细预告及预分配总量、拣货总量
	 * @param deliveryNoteNo
	 * @return
	 */
	List<DeliveryNoteAsnDto> getDeliveryNoteAsnDtoAndExpectQtySumByNoteNo(String deliveryNoteNo);

	/**
	 * 根据uuid查询预出货货物信息
	 * 
	 * @param uuid
	 *            明细UUID
	 * @return 明细实体
	 */
	DeliveryNoteAsn getDeliveryNoteAsnByUUID(String uuid);

	/**
	 * 根据出货单号查询明细分配方式
	 * @param deliveryNoteNo
	 * @param isMerged	
	 * @return
	 */
	List<DeliveryNoteAsn> getIsMergedByNoteNo(String deliveryNoteNo,Boolean isMerged);
	
	/**
	 * 根据出货单号查询出货单明细预告
	 * @param deliveryNoteNo	出货单号
	 * @return	出货单明细预告实体集合
	 */
	List<DeliveryNoteAsn> getDeliveryNoteAsnByDeliveryNoteNo(String deliveryNoteNo);
	
	/**
	 * 根据出库单号统计明细合并后的品名，合并批次号，商品UUID，料号，总预收数量，总出库数量
	 * @param deliveryNoteNo
	 * @return	明细DtoLIst
	 */
	List<DeliveryNoteAsnDto> getMergeDeliveryNoteAsn(String deliveryNoteNo);
	
	/**
	 * 根据商品UUID查询出库明细记录中未出库的信息条数
	 * @param goodsUuid 商品UUID
	 * @return 出库明细记录中未出库的信息条数
	 */
	int getDeliveryNoteAsnCountByGoodsUUID(String goodsUuid);
	
	/**
	 * 统计所有已结束的出库明细信息条数
	 * @param parames
	 * @return
	 */
	Integer countDeliveryNoteAsn(Map<String, Object> parames);
	
	/**
	 * 查询所有已结束的出库明细信息
	 * @param parames
	 * @param pageNumber
	 * @param pageSize
	 * @return	已结束的出库明细信息
	 */
	List<DeliveryNoteAsnDto> listDeliveryNoteAsn(Map<String, Object> parames, int pageNumber, int pageSize);
	
	/**
	 * 根据出货单号或明细UUID修改明细是否合并分配
	 * @param deliveryNoteNo	出库单号
	 * @param asnUuid		明细UUID
	 * @param isMerged		是否合并
	 * @return	影响行数
	 */
	int updateIsMergedByNoteNoOrAsnUUID(String deliveryNoteNo,String asnUuid, Boolean isMerged);

	/**
	 * 根据合并批次号修改明细分配方式为未分配和清空合并批次号
	 * @param MergeLotNo	合并批次号
	 * @return	影响行数
	 */
	Integer updateDeliveryNoteAsnByMergeLotNo(String mergeLotNo);
	
	/**
	 * 根据合并单号修改明细的实际分配量
	 * @param deliveryQty	明细的实际分配量
	 * @param mergeLotNo 合并单号
	 * @return
	 */
	Integer updateDeliveryQtyByMergeLotNo(Integer deliveryQty, String mergeLotNo);
	
	/**
	 * 根据uuid删除收货明细
	 * 
	 * @param uuid
	 *            明细UUID
	 * @return 影响行数
	 */
	int removeByUUID(String uuid);

	/**
	 * 根据出货单号删除收货明细
	 * 
	 * @param deliveryNoteNo
	 *            出货单号
	 * @return 影响行数
	 */
	int deleteDeliveryNoteAsn(String deliveryNoteNo);
	
	
}
