package com.yorma.wms.consumer.zbus;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.Warehouse;
import com.yorma.wms.entity.dto.WarehouseDto;
import com.yorma.wms.service.api.WarehouseService;

import io.zbus.rpc.bootstrap.ClientBootstrap;

public class WarehouseZbusCasesTest {

	static WarehouseService api;

	@SuppressWarnings("resource")
	@BeforeClass
	public static void before() throws Exception {
		ClientBootstrap clientBootstrap = new ClientBootstrap();

		clientBootstrap.serviceName("WMS-RPC-TEST");
		clientBootstrap.serviceAddress("172.31.1.176:15555");
		clientBootstrap.serviceToken("wms_rpc_test_service");
		api = clientBootstrap.createProxy(WarehouseService.class);
		
	}

	@Ignore
	public void add() {
		Warehouse wa = new Warehouse();
		wa.setAddress("朝阳区");
		wa.setCode("T0002");
		wa.setContacts("ss");
		wa.setCreateBy("ss");
		wa.setCreateDate(new Date());
		wa.setDescription("测试");
		wa.setFax("4001-0023-715");
		wa.setFullName("精简仓库");
		wa.setIsEnable(false);
		wa.setMemo("无人租用");
		wa.setName("旧仓库");
		wa.setOwnerCode("23");
		wa.setPhones("15068615674");
		wa.setType("QNBS");

		ResponseMessage msg = api.saveWarehouse(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());
		msg = api.saveWarehouse(wa);
//		Assert.assertFalse(msg.getSuccess());
//		Assert.assertEquals("60001", msg.getStatus());
		
//		System.out.println("success:"+msg.getSuccess());
//		System.out.println("getStatus"+msg.getStatus());
//		System.out.println("getData:"+msg.getData());
//		System.out.println("getMsg:"+msg.getMsg());
		
		 Assert.assertTrue(msg.getSuccess());
		 Assert.assertNotNull(msg.getData());

	}

	@Ignore
	public void get() {

//		Map<String, Object> map = new HashMap<String, Object>();
//		// 异常验证
//		map.put("code=", "E");
//		ResponseMessage msg = api.getWarehouse(map);
//		Assert.assertFalse(msg.getSuccess());
//		Assert.assertEquals("10003", msg.getStatus());
		// 正常返回值
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("code=", "a");
		map1.put("OWNER=", null);
		map1.put("IS_ENABLE=", false);
//		map1.put("TYPE", "QNBS");
//		map1.put("NAME", "");
		ResponseMessage	msg = api.getWarehouse(map1);
//		Assert.assertTrue(msg.getSuccess());
//		Assert.assertNotNull(msg.getData());
		System.out.println("getSuccess:"+msg.getSuccess());
		System.out.println("getData:"+msg.getData());
		System.out.println("getStatus:"+msg.getStatus());

	}

	@Ignore
	public void Byid() {
		// 空值验证
		 ResponseMessage msg = api.find(null);
		 Assert.assertFalse(msg.getSuccess());
		 Assert.assertEquals("10004", msg.getStatus());

		msg = api.getWarehouseById(37L);
//		System.out.println(msg.getData());
//		System.out.println(msg.getStatus());
//		System.out.println(msg.getSuccess());
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());

	}

	@Ignore
	public void update() {
		
		Warehouse wa = new Warehouse();
		wa.setId(37L);
		wa.setLastUpdateBy("s");
		wa.setLastUpdateDate(new Date());
		wa.setName("极致精简仓库");
		wa.setAddress("朝阳区");
		wa.setCode("W0009");
		wa.setContacts("ss");
		wa.setCreateBy("ss");
		wa.setCreateDate(new Date());
		wa.setDescription("测试");
		wa.setFax("4001-0023-715");
		wa.setFullName("精简仓库");
		wa.setIsEnable(false);
		wa.setMemo("无人租用");
		wa.setOwnerCode("23");
		wa.setPhones("15068615674");
		wa.setType("QNBS");
		ResponseMessage msg = api.saveWarehouse(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());

		msg = api.saveWarehouse(wa);
//		System.out.println("update:"+msg.getSuccess());
//		System.out.println(msg.getData());
//		System.out.println("getMsg"+msg.getMsg());
//		System.out.println(msg.getStatus());
		
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());

	}

	@Test
	public void delete() {
		// 非空验证
		ResponseMessage msg = api.removeById(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());
		// 正常返回
		msg = api.removeById(128L);
//		Assert.assertFalse(msg.getSuccess());
//		Assert.assertEquals("60042", msg.getStatus());
		
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());
	}
	
	
	@Ignore
	public void getBycodeName(){
		ResponseMessage msg=api.getWarehouseByCode("W0001","QWPH");
//		for (WarehouseDto wdto : (List<WarehouseDto>) msg.getData()) {
//			System.out.println(wdto.getName()+"!@#:"+wdto.getCode());
//		}
		System.out.println("update:"+msg.getSuccess());
		System.out.println(msg.getData());
		System.out.println("getMsg"+msg.getMsg());
		System.out.println(msg.getStatus());
	}
	
	/**
	 * 根据仓库代码查询仓库id
	 */
	@Ignore
	public void getWarehouseIdByCode(){
		ResponseMessage responseMessage = api.getWarehouseIdByCode("");
		Assert.assertFalse(responseMessage.getSuccess());
		Assert.assertEquals("10012", responseMessage.getStatus());
		
		responseMessage = api.getWarehouseIdByCode("W0001");
		Assert.assertTrue(responseMessage.getSuccess());
		Assert.assertNotNull(responseMessage.getData());
		Long id = (Long) responseMessage.getData();
		System.out.println(id);
	}
	
	
	
}
