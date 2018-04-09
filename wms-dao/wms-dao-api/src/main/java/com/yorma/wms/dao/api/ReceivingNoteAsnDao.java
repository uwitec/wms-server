package com.yorma.wms.dao.api;

import java.util.List;
import java.util.Map;

import com.yorma.wms.dao.base.api.BaseDao;
import com.yorma.wms.entity.ReceivingNoteAsn;
import com.yorma.wms.entity.dto.ReceivingNoteAsnDTO;

/**
 * 收货明细Dao
 * 
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author su 2017年8月25日
 * @version 1.00.00
 * @date 2017年8月25日
 * @version V1.0
 */
public interface ReceivingNoteAsnDao extends BaseDao<ReceivingNoteAsn> {

	/**
	 * 查询收货明细
	 * 
	 * @param receivingNoteNo
	 *            收货单
	 * @param status
	 *            状态
	 * @return List<收货明细实体>
	 */
	List<ReceivingNoteAsn> getReceivingNoteAsnByNoteNo(String receivingNoteNo, Integer status);

	/**
	 * 根据入库单号查询已分配的明细信息条数
	 * 
	 * @param receivingNoteNo
	 *            入库单号
	 * @return 信息条数
	 */
	Integer getReceivingNoteAsnCountByReceivingNoteNo(String receivingNoteNo);

	/**
	 * 根据明细UUID查询明细
	 * 
	 * @param uuid
	 *            明细UUID
	 * @return 明细实体
	 */
	ReceivingNoteAsn getReceivingNoteAsnByUUID(String uuid);

	/**
	 * 根据入库单号、是否到货查询合并后明细预告的预收总量。验收数量、合并批次号、品名、料号。包装数
	 * 
	 * @param receivingNoteNo
	 *            入库单号
	 * @param status
	 *            状态
	 * @return 明细预告实体集合
	 */
	List<ReceivingNoteAsn> getReceivingNoteAsnByreceivingNoteNo(String receivingNoteNo, Integer status);

	/**
	 * 根据合并批次号查询明细预告
	 * 
	 * @param mergeLotNo
	 *            合并批次号
	 * @return 明细预告实体集合
	 */
	List<ReceivingNoteAsn> getReceivingNoteAsnByMergerLotNo(String mergeLotNo);

	/**
	 * 根据商品UUID查询入库明细预告信息条数
	 * 
	 * @param goodsUuid
	 *            商品UUID
	 * @return 入库明细预告信息条数
	 */
	int getReceivingNoteAsnCountByGoodsUUID(String goodsUuid);
	
	/**
	 * 根据条件查询所有已结束的明细信息
	 * @param parames 条件集合
	 * @param pageNumber	
	 * @param pageSize
	 * @return 已结束的明细信息
	 */
	List<ReceivingNoteAsnDTO> listReceivingNoteAsn(Map<String, Object> parames, int pageNumber, int pageSize);
	
	/**
	 * 根据条件查询所有已结束的明细条数
	 * @param parames
	 * @return
	 */
	int countReceivingNoteAsn(Map<String, Object> parames);

	/**
	 * 根据uuid删除收货明细数据
	 * 
	 * @param UUID
	 *            明细UUID
	 * @return 影响行数
	 */
	int removeByUUId(String UUID);

	/**
	 * 根据收货单号删除收货明细数据
	 * 
	 * @param receivingNoteNo
	 *            收货单号
	 * @return 影响行数
	 */
	int delete(String receivingNoteNo);

	/**
	 * 根据明细UUID修改收货明细状态
	 * 
	 * @param status
	 *            状态
	 * @param asnUuid
	 *            明细UUID
	 * @return 影响行数
	 */
	int updateStatusByAsnUUID(Integer status, String asnUuid);

	/**
	 * 根据明细UUID修改合并批次号
	 * 
	 * @param mergeLotNo
	 *            合并批次号
	 * @param asnUuid
	 *            明细UUID
	 * @return 影响行数
	 */
	int updateMergeLotNoByAsnUUID(String mergeLotNo, String asnUuid);

	/**
	 * 根据合并批次号修改明细预告到货状态
	 * 
	 * @param status
	 *            状态
	 * @param mergeLotNo
	 *            合并批次号
	 * @return 影响行数
	 */
	int updateStatusByMergeLotNo(Integer status, String mergeLotNo);

}
