package com.yorma.wms.dao.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yorma.wms.dao.base.api.BaseDao;
import com.yorma.wms.entity.DeliveryNote;
import com.yorma.wms.entity.dto.DeliveryNoteDto;

/**
 * 出货单Dao
 * 
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author su 2017年8月25日
 * @version 1.00.00
 * @date 2017年8月28日
 * @version V1.0
 */
public interface DeliveryNoteDao extends BaseDao<DeliveryNote> {

	/**
	 * 根据条件查询出货单
	 * 
	 * @param map
	 *            查询条件
	 * @return 将查询（所有/符合条件）的数据放入的list集合
	 */
	List<DeliveryNoteDto> getDeliveryNoteAndDepart(Map<String, Object> map);
	
	/**
	 * 根据条件查询出货单 (分页)
	 * @param map	查询条件
	 * @param pageNumber	当前页
	 * @param pageSize		每页显示数
	 * @return	将查询（所有/符合条件）的数据放入的list集合
	 */
	List<DeliveryNoteDto> getDeliveryNoteAndDepart(Map<String, Object> map,Integer pageNumber, Integer pageSize);

	/**
	 * 根据条件查询出库单信息条数
	 * @param map	查询条件
	 * @return	出库单信息条数
	 */
	int getCounts(Map<String, Object> map);
	
	/**
	 * 根据出货单号查询状态码
	 * 
	 * @param deliveryNoteNo
	 *            出货单号
	 * @return 状态码
	 */
	int getStatus(String deliveryNoteNo);

	/**
	 * 根据出货单号修改状态码
	 * 
	 * @param deliveryNoteNo
	 *            出货单号
	 * @param status
	 *            状态码
	 * @return 影响行数
	 */
	int updateStatus(String deliveryNoteNo, int status);
	
	/**
	 * 根据出货单号修改状态码
	 * @param deliveryNoteNo 出货单号
	 * @param status  状态码
	 * @param lastName 修改人
	 * @param laseDate 修改时间
	 * @return
	 */
	int updateStatus(String deliveryNoteNo, int status,String lastName,Date laseDate);

	/**
	 * 根据出货单号删除
	 * 
	 * @param deliveryNoteNo
	 *            出货单号
	 * @return 影响行数
	 */
	int deleteDeliveryNoteByNoteNo(String deliveryNoteNo);
	
	/**
	 * 根据出货单号查询出货单数据
	 * @param deliveryNoteNo
	 * @return
	 */
	DeliveryNote getDeliveryNoteByDeliveryNoteNo(String deliveryNoteNo);

}
