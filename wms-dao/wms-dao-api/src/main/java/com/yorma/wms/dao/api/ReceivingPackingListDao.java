package com.yorma.wms.dao.api;

import java.util.Date;
import java.util.List;

import com.yorma.wms.dao.base.api.BaseDao;
import com.yorma.wms.entity.ReceivingPackingList;

/**
 * 入库货物信息Dao
 * 
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author su 2017年8月25日
 * @version 1.00.00
 * @date 2017年8月25日
 * @version V1.0
 */
public interface ReceivingPackingListDao extends BaseDao<ReceivingPackingList> {

	/**
	 * 根据条件查询入库货物信息
	 * @param receivingNoteNo	收货单号
	 * @param status			是否入库
	 * @param startDate			开始时间
	 * @param endDate			截止时间
	 * @return     List<入库货物信息实体>
	 */
	List<ReceivingPackingList> getReceivingPackingListByNoteNo(String receivingNoteNo,Integer status,Date startDate,Date endDate);
	
	
	/**
	 * 根据明细UUID删除入库货物信息数据
	 * @param asnUuid
	 * @return		影响行数
	 */
	int removeByAsnUUId(String asnUuid);
	
	/**
	 * 根据收货单号删除入库货物信息数据
	 * @param receivingNoteNo
	 * @return		影响行数
	 */
	int delete(String receivingNoteNo);
	
	/**
	 * 根据入库货物信息UUID查询数据
	 * @param uuid
	 * @return
	 */
	ReceivingPackingList getReceivingPackingListByUUID(String uuid);
	
	/**
	 * 根据入库货物信息UUID修改状态
	 * @param status
	 * @param uuid
	 * @return
	 */
	int updateStatusByUUID(Integer status,String uuid);
	
	/**
	 * 根据合并批次号删除入库货物信息
	 * @param mergeLotNo	合并批次号
	 * @return	影响行数
	 */
	int deleteReceivingPackingListByMergeLotNo(String mergeLotNo);
	
	/**
	 * 根据合并批次号查询入库货物信息
	 * @param mergeLotNo	合并批次号
	 * @return	 List<入库货物信息实体>
	 */
	List<ReceivingPackingList> getReceivingPackingListByMergeLotNo(String mergeLotNo);
	
	/**
	 * 根据合并批次号查询已上架的入库货物信息
	 * @param mergeLotNo	合并批次号
	 * @return	List<入库货物信息实体>
	 */
	List<ReceivingPackingList> getLocationByMergeLotNo(String mergeLotNo);
	
	/**
	 * 根据状态和入库信息UUID查询入库货物信息条数
	 * @param status	状态
	 * @param uuid		入库货物信息UUID
	 * @return			信息条数
	 */
	Integer getReceivingPackingListByStatusAndUUID(Integer status,String uuid);
	
}
