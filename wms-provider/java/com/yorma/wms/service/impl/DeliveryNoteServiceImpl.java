package com.yorma.wms.service.impl;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yorma.common.entity.dto.ResponseData;
import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.common.service.api.SerialNumberService;
import com.yorma.wms.dao.api.DeliveryNoteAsnDao;
import com.yorma.wms.dao.api.DeliveryNoteDao;
import com.yorma.wms.dao.api.DeliveryPackingListDao;
import com.yorma.wms.dao.api.SalesOrderDao;
import com.yorma.wms.dao.api.StatusHistoryDao;
import com.yorma.wms.dao.api.StockDao;
import com.yorma.wms.entity.DeliveryNote;
import com.yorma.wms.entity.DeliveryNoteAsn;
import com.yorma.wms.entity.DeliveryPackingList;
import com.yorma.wms.entity.StatusHistory;
import com.yorma.wms.entity.Stock;
import com.yorma.wms.entity.dto.DeliveryNoteDto;
import com.yorma.wms.service.api.DeliveryNoteService;
import com.yorma.wms.service.impl.base.BaseServiceImpl;

/**
 * 出货单service实现
 * 
 * @Description:
 * @author su
 * @date 2017年8月28日
 * @version V1.0
 */
public class DeliveryNoteServiceImpl extends BaseServiceImpl<DeliveryNote>implements DeliveryNoteService {

	private static final Logger logger = LoggerFactory.getLogger(DeliveryNote.class);

	private DeliveryNoteDao deliveryNoteDao;
	private DeliveryNoteAsnDao deliveryNoteAsnDao;
	private DeliveryPackingListDao deliveryPackingListDao;
	private StatusHistoryDao statusHistoryDao;
	private StockDao stockDao;
	private SalesOrderDao orderDao;

	private static SerialNumberService serialNumberService;


	/**
	 * 添加（修改）出货单
	 * 
	 * @param entity
	 *            收货单实体
	 * @return 返回ResponseMessage对象
	 * 
	 *         <pre>
	 * 				[success: false status:10004] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success:false,status:10020]:生成单号失败
	 * 				[success: false status:60020，msg:该出库单已审核] 
	 * 				[success: false status:10014] 影响行数为0，保存失败
	 * 				[success: true 	data:添加：出库单表头id,出库单号；修改：id] 操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage save(DeliveryNote dn) {

		try {
			if (null == dn) {
				return new ResponseMessage(false, "10004");
			}
			if (dn.getId() == null) {
				// 获取生成的出货单号
				ResponseMessage responseMessage = serialNumberService.getSerialNumber("CK",
						new SimpleDateFormat("yyyyMMdd").format(new Date()), 4);
				if (!responseMessage.getSuccess()) {
					return responseMessage;
				}
				String deliveryNoteNo = (String) responseMessage.getData();
				dn.setDeliveryNoteNo(deliveryNoteNo);

				int rows = deliveryNoteDao.save(dn);
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}
				// 添加一条历史状态
				StatusHistory statusHistory = new StatusHistory();
				statusHistory.setApplyId(dn.getDeliveryNoteNo());
				statusHistory.setType("1");
				statusHistory.setCreateTime(new Date());
				statusHistory.setLastTime(new Date());
				statusHistory.setStatusId(10);
				rows = statusHistoryDao.save(statusHistory);
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}
				Field field = dn.getClass().getDeclaredField("id");
				field.setAccessible(true);// 设置些属性是可以访问的
				Object value = field.get(dn); // 得到此属性的值
				Object data = value + "," + dn.getDeliveryNoteNo();
				return new ResponseMessage(true, data);
			}
			if (dn.getStatus() != 10) {
				return new ResponseMessage(false, "60020");
			}
		} catch (Exception e) {
			logger.error("DeliveryNoteService:方法[save]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
		return super.save(dn);
	}

	/**
	 * 查询（条件）出货单
	 * 
	 * @param map
	 *            将查询条件放入map集合
	 * @return 返回ResponseMessage对象
	 * 
	 *         <pre>
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true 	data:List<出库单Dto实体>] 操作成功
	 *         </pre>
	 */
	public ResponseMessage getDeliveryNote(Map<String, Object> map) {
		try {
			List<DeliveryNoteDto> lid = deliveryNoteDao.getDeliveryNoteAndDepart(map);
			return new ResponseMessage(true, lid);
		} catch (Exception e) {
			logger.error("DeliveryNoteService:方法[getDeliveryNote]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 *  根据条件查询出货单
	 * @param map	将查询条件放入map集合
	 * @param pageNumber	当前页
	 * @param pageSize		每页显示数
	 * @return	返回ResponseMessage对象
	 *          <pre>
	 *          	[Success: false,Status:10012] 参数[pageNumber]或[pageSize] 为 null
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true data:new ResponseData<>(当前页, 总页数(1页), 总条数, null)] 无数据(总条数为0)  
	 * 				[success: true 	data:new ResponseData<>(当前页, 总页数, 总条数, List<出库单Dto实体>] 操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage getDeliveryNote(Map<String, Object> map, Integer pageNumber, Integer pageSize) {
		try {
			if (pageNumber == null || pageSize == null) {
				return new ResponseMessage(false, "10012", "参数[pageNumber]或[pageSize] 为 null");
			}
			List<DeliveryNoteDto> deliveryNoteDtos = new ArrayList<>();
			//获取总行数
			int counts = deliveryNoteDao.getCounts(map);
			if (counts == 0) {
				return new ResponseMessage(true, new ResponseData<>(pageNumber, 1, counts, deliveryNoteDtos));
			}
			if (logger.isDebugEnabled()) {
				logger.debug("DeliveryNoteService: 总行数[counts]:{}", counts);
			}
			int page = counts%pageSize == 0 ? counts/pageSize : counts/pageSize+1;
			deliveryNoteDtos = deliveryNoteDao.getDeliveryNoteAndDepart(map,pageNumber, pageSize);
			return new ResponseMessage(true, new ResponseData<>(pageNumber, page, counts, deliveryNoteDtos));
		} catch (Exception e) {
			logger.error("DeliveryNoteService:方法[getDeliveryNote]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 根据出货单号查询出货单状态
	 * 
	 * @param deliveryNoteNo
	 *            收货单号
	 * @return 返回ResponseMessage对象
	 * 
	 *         <pre>
	 * 				[success: false status:10012] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true 	data:出库单状态码] 操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage getDeliveryNoByNoteNo(String deliveryNoteNo) {
		try {
			if (isBlank(deliveryNoteNo)) {
				return new ResponseMessage(false, "10012");
			}
			int status = deliveryNoteDao.getStatus(deliveryNoteNo);
			return new ResponseMessage(true, status);
		} catch (Exception e) {
			logger.error("DeliveryNoteService:方法[getDeliveryNoByNoteNo]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据出货单号修改出货单状态
	 * 
	 * @param deliveryNoteNo
	 *            出货单号
	 * @param status
	 *            状态码
	 * @return 返回ResponseMessage对象
	 * 
	 *         <pre>
	 * 				[success: false status:10012] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: false status:60006，msg:状态不能跳过] 
	 * 				[success: false status:10014] 影响行数为0，修改出库单状态失败
	 * 				[success: true 	data:影响行数] 操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage updateStatus(String deliveryNoteNo, int status) {
		try {
			if (isBlank(deliveryNoteNo)) {
				return new ResponseMessage(false, "10012");
			}
			// 查询数据库中出货单状态
			int status1 = deliveryNoteDao.getStatus(deliveryNoteNo);
			// 确定状态是线形顺序
			if (status1 + 10 != status) {
				return new ResponseMessage(false, "60006");
			}
			int rows = deliveryNoteDao.updateStatus(deliveryNoteNo, status);
			if (rows == 0) {
				return new ResponseMessage(false, "10014", String.format("异常信息%s", "修改出库单状态失败"));
			}
			return new ResponseMessage(true, rows);
		} catch (Exception e) {
			logger.error("DeliveryNoteService:方法[updateStatus]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据出库单号弃审
	 * 
	 * @param deliveryNoteNo
	 *            出库单号
	 * @return Responsemessage对象
	 * 
	 *         <pre>
	 * 			[Success:false ,Status:10012 , mag:出库单号为null或者空字符串]
	 * 			[Success:false ,Status:60021 , mag:该出库单已分配，不可撤审]
	 * 			[Success:false ,Status:10003 , msg:异常信息]
	 * 			[Successs:true ,data:影响行数 ]
	 *         </pre>
	 */
	@Override
	public ResponseMessage revokeAudit(String deliveryNoteNo) {
		try {
			if (isBlank(deliveryNoteNo)) {
				return new ResponseMessage(false, "10012");
			}
			List<DeliveryPackingList> deliveryPackingLists = deliveryPackingListDao
					.getDeliveryPackingListByNoteNo(deliveryNoteNo);
			if (deliveryPackingLists!=null && !deliveryPackingLists.isEmpty()) {
				return new ResponseMessage(false, "60021");
			}
			return new ResponseMessage(true, deliveryNoteDao.updateStatus(deliveryNoteNo, 10));
		} catch (Exception e) {
			logger.error("DeliveryNoteService:方法[revokeAudit]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 删除出货单（明细、入库信息并核减预分配或已分配数量
	 * 
	 * @param deliveryNoteNo
	 *            出货单号
	 * @return Responsemessage对象
	 *         <pre>
	 * 			[Success:false ,Status:10012 , mag:出库单号为null或者空字符串]
	 * 			[Success:false ,Status:60003 , mag:该出库单拣货确认，不可撤审]
	 * 			[Success:false ,Status:10014 , msg:修改订单状态失败]
	 * 			[Success:false ,Status:10003 , msg:异常信息]
	 * 			[Successs:true ,data:true]
	 *         </pre>
	 */
	@Override
	public ResponseMessage delete(String deliveryNoteNo) {
		try {
			if (isBlank(deliveryNoteNo)) {
				return new ResponseMessage(false, "10012");
			}
			DeliveryNote deliveryNote = deliveryNoteDao.getDeliveryNoteByDeliveryNoteNo(deliveryNoteNo);
			if (deliveryNote != null) {
				/*
				 *  判断此数据状态是否拣货确认，如果拣货确认则删除失败返回状态码60003
				 */
				int status = deliveryNoteDao.getStatus(deliveryNoteNo);
				if (status >= 20) {
					return new ResponseMessage(false, "60003");
				}
				/*
				 * 回退订单状态
				 */
				if (deliveryNote.getOrderSo() != null) {
					int rows = orderDao.updateIsDelivery(deliveryNote.getOrderSo(), false);
					if (rows == 0) {
						return new ResponseMessage(false, "10014");
					}
				}
			}
			int rows = deliveryNoteDao.deleteDeliveryNoteByNoteNo(deliveryNoteNo);
			List<DeliveryNoteAsn> deliveryNoteAsns = deliveryNoteAsnDao
					.getDeliveryNoteAsnByDeliveryNoteNo(deliveryNoteNo);
			if (deliveryNoteAsns !=null && !deliveryNoteAsns.isEmpty()) {

				rows = deliveryNoteAsnDao.deleteDeliveryNoteAsn(deliveryNoteNo);

				// 获取拣货信息，用于判断是否存在拣货信息和修改库存
				List<DeliveryPackingList> deliveryPackingLists = deliveryPackingListDao.getDeliveryPackingListByNoteNo(deliveryNoteNo);
				if (deliveryPackingLists!=null && !deliveryPackingLists.isEmpty()) {
					// 当出货单状态为已分配时，库存预分配量核减
					if (deliveryNoteDao.getStatus(deliveryNoteNo) == 30) {
						for (DeliveryPackingList deliveryPackingList : deliveryPackingLists) {
							Stock s = stockDao.getStockByUUID(deliveryPackingList.getStockUuid());
							rows = stockDao.updatePreAllocationStock(s.getPreAllocationStock() - deliveryPackingList.getDeliveryQty(),
									s.getUuid(), s.getVersion());
							if (rows == 0) {
								return new ResponseMessage(false, "10014");
							}
						}
					}
					rows = deliveryPackingListDao.deleteDeliveryPackingList(deliveryNoteNo);
				}
			}

			return new ResponseMessage(true, true);
		} catch (Exception e) {
			logger.error("DeliveryNoteService:方法[delete]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 保存出库单及明细(出库订单生成)
	 * 
	 * @param deliveryNote
	 * @param deliveryNoteAsns
	 * @return ResponsMessage
	 * 
	 *         <pre>
	 * 				[success: false status:10004] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: false status:10014] 影响行数为0，保存失败
	 * 				[success: false status:60002，msg:单号重复] 
	 * 				[success: true 	data:影响行数] 操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage saveDeliveryNoteAndAsn(DeliveryNote deliveryNote, List<DeliveryNoteAsn> deliveryNoteAsns,
			String so) {
		try {
			if (deliveryNote == null) {
				return new ResponseMessage(false, "10004");
			}
			if (deliveryNoteDao.getDeliveryNoteByDeliveryNoteNo(deliveryNote.getDeliveryNoteNo()) != null) {
				return new ResponseMessage(false, "60002", String.format("异常信息：%s", "单号重复！"));
			}
			String deliveryNoteNo = deliveryNote.getDeliveryNoteNo();
			deliveryNote.setCreateDate(new Date());
			int rows = deliveryNoteDao.save(deliveryNote);
			if (rows == 0) {
				return new ResponseMessage(false, "10014");
			}
			for (DeliveryNoteAsn deliveryNoteAsn : deliveryNoteAsns) {
				deliveryNoteAsn.setUuid(UUID.randomUUID().toString());
				deliveryNoteAsn.setDeliveryNoteNo(deliveryNoteNo);
				deliveryNoteAsn.setCreateDate(new Date());
				rows = deliveryNoteAsnDao.save(deliveryNoteAsn);
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}
			}
			rows = orderDao.updateIsDelivery(so, true);
			if (rows == 0) {
				return new ResponseMessage(false, "10014");
			}
			StatusHistory statusHistory = new StatusHistory();
			statusHistory.setApplyId(deliveryNoteNo);
			statusHistory.setType("1");
			statusHistory.setCreateTime(new Date());
			statusHistory.setLastTime(new Date());
			statusHistory.setStatusId(10);
			rows = statusHistoryDao.save(statusHistory);
			if (rows == 0) {
				return new ResponseMessage(false, "10014");
			}
			return new ResponseMessage(true, rows);
		} catch (Exception e) {
			logger.error("DeliveryNoteService:方法[saveDeliveryNoteAndAsn](出库订单生成)出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 保存出库单表头及明细
	 * 
	 * @param deliveryNote
	 *            出库单表头实体
	 * @param deliveryNoteAsns
	 *            出库单明细预告集合
	 * @return ResponseMessage
	 * 
	 *         <pre>
	 * 				[success: false status:10004] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: false status:60013，msg:该出库单已审核] 
	 * 				[success: false status:60002，msg:单号重复] 
	 * 				[success: false status:10014] 影响行数为0，保存失败
	 * 				[success: true 	data:出库单表头id] 操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage saveDeliveryNoteAndAsn(DeliveryNote deliveryNote, List<DeliveryNoteAsn> deliveryNoteAsns) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("saveDeliveryNoteAndAsn: deliveryNote:{}", deliveryNote);
				logger.debug("saveDeliveryNoteAndAsn: deliveryNoteAsns.size:{}", deliveryNoteAsns.size());
			}
			if (deliveryNote == null) {
				return new ResponseMessage(false, "10004");
			}
			if (deliveryNote.getStatus() == 20) {
				return new ResponseMessage(false, "60016", String.format("异常信息：%s", "该出库单已被审核不能修改！"));
			}

			if (deliveryNote.getId() == null) {
				if (deliveryNoteDao.getDeliveryNoteByDeliveryNoteNo(deliveryNote.getDeliveryNoteNo()) != null) {
					return new ResponseMessage(false, "60002", String.format("异常信息：%s", "单号重复！"));
				}
				deliveryNote.setCreateDate(new Date());
			} else {

				deliveryNote.setLastUpdateDate(new Date());
			}
			int rows = deliveryNoteDao.save(deliveryNote);
			if (rows == 0) {
				return new ResponseMessage(false, "10014");
			}
			// 删除入库明细
			rows = deliveryNoteAsnDao.deleteDeliveryNoteAsn(deliveryNote.getDeliveryNoteNo());
			if (deliveryNoteAsns!=null && !deliveryNoteAsns.isEmpty()) {
				for (DeliveryNoteAsn deliveryNoteAsn : deliveryNoteAsns) {
					if (deliveryNoteAsn.getId() == null) {
						deliveryNoteAsn.setDeliveryNoteNo(deliveryNote.getDeliveryNoteNo());
						deliveryNoteAsn.setCreateDate(new Date());
						deliveryNoteAsn.setUuid(UUID.randomUUID().toString());
					}
					deliveryNoteAsn.setId(null);

					rows = deliveryNoteAsnDao.save(deliveryNoteAsn);
					if (rows == 0) {
						return new ResponseMessage(false, "10014");
					}
				}
			}
			Field field = deliveryNote.getClass().getDeclaredField("id");
			field.setAccessible(true);// 设置些属性是可以访问的
			Object value = field.get(deliveryNote); // 得到此属性的值
			return new ResponseMessage(true, value);
		} catch (Exception e) {
			logger.error("DeliveryNoteService:方法[saveDeliveryNoteAndAsn]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	public void setDeliveryNoteDao(DeliveryNoteDao deliveryNoteDao) {
		super.setBaseDao(deliveryNoteDao);
		this.deliveryNoteDao = deliveryNoteDao;
	}

	public void setDeliveryNoteAsnDao(DeliveryNoteAsnDao deliveryNoteAsnDao) {
		this.deliveryNoteAsnDao = deliveryNoteAsnDao;
	}

	public void setDeliveryPackingListDao(DeliveryPackingListDao deliveryPackingListDao) {
		this.deliveryPackingListDao = deliveryPackingListDao;
	}

	public void setStatusHistoryDao(StatusHistoryDao statusHistoryDao) {
		this.statusHistoryDao = statusHistoryDao;
	}

	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}

	public static void setSerialNumberService(SerialNumberService serialNumberService) {
		DeliveryNoteServiceImpl.serialNumberService = serialNumberService;
	}

	public void setSalesOrderDao(SalesOrderDao orderDao) {
		this.orderDao = orderDao;
	}

}
