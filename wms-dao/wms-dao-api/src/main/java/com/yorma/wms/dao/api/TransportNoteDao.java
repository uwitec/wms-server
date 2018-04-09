package com.yorma.wms.dao.api;

import java.util.List;
import java.util.Map;

import com.yorma.wms.dao.base.api.BaseDao;
import com.yorma.wms.entity.TransportNote;
import com.yorma.wms.entity.dto.TransportNoteDto;

/**
 * 运送单Dao
 * 
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author su 2017年11月03日
 * @version 1.00.00
 * @date 2017年11月03日
 * @version V1.0
 */
public interface TransportNoteDao extends BaseDao<TransportNote>{

	/**
	 * 查询运送单信息
	 * @param parameters	条件参数
	 * @return
	 */
	List<TransportNoteDto> getTransportNoteDto(Map<String, Object> parameters);
	
	/**
	 * 根据条件查询运送单信息条数
	 * @param parameters 条件参数
	 * @return 运送单信息条数
	 */
	int getgetTransportNoteDtoCounts(Map<String, Object> parameters);
	
	/**
	 * 查询运送单信息(分页)
	 * @param parameters	条件参数
	 * @return
	 */
	List<TransportNoteDto> getTransportNoteDto(Map<String, Object> parameters,Integer pageNumber,Integer pageSize);;
	
	/**
	 * 根据运送单号查询运送单信息
	 * @param transportNoteNo	元送单号
	 * @return
	 */
	TransportNote getTransportNoteByTransportNoteNo(String transportNoteNo);
	
	/**
	 * 根据运送单号修改状态
	 * @param transportNoteNo
	 * @param status
	 * @return
	 */
	int updateStatusByTransportNoteNo(String transportNoteNo,int status);
	
	/**
	 * 根据运送单号查询运送单信息
	 * @param transportNoteNo
	 * @return
	 */
	TransportNoteDto getTransportNoteDtoByTransportNoteNo(String transportNoteNo);
	
	/**
	 * 根据运送单号删除运送单信息
	 * @param transportNoteNo	运送单号	
	 * @return
	 */
	int removeByTransportNoteNo(String transportNoteNo);
}
