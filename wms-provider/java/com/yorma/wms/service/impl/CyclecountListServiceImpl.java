package com.yorma.wms.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.dao.api.CyclecountListDao;
import com.yorma.wms.entity.CyclecountList;
import com.yorma.wms.service.api.CyclecountListService;
import com.yorma.wms.service.impl.base.BaseServiceImpl;

/**
 * 盘点明细Service 实现类
 * @author Su
 * 2017年12月13日
 */
public class CyclecountListServiceImpl extends BaseServiceImpl<CyclecountList> implements CyclecountListService {

	private static final Logger logger = LoggerFactory.getLogger(CyclecountList.class);
	
	private CyclecountListDao cyclecountListDao;
	
	@Override
	public ResponseMessage save(CyclecountList entity) {
		if (entity == null) {
			return new ResponseMessage(false, "10004");
		}
		return super.save(entity);
	}
	
	/**
	 * 根据条件查询盘点明细信息
	 * @param parames	参数即和
	 * @return	ResponseMessage对象
	 * 				[Success: false	, Status:10003] 异常信息
	 * 				[success: true	, Data: List<盘点明细实体>] 操作成功
	 */
	 @Override
	public ResponseMessage getCyclecountList(Map<String, Object> parames) {
		try {
			return new ResponseMessage(true, cyclecountListDao.getCyclecountList(parames));
		} catch (Exception e) {
			logger.error("CyclecountListService:方法[getCyclecountList]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 根据盘点单号查询盘点单明细
	 * @param cyclecountNo	盘点单号
	 * @return	ResponseMessage对象
	 * 			<pre>
	 * 				[Success: false	, Status:10003] 异常信息
	 * 				[Success: false	, Status:10012] 盘点单号为null或空字符串
	 * 				[success: true	, Data: List<盘点明细实体>] 操作成功
	 * 			</pre>
	 */ 
	@Override
	public ResponseMessage getCyclecountListByCyclecountNo(String cyclecountNo) {
		try {
			if (isBlank(cyclecountNo)) {
				return new ResponseMessage(false, "10012");
			}
			return new ResponseMessage(true, cyclecountListDao.getCyclecountListByCyclecountNo(cyclecountNo));
		} catch (Exception e) {
			logger.error("CyclecountListService:方法[getCyclecountListByCyclecountNo]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	} 
	 
	public void setCyclecountDao(CyclecountListDao cyclecountListDao){
		this.setBaseDao(cyclecountListDao);
		this.cyclecountListDao = cyclecountListDao;
	}
	
}
