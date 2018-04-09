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
import com.yorma.wms.dao.api.PurchaseOrderDao;
import com.yorma.wms.dao.api.ReceivingNoteAsnDao;
import com.yorma.wms.dao.api.ReceivingNoteDao;
import com.yorma.wms.dao.api.ReceivingPackingListDao;
import com.yorma.wms.dao.api.StatusHistoryDao;
import com.yorma.wms.entity.ReceivingNote;
import com.yorma.wms.entity.ReceivingNoteAsn;
import com.yorma.wms.entity.ReceivingPackingList;
import com.yorma.wms.entity.StatusHistory;
import com.yorma.wms.entity.dto.ReceivingNoteDto;
import com.yorma.wms.service.api.ReceivingNoteService;
import com.yorma.wms.service.impl.base.BaseServiceImpl;

/**
 * 收货单service实现
 * 
 * @Description:
 * @author su
 * @date 2017年8月25日
 * @version V1.0
 */
public class ReceivingNoteServiceImpl extends BaseServiceImpl<ReceivingNote>implements ReceivingNoteService {

	private static final Logger logger = LoggerFactory.getLogger(ReceivingNote.class);

	private PurchaseOrderDao orderDao;
	private ReceivingNoteDao receivingNoteDao;
	private ReceivingNoteAsnDao receivingNoteAsnDao;
	private ReceivingPackingListDao receivingPackingListDao;
	private StatusHistoryDao statusHistoryDao;
	private static SerialNumberService serialNumberService;


	/**
	 * 添加(修改)收货单
	 * 
	 * @param map
	 *            用于存放查询条件
	 */
	@Override
	public ResponseMessage save(ReceivingNote receivingNote) {
		try {
			if (receivingNote == null) {
				return new ResponseMessage(false, "10004");
			}
			if (receivingNote.getId() == null) {
				// 获取生成的收货单号
				ResponseMessage responseMessage = serialNumberService.getSerialNumber("RK",
						new SimpleDateFormat("yyyyMMdd").format(new Date()), 4);
				if (!responseMessage.getSuccess()) {
					return responseMessage;
				}
				String receivingNoteNo = (String) responseMessage.getData();
				receivingNote.setReceivingNoteNo(receivingNoteNo);

				int rows = receivingNoteDao.save(receivingNote);
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}

				Field field = receivingNote.getClass().getDeclaredField("id");
				field.setAccessible(true);// 设置些属性是可以访问的
				Object value = field.get(receivingNote); // 得到此属性的值
				Object data = value + "," + receivingNote.getReceivingNoteNo();
				return new ResponseMessage(true, data);
			} else {
				ResponseMessage responseMessage = find(receivingNote.getId());
				ReceivingNote rec = (ReceivingNote) responseMessage.getData();
				receivingNote.setCreateDate(rec.getCreateDate());
				if (rec.getStatus() != 10) {
					return new ResponseMessage(false, "60020");
				}
			}
		} catch (Exception e) {
			logger.error("ReceivingNoteService:方法[save]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
		return super.save(receivingNote);
	}

	/**
	 * 修改状态
	 */
	@Override
	public ResponseMessage updateStatus(String receivingNoteNo, int status) {
		try {
			if (isBlank(receivingNoteNo)) {
				return new ResponseMessage(false, "10012");
			}
			int status1 = receivingNoteDao.getStatus(receivingNoteNo);
			// 确保状态修改是线形顺序
			if (status1 + 10 == status) {
				
				int rows = receivingNoteDao.updateStatus(receivingNoteNo, status);

				return new ResponseMessage(true, rows);
			}
			return new ResponseMessage(false, "60006");
		} catch (Exception e) {
			logger.error("ReceivingNoteService:方法[updateStatus]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}

	}
	
	/**
	 * 修改状态
	 * @param receivingNoteNo 收货单号
	 * @param status 状态码
	 * @param lastName 修改人
	 * @return 返回ResponseMessage对象
	 *         <ul>
	 *         <li>若在修改状态过程中出现了异常信息，ResponseMessage中的success为false、 status:
	 *         10003、msg：异常的说明信息</li>
	 *         <li>修改状态成功,ResponseMessage中的success:true,data: {@code 影响行数}</li>
	 *         <li>修改状态失败,ResponseMessage中的success:false,status: "60006"</li>
	 *         <li>若字符串参数为null或者空字符串，ResponseMessage中的success为false、 status:
	 *         10012</li>
	 *         </ul>
	 */
	@Override
	public ResponseMessage updateStatus(String receivingNoteNo, int status, String lastName) {
		try {
			if (isBlank(receivingNoteNo)) {
				return new ResponseMessage(false, "10012");
			}
			int status1 = receivingNoteDao.getStatus(receivingNoteNo);
			// 确保状态修改是线形顺序
			if (status1 + 10 == status) {
				
				int rows = receivingNoteDao.updateStatus(receivingNoteNo, status, new Date(), lastName);

				return new ResponseMessage(true, rows);
			}
			return new ResponseMessage(false, "60006");
		} catch (Exception e) {
			logger.error("ReceivingNoteService:方法[updateStatus]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据入库单号弃审
	 * 
	 * @param deliveryNoteNo
	 *            出库单号
	 * @return Responsemessage对象
	 * 
	 *         <pre>
	 * 			[Successs:false ,Status:10012 , mag:入库单号为null或者空字符串]
	 * 			[Successs:false ,Status:60021 , mag:该入库单已分配，不可撤审]
	 * 			[Successs:true ,data:影响行数 ]
	 *         </pre>
	 */
	@Override
	public ResponseMessage revokeAudit(String receivingNoteNo) {
		try {
			if (isBlank(receivingNoteNo)) {
				return new ResponseMessage(false, "10012");
			}
			List<ReceivingPackingList> packingLists = receivingPackingListDao
					.getReceivingPackingListByNoteNo(receivingNoteNo, null, null, null);
			if (packingLists !=null && !packingLists.isEmpty()) {
				return new ResponseMessage(false, "60021");
			}
			if (receivingNoteAsnDao.getReceivingNoteAsnCountByReceivingNoteNo(receivingNoteNo) != 0) {
				return new ResponseMessage(false, "60022", String.format("异常信息：%s", "有数据已分配"));
			}
			return new ResponseMessage(true, receivingNoteDao.updateStatus(receivingNoteNo, 10));
		} catch (Exception e) {
			logger.error("ReceivingNoteService:方法[revokeAudit]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10012", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 查询收货单
	 * 
	 * @param map
	 *            用于存放查询条件
	 */
	public ResponseMessage getReceivingNote(Map<String, Object> map, Map<String, Object> map1) {
		try {
			List<ReceivingNoteDto> receivingNoteDtos = receivingNoteDao.getReceivingNote(map, map1);
			return new ResponseMessage(true, receivingNoteDtos);
		} catch (Exception e) {
			logger.error("ReceivingNoteService:方法[getReceivingNote]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据条件查询收货单
	 * @param map	将查询条件放入map集合
	 * @param map1	将查询条件放入map1集合
	 * @param pageNumber	当前页
	 * @param pageSize		每页显示数
	 * @return	返回ResponseMessage对象
	 * 				<pre>
	 * 					[Success:false, Status:10003,Msg:异常信息]
	 * 					[Success:false, Status:10012,Msg:参数[pageNumber]或[pageSize]为null]
	 * 					[success: true data:new ResponseData<>(当前页, 总页数(1页), 总条数, null)] 无数据(总条数为0)
	 * 					[Success:true ,Data:new ResponseData<>(当前页, 总页数, 总条数, List<入库单Dto实体>]
	 * 				</pre>
	 */
	@Override
	public ResponseMessage getReceivingNote(Map<String, Object> map, Map<String, Object> map1, Integer pageNumber,
			Integer pageSize) {
		try {
			if (pageNumber == null || pageSize ==null) {
				return new ResponseMessage(false, "10012", "参数[pageNumber]或[pageSize]为null");
			}
			List<ReceivingNoteDto> receivingNoteDtos = new ArrayList<>();
			//获取总条数
			int counts = receivingNoteDao.getCounts(map, map1);
			if (counts == 0) {
				return new ResponseMessage(true, new ResponseData<>(pageNumber, 1, counts, receivingNoteDtos));
			}
			if (logger.isDebugEnabled()) {
				logger.debug("ReceivingNoteService:方法[getReceivingNote]: 总条数[counts]:{} ", counts);
			}
			//获取总页数
			int page = counts%pageSize == 0 ? counts/pageSize : counts/pageSize+1;
			
			receivingNoteDtos = receivingNoteDao.getReceivingNote(map, map1,pageNumber,pageSize);
			return new ResponseMessage(true, new ResponseData<>(pageNumber, page, counts, receivingNoteDtos));
		} catch (Exception e) {
			logger.error("ReceivingNoteService:方法[getReceivingNote]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 根据收货单号删除收货单
	 * 
	 * @param receivingNoteNo
	 * @return
	 */
	@SuppressWarnings("unused")
	@Override
	public ResponseMessage delete(String receivingNoteNo) {
		try {
			if (isBlank(receivingNoteNo)) {
				return new ResponseMessage(false, "10012");
			}
			ReceivingNote receivingNote = receivingNoteDao.getReceivingNoteByReceivingNoteNo(receivingNoteNo);
			if (receivingNote != null) {
				int status = receivingNoteDao.getStatus(receivingNoteNo);
				// 判断状态是否为已入库，如果是返回60003
				if (status >= 50) {
					return new ResponseMessage(false, "60003");
				}
				/*
				 * 回退订单状态
				 */
				if (receivingNote.getOrderPo() != null) {
					int rows = orderDao.updateReceivingByPo(receivingNote.getOrderPo(), false);
					if (rows == 0) {
						return new ResponseMessage(false, "10014");
					}
				}
				int rows = receivingNoteDao.delete(receivingNoteNo);
				List<ReceivingNoteAsn> receivingNoteAsns = receivingNoteAsnDao
						.getReceivingNoteAsnByNoteNo(receivingNoteNo, null);
				if (receivingNoteAsns !=null && !receivingNoteAsns.isEmpty()) {
					rows = receivingNoteAsnDao.delete(receivingNoteNo);
					rows = receivingPackingListDao.delete(receivingNoteNo);
				}
			}

			return new ResponseMessage(true, true);
		} catch (Exception e) {
			logger.error("ReceivingNoteService:方法[delete]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 保存入库单及明细(入库订单生成)
	 * 
	 * @param receivingNote
	 * @param receivingNoteAsns
	 * @return ResponsMessage
	 * 
	 *         <pre>
	 * 				[success: false status:10004] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: false status:60002] 单号重复 
	 * 				[success: true 	data:影响行数] 操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage saveReceivingNoteAndAsn(ReceivingNote receivingNote,
			List<ReceivingNoteAsn> receivingNoteAsns, String po) {
		try {
			if (receivingNote == null) {
				return new ResponseMessage(false, "10004");
			}
			if (receivingNoteDao.getReceivingNoteByReceivingNoteNo(receivingNote.getReceivingNoteNo()) != null) {
				return new ResponseMessage(false, "60002", String.format("异常信息：%s", "单号重复"));
			}

			receivingNote.setCreateDate(new Date());
			int rows = receivingNoteDao.save(receivingNote);
			if (rows == 0) {
				return new ResponseMessage(false, "10014");
			}
			for (ReceivingNoteAsn receivingNoteAsn : receivingNoteAsns) {
				receivingNoteAsn.setUuid(UUID.randomUUID().toString());
				receivingNoteAsn.setReceivingNoteNo(receivingNote.getReceivingNoteNo());
				receivingNoteAsn.setCreateDate(new Date());
				rows = receivingNoteAsnDao.save(receivingNoteAsn);
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}
			}
			rows = orderDao.updateReceivingByPo(po, true);
			if (rows == 0) {
				return new ResponseMessage(false, "10014");
			}
			StatusHistory statusHistory = new StatusHistory();
			statusHistory.setApplyId(receivingNote.getReceivingNoteNo());
			statusHistory.setType("1");
			statusHistory.setCreateTime(new Date());
			statusHistory.setLastTime(new Date());
			statusHistory.setStatusId(10);
			rows = statusHistoryDao.save(statusHistory);
			return new ResponseMessage(true, rows);
		} catch (Exception e) {
			logger.error("ReceivingNoteService:方法[saveReceivingNoteAndAsn]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 保存入库单及明细
	 * 
	 * @param receivingNote
	 * @param receivingNoteAsns
	 * @return ResponsMessage
	 * 
	 *         <pre>
	 * 				[success: false status:10004] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: false status:60013,msg:该入库订单已审核] 
	 * 				[success: false status:60002,msg:单号重复]
	 * 				[success: false status:10014] 影响行数为0，保存失败
	 * 				[success: true 	data:入库单表头id] 操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage saveReceivingNoteAndAsn(ReceivingNote receivingNote,
			List<ReceivingNoteAsn> receivingNoteAsns) {
		try {
			if (receivingNote == null) {
				return new ResponseMessage(false, "10004");
			}
			if (receivingNote.getStatus() == 20) {
				return new ResponseMessage(false, "60013", String.format("异常信息:%s", "该入库单已被审核！"));
			}

			if (receivingNote.getId() == null) {
				if (receivingNoteDao.getReceivingNoteByReceivingNoteNo(receivingNote.getReceivingNoteNo()) != null) {
					return new ResponseMessage(false, "60002", String.format("异常信息：%s", "单号重复"));
				}
				receivingNote.setCreateDate(new Date());
			} else {
				receivingNote.setLastUpdateDate(new Date());
			}
			int rows = receivingNoteDao.save(receivingNote);
			if (rows == 0) {
				return new ResponseMessage(false, "10014");
			}
			rows = receivingNoteAsnDao.delete(receivingNote.getReceivingNoteNo());
			if (receivingNoteAsns != null && !receivingNoteAsns.isEmpty()) {
				for (ReceivingNoteAsn receivingNoteAsn : receivingNoteAsns) {
					if (receivingNoteAsn.getId() == null) {
						receivingNoteAsn.setUuid(UUID.randomUUID().toString());
						receivingNoteAsn.setReceivingNoteNo(receivingNote.getReceivingNoteNo());
						receivingNoteAsn.setCreateDate(new Date());
					} else {
						receivingNoteAsn.setId(null);
					}
					rows = receivingNoteAsnDao.save(receivingNoteAsn);
					if (rows == 0) {
						return new ResponseMessage(false, "10014");
					}
				}
			}
			Field field = receivingNote.getClass().getDeclaredField("id");
			field.setAccessible(true);// 设置些属性是可以访问的
			Object value = field.get(receivingNote); // 得到此属性的值
			return new ResponseMessage(true, value);
		} catch (Exception e) {
			logger.error("ReceivingNoteService:方法[saveReceivingNoteAndAsn]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	public void setReceivingNoteDao(ReceivingNoteDao receivingNoteDao) {
		super.setBaseDao(receivingNoteDao);
		this.receivingNoteDao = receivingNoteDao;
	}

	public void setReceivingNoteAsnDao(ReceivingNoteAsnDao receivingNoteAsnDao) {
		this.receivingNoteAsnDao = receivingNoteAsnDao;
	}

	public void setReceivingPackingListDao(ReceivingPackingListDao receivingPackingListDao) {
		this.receivingPackingListDao = receivingPackingListDao;
	}

	public static void setSerialNumberService(SerialNumberService serialNumberService) {
		ReceivingNoteServiceImpl.serialNumberService = serialNumberService;
	}

	public void setPurchaseOrderDao(PurchaseOrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void setStatusHistoryDao(StatusHistoryDao statusHistoryDao) {
		this.statusHistoryDao = statusHistoryDao;
	}
}
