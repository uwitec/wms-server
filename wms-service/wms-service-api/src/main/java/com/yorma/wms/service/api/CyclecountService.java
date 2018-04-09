package com.yorma.wms.service.api;

import java.util.List;
import java.util.Map;

import com.yorma.common.entity.dto.ResponseData;
import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.Cyclecount;
import com.yorma.wms.entity.CyclecountList;
import com.yorma.wms.service.base.api.BaseService;

/**
 * 盘点Service接口
 * @author subiao
 * 2017年12月6日
 */
public interface CyclecountService extends BaseService<Cyclecount> {

	
	/**
	 * 根据条件查询盘点信息
	 * @param parames	参数集合
	 * @return	ResponseMessage对象
	 * 			<pre>
	 * 				[Success：false , Status:10003 ] 异常信息
	 * 				[Success：true , Data: List<盘点实体>] 操作成功
	 * 			</pre>
	 */
	ResponseMessage getCyclecount(Map<String, Object> parames);
	
	/**
	 * 根据条件查询盘点信息（分页）
	 * @param parames	参数集合
	 * @param pageSize	每页显示数
	 * @param pageNumber	当前页数
	 * @return ResponseMessage对象
	 * 			<pre>
	 * 				[Success：false , Status:10003 ] 异常信息
	 * 				[Success：true , Data: ResponseData<>(当前页数, 总页数, 总行数, List<盘点实体>) ] 操作成功
	 * 			</pre>
	 */
	ResponseMessage getCyclecount(Map<String, Object> parames,Integer pageSize, Integer pageNumber);
	
	/**
	 * 保存盘点单及明细
	 * @param cyclecount	盘点实体
	 * @param cyclecountLists	盘点明细实体集合
	 * @return	ResponseMessage对象
	 * 			<pre>
	 * 				[Success：false , Status:10003 ] 异常信息
	 * 				[Success：false , Status:10004 ] 盘点表头为null
	 * 				[Success：false , Status:60002 ] 盘点单号重复
	 * 				[Success：false , Status:10014 ] 保存失败
	 * 				[Success：true , Data: 盘点表头ID] 操作成功
	 * 			</pre>
	 */
	ResponseMessage saveCyclecount(Cyclecount cyclecount,List<CyclecountList> cyclecountLists);
	
	/**
	 * 根据条件盘点库存
	 * @param parames
	 * @return  ResponseMessage对象
	 * 			<pre>
	 * 				[Success：false , Status:10003 ] 异常信息
	 * 				[Success：true , Data: List<盘点明细>] 操作成功
	 * 			</pre>
	 */
	ResponseMessage getStockCyclecount(Map<String, Object> parames);
	
	/**
	 * 根据条件盘点库存
	 * @param parames	参数条件
	 * @param mergeCondition	选择合并条件（按主单位合(mu), 按包装键合(packingKey),默认为按主单位合）
	 * @return	  ResponseMessage对象
	 * 			<pre>
	 * 				[Success：false , Status:10003 ] 异常信息
	 * 				[Success：true , Data: List<盘点明细>] 操作成功
	 * 			</pre>
	 */
	ResponseMessage listStockCyclecount(Map<String, Object> parames, String mergeCondition);
	
	/**
	 * 根据盘点单号调整库存
	 * @param cyclecountNo	盘点单号
	 * @return	ResponseMessage对象
	 * 			<pre>
	 * 				[Success：false , Status:10003 ] 异常信息
	 * 				[Success：false , Status:10012 ] 盘点单号或创建人为null
	 * 				[Success：false , Status:60040 ] 该记录已调整
	 * 				[Success：false , Status:60041 ] 盘点明细无数据
	 * 				[Success：false , Status:10014 ] 调整失败
	 * 				[Success：true ] 操作成功
	 * 			</pre>
	 */
	ResponseMessage adjustmentStock(String cyclecountNo,String createBy);
	
	/**
	 * 根据盘点单号查询盘点单表头信息
	 * @param cyclecountNo	盘点单号
	 * @return	ResponseMessage对象
	 * 			 <pre>
	 * 				[Success：false , Status:10003 ] 异常信息
	 * 				[Success：false , Status:10012 ] 盘点单号为null或空字符串
	 * 				[Success：true , Data: 盘点信息实体] 操作成功
	 * 			 </pre>
	 */
	ResponseMessage getCyclecountBycyclecountNo(String cyclecountNo);
	
	/**
	 * 根据盘点单号删除盘点单表头信息
	 * @param cyclecountNo
	 * @return	ResponseMessage对象
	 * 			 <pre>
	 * 				[Success：false , Status:10003 ] 异常信息
	 * 				[Success：false , Status:10012 ] 盘点单号为null或空字符串
	 * 				[Success：true ] 操作成功
	 * 			 </pre>
	 */
	ResponseMessage removeCyclecountBycyclecountNo(String cyclecountNo);
	
}
