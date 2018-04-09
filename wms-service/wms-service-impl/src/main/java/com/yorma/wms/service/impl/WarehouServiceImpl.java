package com.yorma.wms.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.dao.api.StorageDao;
import com.yorma.wms.dao.api.WarehouseDao;
import com.yorma.wms.entity.Warehouse;
import com.yorma.wms.entity.dto.WarehouseDto;
import com.yorma.wms.service.api.WarehouseService;
import com.yorma.wms.service.impl.base.BaseServiceImpl;
import com.yorma.wms.service.impl.util.CodeUtil;

/**
 * 仓库service实现
 * 
 * @Description:
 * @author su
 * @date 2017年8月10日
 * @version V1.0
 */
public class WarehouServiceImpl extends BaseServiceImpl<Warehouse>implements WarehouseService {

	private static final Logger logger = LoggerFactory.getLogger(Warehouse.class);

	private WarehouseDao warehouseDao;
	private StorageDao storageDao;

	/**
	 * 仓库添加(修改)
	 */
	public ResponseMessage saveWarehouse(Warehouse warehouse) {
		if (warehouse == null) {
			return new ResponseMessage(false, "10004");
		}
		try {
			// 传入的仓库代码为空，系统设置默认值
			if (isBlank(warehouse.getCode())) {
				// 系统给CODE默认值
				Long id = warehouseDao.getMaxId();
				if (id == null) {
					id = 0L;
				}
				// 将代码首字母，位数，最大id传入getCode方法生成默认仓库代码
				String code = CodeUtil.getCode("W", 5, id);
				warehouse.setCode(code);
			} else {
				// 确保数据库中没有代码号相同的数据
				Warehouse warehouse2 = warehouseDao.checkCode(warehouse.getCode());
				if (warehouse.getId() == null) {
					if (warehouse2 != null) {
						return new ResponseMessage(false, "60001");
					}
				}
			}
			int rows = warehouseDao.saveWarehouse(warehouse);
			if (rows == 0) {
				return new ResponseMessage(false, "10014");
			}

			Field field = warehouse.getClass().getDeclaredField("id");
			field.setAccessible(true);// 设置些属性是可以访问的
			Object id = field.get(warehouse); // 得到此属性的值
			Object[] ob = new Object[] { id, warehouse.getCode() };
			return new ResponseMessage(true, ob);
		} catch (Exception e) {
			logger.error("WarehouService:方法[saveWarehouse]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 查询仓库
	 * 
	 * @param map
	 *            查询条件
	 */

	public ResponseMessage getWarehouse(Map<String, Object> map) {
		ResponseMessage msg = new ResponseMessage();
		try {

			List<WarehouseDto> liw = warehouseDao.getWarehouse(map);
			msg.setData(liw);
		} catch (Exception e) {
			logger.error("WarehouService:方法[getWarehouse]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息： %s", e));

		}
		return msg;
	}

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
	@Override
	public ResponseMessage getWarehouseByCode(String code,String type) {
		try {

			List<WarehouseDto> warehouseList = warehouseDao.getWarehouseByCode(code,type);
			return new ResponseMessage(true, warehouseList);
		} catch (Exception e) {
			logger.error("WarehouService:方法[getWarehouseByCode]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * ID查询仓库
	 * 
	 */
	public ResponseMessage getWarehouseById(Long id) {
		try {
			// 1.非空验证
			if (id == null) {
				return new ResponseMessage(false, "10004");
			}
			return new ResponseMessage(true, warehouseDao.getWarehouseById(id));
		} catch (Exception e) {
			logger.error("WarehouService:方法[getWarehouseById]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息:%s", e));
		}
	}
	
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
	@Override
	public ResponseMessage getWarehouseIdByCode(String code) {
		try {
			if (isBlank(code)) {
				return new ResponseMessage(false, "10012", String.format("异常信息：%s", "仓库代码为空"));
			}
			return new ResponseMessage(true, warehouseDao.getWarehouseIdByCode(code));
		} catch (Exception e) {
			logger.error("WarehouService:方法[getWarehouseIdBycode]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息:%s", e));
		}
	}

	
	@Override
	public ResponseMessage removeById(Long id) {
		try {
			if (id == null) {
				return new ResponseMessage(false, "10004");
			}
			String code = warehouseDao.getWarehouseCodeById(id);
			int storageCount = storageDao.getStorageCountsByWarehouseCode(code);
			if (storageCount>0) {
				return new ResponseMessage(false, "60042", String.format("异常信息：%s", "请先删除库区信息！"));
			}
			return new ResponseMessage(true, warehouseDao.removeById(id));
		} catch (Exception e) {
			logger.error("WarehouService:方法[getWarehouseIdBycode]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息:%s", e));
		}
	}
	
	public void setWarehouseDao(WarehouseDao warehouseDao) {
		super.setBaseDao(warehouseDao);
		this.warehouseDao = warehouseDao;
	}

	public void setStorageDao(StorageDao storageDao) {
		this.storageDao = storageDao;
	}

	
}
