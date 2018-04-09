package com.yorma.wms.dao.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yorma.wms.dao.base.api.BaseDao;
import com.yorma.wms.entity.ReceivingNote;
import com.yorma.wms.entity.dto.ReceivingNoteDto;

/**
 * 收货单Dao
 * 
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author su 2017年8月25日
 * @version 1.00.00
 * @date 2017年8月25日
 * @version V1.0
 */
public interface ReceivingNoteDao extends BaseDao<ReceivingNote> {

	/**
	 * 根据收货单号查询收货单状态
	 * 
	 * @param receivingNoteNo
	 *            收货单号
	 * @return    状态码
	 */
	Integer getStatus(String receivingNoteNo);

	/**
	 * 根据收货单号修改状态
	 * 
	 * @param receivingNoteNo
	 *            收货单号
	 * @param status
	 *            状态码
	 * @return    影响行数
	 */
	int updateStatus(String receivingNoteNo, int status);
	
	/**
	 * 更新状态并添加 最后修改时间、和最后修改人
	 * @param receivingNoteNo
	 * @param status
	 * @param lastDate
	 * @param lastName
	 * @return
	 */
	int updateStatus(String receivingNoteNo, int status,Date lastDate,String lastName);

	/**
	 * 查询收货单
	 * 
	 * @param map
	 *            查询条件
	 * @return    将查询（所有/符合条件）的数据放入的list集合
	 */
	List<ReceivingNoteDto> getReceivingNote(Map<String, Object> map, Map<String, Object> map1);
	
	/**
	 * 根据条件查询收货单信息
	 * @param map	条件一
	 * @param map1	条件二
	 * @param pageNumber	当前页
	 * @param pageSize		每页显示数
	 * @return	 将查询（所有/符合条件）的数据放入的list集合
	 */
	List<ReceivingNoteDto> getReceivingNote(Map<String, Object> map, Map<String, Object> map1, Integer pageNumber, Integer pageSize);

	/***
	 * 根据条件查询收货单信息条数
	 * @param map	条件一
	 * @param map1	条件二
	 * @return	收货单信息条数
	 */
	int getCounts(Map<String, Object> map, Map<String, Object> map1);
	
	/**
	 * 根据收货单号删除数据
	 * 
	 * @param receivingNoteNo
	 *            收货单号
	 * @return    影响行数
	 */
	int delete(String receivingNoteNo);
	
	/**
	 * 根据收货单号查询入库表头信息
	 * @param receivingNoteNo	收货单号
	 * @return	入库表头信息实体
	 */
	ReceivingNote getReceivingNoteByReceivingNoteNo(String receivingNoteNo);

}
