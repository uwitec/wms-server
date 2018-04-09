package com.yorma.wms.consumer.zbus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.yorma.common.entity.dto.Parameter;
import com.yorma.common.entity.dto.ResponseData;
import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.Goods;
import com.yorma.wms.entity.dto.GoodsDto;
import com.yorma.wms.service.api.GoodsService;

import io.zbus.rpc.bootstrap.ClientBootstrap;

public class GoodsZbusCasesTest {

	static GoodsService api;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void before() throws Exception {
		ClientBootstrap clientBootstrap = new ClientBootstrap();

		clientBootstrap.serviceName("WMS-RPC-TEST");
		clientBootstrap.serviceAddress("172.31.1.176:15555");
		clientBootstrap.serviceToken("wms_rpc_test_service");
		api=clientBootstrap.createProxy(GoodsService.class);
	} 
	
	
	
	//添加 or 修改（根据有无id）
	@Ignore
	public void save(){
		Goods goods=new Goods();
		goods.setAttachments(null);
		goods.setBarcode("123456");
		goods.setCategory("饮料");
		goods.setChargingStandand("1L5");
		goods.setCreateBy("su");
		goods.setCreateDate(new Date());
		goods.setDefaultEmsNo("456456");
		goods.setDefaultItemNo("123123");
		goods.setDefaultUnit("789");
		goods.setDeliveryMode("快进快出");
		goods.setExpWarnDays(5);
		goods.setHeight(new BigDecimal(5.5));
		goods.setLength(new BigDecimal(10));
		goods.setPurchasePrice(new BigDecimal(1.5));
		goods.setSalePrice(new BigDecimal(2.5));
		goods.setStockHighest(new BigDecimal(100));
		goods.setStockLowest(new BigDecimal(20));
		goods.setWidth(new BigDecimal(2));
		goods.setInventoryChecking(false);
		goods.setIsEnable(true);
		goods.setMemo("百年品牌");
		goods.setModel("1W20瓶");
		goods.setName("可口可乐");
		goods.setOwnerCode("23");
		goods.setPn("159159");
		goods.setShelfLifeDays(15); 
		goods.setSku("753753");
		goods.setStatus("上架");
		goods.setStorageType("");
		goods.setUnpackingMode("轻拿轻放");
		goods.setWeight(new BigDecimal(15.0));
		
		ResponseMessage msg=api.save(goods);
//		Assert.assertTrue(msg.getSuccess());
//		Assert.assertNotNull(msg.getData());
		System.out.println("添加:"+msg.getSuccess());
		System.out.println("添加"+msg.getMsg());
		System.out.println("添加"+msg.getData());
		
	}
	
	
	@Ignore
	public void testadds() {
		List<Goods> lig=new ArrayList<>();
		Goods goods=new Goods();
		goods.setId(89L);
		goods.setAttachments(null);
		goods.setBarcode("123456");
		goods.setCategory("饮料");
		goods.setChargingStandand("1L5");
		goods.setCreateBy("su");
		goods.setCreateDate(new Date());
		goods.setDefaultEmsNo("456456");
		goods.setDefaultItemNo("123123");
		goods.setDefaultUnit("789");
		goods.setDeliveryMode("快进快出");
		goods.setExpWarnDays(5);
		goods.setHeight(new BigDecimal(5.5));
		goods.setLength(new BigDecimal(10));
		goods.setPurchasePrice(new BigDecimal(1.5));
		goods.setSalePrice(new BigDecimal(2.5));
		goods.setStockHighest(new BigDecimal(100));
		goods.setStockLowest(new BigDecimal(20));
		goods.setWidth(new BigDecimal(2));
		goods.setInventoryChecking(false);
		goods.setIsEnable(true);
		goods.setMemo("百年品牌");
		goods.setModel("1W20瓶");
		goods.setName("尖叫");
		goods.setOwnerCode("23");
		goods.setPn("159159");
		goods.setShelfLifeDays(15); 
		goods.setSku("753753");
		goods.setStatus("上架");
		goods.setStorageType("");
		goods.setUnpackingMode("轻拿轻放");
		goods.setWeight(new BigDecimal(15.0));
		Goods good=new Goods();
		
		good.setAttachments(null);
		good.setBarcode("123456");
		good.setCategory("饮料");
		good.setChargingStandand("1L5");
		good.setCreateBy("su");
		good.setCreateDate(new Date());
		good.setDefaultEmsNo("456456");
		good.setDefaultItemNo("123123");
		good.setDefaultUnit("789");
		good.setDeliveryMode("快进快出");
		good.setExpWarnDays(5);
		good.setHeight(new BigDecimal(5.5));
		good.setLength(new BigDecimal(10));
		good.setPurchasePrice(new BigDecimal(1.5));
		good.setSalePrice(new BigDecimal(2.5));
		good.setStockHighest(new BigDecimal(100));
		good.setStockLowest(new BigDecimal(20));
		good.setWidth(new BigDecimal(2));
		good.setInventoryChecking(false);
		good.setIsEnable(true);
		good.setMemo("百年品牌");
		good.setModel("1W20瓶");
		good.setName("百事可乐");
		good.setOwnerCode("23");
		good.setPn("159159");
		good.setShelfLifeDays(15); 
		good.setSku("753753");
		good.setStatus("上架");
		good.setStorageType("");
		good.setUnpackingMode("轻拿轻放");
		good.setWeight(new BigDecimal(15.0));
		Goods gd=new Goods();
		
		gd.setAttachments(null);
		gd.setBarcode("123456");
		gd.setCategory("");
		gd.setChargingStandand("1L5");
		gd.setCreateBy("su");
		gd.setCreateDate(new Date());
		gd.setDefaultEmsNo("456456");
		gd.setDefaultItemNo("123123");
		gd.setDefaultUnit("789");
		gd.setDeliveryMode("快进快出");
		gd.setExpWarnDays(5);
		gd.setInventoryChecking(false);
		gd.setIsEnable(true);
		gd.setMemo("百年品牌");
		gd.setModel("1W20瓶");
		gd.setName("美年达");
		gd.setOwnerCode("23");
		gd.setPn("159159");
		gd.setShelfLifeDays(15); 
		gd.setSku("753753");
		gd.setStatus("上架");
		gd.setStorageType("");
		gd.setUnpackingMode(null);
		gd.setWeight(new BigDecimal(15.0));
		lig.add(gd);
		lig.add(good);
		lig.add(goods);
		
		ResponseMessage msg=api.createBatch(lig);
//		Assert.assertTrue(msg.getSuccess());
//		Assert.assertNotNull(msg.getData());
		System.out.println("添加:"+msg.getSuccess());
		System.out.println("添加"+msg.getMsg());
		System.out.println("添加"+msg.getData());
		
	}

	//查询
	@Test
	public void testGet(){
		Map<String, Object> map=new HashMap<>();
//		map.put("is_enable", null);
//		map.put("BARCODE", null);
//		map.put("name", "");
//		map.put("OWNER_CODE=", "23");
//		map.put("STATUS", "");
//		map.put("DELIVERY_MODE", null);
//		map.put("UNPACKING_MODE", null);
//		map.put("DEFAULT_EMS_NO", "");
		ResponseMessage	msg=api.getGoods(map,1,10);
	
//		Assert.assertTrue(msg.getSuccess());
//		Assert.assertNotNull(msg.getData());
		System.out.println("Success:"+msg.getSuccess());
		System.out.println("Status:"+msg.getStatus());
		
		
		ResponseData<GoodsDto> responseData = (ResponseData<GoodsDto>)msg.getData();
		System.out.println("Data:"+responseData.getList().size());
		
		for (GoodsDto goodsDto : responseData.getList()) {
			System.out.println("在库量："+goodsDto.getSumInStock());
		}
	}
	
	@Ignore
	public void update(){
		UUID uuid=UUID.randomUUID();
		
		Goods goods=new Goods();
		goods.setUuid(uuid.toString());
		goods.setAttachments(null);
		goods.setBarcode("123456");
		goods.setCategory("饮料");
		goods.setChargingStandand("1L5");
		goods.setCreateBy("su");
		goods.setCreateDate(new Date());
		goods.setDefaultEmsNo("456456");
		goods.setDefaultItemNo("123123");
		goods.setDefaultUnit("789");
		goods.setDeliveryMode("快进快出");
		goods.setExpWarnDays(5);
		goods.setHeight(new BigDecimal(5.5));
		goods.setLength(new BigDecimal(10));
		goods.setPurchasePrice(new BigDecimal(1.5));
		goods.setSalePrice(new BigDecimal(2.5));
		goods.setStockHighest(new BigDecimal(100));
		goods.setStockLowest(new BigDecimal(20));
		goods.setWidth(new BigDecimal(2));
		goods.setInventoryChecking(false);
		goods.setIsEnable(true);
		goods.setLastUpdateBy("su");
		
		goods.setStorageType("");
		goods.setUnpackingMode("轻拿轻放");
		goods.setWeight(new BigDecimal(15.5));
		goods.setId(18L);
		
		ResponseMessage msg=api.save(goods);
//		Assert.assertTrue(msg.getSuccess());
//		Assert.assertNotNull(msg.getData());
		System.out.println("修改:"+msg.getSuccess());
		System.out.println("修改:"+msg.getMsg()+"Data:"+msg.getData());
		
		
	}

	//根据id删除
	@Ignore
	public void testDeleteGoods() {
		long id =28;
//		Scanner sc=new Scanner(System.in);
//		System.out.println("输入删除id：");
//		id=sc.nextLong();

		ResponseMessage msg=api.removeById(id);
		
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());
		System.out.println("删除"+msg.getSuccess()+"影响行数："+msg.getData());
		
	}
	
	@Ignore
	public void getGoodsByUuid(){
		ResponseMessage msg=api.getGoodsByUUID("123");
		System.out.println("ByUuid:"+msg.getSuccess());
		System.out.println("ByUuid:"+msg.getData());
		System.out.println("ByUuid:"+msg.getStatus());
	}
	
	@SuppressWarnings("unchecked")
	@Ignore
	public void testget(){
		Integer pageNumber=0;
		Integer pageSize=10;
		List<Parameter> parameters=new ArrayList<>();
		Parameter parameter=new Parameter();
		parameter.setField("Goods.name");
		parameter.setValue("可乐");
		parameters.add(parameter);
		
		ResponseMessage responseMessage=api.searchParameter(parameters,pageNumber,pageSize);
		
		List<Goods> lig=(List<Goods>)responseMessage.getData();
		System.out.println("length:"+lig.size());
		for (Goods g : lig) {
			System.out.println(g.getId());
			
		}
	}

	
}
