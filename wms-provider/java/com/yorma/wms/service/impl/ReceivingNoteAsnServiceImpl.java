package com.yorma.wms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.dao.api.ReceivingNoteAsnDao;
import com.yorma.wms.dao.api.ReceivingNoteDao;
import com.yorma.wms.dao.api.ReceivingPackingListDao;
import com.yorma.wms.entity.ReceivingNoteAsn;
import com.yorma.wms.entity.ReceivingPackingList;
import com.yorma.wms.service.api.ReceivingNoteAsnService;
import com.yorma.wms.service.impl.base.BaseServiceImpl;

/**
 * 收货明细service实现
 * 
 * @Description:
 * @author su
 * @date 2017年8月25日
 * @version V1.0
 */
public class ReceivingNoteAsnServiceImpl extends BaseServiceImpl<ReceivingNoteAsn>implements ReceivingNoteAsnService {

	private static final Logger logger = LoggerFactory.getLogger(ReceivingNoteAsn.class);

	private ReceivingNoteDao receivingNoteDao;
	private ReceivingNoteAsnDao receivingNoteAsnDao;
	private ReceivingPackingListDao receivingPackingListDao;

	/**
	 * 重写save方法，根据id是否为null判断是添加还是修改，添加时自动生成uuid
	 */
	@Override
	public ResponseMessage save(ReceivingNoteAsn rna) {
		if (rna == null) {
			return new ResponseMessage(false, "10004");
		}
		if (rna.getId() == null) {
			// 生成全球唯一标识
			UUID uuid = UUID.randomUUID();
			rna.setUuid(uuid.toString());
		}
		return super.save(rna);
	}

	/**
	 * 根据收货单号查询收货明细
	 * @param receivingNoteNo		收货单号
	 * @param status				状态码
	 * @return
	 *  				返回ResponseMessage对象
	 *  	<ul>
	 *         <li>若字符串参数为null或者空字符串，ResponseMessage中的success为false、
	 *         status: 10012</li>
	 *         <li>若在操作的过程中出现了异常信息，ResponseMessage中的success为false、
	 *         status: 10003、msg：异常的说明信息</li>
	 *         <li>操作成功,ResponseMessage中的success:true,data:
	 *         {@code List<ReceivingNoteAsn>}</li>
	 *       </ul>
	 */
	public ResponseMessage getReceivingNoteAsnByNoteNo(String receivingNoteNo,Integer status) {
		try {
			if (isBlank(receivingNoteNo)) {
				return new ResponseMessage(false, "10012");
			}
			List<ReceivingNoteAsn> lia = receivingNoteAsnDao.getReceivingNoteAsnByNoteNo(receivingNoteNo,status);
			return new ResponseMessage(true, lia);
		} catch (Exception e) {
			logger.error("ReceivingNoteAsnService:方法[getReceivingNoteAsnByNoteNo]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据UUID删除明细，并添加多条明细
	 */
	@Override
	public ResponseMessage batchByAsn(String asnUuid, List<ReceivingNoteAsn> asnList) {
		try {
			if (isBlank(asnUuid)) {
				return new ResponseMessage(false, "10012");
			}
			if (asnList == null ||asnList.isEmpty()) {
				return new ResponseMessage(false, "10004");
			}
			int rows=receivingNoteAsnDao.removeByUUId(asnUuid);
			for (ReceivingNoteAsn rec : asnList) {
				UUID uuid=UUID.randomUUID();
				rec.setUuid(uuid.toString());
				rows=receivingNoteAsnDao.save(rec);
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}
			}
			return new ResponseMessage(true, true);
		} catch (Exception e) {
			logger.error("ReceivingNoteAsnService:方法[batchByAsn]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 删除明细和入库货物信息
	 */
	@SuppressWarnings("unused")
	@Override
	public ResponseMessage delete(String uuid) {

		try {
			if (isBlank(uuid)) {
				return new ResponseMessage(false, "10012");
			}
			//用于判断收货单状态在入库之前
			ReceivingNoteAsn receivingNoteAsn=receivingNoteAsnDao.getReceivingNoteAsnByUUID(uuid);
			if (receivingNoteDao.getStatus(receivingNoteAsn.getReceivingNoteNo())>=50) {
				return new ResponseMessage(false, "60003");
			}
			int rows = receivingNoteAsnDao.removeByUUId(uuid);
			rows= receivingPackingListDao.removeByAsnUUId(uuid);
			return new ResponseMessage(true, true);
		} catch (Exception e) {
			logger.error("ReceivingNoteAsnService:方法[delete]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 明细合并
	 * @param asnUuids	明细UUID集合
	 * @return	ResponseMessage对象
	 * 				<pre>
	 * 					[Success:false,Status:10004,msg:参数为null]
	 * 					[Success:false,Status:10003,msg:异常信息]
	 * 					[Success:false,Status:60022,msg:有记录已分配]
	 * 					[Success:false,Status:60025,msg:存在单条分配的记录]
	 * 					[Success:false,Status:60028,msg:明细商品种类不同]
	 * 					[Success:true,Data:明细实体]
	 * 				</pre>
	 */
	@Override
	public ResponseMessage mergeReceivingNoteAsn(List<String> asnUuids) {
		try {
			if (asnUuids == null || asnUuids.isEmpty()) {
				return new ResponseMessage(false, "10004",String.format("异常信息：%s", "参数为null"));
			}
			//用于存放合并后明细预告
			ReceivingNoteAsn newReceivingNoteAsn = new ReceivingNoteAsn();
			//用于判断是否进行了明细合并
			boolean falg=false;
			//循环明细UUID获取明细并合并
			for (String asnUuid : asnUuids) {
				falg=false;
				ReceivingNoteAsn oldReceivingNoteAsn = receivingNoteAsnDao.getReceivingNoteAsnByUUID(asnUuid);
				//判断是否到货
				if (oldReceivingNoteAsn.getStatus()==20) {
					return new ResponseMessage(false, "60024",String.format("异常信息：%s", "该记录已到货"));
				}
				//判断是否存在单条分配的明细信息
				if (oldReceivingNoteAsn.getIsMerge() != null && !oldReceivingNoteAsn.getIsMerge()) {
					return new ResponseMessage(false, "60025",String.format("异常信息：%s", "存在单条分配的记录"));
				}
				//判断是否存在分配后的信息
				if (oldReceivingNoteAsn.getMergeLotNo() != null) {
					return new ResponseMessage(false, "60022",String.format("异常信息：%s", "存在已分配的记录"));
				}
				if (oldReceivingNoteAsn.getPackagingKey() == null) {
					oldReceivingNoteAsn.setPackagingKey("");
				}
				if (newReceivingNoteAsn.getId() == null) {
					newReceivingNoteAsn=oldReceivingNoteAsn;
					continue;
				}
				
				//判断明细是否符合合并条件
				if (newReceivingNoteAsn.getPn().equals(oldReceivingNoteAsn.getPn()) && newReceivingNoteAsn.getPackagingKey().equals(oldReceivingNoteAsn.getPackagingKey())) {
					newReceivingNoteAsn.setExpectQty(newReceivingNoteAsn.getExpectQty()+oldReceivingNoteAsn.getExpectQty());
					newReceivingNoteAsn.setEmsNo(null);
					newReceivingNoteAsn.setItem(null);
					falg=true;
				}
				if (!falg) {
					return new ResponseMessage(false, "60028", String.format("异常信息：%s", "明细商品种类不同"));
				}
			}
			return new ResponseMessage(true, newReceivingNoteAsn);
		} catch (Exception e) {
			logger.error("ReceivingNoteAsnService:方法[mergeReceivingNoteAsn]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 根据合并批次号修改明细预告到货状态并修改收货单状态
	 * @param status
	 * @param MergeLotNo
	 * @return	ResponseMessage对象
	 * 				<pre>
	 * 					[Success:false,Status:10012,msg:参数为null]
	 * 					[Success:false,Status:10014,msg:影响行数为0]
	 * 					[Success:false,Status:10003,msg:异常信息]
	 * 					[Success:true,Data:影响行数]
	 * 				</pre>
	 */
	@Override
	public ResponseMessage updateIsArrivedByMergeLotNo(Integer status, List<String> mergeLotNos, String receivingNoteNo) {
		try {
			if (mergeLotNos == null || mergeLotNos.isEmpty()) {
				return new ResponseMessage(false, "10012");
			}
			for (String mergeLotNo : mergeLotNos) {
				//根据合并批次号修改明细预告到货状态
				int rows=receivingNoteAsnDao.updateStatusByMergeLotNo(status, mergeLotNo);
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}
			}
			//修改收货单状态
			int rows = receivingNoteDao.updateStatus(receivingNoteNo, 30);
			if (rows == 0) {
				return new ResponseMessage(false, "10014");
			}
			return new ResponseMessage(true, rows);
		} catch (Exception e) {
			logger.error("ReceivingNoteAsnService:方法[updateIsArrivedByMergeLotNo]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 根据入库单号查询合并后明细预告的预收总量。验收数量、合并批次号品名
	 * @param receivingNoteNo	入库单号
	 * @return	ResponseMessage对象
	 * 				<pre>
	 * 					[Success:false,Status:10012,msg:参数为null]60023
	 * 					[Success:false,Status:10003,msg:异常信息]
	 * 					[Success:false,Status:60023,msg:]
	 * 					[Success:true,Data:明细预告实体集合]
	 * 				</pre>
	 */
	@Override
	public ResponseMessage getReceivingNoteAsnByreceivingNoteNo(String receivingNoteNo,Integer status) {
		try {
			if (isBlank(receivingNoteNo)) {
				return new ResponseMessage(false, "10012");
			}
			//用于存储筛选后的数据
			List<ReceivingNoteAsn> receivingNoteAsns= new ArrayList<>();
			
			//循环根据入库单号、是否到货查询合并后明细预告的预收总量。验收数量、合并批次号、品名、料号。包装数
			for (ReceivingNoteAsn receivingNoteAsn : receivingNoteAsnDao.getReceivingNoteAsnByreceivingNoteNo(receivingNoteNo,status)) {
				//判断数据是否已上架
				if (receivingPackingListDao.getLocationByMergeLotNo(receivingNoteAsn.getMergeLotNo()).size()==0) {
					receivingNoteAsns.add(receivingNoteAsn);
				}
			}
			return new ResponseMessage(true, receivingNoteAsns);
		} catch (Exception e) {
			logger.error("ReceivingNoteAsnService:方法[getReceivingNoteAsnByreceivingNoteNo]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 根据合并批次号撤销包装分配
	 * @param mergerLotNo	合并批次号
	 * @return	ResponseMessage对象
	 * 				<pre>
	 * 					[Success:false,Status:10012,msg:参数为null]
	 * 					[Success:false,Status:10014,msg:影响行数为0，修改状态失败]
	 * 					[Success:false,Status:10003,msg:异常信息]
	 * 					[Success:true]
	 * 				</pre>
	 */
	@Override
	public ResponseMessage revokePacking(List<String> mergeLotNos) {
		try {
			if (mergeLotNos == null || mergeLotNos.isEmpty()) {
				return new ResponseMessage(false, "10012");
			}
			for (String mergeLotNo : mergeLotNos) {
				//根据合并批次号查询明细信息
				List<ReceivingNoteAsn> receivingNoteAsns = receivingNoteAsnDao.getReceivingNoteAsnByMergerLotNo(mergeLotNo);
				
				//遍历明细集合
				for (ReceivingNoteAsn receivingNoteAsn : receivingNoteAsns) {
					//判断明细是否到货
					if (receivingNoteAsn.getStatus()==20) {
						return new ResponseMessage(false, "60024", String.format("异常信息：%s", "有记录已到货"));
					}
					//判断是否未分配过
					if (receivingNoteAsn.getIsMerge() == null) {
						return new ResponseMessage(false, "60023", String.format("异常信息：%s", "有记录未分配"));
					}
					receivingNoteAsn.setAcceptanceQty(null);
					receivingNoteAsn.setMergeLotNo(null);
					receivingNoteAsn.setPackagingQty(null);
					receivingNoteAsn.setIsMerge(null);
					receivingNoteAsn.setGrossWeight(null);
					receivingNoteAsn.setNetWeight(null);
					receivingNoteAsn.setVolume(null);
					receivingNoteAsn.setStatus(0);
					int rows=receivingNoteAsnDao.save(receivingNoteAsn);
					if (rows == 0) {
						return new ResponseMessage(false, "10014");
					}
					
				}
				//根据合并批次号删除入库货物信息
				int rows=receivingPackingListDao.deleteReceivingPackingListByMergeLotNo(mergeLotNo);
			}
			
			return new ResponseMessage(true);
			
		} catch (Exception e) {
			logger.error("ReceivingNoteAsnService:方法[revokePacking]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 根据合并批次号撤销到货
	 * @param mergerLotNo	合并批次号
	 * @return	ResponseMessage对象
	 * 				<pre>
	 * 					[Success:false,Status:10012,msg:参数为null]
	 * 					[Success:false,Status:10014,msg:影响行数为0，修改失败]
	 * 					[Success:false,Status:60027,msg:已有记录上架]
	 * 					[Success:false,Status:10003,msg:异常信息]
	 * 					[Success:true]
	 * 				</pre>
	 */
	@Override
	public ResponseMessage revokeArrived(List<String> mergeLotNos) {
		try {
			if (mergeLotNos == null || mergeLotNos.isEmpty()) {
				return new ResponseMessage(false, "10012");
			}
			for (String mergeLotNo : mergeLotNos) {
				
				//修改入库货物信息
				for (ReceivingPackingList receivingPackingList : receivingPackingListDao.getReceivingPackingListByMergeLotNo(mergeLotNo)) {
					//判断是否已上架
					if (receivingPackingList.getStatus()>=10) {
						return new ResponseMessage(false, "60027", String.format("异常信息：%s", "已有记录上架"));
					}
					//将集装信息赋null值
					receivingPackingList.setPalletArea(null);
					receivingPackingList.setPalletGrossWeight(null);
					receivingPackingList.setPalletNetWeiht(null);
					receivingPackingList.setPalletNo(null);
					receivingPackingList.setPalletUnit(null);
					receivingPackingList.setPalletVolume(null);
					
					int rows=receivingPackingListDao.save(receivingPackingList);
					if (rows == 0) {
						return new ResponseMessage(false, "10014");
					}
				}
				//根据合并批次号查询明细信息
				List<ReceivingNoteAsn> receivingNoteAsns = receivingNoteAsnDao.getReceivingNoteAsnByMergerLotNo(mergeLotNo);
				
				//遍历明细集合
				for (ReceivingNoteAsn receivingNoteAsn : receivingNoteAsns) {
					receivingNoteAsn.setStatus(10);
					
					int rows=receivingNoteAsnDao.save(receivingNoteAsn);
					if (rows == 0) {
						return new ResponseMessage(false, "10014");
					}
				}
			}
			
			return new ResponseMessage(true);
			
		} catch (Exception e) {
			logger.error("ReceivingNoteAsnService:方法[revokeArrived]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	public void setReceivingNoteAsnDao(ReceivingNoteAsnDao receivingNoteAsnDao) {
		super.setBaseDao(receivingNoteAsnDao);
		this.receivingNoteAsnDao = receivingNoteAsnDao;
	}
	public void setReceivingPackingListDao(ReceivingPackingListDao receivingPackingListDao){
		this.receivingPackingListDao=receivingPackingListDao;
	}
	public void setReceivingNoteDao(ReceivingNoteDao receivingNoteDao){
		this.receivingNoteDao=receivingNoteDao;
	}
}
