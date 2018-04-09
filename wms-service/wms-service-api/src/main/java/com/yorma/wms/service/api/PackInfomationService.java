package com.yorma.wms.service.api;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.PackInfomation;
import com.yorma.wms.service.base.api.BaseService;


/**
 * 货物包装信息service
 * 
 * @Description:
 * @author su
 * @date 2017年9月01日
 * @version V1.0
 */
public interface PackInfomationService extends BaseService<PackInfomation> {

	/**
	 * 根据商品UUID查询包装信息
	 * @param goodsUuid
	 *            	商品uuid
	 * @return 	返回ResponseMessage对象
	 *        <pre>
	 * 				[success: false status:10012] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true 	data:List<包装信息Dto>] 操作成功
	 *         </pre>
	 */
	ResponseMessage getPackInfomationByUUID(String goodsUuid);
	
	/**
	 * 根据id查询包装信息
	 * @param id
	 *            	包装信息id
	 * @return 	返回ResponseMessage对象
	 *        <pre>
	 * 				[success: false status:10004] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true 	data:包装信息Dto] 操作成功
	 *         </pre>
	 */
	ResponseMessage getPackInfomationById(Long id);
	
	/**
	 * 根据商品UUID查询该商品的默认包装键
	 * 
	 * @param uuid:商品UUID
	 * @return ResponseMessage对象
	 * 
	 *         <pre>
	 * 		[success:false,status:10012]:参数为null
	 * 		[success:false,status:10003,msg:异常说明信息]
	 * 		[success:true,data:true（有默认包装信息）/false（没有包装信息）]:操作成功
	 *         </pre>
	 */
	ResponseMessage getPackInfomationIsDefaultByGoodsUUID(String goodsUuid);
	
}
