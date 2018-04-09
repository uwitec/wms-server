package com.yorma.wms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.common.service.api.SerialNumberService;
import com.yorma.wms.dao.api.DeliveryNoteAsnDao;
import com.yorma.wms.dao.api.DeliveryNoteDao;
import com.yorma.wms.dao.api.DeliveryPackingListDao;
import com.yorma.wms.dao.api.StockDao;
import com.yorma.wms.entity.DeliveryNoteAsn;
import com.yorma.wms.entity.DeliveryPackingList;
import com.yorma.wms.entity.Stock;
import com.yorma.wms.entity.dto.DeliveryNoteAsnDto;
import com.yorma.wms.service.api.DeliveryNoteAsnService;
import com.yorma.wms.service.impl.base.BaseServiceImpl;

/**
 * 出货明细service实现
 * 
 * @Description:
 * @author su
 * @date 2017年8月28日
 * @version V1.0
 */
public class DeliveryNoteAsnServiceImpl extends BaseServiceImpl<DeliveryNoteAsn>implements DeliveryNoteAsnService {

	private static final Logger logger = LoggerFactory.getLogger(DeliveryNoteAsn.class);

	private StockDao stockDao;
	private DeliveryNoteDao deliveryNoteDao;
	private DeliveryNoteAsnDao deliveryNoteAsnDao;
	private DeliveryPackingListDao deliveryPackingListDao;

	private SerialNumberService serialNumberService;

	/**
	 * 重写save方法，根据id是否为null判断是添加还是修改，添加时自动生成UUID
	 */
	@Override
	public ResponseMessage save(DeliveryNoteAsn dna) {
		if (dna == null) {
			return new ResponseMessage(false, "10004");
		}
		if (dna.getId() == null) {
			UUID uuid = UUID.randomUUID();
			dna.setUuid(uuid.toString());
		}
		return super.save(dna);
	}

	/**
	 * 根据收货单号查询出货明细Dto
	 * 
	 * @param deliveryNoteNo
	 *            出货单号
	 * @return 返回ResponseMessage对象
	 *        <pre>
	 * 					[Success:false,Status:10012]参数为null
     *                  [success:false,status:10003,msg:异常说明信息]
     *                  [success:true,Data:List<明细Dto实体>]
	 * 				</pre>
	 */
	public ResponseMessage getDeliveryNoteAsnByNoteNo(String deliveryNoteNo) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("getDeliveryNoteAsnByNoteNo: deliveryNoteNo:{}", deliveryNoteNo);
			}
			if (isBlank(deliveryNoteNo)) {
				return new ResponseMessage(false, "10012");
			}
			List<DeliveryNoteAsnDto> lid = deliveryNoteAsnDao.getDeliveryNoteAsnDtoByNoteNo(deliveryNoteNo);
			return new ResponseMessage(true, lid);
		} catch (Exception e) {
			logger.error("DeliveryNoteAsnService:方法[getDeliveryNoteAsnByNoteNo]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据明细UUID查询明细信息
	 * 
	 * @param UUID
	 *            明细UUID
	 * @return 返回ResponseMessage对象
	 *         		<pre>
	 * 					[Success:false,Status:10012]参数为null
     *                  [success:false,status:10003,msg:异常说明信息]
     *                  [success:true，Data:明细实体]
	 * 				</pre>
	 */
	@Override
	public ResponseMessage getDeliveryNoteAsnByUUID(String uuid) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("getDeliveryNoteAsnByUUID: asnUUID: {}", uuid);
			}
			if (isBlank(uuid)) {
				return new ResponseMessage(false, "10012");
			}
			DeliveryNoteAsn deliveryNoteAsn = deliveryNoteAsnDao.getDeliveryNoteAsnByUUID(uuid);
			return new ResponseMessage(true, deliveryNoteAsn);
		} catch (Exception e) {
			logger.error("DeliveryNoteAsnService:方法[getDeliveryNoteAsnByUUID]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据uuid删除出货预告明细和拣货信息
	 * 
	 * @param uuid
	 *            明细UUID
	 * @return 返回ResponseMessage对象
	 *         <pre>
	 * 				[Success:false,Status:10012]参数为null
     *              [success:false,status:10003,msg:异常说明信息]
     *              [success:false,status:60003,msg:有记录已拣货确认]
     *              [success:false,status:60010,msg:修改库存时失败]
     *              [success:true]
	 * 			</pre>
	 */
	@SuppressWarnings("unused")
	@Override
	public ResponseMessage removeByUUID(String uuid) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("removeByUUID: asnUUID: {}", uuid);
			}
			if (isBlank(uuid)) {
				return new ResponseMessage(false, 10012);
			}
			// 用于获取出货单号
			DeliveryNoteAsn asn = deliveryNoteAsnDao.getDeliveryNoteAsnByUUID(uuid);
			// 获取拣货信息，用于判断是否有拣货信息，和修改库存信息
			List<DeliveryPackingList> deliveryPackingLists = deliveryPackingListDao.getDeliveryPackingListByAsnUUID(uuid);
			// 判断出货单状态是否在出库之前
			if (deliveryNoteDao.getStatus(asn.getDeliveryNoteNo()) >= 40) {
				return new ResponseMessage(false, "60003");
			}

			if (deliveryPackingLists.size() > 0) {
				// 当出货单状态为已分配时，需要核减库存中的预分配量
				if (deliveryNoteDao.getStatus(asn.getDeliveryNoteNo()) == 30) {
					for (DeliveryPackingList dpl : deliveryPackingLists) {
						Stock s = stockDao.getStockByUUID(dpl.getStockUuid());
						int rows = stockDao.updatePreAllocationStock(s.getPreAllocationStock() - dpl.getDeliveryQty(),
								s.getUuid(), s.getVersion());
						if (rows == 0) {
							return new ResponseMessage(false, "60010", String.format("异常信息：%s", "删除明细时修改库存出错"));
						}
					}
				}
				
				int rows = deliveryPackingListDao.deleteDeliveryPackingListByAsnUUID(uuid);
			}
			int rows = deliveryNoteAsnDao.removeByUUID(uuid);
			return new ResponseMessage(true, true);
		} catch (Exception e) {
			logger.error("DeliveryNoteAsnService:方法[removeByUUID]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据单号查询相同商品合并后的明细（暂定）
	 * @param deliveryNoteNo	出库单号
	 * @return 返回ResponseMessage对象
	 *         		<pre>
	 * 					[Success:false,Status:10012]参数为null
     *                  [success:false,status:10003,msg:异常说明信息]
     *                  [success:true，Data:List<明细Dto实体>]
	 * 				</pre>
	 */
	@Override
	public ResponseMessage getDeliveryNoteAsnMergeByDeliveryNoteNo(String deliveryNoteNo) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("getDeliveryNoteAsnMergeByDeliveryNoteNo: deliveryNoteNo: {}", deliveryNoteNo);
			}
			if (isBlank(deliveryNoteNo)) {
				return new ResponseMessage(false, 10012);
			}
			List<DeliveryNoteAsnDto> deliveryNoteAsnDtos = deliveryNoteAsnDao
					.getDeliveryNoteAsnDtoAndExpectQtySumByNoteNo(deliveryNoteNo);
			return new ResponseMessage(true, deliveryNoteAsnDtos);
		} catch (Exception e) {
			logger.error("DeliveryNoteAsnService:方法[getDeliveryNoteAsnMergeByDeliveryNoteNo]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	/**
	 * 判断单条或多条明细是否能分配和多条明细是否符合合并条件
	 * @param deliveryNoteAsnUuid
	 * @return	ResponseMessage对象
	 * 				<pre>
	 * 					[Success:false,Status:10012]参数为null
     *                  [success:false,status:10003,msg:异常说明信息]
     *                  [success:false,status:60029,msg:有记录已拣货确认]
     *                  [success:false,status:60028,msg:明细商品种类不同]
     *                  [success:true]
	 * 				</pre>
	 */
	@Override
	public ResponseMessage judgeDeliveryNoteAsn(List<String> deliveryNoteAsnUuids) {
		try {
			
			if (deliveryNoteAsnUuids.size() == 0) {
				return new ResponseMessage(false, 10012);
			}
			DeliveryNoteAsn deliveryNoteAsnMerge = new DeliveryNoteAsn();
			boolean falg = false;
			for (String asnUuid : deliveryNoteAsnUuids) {
				DeliveryNoteAsn deliveryNoteAsn = deliveryNoteAsnDao.getDeliveryNoteAsnByUUID(asnUuid);

				if (deliveryNoteAsn.getMergeLotNo() !=null) {
					return new ResponseMessage(false, "60029", String.format("异常信息：%s", "有记录已拣货"));
				}
				if (deliveryNoteAsnUuids.size() == 1) {
					return new ResponseMessage(true);
				}
				
				if (deliveryNoteAsnMerge.getPn() == null) {
					deliveryNoteAsnMerge = deliveryNoteAsn;
				}
				
				//根据主单位和商品UUID合并明细
				if (!deliveryNoteAsnMerge.getMu().equals(deliveryNoteAsn.getMu()) || !deliveryNoteAsnMerge.getGoodsUuid().equals(deliveryNoteAsn.getGoodsUuid())) {
					falg=true;
					break;
				}
			}
			if (falg) {
				return new ResponseMessage(false, "60028", String.format("异常信息：%s", "明细商品种类不同"));
			}
			return new ResponseMessage(true);
		} catch (Exception e) {
			logger.error("DeliveryNoteAsnService:方法[judgeDeliveryNoteAsn]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 根据明细uuid合并明细信息
	 * @param deliveryNoteAsnUuid
	 * @return	ResponseMessage对象
	 * 				<pre>
	 * 					[Success:false,Status:10012]参数为null
     *                  [success:false,status:10003,msg:异常说明信息]
     *                  [success:true,data:合并后明细实体]
	 * 				</pre>
	 */
	@Override
	public ResponseMessage getDeliveryNoteAsnMergeByAsnUUID(List<String> asnUuids) {
		try {
			if (asnUuids.size() == 0) {
				return new ResponseMessage(false, 10012);
			}
			DeliveryNoteAsn deliveryNoteAsnMerge = new DeliveryNoteAsn();
			
			for (String asnUuid : asnUuids) {
				DeliveryNoteAsn deliveryNoteAsn = deliveryNoteAsnDao.getDeliveryNoteAsnByUUID(asnUuid);

				if (deliveryNoteAsnMerge.getPn() == null) {
					deliveryNoteAsnMerge = deliveryNoteAsn;
					continue;
				}
				
				//预出数量和
				deliveryNoteAsnMerge.setExpectQty(deliveryNoteAsnMerge.getExpectQty()+deliveryNoteAsn.getExpectQty());
				
			}
			return new ResponseMessage(true,deliveryNoteAsnMerge);
		} catch (Exception e) {
			logger.error("DeliveryNoteAsnService:方法[getDeliveryNoteAsnMergeByAsnUUID]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据出货单号查询明细分配方式
	 * 
	 * @param deliveryNoteNo
	 * @return 返回ResponseMessage对象
	 * 
	 *         <pre>
	 * 			[Success: false status:10012] 参数为null
	 * 			[Success: false	status:10003] 异常信息
	 * 			[Success： true  status: 001(没有分配过明细)、002(单个明细分配)、003(合并明细分配)]
	 *         </pre>
	 */
	@Override
	public ResponseMessage getIsMergedByNoteNo(String deliveryNoteNo) {
		try {
			if (isBlank(deliveryNoteNo)) {
				return new ResponseMessage(false, "10012");
			}
			// 查询分配过的明细信息
			List<DeliveryNoteAsn> deliveryNoteAsns = deliveryNoteAsnDao.getIsMergedByNoteNo(deliveryNoteNo, null);
			if (deliveryNoteAsns.size() == 0) {
				// 001代表没有分配过
				return new ResponseMessage(true, "001");
			}
			deliveryNoteAsns = deliveryNoteAsnDao.getIsMergedByNoteNo(deliveryNoteNo, true);
			if (deliveryNoteAsns.size() == 0) {
				// 002代表单个明细分配
				return new ResponseMessage(true, "002");
			}
			return new ResponseMessage(true, "003");
		} catch (Exception e) {
			logger.error("DeliveryNoteAsnService:方法[getIsMergedByNoteNo]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据出库单号统计明细合并后的品名，合并批次号，商品UUID，料号，总预收数量，总出库数量
	 * @param deliveryNoteNo	出库单号
	 * @return	返回ResponseMessage对象
	 * 			<pre>
	 * 			[Success: false status:10012] 参数为null
	 * 			[Success: false	status:10003] 异常信息
	 * 			[Success： true  data:List<明细Dto>]
	 * 			</pre>
	 */
	@Override
	public ResponseMessage getMergeDeliveryNoteAsn(String deliveryNoteNo) {
		try {
			if (isBlank(deliveryNoteNo)) {
				return new ResponseMessage(false, "10012");
			}
			List<DeliveryNoteAsnDto> deliveryNoteAsnDtos = new ArrayList<>();
			List<DeliveryNoteAsnDto> asnDtos = deliveryNoteAsnDao.getMergeDeliveryNoteAsn(deliveryNoteNo);
			for (DeliveryNoteAsnDto deliveryNoteAsnDto : asnDtos) {
				/*
				 * 筛选出未拣货确认的明细信息
				 */
				if (deliveryPackingListDao.getDeliveryPackingListByMergeLotNo(deliveryNoteAsnDto.getMergeLotNo(),10).size() == 0) {
					deliveryNoteAsnDtos.add(deliveryNoteAsnDto);
				}
			}
			return new ResponseMessage(true, deliveryNoteAsnDtos);
		} catch (Exception e) {
			logger.error("DeliveryNoteAsnService:方法[getMergeDeliveryNoteAsn]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	
	public void setDeliveryNoteAsnDao(DeliveryNoteAsnDao deliveryNoteAsnDao) {
		super.setBaseDao(deliveryNoteAsnDao);
		this.deliveryNoteAsnDao = deliveryNoteAsnDao;
	}

	public void setDeliveryPackingListDao(DeliveryPackingListDao deliveryPackingListDao) {
		this.deliveryPackingListDao = deliveryPackingListDao;
	}

	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}

	public void setDeliveryNoteDao(DeliveryNoteDao deliveryNoteDao) {
		this.deliveryNoteDao = deliveryNoteDao;
	}

	public  void setSerialNumberService(SerialNumberService serialNumberService) {
		this.serialNumberService = serialNumberService;
	}

}
