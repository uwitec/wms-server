package com.yorma.wms.service.api;

import java.util.Map;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.Storage;
import com.yorma.wms.service.base.api.BaseService;


/**
 * 库区service
 * 
 * @Description:
 * @author su
 * @date 2017年8月10日
 * @version V1.0
 */
public interface StorageService extends BaseService<Storage> {

	/**
	 * 添加(修改)库区
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
	 *         {@code Object[]{id, 库区代码 }}</li>
	 *       </ul>
	 */
	ResponseMessage saveStorage(Storage storage);
	
	/**
	 * 查询（条件）库区
	 * @param map
	 *            	将查询条件放入map集合
	 * @return 	      
	 * 				返回ResponseMessage对象
	 *  	<ul>
	 *         <li>若在根据条件获取获取库区实体集合的过程中出现了异常信息，ResponseMessage中的success为false、
	 *         status: 10003、msg：异常的说明信息</li>
	 *         <li>根据条件获取获取库区实体集合成功,ResponseMessage中的success:true,data:
	 *         {@code List<Storage>}</li>
	 *       </ul>
	 */
	ResponseMessage getStorage(Map<String, Object> map);
	
	
	
}
