package com.yorma.wms.dao.api;

import java.util.List;
import java.util.Map;

import com.yorma.common.entity.dto.Parameter;
import com.yorma.wms.dao.base.api.BaseDao;

//import com.alibaba.fastjson.JSONObject;

import com.yorma.wms.entity.Goods;
import com.yorma.wms.entity.dto.GoodsDto;

/**
 * 商品基础资料Dao
 * 
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author su 2017年8月7日
 * @version 1.00.00
 * @date 2017年8月07日
 * @version V1.0
 */
public interface GoodsDao extends BaseDao<Goods> {
	
	/**
	 * 查询商品基础资料
	 * 
	 * @param map
	 *            查询条件
	 * @return List<商品Dto实体>
	 */
	List<GoodsDto> getGoods(Map<String, Object> map);
	
	/**
	 * 商品资料查询（分页）
	 * 
	 * @param map
	 *            参数
	 * @param pageNumber
	 *            当前页
	 * @param pageSize
	 *            每页显示条数
	 * @return List<商品Dto实体>
	 */
	List<GoodsDto> getGoods(Map<String, Object> map, Integer pageNumber, Integer pageSize);
	
	/**
	 * 根据条件查询商品信息条数
	 * @param map	查询条件
	 * @return	商品信息条数
	 */
	int getCounts(Map<String, Object> map);

	/**
	 * 商品资料查询（分页）
	 * 
	 * @param parameters
	 *            参数
	 * @param pageNumber
	 *            当前页-1
	 * @param pageSize
	 *            每页显示条数
	 * @return List<商品Dto实体>
	 */
	List<GoodsDto> getGoods(List<Parameter> parameters, Integer pageNumber, Integer pageSize);

	/**
	 * 根据id查询商品资料
	 * 
	 * @param id
	 *            商品ID
	 * @return 商品Dto
	 */
	GoodsDto getGoodsById(Long id);

	/**
	 * 根据UUID查询商品资料
	 * 
	 * @param uuid
	 *            商品UUID
	 * @return 商品Dto
	 */
	GoodsDto getGoodsDtoByUUID(String uuid);
	
	/**
	 * 根据UUID查询商品资料
	 * @param uuid 商品UUID
	 * @return 商品实体
	 */
	Goods getGoodsByUUID(String uuid);
	
	/**
	 * 根据货主代码和料号查询商品信息
	 * @param departCode
	 * @param pn
	 * @return	商品实体
	 */
	Goods getGoodsByDepartCodeAndPn(String departCode , String pn);
	
	/**
	 * 查询说有商品UUID
	 * @return	商品UUID集合
	 */
	List<String> getUUIDByGoodsUUID();
	
	/**
	 * 查询商品基础资料及默认包装键（含分页）
	 * 
	 * @param map
	 *            查询条件
	 * @return List<商品Dto实体>
	 */
	List<GoodsDto> getGoodsAndPackInfomation(Map<String, Object> map);

}
