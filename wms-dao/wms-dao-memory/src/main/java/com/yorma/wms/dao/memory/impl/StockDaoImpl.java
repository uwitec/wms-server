package com.yorma.wms.dao.memory.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yorma.wms.dao.api.StockDao;
import com.yorma.wms.dao.memory.base.BaseDaoImpl;
import com.yorma.wms.entity.Location;
import com.yorma.wms.entity.Stock;
import com.yorma.wms.entity.dto.InAndOutOfStockDTO;
import com.yorma.wms.entity.dto.StockDto;
import com.yorma.wms.entity.vo.StockVO;

import cn.ffcs.memory.BeanHandler;
import cn.ffcs.memory.BeanListHandler;
import cn.ffcs.memory.ColumnHandler;
import cn.ffcs.memory.ColumnListHandler;

/**
 * 
 * @Description:库存Dao层实现
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author 苏飚 2017年8月10日
 * @version 1.00.00
 * @history:
 *
 */
public class StockDaoImpl extends BaseDaoImpl<Stock>implements StockDao {

	private static final Logger logger = LoggerFactory.getLogger(StockDaoImpl.class);
	
	/**
	 * 库存查询操作
	 * 
	 * @return：查询（所有/符合条件）的数据
	 */
	@Override
	public List<Stock> getStock(Map<String, Object> map) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<>();

		sql.append("SELECT * FROM stock  where 1=1  ");
		//where之后的查询条件
		String prerequisite = "";
		for (String key : map.keySet()) {
			if (map.get(key) != null && !"".equals(map.get(key)) && !"%%".equals(map.get(key))
					&& !"% %".equals(map.get(key))) {
				prerequisite += " and " + key + " ? ";
				params.add(map.get(key));
			}
		}
		sql.append(prerequisite);
		sql.append("ORDER BY WAREHOUSE,`STORAGE`,LOCATION ");
		return memory.query(sql, new BeanListHandler<Stock>(Stock.class), params);
	}

	/**
	 * 查询库存
	 * @param params
	 *            查询条件
	 * @return List<StockVO>
	 */
	@Override
	public List<StockVO> listStockVO(Map<String, Object> parames) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<>();

		sql.append("SELECT * FROM stock  where 1=1  ");
		//where之后的查询条件
		String prerequisite = "";
		for (String key : parames.keySet()) {
			if (parames.get(key) != null && !"".equals(parames.get(key)) && !"%%".equals(parames.get(key))
					&& !"% %".equals(parames.get(key))) {
				prerequisite += " and " + key + " ? ";
				params.add(parames.get(key));
			}
		}
		sql.append(prerequisite);
		sql.append(" ORDER BY pn ");
		if (logger.isDebugEnabled()) {
			logger.debug("Dao:方法[listStockVO] sql 语句:{}", sql);
			logger.debug("Dao:方法[listStockVO] params 参数:{}", params);
		}
		return memory.query(sql, new BeanListHandler<StockVO>(StockVO.class), params);
	}
	
	/**
	 * 根据条件查询库存
	 * @param params 查询条件
	 * @param pageNumber	当前页
	 * @param pageSize	每页显示数
	 * @return	将查询（所有/符合条件）的数据放入的list集合
	 */
	@Override
	public List<Stock> getStock(Map<String, Object> parames, Integer pageNumber, Integer pageSize) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<>();

		sql.append("SELECT * FROM stock  where 1=1  ");
		//where之后的查询条件
		String prerequisite = "";
		for (String key : parames.keySet()) {
			if (parames.get(key) != null && !"".equals(parames.get(key)) && !"%%".equals(parames.get(key))
					&& !"% %".equals(parames.get(key))) {
				prerequisite += " and " + key + " ? ";
				params.add(parames.get(key));
			}
		}
		sql.append(prerequisite);
		sql.append("ORDER BY ENTRY_DATE, packaging_no,WAREHOUSE,`STORAGE`,LOCATION");
		if (pageNumber !=null && pageSize!=null) {
			sql.append(" LIMIT ?,?");
			params.add((pageNumber-1)*pageSize);
			params.add(pageSize);
		}
		return memory.query(sql.toString(), new BeanListHandler<Stock>(Stock.class), params.toArray());
	}
	
	/**
	 * 库存汇总查询（分页）
	 * @param parames
	 * @param pageNumber
	 * @param pageSize
	 * @return 将查询（所有/符合条件）的数据放入的list集合
	 */
	@Override
	public List<StockVO> getStockStatistics(Map<String, Object> parames, Integer pageNumber, Integer pageSize) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<>();

		sql.append("SELECT  stock.*,SUM(stock.IN_STOCK) AS sumInStock, SUM(stock.PRE_ALLOCATION_STOCK) AS sumPreAllocationStock, SUM(stock.ALLOCATED_STOCK) AS sumAllocationStock FROM stock  WHERE 1=1 ");
		//where之后的查询条件
		String prerequisite = "";
		for (String key : parames.keySet()) {
			if (parames.get(key) != null && !"".equals(parames.get(key)) && !"%%".equals(parames.get(key))
					&& !"% %".equals(parames.get(key))) {
				prerequisite += " and " + key + " ? ";
				params.add(parames.get(key));
			}
		}
		sql.append(prerequisite);
		sql.append("GROUP BY warehouse, GOODS_UUID, MU, OWNER_CODE,PN ORDER BY WAREHOUSE,`STORAGE`,LOCATION ");
		params.add((pageNumber-1)*pageNumber);
		params.add(pageSize);
		sql.append(" limit ?,? ");
		
		return memory.query(sql, new BeanListHandler<StockVO>(StockVO.class), params);
	}
	
	/**
	 * 根据条件查询库存条数
	 * @param parames	查询条件
	 * @return	库存信息条数
	 */
	@Override
	public int getCounts(Map<String, Object> parames) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<>();

		sql.append("SELECT COUNT(*) FROM (SELECT * FROM stock  where 1=1  ");
		//where之后的查询条件
		String prerequisite = "";
		for (String key : parames.keySet()) {
			if (parames.get(key) != null && !"".equals(parames.get(key)) && !"%%".equals(parames.get(key))
					&& !"% %".equals(parames.get(key))) {
				prerequisite += " and " + key + " ? ";
				params.add(parames.get(key));
			}
		}
		sql.append(prerequisite);
		sql.append(" ) stock");
		
		return memory.query(sql.toString(), new ColumnHandler<Integer>(Integer.class), params.toArray());
	}

	/**
	 * 库存汇总查询行数
	 * @param parames	查询条件
	 * @return	库存汇总条数
	 */
	@Override
	public int countStockStatistics(Map<String, Object> parames) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<>();
		sql.append("SELECT COUNT(*) FROM (SELECT * FROM stock  where 1=1  ");
		String prerequisite = "";
		for (String key : parames.keySet()) {
			if (parames.get(key) != null && !"".equals(parames.get(key)) && !"%%".equals(parames.get(key))
					&& !"% %".equals(parames.get(key))) {
				prerequisite += " and " + key + " ? ";
				params.add(parames.get(key));
			}
		}
		sql.append(prerequisite);
		sql.append(" GROUP BY warehouse, GOODS_UUID, MU, OWNER_CODE,PN )s");
		return memory.query(sql.toString(), new ColumnHandler<Integer>(Integer.class), params.toArray());
	}
	
	/**
	 * 根据商品UUID查询、住单位代码、出货类型、仓库代码查询可分配库存(choise：true为查询全部库存，false为查询不为零的库存)
	 */
	@Override
	public List<StockDto> getStockByGoodsUUID(String goodsUuid, String mu, String deliveryStrategy, Boolean choise,
			String warehouseCode) {
		StringBuffer sql = new StringBuffer();
		if (choise) {
			sql.append("SELECT depart.DEPART_NAME, stock.* FROM stock LEFT JOIN sys_depart depart on stock.OWNER_CODE=depart.DEPART_CODE where stock.GOODS_UUID=? and stock.MU=? and stock.WAREHOUSE=?") ;
		} else {
			sql.append("SELECT depart.DEPART_NAME, stock.* FROM stock LEFT JOIN sys_depart depart on stock.OWNER_CODE=depart.DEPART_CODE where stock.IN_STOCK-stock.ALLOCATED_STOCK-stock.PRE_ALLOCATION_STOCK <> 0 and stock.GOODS_UUID=? and stock.MU=? and stock.WAREHOUSE=?") ;
		}
		
		// 2017-10-25 同一收货单不分先后，先出散装
		if (deliveryStrategy != null && !"".equals(deliveryStrategy)) {
			
			switch (deliveryStrategy) {
			case "先进先出":
				sql.append("ORDER BY RECEIVING_NOTE_NO ,ENTRY_QTY") ;
				break;
			case "后进先出":
				sql.append("ORDER BY RECEIVING_NOTE_NO desc ,ENTRY_QTY") ;
				break;
			default:// 默认先进先出
				sql.append("ORDER BY RECEIVING_NOTE_NO ,ENTRY_QTY") ;
				break;
			}
		}
		return memory.query(sql.toString(), new BeanListHandler<StockDto>(StockDto.class),
				new Object[] { goodsUuid, mu, warehouseCode });
	}
	
	/**
	 * 根据商品UUID查询、住单位代码、出货类型、仓库代码查询可分配库存(choise：true为查询全部库存，false为查询不为零的库存)
	 */
	@Override
	public List<StockDto> getStockByGoodsUUID(String deliveryStrategy, Boolean choise,
			Map<String, String> paramtes) {
		StringBuffer sql = new StringBuffer();
		List<Object> params=new ArrayList<>();
		if (choise) {
			sql.append("SELECT depart.DEPART_NAME, stock.* FROM stock LEFT JOIN sys_depart depart on stock.OWNER_CODE=depart.DEPART_CODE where 1=1  stock.MU=? ") ;
		} else {
			sql.append("SELECT depart.DEPART_NAME, stock.* FROM stock LEFT JOIN sys_depart depart on stock.OWNER_CODE=depart.DEPART_CODE where stock.IN_STOCK-stock.ALLOCATED_STOCK-stock.PRE_ALLOCATION_STOCK <> 0 ") ;
		}
		String manufactureLotNo=null;
		for (String key : paramtes.keySet()) {
			if ("manufacture_Lot_No".equals(key)) {
				manufactureLotNo=paramtes.get(key);
				paramtes.put("manufacture_Lot_No", null);
			}
			if (paramtes.get(key) != null && !"".equals(paramtes.get(key))) {
				sql.append(" and "+key+" = ? ");
				params.add(paramtes.get(key));
			}
		}
		// 2017-10-25 同一收货单不分先后，先出散装
		if (deliveryStrategy != null && !"".equals(deliveryStrategy)) {
			
			switch (deliveryStrategy) {
			case "先进先出":
				sql.append(" ORDER BY RECEIVING_NOTE_NO ,ENTRY_QTY") ;
				break;
			case "后进先出":
				sql.append(" ORDER BY RECEIVING_NOTE_NO desc ,ENTRY_QTY") ;
				break;
			case "按批次号":
				sql.append(" and manufacture_Lot_No= ? ORDER BY ENTRY_DATE, packaging_no") ;
				params.add(manufactureLotNo);
				break;
			default:// 默认先进先出
				sql.append(" ORDER BY RECEIVING_NOTE_NO ,ENTRY_QTY") ;
				break;
			}
		}
		return memory.query(sql.toString(), new BeanListHandler<StockDto>(StockDto.class),params.toArray());
	}

	/**
	 * 根据明细UUID查询已拣货的库存信息
	 */
	@Override
	public List<StockDto> getStockAndDeliveryPackingListByAsnUUID(String asnUuid) {
		String sql = "select deList.DELIVERY_QTY as deliveryQty, stock.* from stock stock ,delivery_packing_list deList where stock.UUID=deList.STOCK_UUID and deList.ANS_UUID=?";
		return memory.query(sql, new BeanListHandler<StockDto>(StockDto.class), asnUuid);
	}

	/**
	 * 根据出货单号查询已拣货的库存信息
	 */
	@Override
	public List<StockDto> getStockAndDeliveryPackingListByDeliveryNoteNo(String deliveryNoteNo) {
		String sql="SELECT deList.DELIVERY_QTY AS deliveryQty, stock.* FROM stock stock, delivery_packing_list deList WHERE stock.UUID = deList.STOCK_UUID and status=0 AND deList.DELIVERY_NOTE_NO =?";
		return memory.query(sql, new BeanListHandler<StockDto>(StockDto.class), deliveryNoteNo);
	}
	
	/**
	 * 根据明细UUID查询已拣货的库存信息和拣货分配量
	 */
	@Override
	public Map<String, Integer> getStockUUIDAndDeliveryPackingListByAsnUUID(String asnUuid) {
		String sql = "select stock.uuid, deliveryPackingList.DELIVERY_QTY as deliveryQty from stock stock ,delivery_packing_list deliveryPackingList where stock.UUID=deliveryPackingList.STOCK_UUID and deliveryPackingList.status=0 and deliveryPackingList.ANS_UUID=?";
		List<StockDto> stockDtoList = memory.query(sql, new BeanListHandler<>(StockDto.class), asnUuid);
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (StockDto stockDto : stockDtoList) {
			if (!map.containsKey(stockDto.getUuid())) {
				map.put(stockDto.getUuid(), stockDto.getDeliveryQty());
			}
		}
		return map;
	}

	
	/**
	 * 根据出货单号和商品UUID查询已拣货的库存信息和拣货分配量
	 */
	@Override
	public Map<String, Integer> getStockUUIDAndDeliveryPackingListByNoteNoAndGoodsUUID(String deliveryNoteNO,String goodsUuid) {
		String sql="SELECT stock.uuid, deliveryPackingList.DELIVERY_QTY AS deliveryQty FROM stock stock, delivery_packing_list deliveryPackingList WHERE stock.UUID = deliveryPackingList.STOCK_UUID and deliveryPackingList.status=0 AND deliveryPackingList.DELIVERY_NOTE_NO=? and deliveryPackingList.GOODS_UUID=?";
		List<StockDto> stockDtoList = memory.query(sql, new BeanListHandler<>(StockDto.class), new Object[]{deliveryNoteNO,goodsUuid});
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (StockDto stockDto : stockDtoList) {
			if (!map.containsKey(stockDto.getUuid())) {
				map.put(stockDto.getUuid(), stockDto.getDeliveryQty());
			}
		}
		return map;
	}
	
	
	/**
	 * 根据uuid查询
	 */
	@Override
	public Stock getStockByUUID(String uuid) {
		String sql = "select * from stock where UUID=?";
		if (null != connection) {
			return memory.query(connection, sql, new BeanHandler<Stock>(Stock.class), uuid);
		}
		return memory.query(sql, new BeanHandler<Stock>(Stock.class), uuid);
	}

	/**
	 * 根据UUID修改预分配量
	 */
	// @Override
	public int updatePreAllocationStock(int preAllocationStock, String UUID, Integer version) {
		if (version == null) {
			String sql = "update stock set PRE_ALLOCATION_STOCK=? where UUID=? ";
			return memory.update(connection, sql, new Object[] { preAllocationStock, UUID });
		} else {
			String sql = "update stock set PRE_ALLOCATION_STOCK=?,version=version+1 where UUID=? and version=? ";
			return memory.update(connection, sql, new Object[] { preAllocationStock, UUID, version });
			
		}
	}

	/**
	 * 根据id修改已分配库量、预分配量
	 */
	@Override
	public int updateAllocatedStock(int allocatedStock, int preAllocationStock, String uuid, Integer version) {
		String sql = "update stock set ALLOCATED_STOCK=?,PRE_ALLOCATION_STOCK=?,version=version+1 where UUID=? and version=? ";

		return memory.update(connection, sql, new Object[] { allocatedStock, preAllocationStock, uuid, version });
	}

	/**
	 * 根据id修改已分配库量、在库量
	 */
	@Override
	public int updateInStock(int allocatedStock, int inStock, String uuid, Integer version) {
		String sql = "update stock set ALLOCATED_STOCK=?,IN_STOCK=?,version=version+1 where UUID=? and version=?";

		return memory.update(connection, sql, new Object[] { allocatedStock, inStock, uuid, version });
	}
	
	/**
	 * 根据商品UUID、仓库编码，库区编码、储位编码查询库存信息
	 * @param goodsUuid		商品UUID
	 * @param warehouse		仓库编码
	 * @param storage		库区编码
	 * @param location		储位编码
	 * @return	List<库存信息实体>
	 */
	@Override
	public List<Stock> getStockByGoodsUUIDAndWarehouse(String goodsUuid, String warehouse, String storage, String location,String mu) {
		String sql="SELECT * FROM stock WHERE GOODS_UUID=? and WAREHOUSE=? and STORAGE=? and LOCATION=? and MU=? and IN_STOCK!=0";
		if (null != connection) {
			return memory.query(connection, sql, new BeanListHandler<Stock>(Stock.class), new Object[]{goodsUuid,warehouse,storage,location,mu});
		}
		return memory.query(sql, new BeanListHandler<Stock>(Stock.class), new Object[]{goodsUuid,warehouse,storage,location,mu});
	}

	/**
	 * 根据库存UUID删除库存信息
	 * @param uuid	库存UUID
	 * @return	影响行数
	 */
	@Override
	public int removeStockByUUID(String uuid,int version) {
		String sql="DELETE FROM stock WHERE stock.UUID=? and stock.VERSION=?";
		if (null != connection) {
			return memory.update(connection, sql, new Object[]{uuid,version});
		}
		return memory.update(sql, new Object[]{uuid,version});
	}
	
	/**
	 * 根据入库货物信息UUID查询库存信息
	 * @param pLUuid	入库货物信息UUID
	 * @return	库存信息实体
	 */
	@Override
	public Stock getStockByPLUUID(String pLUuid) {
		String sql="SELECT * from stock where PL_UUID=?";
		if (null != connection) {
			return memory.query(connection, sql, new BeanHandler<Stock>(Stock.class), pLUuid);
		}
		return memory.query(sql, new BeanHandler<Stock>(Stock.class), pLUuid);
	}
	
	/**
	 * 根据商品UUID、料号、货主、仓库查询库存储位和库区
	 * @param goodsUuid	商品UUID
	 * @param warehouse	仓库代码
	 * @return	库存实体集合
	 */
	@Override
	public List<Location> getStockStorageAndLocatiomByGoodsUUIDAndWarehouse(String goodsUuid, String warehouse,String location) {
		StringBuffer sql = new StringBuffer();
		List<Object> params=new ArrayList<>();
		sql.append("select STORAGE as storageCode,LOCATION as code from stock WHERE 1=1");
		if (goodsUuid !=null && !"".equals(goodsUuid)) {
			sql.append(" AND GOODS_UUID=? ");
			params.add(goodsUuid);
		}
		if (warehouse !=null && !"".equals(warehouse)) {
			sql.append(" AND WAREHOUSE=? ");
			params.add(warehouse);
		}
		if (location !=null && !"".equals(location)) {
			sql.append(" AND LOCATION LIKE ? ");
			params.add(String.format("%s%s%s", "%",location,"%"));
		}
		sql.append(" group by STORAGE,LOCATION ");
		
		if (null != connection) {
			return memory.query(connection, sql.toString(), new BeanListHandler<Location>(Location.class), params.toArray());
		}
		return memory.query(sql.toString(), new BeanListHandler<Location>(Location.class), params.toArray());
	}
	
	/**
	 * 柑橘商品UUID、仓库、库区、储位查询库存中的主单位
	 * @param goodsUuid	商品UUID
	 * @param warehouse	仓库
	 * @param stroage	库区
	 * @param location	储位
	 * @return 	List<String>
	 */
	@Override
	public List<String> getStockMuByGoodsUUIDAndWarehouse(String goodsUuid, String warehouse, String storage, String location) {
		String sql="select DISTINCT MU from stock WHERE GOODS_UUID=? and  WAREHOUSE=? and STORAGE=? and LOCATION=?";
		if (null != connection) {
			return memory.query(connection, sql, new ColumnListHandler<String>(String.class), new Object[]{goodsUuid, warehouse, storage, location});
		}
		return memory.query(sql, new ColumnListHandler<String>(String.class), new Object[]{goodsUuid, warehouse, storage, location});
	}
	
	/**
	 * 根据商品UUID查询库存为0的信息条数
	 * @param goodsUuid	商品UUID
	 * @return	库存为0的信息条数
	 */
//	@Override
//	public Integer getStockCountsByGoodsUUID(String goodsUuid) {
//		String sql="SELECT COUNT(*) FROM stock WHERE IN_STOCK <> 0 AND  GOODS_UUID=";
//		if (null != connection) {
//			return memory.query(connection, sql, new ColumnHandler<Integer>(Integer.class), goodsUuid);
//		}
//		return memory.query(sql, new ColumnHandler<Integer>(Integer.class), goodsUuid);
//	}
	
	/**
	 * 根据单位代码查询计量单位
	 * @param unitNumber 单位代码
	 * @return	计量单位
	 */
	@Override
	public String getUnit(String unitNumber) {
		String sql="SELECT ITEM_NAME from dict_item WHERE DICT_CODE='JLDW' AND ITEM_CODE=?";
		if (null != connection) {
			return memory.query(connection, sql, new ColumnHandler<String>(String.class), unitNumber);
		}
		return memory.query(sql, new ColumnHandler<String>(String.class), unitNumber);
	}
	
	/**
	 * 根据储位代码查询该储位下库存不为0的信息条数
	 * @param locationCode	储位代码
	 * @return	库存条数
	 */
	@Override
	public Integer getStockCountsByLocationCode(String locationCode,String warehouse,String storage) {
		String sql="SELECT COUNT(ID) FROM stock WHERE IN_STOCK<>0 AND LOCATION=? AND WAREHOUSE=? AND `STORAGE`=?";
		return memory.query(sql, new ColumnHandler<Integer>(Integer.class), new Object[]{locationCode,warehouse,storage});
	}
	
	/**
	 * 查询料号明细收发存信息条数
	 * @param parames
	 * @return 料号明细收发存信息条数
	 */
	@Override
	public Integer countInAndOutOfStock(Map<String, Object> parames) {
		StringBuffer sql =new StringBuffer();
		List<Object> params = new ArrayList<>();
		sql.append("SELECT COUNT(*) FROM (SELECT stock.* FROM stock LEFT JOIN delivery_packing_list deliveryPackingList ON stock.GOODS_UUID = deliveryPackingList.GOODS_UUID WHERE stock.IN_STOCK <> 0 ");
		if (parames != null) {
			for (String key : parames.keySet()) {
				if (parames.get(key) != null && !"".equals(parames.get(key)) && !"%%".equals(parames.get(key))
						&& !"% %".equals(parames.get(key))) {
					sql.append(" and " + key + " ? ");
					params.add(parames.get(key));
				}
			}
		}
		sql.append(" GROUP BY stock.GOODS_UUID) s");
		return memory.query(sql, new ColumnHandler<Integer>(Integer.class), params);
	}
	
	/**
	 * 查询料号明细收发存信息
	 * @param parames 参数集合
	 * @param pageNumber 当前页
	 * @param pageSize 每页显示数
	 * @return 料号明细收发存信息DTO集合
	 */
	@Override
	public List<InAndOutOfStockDTO> listInAndOutOfStockDTO(Map<String, Object> parames, int pageNumber,
			int pageSize) {
		StringBuffer sql =new StringBuffer();
		List<Object> params = new ArrayList<>();
		sql.append("SELECT SUM(deliveryPackingList.DELIVERY_QTY) AS sumDeliveryQty, SUM(stock.ENTRY_QTY) AS sumEntryQty, stock.GOODS_NAME, stock.GOODS_MODEL, stock.OWNER_CODE, stock.PN, stock.GOODS_UUID, stock.MU, stock.WAREHOUSE, stock.`STORAGE`, stock.LOCATION, stock.PACKAGING_KEY FROM stock LEFT JOIN delivery_packing_list deliveryPackingList ON stock.GOODS_UUID = deliveryPackingList.GOODS_UUID WHERE stock.IN_STOCK <> 0");
		if (parames != null) {
			for (String key : parames.keySet()) {
				if (parames.get(key) != null && !"".equals(parames.get(key)) && !"%%".equals(parames.get(key))
						&& !"% %".equals(parames.get(key))) {
					sql.append(" and " + key + " ? ");
					params.add(parames.get(key));
				}
			}
		}
		sql.append(" GROUP BY stock.GOODS_UUID  ORDER BY stock.ENTRY_DATE DESC");
		if (pageNumber != 0 && pageSize != 0) {
			sql.append(" limit ?,?");
			params.add((pageNumber-1)* pageSize);
			params.add(pageSize);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Dao:[listInAndOutOfStockDTO] sql:{}", sql.toString());
			logger.debug("Dao:[listInAndOutOfStockDTO] params:{}", params.toArray());
		}
		return memory.query(sql.toString(), new BeanListHandler<InAndOutOfStockDTO>(InAndOutOfStockDTO.class), params.toArray());
		
	}
	
}
