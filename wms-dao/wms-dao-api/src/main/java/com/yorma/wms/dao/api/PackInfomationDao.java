package com.yorma.wms.dao.api;

import java.util.List;

import com.yorma.wms.dao.base.api.BaseDao;
import com.yorma.wms.entity.PackInfomation;
import com.yorma.wms.entity.dto.PackInfomationDto;

/**
 * 货物包装信息Dao
 * 
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author su 2017年8月25日
 * @version 1.00.00
 * @date 2017年8月25日
 * @version V1.0
 */
public interface PackInfomationDao extends BaseDao<PackInfomation> {

	/**
	 * 根据商品UUID查询包装信息
	 * 
	 * @param goodsUuid
	 * @return list<货物包装信息Dto>
	 */
	List<PackInfomationDto> getPackInfomationDtoByUUID(String goodsUuid);
	
	/**
	 * 根据商品UUID查询包装信息
	 * 
	 * @param goodsUuid
	 * @return list<货物包装信息>
	 */
	List<PackInfomation> getPackInfomationByUUID(String goodsUuid);

	/**
	 * 根据id查询包装信息
	 * 
	 * @param id
	 * @return 货物包装信息Dto
	 */
	PackInfomationDto getPackInfomationByid(Long id);

	/**
	 * 根据商品UUID删除包装信息
	 * 
	 * @param goodsUuid
	 *            商品UUID
	 * @return 影响行数
	 */
	int removeByGoodsUUID(String goodsUuid);
	
	/**
	 * 根据商品UUID查询该商品的默认包装键
	 * @param uuid	商品UUID
	 * @return	货物包装信息实体
	 */
	PackInfomation getPackInfomationIsDefaultByGoodsUUID(String uuid);

}
