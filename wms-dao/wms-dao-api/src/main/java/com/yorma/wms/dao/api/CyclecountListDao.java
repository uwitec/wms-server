package com.yorma.wms.dao.api;

import java.util.List;
import java.util.Map;

import com.yorma.wms.dao.base.api.BaseDao;
import com.yorma.wms.entity.CyclecountList;


/**
 * 盘点明细信息Dao 接口类
 * 
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author su 
 * @version 1.00.00
 * @date 2017年12月06日
 * @version V1.0
 */
public interface CyclecountListDao extends BaseDao<CyclecountList>  {

	/**
	 * 根据条件查询盘点明细信息
	 * @param parames	参数聚合
	 * @return	List<盘点明细信息>
	 */
	List<CyclecountList> getCyclecountList(Map<String, Object> parames);
	
	/**
	 * 根据盘点单号删除盘点明细信息
	 * @param cyclecountNo	盘点单号
	 * @return	影响行数
	 */
	int removeCyclecountListByCyclecountNo(String cyclecountNo);
	
	/**
	 * 根据盘点单号查询盘点明细信息
	 * @param cyclecountNo	盘点单号
	 * @return	List<盘点明细信息>
	 */
	List<CyclecountList> getCyclecountListByCyclecountNo(String cyclecountNo);
	
	/**
	 * 根据id修改盘点明细的入库单号
	 * @param receivingNoteNo	入库单号
	 * @param id	盘点明细ID
	 * @return	影响行数
	 */
	int updateReceivingNoteNoById(String receivingNoteNo,Long id);
	
	/**
	 * 根据id修改盘点明细的出库单号
	 * @param deliveryNoteNo	出库单号
	 * @param id	盘点明细ID
	 * @return	影响行数
	 */
	int updateDeliveryNoteNoById(String deliveryNoteNo,Long id);
	
}
