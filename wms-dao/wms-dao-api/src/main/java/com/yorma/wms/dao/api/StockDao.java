package com.yorma.wms.dao.api;

import java.util.List;
import java.util.Map;

import com.yorma.wms.dao.base.api.BaseDao;
import com.yorma.wms.entity.Location;
import com.yorma.wms.entity.Stock;
import com.yorma.wms.entity.dto.InAndOutOfStockDTO;
import com.yorma.wms.entity.dto.StockDto;
import com.yorma.wms.entity.vo.StockVO;

/**
 * 库存Dao
 * 
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author su 2017年8月10日
 * @version 1.00.00
 * @date 2017年8月10日
 * @version V1.0
 */
public interface StockDao extends BaseDao<Stock>{

	/**
	 * 查询库存
	 * @param params
	 *            查询条件
	 * @return 将查询（所有/符合条件）的数据放入的list集合
	 */
	List<Stock> getStock(Map<String, Object> params);
	
	/**
	 * 查询库存
	 * @param params
	 *            查询条件
	 * @return List<StockVO>
	 */
	List<StockVO> listStockVO(Map<String, Object> params);
	
	/**
	 * 根据条件查询库存
	 * @param params 查询条件
	 * @param pageNumber	当前页
	 * @param pageSize	每页显示数
	 * @return	将查询（所有/符合条件）的数据放入的list集合
	 */
	List<Stock> getStock(Map<String, Object> parames,Integer pageNumber,Integer pageSize);
	
	/**
	 * 库存汇总查询（分页）
	 * @param parames
	 * @param pageNumber
	 * @param pageSize
	 * @return 将查询（所有/符合条件）的数据放入的list集合
	 */
	List<StockVO> getStockStatistics(Map<String, Object> parames,Integer pageNumber,Integer pageSize);
	
	/**
	 * 根据条件查询库存条数
	 * @param parames	查询条件
	 * @return	库存信息条数
	 */
	int getCounts(Map<String, Object> parames);
	
	/**
	 * 库存汇总查询行数
	 * @param parames	查询条件
	 * @return	库存汇总条数
	 */
	int countStockStatistics(Map<String, Object> parames);
	
	/**
	 * 根据商品UUID查询、住单位代码、出货类型、仓库代码查询可分配库存(choise：true为查询全部库存，false为查询不为零的库存)
	 * @param ownerCode
	 * @param pn
	 * @return		List<库存Dto>
	 */
	List<StockDto> getStockByGoodsUUID(String goodsUuid,String mu,String deliveryStrategy,Boolean choise,String warehouseCode); 
	
	/**
	 * 根据出货类型等条件查询可分配库存(choise：true为查询全部库存，false为查询不为零的库存)
	 * @param mu
	 * @param deliveryStrategy
	 * @param choise
	 * @param paramtes	条件集合
	 * @return
	 */
	List<StockDto> getStockByGoodsUUID(String deliveryStrategy,Boolean choise,Map<String, String> paramtes); 

	/**
	 * 根据明细UUID查询已拣货的库存信息
	 * @param asnUuid
	 * @return		List<库存Dto>
	 */
	List<StockDto> getStockAndDeliveryPackingListByAsnUUID(String asnUuid);
	
	/**
	 * 根据出货单号查询已拣货的库存信息
	 * @param deliveryNoteNo
	 * @return	List<StockDto>
	 */
	List<StockDto> getStockAndDeliveryPackingListByDeliveryNoteNo(String deliveryNoteNo);
	
	/**
	 * 根据明细UUID查询已拣货的库存信息和拣货分配量
	 * @param asnUuid
	 * @return		Map<库存UUID, 拣货分配量>
	 */
	Map<String, Integer> getStockUUIDAndDeliveryPackingListByAsnUUID(String asnUuid);
	
	
	/**
	 * * 根据出货单号和商品UUID查询已拣货的库存信息和拣货分配量
	 * @param deliveryNoteNO	出货单号
	 * @param goodsUuid			商品UUID
	 * @return	Map<库存UUID, 拣货分配量>
	 */
	Map<String, Integer> getStockUUIDAndDeliveryPackingListByNoteNoAndGoodsUUID(String deliveryNoteNO,String goodsUuid);
	
	/**
	 * 根据库存uuid查询数据
	 * @param uuid
	 * @return		库存实体
	 */
	Stock getStockByUUID(String uuid);
	
	/**
	 * 根据UUID修改预分配量
	 * @param preAllocationStock
	 * @param UUID
	 * @return		影响行数
	 */
	int updatePreAllocationStock(int preAllocationStock,String UUID,Integer version);
	
	/**
	 * 根据id修改已分配库量、预分配量
	 * @param allocatedStock
	 * @param preAllocationStock
	 * @param uuid
	 * @return		影响行数
	 */
	int updateAllocatedStock(int allocatedStock,int preAllocationStock,String uuid,Integer version);
	
	/**
	 * 根据id修改已分配库量、在库量
	 * @param allocatedStock
	 * @param inStock
	 * @param id
	 * @return		影响行数
	 */
	int updateInStock(int allocatedStock,int inStock,String uuid ,Integer version);
	
	/**
	 * 根据商品UUID、仓库编码，库区编码、储位编码、主单位,货主查询库存信息
	 * @param goodsUuid		商品UUID
	 * @param warehouse		仓库编码
	 * @param storage		库区编码
	 * @param location		储位编码
	 * @param mu			主单位
	 * @return	List<库存信息实体>
	 */
	List<Stock> getStockByGoodsUUIDAndWarehouse(String goodsUuid,String warehouse,String storage,String location,String mu);
	
	/**
	 * 根据库存UUID删除库存信息
	 * @param uuid	库存UUID
	 * @return	影响行数
	 */
	int removeStockByUUID(String uuid,int version);
	
	/**
	 * 根据入库货物信息UUID查询库存信息
	 * @param pLUuid	入库货物信息UUID
	 * @return	库存信息实体
	 */
	Stock getStockByPLUUID(String pLUuid);
	
	/**
	 * 根据商品UUID、料号、货主、仓库查询库存储位和库区
	 * @param goodsUuid	商品UUID
	 * @param warehouse	仓库代码
	 * @return	库存实体集合
	 */
	List<Location> getStockStorageAndLocatiomByGoodsUUIDAndWarehouse(String goodsUuid,String warehouse,String location);
	
	/**
	 * 柑橘商品UUID、仓库、库区、储位查询库存中的主单位
	 * @param goodsUuid	商品UUID
	 * @param warehouse	仓库
	 * @param stroage	库区
	 * @param location	储位
	 * @return 	List<String>
	 */
	List<String> getStockMuByGoodsUUIDAndWarehouse(String goodsUuid,String warehouse,String storage,String location);
	
	/**
	 * 根据商品UUID查询库存为0的信息条数
	 * @param goodsUuid	商品UUID
	 * @return	库存为0的信息条数
	 */
//	Integer getStockCountsByGoodsUUID(String goodsUuid);
	
	/**
	 * 根据单位代码查询计量单位
	 * @param unitNumber 单位代码
	 * @return	计量单位
	 */
	String getUnit(String unitNumber);
	
	/**
	 * 根据储位代码查询该储位下库存不为0的信息条数
	 * @param locationCode	储位代码
	 * @return	库存条数
	 */
	Integer getStockCountsByLocationCode(String locationCode,String warehouse,String storage);
	
	/**
	 * 查询料号明细收发存信息条数
	 * @param parames  参数集合
	 * @return 料号明细收发存信息条数
	 */
	Integer countInAndOutOfStock(Map<String, Object> parames);
	
	/**
	 * 查询料号明细收发存信息
	 * @param parames 参数集合
	 * @param pageNumber 当前页
	 * @param pageSize 每页显示数
	 * @return 料号明细收发存信息DTO集合
	 */
	List<InAndOutOfStockDTO> listInAndOutOfStockDTO(Map<String, Object> parames,int pageNumber,int pageSize);
	
}
