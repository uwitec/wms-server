package com.yorma.wms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yorma.common.entity.dto.Parameter;
import com.yorma.common.entity.dto.ResponseData;
import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.dao.api.GoodsDao;
import com.yorma.wms.dao.api.PackInfomationDao;
import com.yorma.wms.dao.api.StockDao;
import com.yorma.wms.entity.Goods;
import com.yorma.wms.entity.PackInfomation;
import com.yorma.wms.entity.dto.GoodsDto;
import com.yorma.wms.service.api.GoodsService;
import com.yorma.wms.service.impl.base.BaseServiceImpl;

/**
 * 商品基础资料service实现
 * 
 * @Description:
 * @author su
 * @date 2017年8月7日
 * @version V1.0
 */
public class GoodsServiceImpl extends BaseServiceImpl<Goods>implements GoodsService {

	private static final Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

	private StockDao stockDao;
	private GoodsDao goodsDao;
	private PackInfomationDao packInfomationDao;

	/**
	 * 商品基础资料添加/修改
	 * 
	 * @param uuid
	 *            全球唯一标识
	 */
	@Override
	public ResponseMessage save(Goods goods) {
		if (goods == null) {
			return new ResponseMessage(false, "10004");
		}
		// 根据id是否为空判断是添加（为空）还是修改(不为空)
		if (goods.getId() == null) {
			// 生成全球唯一标识
			UUID uuid = UUID.randomUUID();
			goods.setUuid(uuid.toString());
			Goods goodsByPn = goodsDao.getGoodsByDepartCodeAndPn(goods.getOwnerCode(), goods.getPn());
			if (goodsByPn != null) {
				return new ResponseMessage(false, "60015", "料号重复!");
			}
		}
		return super.save(goods);
	}

	/**
	 * 批量添加（更新）商品
	 * 
	 * @param goodss
	 *            商品集合
	 * @return 返回ResponseMessage对象
	 *        <pre>
	 * 				[success: false status:10004] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: false status:60015] 料号重复
	 * 				[success: false status:10014] 影响行数为0，保存失败
	 * 				[success: true 	data:true] 操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage createBatchByGoods(List<Goods> goodss) {
		try {
			if (goodss == null) {
				return new ResponseMessage(false, "10004");
			}
			for (Goods goods : goodss) {
				if (goods.getUuid() != null) {
					GoodsDto goodsDto = goodsDao.getGoodsDtoByUUID(goods.getUuid());
					if (goodsDto != null) {
						goods.setId(goodsDto.getId());
					}
				} else {
					UUID uuid = UUID.randomUUID();
					goods.setUuid(uuid.toString());
				}
				Goods goodsByPn = goodsDao.getGoodsByDepartCodeAndPn(goods.getOwnerCode(), goods.getPn());
				if (goodsByPn != null) {
					return new ResponseMessage(false, "60015", "料号重复!");
				}
				goods.setIsEnable(true);
				int rows = goodsDao.save(goods);
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}
			}
			return new ResponseMessage(true, true);
		} catch (Exception e) {
			logger.error("GoodsService:方法[createBatchByGoods]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 查询（条件）商品基础资料
	 * 
	 * @param map
	 *            将查询条件放入map集合
	 * @return 返回ResponseMessage对象
	 *        <pre>
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true 	data:List<商品信息实体Dto>] 操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage getGoods(Map<String, Object> map) {

		try {
			List<GoodsDto> goodsDtos = goodsDao.getGoods(map);
			return new ResponseMessage(true, goodsDtos);
		} catch (Exception e) {
			logger.error("GoodsService:方法[getGoods]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 查询（条件）商品基础资料(分页)
	 * @param map	将查询条件放入map集合
	 * @param pageNumber	当前页
	 * @param pageSize		每页显示数
	 * @return	返回ResponseMessage对象
	 *        <pre>
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: false status:10012] 当前页或每页显示数为null！
	 * 				[success: true data:new ResponseData<>(当前页, 总页数(1页), 总条数, null)] 无数据(总条数为0)
	 * 				[success: true 	data:new ResponseData<>(当前页, 总页数, 总条数, List<商品信息实体Dto>)] 操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage getGoods(Map<String, Object> map, Integer pageNumber, Integer pageSize) {
		try {
			if (pageNumber == null || pageSize == null) {
				return new ResponseMessage(false, "10012", "当前页或每页显示数为null！");
			}
			List<GoodsDto> goodsDtos = new ArrayList<>();
			int counts = goodsDao.getCounts(map);
			if (counts == 0) {
				return new ResponseMessage(true, new ResponseData<>(pageNumber, 1, counts, goodsDtos));
			}
			int page = counts% pageSize == 0 ? counts/pageSize : counts/pageSize+1;
			goodsDtos = goodsDao.getGoods(map,pageNumber,pageSize);
			
			return new ResponseMessage(true, new ResponseData<>(pageNumber, page, counts, goodsDtos));
		} catch (Exception e) {
			logger.error("GoodsService:方法[getGoods]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 商品基础资料查询（分页）
	 */
	@Override
	public ResponseMessage searchParameter(List<Parameter> parameters, Integer pageNumber, Integer pageSize) {
		try {
			if (pageSize == null) {
				return new ResponseMessage(false, "10004", "查询的条数不能为空");
			}
			if (pageNumber == null) {
				return new ResponseMessage(false, "10004", "页码不能为空");
			}
			List<GoodsDto> goodsDtos = goodsDao.getGoods(parameters, pageNumber, pageSize);
			return new ResponseMessage(true, goodsDtos);
		} catch (Exception e) {
			logger.error("GoodsService:方法[searchParameter]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据id查询商品基础资料
	 * 
	 * @param id
	 *            商品ID
	 * @return 返回ResponseMessage对象
	 *        <pre>
	 * 				[success: false status:10012] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true 	data:商品信息实体Dto] 操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage getGoodsById(Long id) {
		try {
			if (null == id) {
				return new ResponseMessage(false, "10012");
			}
			GoodsDto goodsDto = goodsDao.getGoodsById(id);
			return new ResponseMessage(true, goodsDto);
		} catch (Exception e) {
			logger.error("GoodsService:方法[getGoodsById]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据uuid查询商品基础资料
	 * 
	 * @param uuid
	 *            商品UUID
	 * @return 返回ResponseMessage对象
	 *        <pre>
	 * 				[success: false status:10012] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true 	data:商品信息实体Dto] 操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage getGoodsByUUID(String uuid) {

		try {
			if (uuid == null) {
				return new ResponseMessage(false, "10012");
			}
			GoodsDto goodsDto = goodsDao.getGoodsDtoByUUID(uuid);
			return new ResponseMessage(true, goodsDto);
		} catch (Exception e) {
			logger.error("GoodsService:方法[getGoodsByUUID]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据商品id删除商品信息及该商品包装信息
	 */
	@Override
	public ResponseMessage removeById(Long id) {
		try {
			Goods goods = goodsDao.find(id);
			if (stockDao.getStockCountsByGoodsUUID(goods.getUuid()) != 0) {
				return new ResponseMessage(false, "60039", String.format("异常信息：%s", "该商品存在库存记录"));
			}
			int rows = packInfomationDao.removeByGoodsUUID(goods.getUuid());
			rows = goodsDao.removeById(id);
			return new ResponseMessage(true, rows);
		} catch (Exception e) {
			logger.error("GoodsService:方法[removeById]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 保存商品信息及包装键
	 * @param goodss
	 * @param packInfomations
	 * @return	ResponseMessage对象
	 * 				<pre>
	 * 					[Success:false, Status:10004]参数为null
	 * 					[Success:false, Status:60015, Msg:商品料号重复]
	 * 					[Success:false, Status:10014]影响行数为0，保存失败
	 * 					[Success:false, Status:10003, Msg:异常信息]
	 * 					[Success:true, Data:商品UUID]
	 * 				</pre>
	 */
	@Override
	public ResponseMessage saveGoodsAndPackInfomation(Goods goods,
			List<PackInfomation> packInfomations) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("saveGoodsAndPackInfomation : goods:{}", goods);
				logger.debug("saveGoodsAndPackInfomation : packInfomations.size:{}", packInfomations.size());
			}
			if (goods == null ) {
				return new ResponseMessage(false, "10004");
			}
			String goodsUuid=null;
			// 根据id是否为空判断是添加（为空）还是修改(不为空)
			if (goods.getId() == null) {
				// 生成全球唯一标识
				goodsUuid = UUID.randomUUID().toString();
				goods.setUuid(goodsUuid);
				Goods goodsByPn = goodsDao.getGoodsByDepartCodeAndPn(goods.getOwnerCode(), goods.getPn());
				if (goodsByPn != null) {
					return new ResponseMessage(false, "60015", String.format("异常信息：%s", "料号重复!"));
				}
				goods.setCreateDate(new Date());
			}else {
				goodsUuid=goods.getUuid();
				goods.setLastUpdateDate(new Date());
			}

			int rows = goodsDao.save(goods);
			if (rows == 0) {
				return new ResponseMessage(false, "10014");
			}
			//删除包装键信息
			rows = packInfomationDao.removeByGoodsUUID(goodsUuid);
			//判断是否存在包装键信息
			if (packInfomations!=null && !packInfomations.isEmpty()) {
				boolean falg=false;
				//循环保存包装键
				for (PackInfomation packInfomation : packInfomations) {
					if (packInfomation.getIsDefault() == 1) {
						//如果有一条包装键是默认的，那么其他包装键取消默认
						if (falg) {
							packInfomation.setIsDefault(0);
						}
						falg=true;
					}
					if (packInfomation.getId() == null) {
						packInfomation.setGoodsUuid(goodsUuid);
					}else {
						packInfomation.setId(null);
					}
					
					rows = packInfomationDao.save(packInfomation);
					if (rows == 0) {
						return new ResponseMessage(false, "10014");
					}
				}
			}
			Object date=goodsUuid;
			return new ResponseMessage(true, date);
		} catch (Exception e) {
			logger.error("GoodsService:方法[saveGoodsAndPackInfomation]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据商品UUID查询商品信息和包装键集合
	 * @param goodsUuid	商品UUID
	 * @return	ResponseMessage 对象
	 * 				<pre>
	 * 					[Success:false, Status:10012]参数为null
	 * 					[Success:false, Status:10003, Msg:异常信息]
	 * 					[Success:true, Data:Map<String, Object> (kry:goods, value:goods,key:PackInfomations,value:List<PackInfomation>)]
	 * 				</pre>
	 */
	@Override
	public ResponseMessage getGoodsAndPackInfomationByGoodsUUID(String goodsUuid) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("getGoodsAndPackInfomationByGoodsUUID : goodsUuid:{}", goodsUuid);
			}
			if (isBlank(goodsUuid)) {
				return new ResponseMessage(false, "10012");
			}
			Map<String, Object> goodsDtoAndPackInfomationDtos = new HashMap<>();
			goodsDtoAndPackInfomationDtos.put("goods", goodsDao.getGoodsByUUID(goodsUuid));
			goodsDtoAndPackInfomationDtos.put("PackInfomations", packInfomationDao.getPackInfomationByUUID(goodsUuid));
			return new ResponseMessage(true, goodsDtoAndPackInfomationDtos);
		} catch (Exception e) {
			logger.error("GoodsService:方法[getGoodsAndPackInfomationByGoodsUUID]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 查询所有商品UUID
	 * @return	ResponseMessage 对象
	 * 				<pre>
	 * 					[Success:false, Status:10003, Msg:异常信息]
	 * 					[Success:true, Data:Map<String, Object> (key:goodsUUID, value:"1"作为占位，没用)]
	 * 				</pre>
	 */
	@Override
	public ResponseMessage getGoodsUUID() {
		try {
			Map<String, Object> map = new HashMap<>();
			for (String goodsUuid : goodsDao.getUUIDByGoodsUUID()) {
				map.put(goodsUuid, "1");
			}
			return new ResponseMessage(false, map);
		} catch (Exception e) {
			logger.error("GoodsService:方法[getGoodsUUID]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 批量添加商品及包装信息
	 * @param goodsList		商品实体集合
	 * @param packInfomations	包装信息实体集合
	 * @return	ResponseMessage对象
	 * 				<pre>
	 * 					[Success:false, Status:10004]参数为null
	 * 					[Success:false, Status:60015, Msg:商品料号重复]
	 * 					[Success:false, Status:10014]影响行数为0，保存失败
	 * 					[Success:false, Status:10003, Msg:异常信息]
	 * 					[Success:true]
	 * 				</pre>
	 */
	@Override
	public ResponseMessage createBatchByGoodsAndPackInfomation(List<Goods> goodsList,
			List<PackInfomation> packInfomations) {
		try {
			if (goodsList == null ||goodsList.isEmpty()) {
				return new ResponseMessage(false, "10004");
			}
			for (Goods goods : goodsList) {
				Goods goodsByPn = goodsDao.getGoodsByDepartCodeAndPn(goods.getOwnerCode(), goods.getPn());
				if (goodsByPn != null) {
					return new ResponseMessage(false, "60015", "料号重复!");
				}
				int rows = goodsDao.save(goods);
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}
			}
			for (PackInfomation packInfomation : packInfomations) {
				int rows = packInfomationDao.save(packInfomation);
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}
			}
			return new ResponseMessage(true);
		} catch (Exception e) {
			logger.error("GoodsService:方法[createBatchByGoodsAndPackInfomation]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 查询（条件）商品基础资料及默认包装键
	 * 
	 * @param parames
	 *            将查询条件放入map集合
	 * @return 返回ResponseMessage对象
	 *        <pre>
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true 	data:List<商品信息实体Dto>] 操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage getGoodsAndPackInfomation(Map<String, Object> parames) {
		try {
			return new ResponseMessage(true, goodsDao.getGoodsAndPackInfomation(parames));
		} catch (Exception e) {
			logger.error("GoodsService:方法[getGoodsAndPackInfomation]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	
	public void setGoodsDao(GoodsDao goodsDao) {
		super.setBaseDao(goodsDao);
		this.goodsDao = goodsDao;
	}

	public void setPackInfomationDao(PackInfomationDao packInfomationDao) {
		this.packInfomationDao = packInfomationDao;
	}

	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}

}
