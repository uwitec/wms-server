package com.yorma.wms.service.api;

import java.util.Map;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.CyclecountList;
import com.yorma.wms.service.base.api.BaseService;

/**
 * 盘点明细Service 接口类
 * @author Su
 * 2017年12月13日
 */
public interface CyclecountListService extends BaseService<CyclecountList> {

	/**
	 * 根据条件查询盘点明细信息
	 * @param parames	参数即和
	 * @return	ResponseMessage对象
	 * 			 <pre>
	 * 				[Success: false	, Status:10003] 异常信息
	 * 				[success: true	, Data: List<盘点明细实体>] 操作成功
	 * 			</pre>
	 */
	ResponseMessage getCyclecountList(Map<String, Object> parames);
	
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
	ResponseMessage getCyclecountListByCyclecountNo(String cyclecountNo);
	
}
