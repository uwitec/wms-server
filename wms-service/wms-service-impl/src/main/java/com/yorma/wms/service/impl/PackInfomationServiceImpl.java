package com.yorma.wms.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.dao.api.PackInfomationDao;
import com.yorma.wms.entity.PackInfomation;
import com.yorma.wms.entity.dto.PackInfomationDto;
import com.yorma.wms.service.api.PackInfomationService;
import com.yorma.wms.service.impl.base.BaseServiceImpl;

/**
 * 货物包装信息service实现
 * 
 * @Description:
 * @author su
 * @date 2017年9月01日
 * @version V1.0
 */
public class PackInfomationServiceImpl extends BaseServiceImpl<PackInfomation> implements PackInfomationService {

	private static final Logger logger=LoggerFactory.getLogger(PackInfomation.class);
	
	private PackInfomationDao packInfomationDao;
	
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
	@Override
	public ResponseMessage getPackInfomationByUUID(String goodsUuid) {
		try {
			if (isBlank(goodsUuid)) {
				return new ResponseMessage(false, "10012");
			}
			List<PackInfomationDto> packInfomationDtos=packInfomationDao.getPackInfomationDtoByUUID(goodsUuid);
			return new ResponseMessage(true, packInfomationDtos);
		} catch (Exception e) {
			logger.error("PackInfomationService:方法[getPackInfomationByUUID]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003",String.format("异常信息：%s", e));
		}
		
	}
	
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
	@Override
	public ResponseMessage getPackInfomationById(Long id) {
		try {
			if (null == id) {
				return new ResponseMessage(false, "10004");
			}
			PackInfomationDto packInfomationDto	= packInfomationDao.getPackInfomationByid(id);
			return new ResponseMessage(true, packInfomationDto);
		} catch (Exception e) {
			logger.error("PackInfomationService:方法[getPackInfomationById]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003",String.format("异常信息：%s", e));
		}
	}
	
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
	@Override
	public ResponseMessage getPackInfomationIsDefaultByGoodsUUID(String goodsUuid) {
		try {
			if (isBlank(goodsUuid)) {
				return new ResponseMessage(false, "10012");
			}
			PackInfomation packInfomation=packInfomationDao.getPackInfomationIsDefaultByGoodsUUID(goodsUuid);
			if (packInfomation !=null) {
				return new ResponseMessage(true, Boolean.valueOf(true));
			}
			return new ResponseMessage(true, Boolean.valueOf(false));
		} catch (Exception e) {
			logger.error("PackInfomationService:方法[getPackInfomationIsDefaultByGoodsUUID]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 根据商品UUID查询该商品的默认包装键
	 * 
	 * @param uuid:商品UUID
	 * @return ResponseMessage对象
	 * 
	 *         <pre>
	 * 		[success:false,status:10012]:参数为null
	 * 		[success:false,status:60005]:connection对象为null
	 * 		[success:false,status:10003,msg:异常说明信息]
	 * 		[success:true,data:true（有默认包装信息）/false（没有包装信息）]:操作成功
	 *         </pre>
	 */
	@SuppressWarnings("unused")
	@Override
	public ResponseMessage save(PackInfomation entity) {
		try {
			if (entity == null) {
				return new ResponseMessage(false, "10004");
			}
			if (entity.getIsDefault()==0) {
				return super.save(entity);
			}
			PackInfomation packInfomation=packInfomationDao.getPackInfomationIsDefaultByGoodsUUID(entity.getGoodsUuid());
			if (packInfomation != null) {
				packInfomation.setIsDefault(0);
				int rows=packInfomationDao.save(packInfomation);
			}
			return super.save(entity);
		} catch (Exception e) {
			logger.error("PackInfomationService:方法[save]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	public void setPackInfomationDao(PackInfomationDao packInfomationDao){
		super.setBaseDao(packInfomationDao);
		this.packInfomationDao=packInfomationDao;
	}
	
}
