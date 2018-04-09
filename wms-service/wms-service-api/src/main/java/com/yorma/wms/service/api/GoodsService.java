package com.yorma.wms.service.api;

import java.util.List;
import java.util.Map;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.Goods;
import com.yorma.wms.entity.PackInfomation;
import com.yorma.wms.service.base.api.BaseService;

/**
 * 商品基础资料service
 * 
 * @Description:
 * @author su
 * @date 2017年8月7日
 * @version V1.0
 */
public interface GoodsService extends BaseService<Goods> {

	/**
	 * 查询（条件）商品基础资料
	 * 
	 * @param map
	 *            将查询条件放入map集合
	 * @return 返回ResponseMessage对象
	 *        <pre>
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true 	data:List<商品信息实体Dto>] 操作成功
	 *         </pre>
	 */
	ResponseMessage getGoods(Map<String, Object> map);
	
	/**
	 * 查询（条件）商品基础资料(分页)
	 * @param map	将查询条件放入map集合
	 * @param pageNumber	当前页
	 * @param pageSize		每页显示数
	 * @return	返回ResponseMessage对象
	 *        <pre>
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: false status:10012] 当前页或每页显示数为null！
	 * 				[success: true data:new ResponseData<>(当前页, 总页数(1页), 总条数, null)] 无数据(总条数为0)
	 * 				[success: true 	data:new ResponseData<>(当前页, 总页数, 总条数, List<商品信息实体Dto>)] 操作成功
	 *         </pre>
	 */
	ResponseMessage getGoods(Map<String, Object> map,Integer pageNumber,Integer pageSize);
	
	/**
	 * 根据id查询商品基础资料
	 * 
	 * @param id
	 *            商品ID
	 * @return 返回ResponseMessage对象
	 *        <pre>
	 * 				[success: false status:10012] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true 	data:商品信息实体Dto] 操作成功
	 *         </pre>
	 */
	ResponseMessage getGoodsById(Long id);

	/**
	 * 根据uuid查询商品基础资料
	 * 
	 * @param uuid
	 *            商品UUID
	 * @return 返回ResponseMessage对象
	 *        <pre>
	 * 				[success: false status:10012] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true 	data:商品信息实体Dto] 操作成功
	 *         </pre>
	 */
	ResponseMessage getGoodsByUUID(String uuid);

	/**
	 * 批量添加（更新）商品
	 * 
	 * @param goodss
	 *            商品集合
	 * @return 返回ResponseMessage对象
	 *        <pre>
	 * 				[success: false status:10004] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: false status:60015] 料号重复
	 * 				[success: false status:10014] 影响行数为0，保存失败
	 * 				[success: true 	data:true] 操作成功
	 *         </pre>
	 */
	ResponseMessage createBatchByGoods(List<Goods> goodss);
	
	/**
	 * 保存商品信息及包装键
	 * @param goodss
	 * @param packInfomations
	 * @return	ResponseMessage对象
	 * 				<pre>
	 * 					[Success:false, Status:10004]参数为null
	 * 					[Success:false, Status:60015, Msg:商品料号重复]
	 * 					[Success:false, Status:10014]影响行数为0，保存失败
	 * 					[Success:false, Status:10003, Msg:异常信息]
	 * 					[Success:true, Data:商品UUID]
	 * 				</pre>
	 */
	ResponseMessage saveGoodsAndPackInfomation(Goods goods, List<PackInfomation> packInfomations);
	
	/**
	 * 根据商品UUID查询商品信息和包装键集合
	 * @param goodsUuid	商品UUID
	 * @return	ResponseMessage 对象
	 * 				<pre>
	 * 					[Success:false, Status:10012]参数为null
	 * 					[Success:false, Status:10003, Msg:异常信息]
	 * 					[Success:true, Data:Map<String, Object> (key:goods, value:goods,key:PackInfomations,value:List<PackInfomation>)]
	 * 				</pre>
	 */
	public ResponseMessage getGoodsAndPackInfomationByGoodsUUID(String goodsUuid);

	/**
	 * 查询所有商品UUID
	 * @return	ResponseMessage 对象
	 * 				<pre>
	 * 					[Success:false, Status:10003, Msg:异常信息]
	 * 					[Success:true, Data:Map<String, Object> (key:goodsUUID, value:"1"作为占位，没用)]
	 * 				</pre>
	 */
	ResponseMessage getGoodsUUID();
	
	/**
	 * 批量添加商品及包装信息
	 * @param goodsList		商品实体集合
	 * @param packInfomations	包装信息实体集合
	 * @return	ResponseMessage对象
	 * 				<pre>
	 * 					[Success:false, Status:10004]参数为null
	 * 					[Success:false, Status:60015, Msg:商品料号重复]
	 * 					[Success:false, Status:10014]影响行数为0，保存失败
	 * 					[Success:false, Status:10003, Msg:异常信息]
	 * 					[Success:true]
	 * 				</pre>
	 */
	ResponseMessage createBatchByGoodsAndPackInfomation(List<Goods> goodsList,List<PackInfomation> packInfomations);
	
	/**
	 * 查询（条件）商品基础资料及默认包装键
	 * 
	 * @param parames
	 *            将查询条件放入map集合
	 * @return 返回ResponseMessage对象
	 *        <pre>
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true 	data:List<商品信息实体Dto>] 操作成功
	 *         </pre>
	 */
	ResponseMessage getGoodsAndPackInfomation(Map<String, Object> parames);
	
}
