package com.yorma.wms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yorma.common.entity.dto.ResponseData;
import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.dao.api.DeliveryNoteAsnDao;
import com.yorma.wms.dao.api.DeliveryPackingListDao;
import com.yorma.wms.dao.api.ReceivingNoteAsnDao;
import com.yorma.wms.dao.api.StockDao;
import com.yorma.wms.entity.dto.DeliveryNoteAsnDto;
import com.yorma.wms.entity.dto.DeliveryPackingListDto;
import com.yorma.wms.entity.dto.InAndOutOfStockDTO;
import com.yorma.wms.entity.dto.MapParameters;
import com.yorma.wms.entity.dto.ReceivingNoteAsnDTO;
import com.yorma.wms.service.api.ReportFormServicce;

public class ReportFormServicceImpl implements ReportFormServicce {

	private static final Logger logger = LoggerFactory.getLogger(ReportFormServicceImpl.class);

	private StockDao stockDao;

	private ReceivingNoteAsnDao receivingNoteAsnDao;

	private DeliveryNoteAsnDao deliveryNoteAsnDao;
	private DeliveryPackingListDao deliveryPackingListDao;

	public ReportFormServicceImpl() {
	}

	public ReportFormServicceImpl(StockDao stockDao, ReceivingNoteAsnDao receivingNoteAsnDao,
			DeliveryNoteAsnDao deliveryNoteAsnDao, DeliveryPackingListDao deliveryPackingListDao) {
		this.stockDao = stockDao;
		this.receivingNoteAsnDao = receivingNoteAsnDao;
		this.deliveryNoteAsnDao = deliveryNoteAsnDao;
		this.deliveryPackingListDao = deliveryPackingListDao;
	}

	/**
	 * 入库明细报表
	 * 
	 * @param parameters
	 *            参数实体
	 * @return ResponseMessage对象
	 * 
	 *         <pre>
	 * 				[Success:false,Status:10012,msg:参数为null]
	 * 				[Success:false,Status:10003,msg:异常信息]
	 * 				[Success:true,Data:ResponseData<>{当前页，总页数，总条数，入库明细集合}]
	 *         </pre>
	 */
	@Override
	public ResponseMessage listReceivingNoteAsn(MapParameters parameters) {
		try {
			int pageNumber = parameters.getPageNumber();
			int pageSize = parameters.getPageSize();
			Map<String, Object> parames = parameters.getParamters();
			List<ReceivingNoteAsnDTO> receivingNoteAsns = new ArrayList<>();
			int counts = 0;
			int page = 0;
			boolean choise = false;// 用于判断是否分页
			if (parameters.getIsEnable()) {
				counts = receivingNoteAsnDao.countReceivingNoteAsn(parames);
				if (counts == 0) {
					return new ResponseMessage(true, new ResponseData<>(pageNumber, 1, counts, receivingNoteAsns));
				}
				page = getTotal(counts, pageSize);
				choise = true;
			}
			if (!choise) {// 若为false,代表未分页接口
				if (pageNumber != 0 || pageSize != 0) {
					return new ResponseMessage(false, "10012",
							String.format("[pageNumber],[pageSize]:{},{}", pageNumber, pageSize));
				}
			}

			receivingNoteAsns = receivingNoteAsnDao.listReceivingNoteAsn(parames, pageNumber, pageSize);
			if (counts != 0 && page != 0) {
				return new ResponseMessage(true, new ResponseData<>(pageNumber, page, counts, receivingNoteAsns));
			}
			return new ResponseMessage(true, receivingNoteAsns);

		} catch (Exception e) {
			logger.error("Service:方法[listReceivingNoteAsn]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 出库明细报表
	 * 
	 * @param parameters
	 *            参数实体
	 * @return 返回ResponseMessage对象
	 * 
	 *         <pre>
	 * 			[Success: false status:10012] 参数为null
	 * 			[Success: false	status:10003] 异常信息
	 * 			[Success： true  data:ResponseData<>{当前页，总页数，总条数，明细实体集合}]
	 *         </pre>
	 */
	@Override
	public ResponseMessage listDeliveryNoteAsn(MapParameters parameters) {
		try {
			int pageNumber = parameters.getPageNumber();
			int pageSize = parameters.getPageSize();
			Map<String, Object> parames = parameters.getParamters();

			List<DeliveryNoteAsnDto> deliveryNoteAsns = new ArrayList<>();
			int counts = 0;
			int page = 0;
			boolean choise = false;// 用于判断是否分页
			if (parameters.getIsEnable()) {
				counts = deliveryNoteAsnDao.countDeliveryNoteAsn(parames);
				if (counts == 0) {
					return new ResponseMessage(true, new ResponseData<>(pageNumber, 1, counts, deliveryNoteAsns));
				}
				page = getTotal(counts, pageSize);
				choise = true;
			}
			if (!choise) {// 若为false,代表未分页接口
				if (pageNumber != 0 || pageSize != 0) {
					return new ResponseMessage(false, "10012",
							String.format("[pageNumber],[pageSize]:{},{}", pageNumber, pageSize));
				}
			}
			deliveryNoteAsns = deliveryNoteAsnDao.listDeliveryNoteAsn(parames, pageNumber, pageSize);

			if (counts != 0 && page != 0) {
				return new ResponseMessage(true, new ResponseData<>(pageNumber, page, counts, deliveryNoteAsns));
			}
			return new ResponseMessage(true, deliveryNoteAsns);
		} catch (Exception e) {
			logger.error("Service:方法[listDeliveryNoteAsn]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 出库报表
	 * 
	 * @param parameters
	 *            参数实体
	 * @return 返回ResponseMessage对象
	 * 
	 *         <pre>
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: false status:10012] 参数为空
	 * 				[success: true 	data:ResponseDate<>{当前页，总页数，总条数，拣货Dto实体集合}] 操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage listDeliveryPackingList(MapParameters parameters) {
		try {
			int pageNumber = parameters.getPageNumber();
			int pageSize = parameters.getPageSize();
			Map<String, Object> parames = parameters.getParamters();
			List<DeliveryPackingListDto> deliveryPackingLists = new ArrayList<>();
			int page = 0;// 总页数
			int counts = 0;// 总条数
			boolean choise = false;// 用于判断是否分页
			if (parameters.getIsEnable()) {
				counts = deliveryPackingListDao.countDeliveryPackingList(parames);
				if (counts == 0) {
					return new ResponseMessage(true, new ResponseData<>(pageNumber, 1, counts, deliveryPackingLists));
				}
				page = getTotal(counts, pageSize);
				choise = true;
			}
			if (!choise) {// 若为false,代表未分页接口
				if (pageNumber != 0 || pageSize != 0) {
					return new ResponseMessage(false, "10012",
							String.format("[pageNumber],[pageSize]:{},{}", pageNumber, pageSize));
				}
			}
			deliveryPackingLists = deliveryPackingListDao.listDeliveryPackingListDto(parames, pageNumber, pageSize);
			if (counts != 0 && page != 0) {// 如果总条数和总页数都不为0，则为分页
				return new ResponseMessage(true, new ResponseData<>(pageNumber, page, counts, deliveryPackingLists));
			}
			return new ResponseMessage(true, deliveryPackingLists);
		} catch (Exception e) {
			logger.error("Service:方法[listDeliveryPackingList]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 查询料号明细收发存
	 * 
	 * @param parameters
	 *            参数实体
	 * @return 返回ResponseMessage对象
	 * 
	 *         <pre>
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: false status:10012] 参数为空
	 * 				[success: true 	data:ResponseDate<>{当前页，总页数，总条数，料号明细收发存Dto实体集合}] 操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage listInAndOutOfStock(MapParameters parameters) {
		try {

			int pageNumber = parameters.getPageNumber();
			int pageSize = parameters.getPageSize();
			Map<String, Object> parames = parameters.getParamters();

			List<InAndOutOfStockDTO> inAndOutOfStockDTOs = new ArrayList<>();
			int page = 0;// 总页数
			int counts = 0;// 总条数
			boolean choise = false;// 用于判断是否分页
			if (parameters.getIsEnable()) {
				counts = stockDao.countInAndOutOfStock(parames);
				if (counts == 0) {
					return new ResponseMessage(true, new ResponseData<>(pageNumber, 1, counts, inAndOutOfStockDTOs));
				}
				page = getTotal(counts, pageSize);
				choise = true;
			}
			if (!choise) {// 若为false,代表未分页接口
				if (pageNumber != 0 || pageSize != 0) {
					return new ResponseMessage(false, "10012",
							String.format("[pageNumber]:{},[pageSize]:{}", pageNumber, pageSize));
				}
			}

			inAndOutOfStockDTOs = stockDao.listInAndOutOfStockDTO(parames, pageNumber, pageSize);
			if (counts != 0 && page != 0) {// 如果总条数和总页数都不为0，则为分页
				return new ResponseMessage(true, new ResponseData<>(pageNumber, page, counts, inAndOutOfStockDTOs));
			}
			return new ResponseMessage(true, inAndOutOfStockDTOs);
		} catch (Exception e) {
			logger.error("Service:方法[listInAndOutOfStock]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 计算总页数
	 * 
	 * @param counts
	 *            总条数
	 * @param pageSize
	 *            每页显示数
	 * @return 总页数
	 */
	private int getTotal(int counts, int pageSize) {
		return counts % pageSize == 0 ? counts / pageSize : counts / pageSize + 1;
	}

	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}

	public void setReceivingNoteAsnDao(ReceivingNoteAsnDao receivingNoteAsnDao) {
		this.receivingNoteAsnDao = receivingNoteAsnDao;
	}

	public void setDeliveryNoteAsnDao(DeliveryNoteAsnDao deliveryNoteAsnDao) {
		this.deliveryNoteAsnDao = deliveryNoteAsnDao;
	}

	public void setDeliveryPackingListDao(DeliveryPackingListDao deliveryPackingListDao) {
		this.deliveryPackingListDao = deliveryPackingListDao;
	}

}
