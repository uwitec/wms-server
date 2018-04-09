package com.yorma.wms.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.common.service.api.SerialNumberService;
import com.yorma.wms.dao.api.ReceivingNoteAsnDao;
import com.yorma.wms.dao.api.ReceivingNoteDao;
import com.yorma.wms.dao.api.ReceivingPackingListDao;
import com.yorma.wms.entity.ReceivingNoteAsn;
import com.yorma.wms.entity.ReceivingPackingList;
import com.yorma.wms.service.api.ReceivingPackingListService;
import com.yorma.wms.service.impl.base.BaseServiceImpl;

/**
 * 入库货物信息service实现
 * 
 * @Description:
 * @author su
 * @date 2017年8月25日
 * @version V1.0
 */
public class ReceivingPackingListServiceImpl extends BaseServiceImpl<ReceivingPackingList>
		implements ReceivingPackingListService {

	private static final Logger logger = LoggerFactory.getLogger(ReceivingPackingList.class);

	private ReceivingNoteDao receivingNoteDao;
	private ReceivingNoteAsnDao receivingNoteAsnDao;
	private ReceivingPackingListDao receivingPackingListDao;

	private static SerialNumberService serialNumberService;

	@Override
	public ResponseMessage save(ReceivingPackingList rpl) {
		if (rpl == null) {
			return new ResponseMessage(false, "10004");
		}
		// id为null时生成UUID
		if (rpl.getId() == null) {
			UUID uuid = UUID.randomUUID();
			rpl.setUuid(uuid.toString());
		}
		return super.save(rpl);
	}

	/**
	 * 批量添加入库明细
	 */
	@Override
	public ResponseMessage saveBatch(List<ReceivingPackingList> receivingPackingLists) {
		try {
			if (receivingPackingLists == null || receivingPackingLists.isEmpty()) {
				return new ResponseMessage(false, "10004");
			}
			
			for (ReceivingPackingList receivingPackingList : receivingPackingLists) {
				if (receivingPackingList.getId() == null) {
					ResponseMessage responseMessage = serialNumberService.getSerialNumber("PK",
							new SimpleDateFormat("yyMMdd").format(new Date()), 4);
					if (!responseMessage.getSuccess()) {
						return responseMessage;
					}

					String packagingNo = (String) responseMessage.getData();
					receivingPackingList.setPackagingNo(packagingNo);
					
					UUID uuid = UUID.randomUUID();
					receivingPackingList.setUuid(uuid.toString());
				}
				
				int rows = receivingPackingListDao.save(receivingPackingList);
				if (rows==0) {
					return new ResponseMessage(false, "10014");
				}
				rows=receivingNoteAsnDao.updateStatusByAsnUUID(20, receivingPackingList.getAnsUuid());
				if (rows==0) {
					return new ResponseMessage(false, "10014");
				}
			}
			
			return new ResponseMessage(true, true);
		} catch (Exception e) {
			logger.error("ReceivingPackingListService:方法[saveBatch]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 批量修改入库货物信息并生成集装条码
	 */
	@Override
	public ResponseMessage updateBatch(List<ReceivingPackingList> entitys) {
		try {
			ResponseMessage responseMessage = serialNumberService.getSerialNumber("PL",
					new SimpleDateFormat("yyMMdd").format(new Date()), 4);
			if (!responseMessage.getSuccess()) {
				return responseMessage;
			}
			String palletNo = (String) responseMessage.getData();
			for (ReceivingPackingList receivingPackingList : entitys) {
				receivingPackingList.setPalletNo(palletNo);
				int rows=receivingPackingListDao.save(receivingPackingList);
				if (rows==0) {
					return new ResponseMessage(false, "10014");
				}
			}
			return new ResponseMessage(true);
		} catch (Exception e) {
			logger.error("ReceivingPackingListService:方法[updateBatch]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息%s", e));
		}
	}
	
	/**
	 * 查询入库货物信息
	 * 
	 * @param receivingNoteNo
	 *            收货单号
	 */
	public ResponseMessage getReceivingPackingListByNoteNo(String receivingNoteNo) {
		try {
			if (isBlank(receivingNoteNo)) {
				return new ResponseMessage(false, "10012");
			}
			List<ReceivingPackingList> receivingPackingLists = receivingPackingListDao.getReceivingPackingListByNoteNo(receivingNoteNo,
					null, null, null);
			return new ResponseMessage(true, receivingPackingLists);
		} catch (Exception e) {
			logger.error("ReceivingPackingListService:方法[getReceivingPackingListByNoteNo]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 新增入库货物信息同时修改入库明细信息、修改入库单状态为30（已到货）
	 */
	@Override
	public ResponseMessage oneSaveBatch(List<ReceivingPackingList> receivingPackingLists,
			List<ReceivingNoteAsn> receivingNoteAsns) {
		try {
			if (receivingPackingLists == null || receivingPackingLists.isEmpty()) {
				return new ResponseMessage(false, "10004");
			}
			if (receivingNoteAsns == null || receivingNoteAsns.isEmpty()) {
				return new ResponseMessage(false, "10004");
			}

			for (ReceivingPackingList receivingPackingList : receivingPackingLists) {
				UUID uuid = UUID.randomUUID();
				receivingPackingList.setUuid(uuid.toString());

				String packagingNo = "";

				ResponseMessage responseMessage = serialNumberService.getSerialNumber("PK",
						new SimpleDateFormat("yyMMdd").format(new Date()), 4);
				if (!responseMessage.getSuccess()) {
					return responseMessage;
				}
				packagingNo = (String) responseMessage.getData();
				receivingPackingList.setPackagingNo(packagingNo);
				receivingPackingList.setStatus(10);
				int rows = receivingPackingListDao.save(receivingPackingList);
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}
			}
			String receivingNoteNo = "";
			for (ReceivingNoteAsn receivingNoteAsn : receivingNoteAsns) {
				ReceivingNoteAsn noteAsn = receivingNoteAsnDao.getReceivingNoteAsnByUUID(receivingNoteAsn.getUuid());
				receivingNoteAsn.setCreateBy(noteAsn.getCreateBy());
				receivingNoteAsn.setCreateDate(noteAsn.getCreateDate());
				receivingNoteAsn.setStatus(20);
				int rows = receivingNoteAsnDao.save(receivingNoteAsn);
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}
				receivingNoteNo = receivingNoteAsn.getReceivingNoteNo();
			}
			int rows = receivingNoteDao.updateStatus(receivingNoteNo, 30);
			if (rows == 0) {
				return new ResponseMessage(false, "10014");
			}
			return new ResponseMessage(true);
		} catch (Exception e) {
			logger.error("ReceivingPackingListService:方法[oneSaveBatch]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据入库单号、状态。开始日期。截止日期查询入库货物信息
	 */
	@Override
	public ResponseMessage getReceivingPackingListByNoteNo(String receivingNoteNo, Integer status, Date startDate,
			Date endDate) {
		try {
			if (isBlank(receivingNoteNo)) {
				return new ResponseMessage(false, "10012");
			}
			
			
			List<ReceivingPackingList> receivingPackingLists = receivingPackingListDao.getReceivingPackingListByNoteNo(receivingNoteNo, status, startDate, endDate);
			return new ResponseMessage(true, receivingPackingLists);
		} catch (Exception e) {
			logger.error("ReceivingPackingListService:方法[getReceivingPackingListByNoteNo]出现异常，异常信息是 :  {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 添加多条明细和入库货物信息并删除单条明细（单条包装分配）
	 * @param receivingNoteAsns	入库明细预告集合
	 * @param asnUuid	入库明细预告UUID
	 * @param warehouse	仓库代码
	 * @param createBy	创建人
	 * @return	ResponseMessage对象
	 * 				<pre>
	 * 					[Success:false,Status:10012,msg:参数为null或空字符串]
	 * 					[Success:false,Status:10020,msg:单号生成失败]
	 * 					[Success:false,Status:60024,msg:该记录已到货，请撤销后操作]
	 * 					[Success:false,Status:60025,msg:请核实分配方式]
	 * 					[Success:false,Status:60022,msg:该记录已分配]
	 * 					[Success:false,Status:10003,msg:异常信息]
	 * 					[Success:false,Status:60036,msg:包装数量为异常]
	 * 					[Success:true] 操作成功
	 * 				</pre>
	 */
	@Override
	public ResponseMessage saveReceivingPackingListAndAsn(List<ReceivingNoteAsn> receivingNoteAsns, String asnUuid,String warehouse,String createBy) {
		try {
			if (isBlank(asnUuid) || isBlank(warehouse)) {
				return new ResponseMessage(false, "10012");
			}
			ReceivingNoteAsn oldReceivingNoteAsn = receivingNoteAsnDao.getReceivingNoteAsnByUUID(asnUuid);
			if (oldReceivingNoteAsn.getIsMerge()!=null && oldReceivingNoteAsn.getIsMerge() ) {
				return new ResponseMessage(false, "60025");
			}
			
			if (oldReceivingNoteAsn.getStatus()==20) {
				return new ResponseMessage(false, "60024");
			}
			//如果原明细存在合并批次号
			if (oldReceivingNoteAsn.getMergeLotNo() != null) {
				return new ResponseMessage(false, "60022", String.format("异常信息：%s", "该记录已分配，请撤销后分配"));
			}
			//删除单条明细
			int rows=receivingNoteAsnDao.removeByUUId(asnUuid);
			//循环保存多条明细
			for (ReceivingNoteAsn receivingNoteAsn : receivingNoteAsns) {
				if (receivingNoteAsn.getPackagingQty() == 0) {
					return new ResponseMessage(false, "60036", String.format("异常信息：%s", "包装数量为异常"));
				}
				//生成合并批次号
				ResponseMessage responseMessage = serialNumberService.getSerialNumber("ME",
						new SimpleDateFormat("yyMMdd").format(new Date()), 4);
				if (!responseMessage.getSuccess()) {
					return responseMessage;
				}
				String mergeLotNo= (String) responseMessage.getData();
				
				receivingNoteAsn.setUuid(UUID.randomUUID().toString());
				receivingNoteAsn.setMergeLotNo(mergeLotNo);
				receivingNoteAsn.setIsMerge(false);
				receivingNoteAsn.setStatus(10);
				//保存明细
				rows= receivingNoteAsnDao.save(receivingNoteAsn);
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}
				
				int pos = receivingNoteAsn.getPackagingKey().indexOf("/");
				// 获取包装键最大包装
				int length = receivingNoteAsn.getPackagingKey().substring(0, pos).split("-").length;
				int entryQty = Integer.valueOf(receivingNoteAsn.getPackagingKey().substring(0, pos).split("-")[length - 1]);
				
				for (int i = 0; i < receivingNoteAsn.getPackagingQty(); i++) {
					//生成包装条码
					responseMessage = serialNumberService.getSerialNumber("PK",
							new SimpleDateFormat("yyMMdd").format(new Date()), 4);
					if (!responseMessage.getSuccess()) {
						return responseMessage;
					}
					String packagingNo = (String) responseMessage.getData();
					ReceivingPackingList receivingPackingList = new ReceivingPackingList(null, UUID.randomUUID().toString(), receivingNoteAsn.getUuid(), receivingNoteAsn.getReceivingNoteNo(), receivingNoteAsn.getGoodsUuid(), receivingNoteAsn.getPn(), receivingNoteAsn.getGoodsName(), receivingNoteAsn.getGoodsModel(), receivingNoteAsn.getItem(), receivingNoteAsn.getEmsNo(), receivingNoteAsn.getSku(), receivingNoteAsn.getMu(), warehouse, null, null, entryQty, packagingNo, receivingNoteAsn.getPackagingKey(), null, null, null, null, null, null, null, null, null, receivingNoteAsn.getIsQualifd(), createBy, new Date(), null, null, 0, mergeLotNo); 
					receivingPackingList.setLotNo(receivingNoteAsn.getLotNo());
					rows = receivingPackingListDao.save(receivingPackingList); 
					if (rows == 0) {
						return new ResponseMessage(false, "10014");
					}
				}
			}
			return new ResponseMessage(true);
		} catch (Exception e) {
			logger.error("ReceivingPackingListService:方法[saveReceivingPackingListAndAsn]（单条包装分配）出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 添加多条明细和修改多条入库货物信息（合并包装分配）
	 * @param asnUuids		明细UUID集合
	 * @param receivingNoteAsn	合并明细实体
	 * @param warehouse	仓库代码
	 * @param createBy	创建人
	 * @return ResponseMessage对象
	 * 				<pre>
	 * 					[Success:false,Status:10012,msg:参数为null或空字符串]
	 * 					[Success:false,Status:10020,msg:单号生成失败]
	 * 					[Success:false,Status:60024,msg:该记录已到货，请撤销后操作]
	 * 					[Success:false,Status:60025,msg:该记录已分配]
	 * 					[Success:false,Status:10003,msg:异常信息]
	 * 					[Success:false,Status:60036,msg:包装数量为异常]
	 * 					[Success:true] 操作成功
	 * 				</pre>
	 */
	@SuppressWarnings("unused")
	@Override
	public ResponseMessage saveReceivingPackingListAndAsns(List<String> asnUuids, ReceivingNoteAsn receivingNoteAsn,
			String warehouse, String createBy) {
		try {
			if (isBlank(createBy) || isBlank(warehouse)) {
				return new ResponseMessage(false, "10012");
			}
			//如果合并批次号不为null，根据合并批次号删除入库货物信息
			if (receivingNoteAsn.getMergeLotNo() != null) {
				int rows= receivingPackingListDao.deleteReceivingPackingListByMergeLotNo(receivingNoteAsn.getMergeLotNo());
			}
			//合并批次号生成
			ResponseMessage responseMessage = serialNumberService.getSerialNumber("ME",
					new SimpleDateFormat("yyMMdd").format(new Date()), 4);
			if (!responseMessage.getSuccess()) {
				return responseMessage;
			}
			String mergeLotNo= (String) responseMessage.getData();
			
			for (String asnUuid : asnUuids) {
				ReceivingNoteAsn noteAsn = receivingNoteAsnDao.getReceivingNoteAsnByUUID(asnUuid);
				if (noteAsn.getStatus()==20) {
					return new ResponseMessage(false, "60024");
				}
				if (receivingNoteAsn.getPackagingQty() == 0) {
					return new ResponseMessage(false, "60036", String.format("异常信息：%s", "包装数量为异常"));
				}
				
				noteAsn.setMergeLotNo(mergeLotNo);
				noteAsn.setPackagingKey(receivingNoteAsn.getPackagingKey());
				noteAsn.setAcceptanceQty(receivingNoteAsn.getAcceptanceQty());
				noteAsn.setPackagingQty(receivingNoteAsn.getPackagingQty());
				noteAsn.setIsMerge(true);
				noteAsn.setStatus(10);
				int rows=receivingNoteAsnDao.save(noteAsn);
				if (rows== 0) {
					return new ResponseMessage(false, "10014");
				}
			}
			
			int pos = receivingNoteAsn.getPackagingKey().indexOf("/");
			// 获取包装键最大包装
			int length = receivingNoteAsn.getPackagingKey().substring(0, pos).split("-").length;
			int entryQty = Integer.valueOf(receivingNoteAsn.getPackagingKey().substring(0, pos).split("-")[length - 1]);
			//生成入库货物信息
			for (int i = 0; i < receivingNoteAsn.getPackagingQty(); i++) {
				//生成包装条码
				responseMessage = serialNumberService.getSerialNumber("PK",
						new SimpleDateFormat("yyMMdd").format(new Date()), 4);
				if (!responseMessage.getSuccess()) {
					return responseMessage;
				}
				String packagingNo = (String) responseMessage.getData();
				
				int rows = receivingPackingListDao.save(new ReceivingPackingList(null, UUID.randomUUID().toString(), receivingNoteAsn.getUuid(), receivingNoteAsn.getReceivingNoteNo(), receivingNoteAsn.getGoodsUuid(), receivingNoteAsn.getPn(), receivingNoteAsn.getGoodsName(), receivingNoteAsn.getGoodsModel(), receivingNoteAsn.getItem(), receivingNoteAsn.getEmsNo(), receivingNoteAsn.getSku(), receivingNoteAsn.getMu(), warehouse, null, null, entryQty, packagingNo, receivingNoteAsn.getPackagingKey(), null, null, null, null, null, null, null, null, null, receivingNoteAsn.getIsQualifd(), createBy, new Date(), null, null, 0, mergeLotNo)); 
			}
			
			
			return new ResponseMessage(true);
		} catch (Exception e) {
			logger.error("ReceivingPackingListService:方法[saveReceivingPackingListAndAsn]（合并包装分配）出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	public void setReceivingNoteDao(ReceivingNoteDao receivingNoteDao) {
		this.receivingNoteDao = receivingNoteDao;
	}

	public static void setSerialNumberService(SerialNumberService serialNumberService) {
		ReceivingPackingListServiceImpl.serialNumberService = serialNumberService;
	}

	public void setReceivingNoteAsnDao(ReceivingNoteAsnDao receivingNoteAsnDao) {
		this.receivingNoteAsnDao = receivingNoteAsnDao;
	}

	public void setReceivingPackingListDao(ReceivingPackingListDao receivingPackingListDao) {
		super.setBaseDao(receivingPackingListDao);
		this.receivingPackingListDao = receivingPackingListDao;
	}
}
