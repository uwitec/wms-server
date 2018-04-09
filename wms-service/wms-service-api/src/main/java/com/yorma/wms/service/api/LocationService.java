package com.yorma.wms.service.api;

import java.util.Map;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.Location;
import com.yorma.wms.service.base.api.BaseService;

/**
 * 储位、库位service
 * 
 * @Description:
 * @author su
 * @date 2017年8月9日
 * @version V1.0
 */
public interface LocationService extends BaseService<Location> {

	/**
	 * 添加(修改)储位
	 * 
	 * @param location
	 *            储位实体类
	 * @return	ResponseMessage对象
	 * 				<pre>
	 * 					[Success:false, Status:10004]参数为null
	 * 					[Success:false, Status:60001, Msg:储位代码重复]
	 * 					[Success:false, Status:10014]影响行数为0，保存失败
	 * 					[Success:false, Status:10003, Msg:异常信息]
	 * 					[Success:true, Data:储位id,储位代码]
	 * 				</pre>
	 */
	ResponseMessage saveLocation(Location location);

	/**
	 * ※暂时保留（不确定） 查询（条件）库位
	 * 
	 * @param map
	 *            将查询条件放入map集合
	 * @return 返回ResponseMessage对象
	 *         		<pre>
	 * 					[Success:false, Status:10003, Msg:异常信息]
	 * 					[Success:true, Data:List<储位实体>]
	 * 				</pre>
	 */
	ResponseMessage getLocation(Map<String, Object> map);

	/**
	 * 查询（条件）储位及仓库全称
	 * 
	 * @param map
	 *            将查询条件放入map集合
	 * @return 返回ResponseMessage对象
	 *         		<pre>
	 * 					[Success:false, Status:10003, Msg:异常信息]
	 * 					[Success:true, Data:List<储位Dto实体>]
	 * 				</pre>
	 */
	ResponseMessage getLocationAndWarehouse(Map<String, Object> map);
	
	/**
	 * 查询所有储位信息的仓库代码、库区代码、储位代码
	 * @return	ResponseMessage对象
	 * 				<pre>
	 * 					[Success：false,Status:10003,msg:异常信息]
	 * 					[Success:ture,Data:Map<String,Object>(key:仓库代码库区代码储位代码，value："1"(用于占位，没有特殊意义))]
	 * 				</pre>
	 */
	ResponseMessage getWarehouse();

}
