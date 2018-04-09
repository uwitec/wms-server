package com.yorma.wms.dao.api;

import java.util.List;

import com.yorma.wms.dao.base.api.BaseDao;
import com.yorma.wms.entity.TransportNoteList;
import com.yorma.wms.entity.dto.TransportNoteListDto;
import com.yorma.wms.entity.vo.TransportNoteListVO;

/**
 * 运送单明细Dao
 * 
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author su 2017年11月03日
 * @version 1.00.00
 * @date 2017年11月03日
 * @version V1.0
 */
public interface TransportNoteListDao extends BaseDao<TransportNoteList> {

	/**
	 * 根据运送单号查询运输配送明细
	 * @param transportNoteNo
	 * @return
	 */
	List<TransportNoteListDto>  getTransportNoteListDtoByTransportNoteNo(String transportNoteNo);
	
	/**
	 * 根据运送单号查询运输配送明细
	 * @param transportNoteNo
	 * @return List<运输配送明细VO>
	 */
	List<TransportNoteListVO>  listByTransportNoteNo(String transportNoteNo);
	
	/**
	 * 根据UUID删除运输配送明细
	 * @param uuid
	 * @return
	 */
	int removeTransportNoteListByUUID(String uuid);
	
	/**
	 * 根据uuid查询运输配送明细
	 * @param uuid
	 * @return
	 */
	TransportNoteList getTransportNoteListByUUID(String uuid);
	
	/**
	 * 根据拣货信息UUID删除配送运输明细
	 * @param deliveryPackingListUuid	拣货信息UUID
	 * @return	影响行数
	 */
	int removeTransportNoteListByPLUUID(String deliveryPackingListUuid);
	
	/**
	 * 根据配送单号删除配送明细
	 * @param TransportNoteNO	配送单号
	 * @return	影响行数
	 */
	int removeTransportNoteListByTransportNoteNO(String TransportNOteNO);
	
	/**
	 * 根据运送单号查询配送明细
	 * @param TransportNOteNO	运送单号
	 * @return	List<配送明细实体>
	 */
	List<TransportNoteList> getTransportNoteListByTransportNoteNo(String transportNoteNo);
	
}
