package com.yorma.wms.service.api;

import java.util.Map;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.Warehouse;
import com.yorma.wms.service.base.api.BaseService;


/**
 * 仓库service
 * 
 * @Description:
 * @author su
 * @date 2017年8月10日
 * @version V1.0
 */
public interface WarehouseService extends BaseService<Warehouse>{

	/**
	 * 添加(修改)仓库
	 * @return
	 * 			返回ResponseMessage对象
	 *  	<ul>
	 *         <li>若在添加(修改)过程中出现了异常信息，ResponseMessage中的success为false、
	 *         status: 10003、msg：异常的说明信息</li>
	 *          <li>若在添加(修改)过程中出现了null，ResponseMessage中的success为false、
	 *         status: 10004</li>
	 *          <li>若在添加(修改)过程中出现了code重复，ResponseMessage中的success为false、
	 *         status: 60001</li>
	 *         <li>根据添加(修改)成功,ResponseMessage中的success:true,data:
	 *         {@code Object[]{id,仓库代码}}</li>
	 *       </ul>
	 */
	ResponseMessage saveWarehouse(Warehouse warehouse);
	
	/**
	 * 查询（条件）仓库
	 * @param map
	 *            	将查询条件放入map集合
	 * @return 	      
	 * 				返回ResponseMessage对象
	 *  	<ul>
	 *         <li>若在根据条件获取获取仓库实体集合的过程中出现了异常信息，ResponseMessage中的success为false、
	 *         status: 10003、msg：异常的说明信息</li>
	 *         <li>根据条件获取获取仓库实体集合成功,ResponseMessage中的success:true,data:
	 *         {@code List<WarehouseDto>}</li>
	 *       </ul>
	 */
	ResponseMessage getWarehouse(Map<String, Object> map);
	
	/**
	 * 根据仓库代码或者仓库名称、仓库类型查询数据
	 * @param code	仓库代码
	 * @param type	仓库类型
	 * @return
	 * 		返回ResponseMessage对象
	 *  	<ul>
	 *         <li>若在根据条件获取获取仓库实体集合的过程中出现了异常信息，ResponseMessage中的success为false、
	 *         status: 10003、msg：异常的说明信息</li>
	 *         <li>根据条件获取获取仓库实体集合成功,ResponseMessage中的success:true,data:
	 *         {@code List<WarehouseDto>}</li>
	 *       </ul>
	 */
	ResponseMessage getWarehouseByCode(String code,String type);
	

	/**
	 * ID查询仓库
	 * @param map
	 *            	将查询条件放入map集合
	 * @return 	      
	 * 				返回ResponseMessage对象
	 *  	<ul>
	 *         <li>若在根据角色Id获取用户实体集合的过程中出现了异常信息，ResponseMessage中的success为false、
	 *         status: 10003、msg：异常的说明信息</li>
	 *         <li>根据角色Id获取用户实体成功,ResponseMessage中的success:true,data:
	 *         {@code WarehouseDto}</li>
	 *       </ul>
	 */
	ResponseMessage getWarehouseById(Long id);
	
	/**
	 * 根据仓库代码查询仓库id
	 * @param code	仓库代码
	 * @return	ResponseMessage对象
	 * 				<ore>
	 * 					[Success:false,Status:10012,Msg:仓库代码为null或空字符串]
	 * 					[Success:false,Status:10003,Msg:异常信息]
	 * 					[Success:true,Data:有值返回：仓库ID，无值返回：null]
	 * 				</pre>
	 */
	ResponseMessage getWarehouseIdByCode(String code);
}
