package com.yorma.wms.consumer.zbus;



import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.Storage;
import com.yorma.wms.service.api.StorageService;

import io.zbus.rpc.bootstrap.ClientBootstrap;

public class StorageZbusCasesTest {

	static StorageService api;

	@SuppressWarnings("resource")
	@BeforeClass
	public static void before() throws Exception {
		ClientBootstrap clientBootstrap = new ClientBootstrap();

		clientBootstrap.serviceName("WMS-RPC-TEST");
		clientBootstrap.serviceAddress("172.31.1.176:15555");
		clientBootstrap.serviceToken("wms_rpc_test_service");
		api = clientBootstrap.createProxy(StorageService.class);
	}

	@Ignore
	public void add() {
		Storage re = new Storage();
		re.setArea(new BigDecimal(100));
		 re.setCode("T0004");
		re.setCreateBy("su");
		re.setCreateDate(new Date());
		re.setDescription("测试数据");
		re.setHeader("su");
		re.setIsEnable(false);
		re.setMemo("测试");
		re.setName("A1100");
		re.setPosition("A1030");
		re.setType("RA");
		re.setWarehouseCode("W0001");

		ResponseMessage msg = api.saveStorage(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());
		
		msg=api.saveStorage(re);
		
//		Assert.assertFalse(msg.getSuccess());
//		Assert.assertEquals("10003", msg.getStatus());
		
//		System.out.println("getSuccess"+msg.getSuccess());
//		System.out.println("getData"+msg.getData());
//		System.out.println("getMsg"+msg.getMsg());
		
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());
		

	}

	@Ignore
	public void get() {
//		Map<String, Object> map = new HashMap<>();
//		map.put("createBy", null);
//		map.put("code", "");
//		map.put("type=", "ra");
//		ResponseMessage msg = api.getStorage(map);
//		Assert.assertFalse(msg.getSuccess());
//		Assert.assertEquals("10003", msg.getStatus());

		Map<String, Object> map1 = new HashMap<>();
		map1.put("createBy", null);
		map1.put("code", "");
		map1.put("type", "ra");
		ResponseMessage	msg = api.getStorage(map1);
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());

	}

	@Ignore
	public void getById() {

		ResponseMessage msg = api.find(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());

		msg = api.find(9L);
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());

	}

	@Ignore
	public void update() {
		Storage re = new Storage();
		re.setArea(new BigDecimal(100));
		re.setCode("S0012");
		re.setCreateBy("ss");
//		re.setCreateDate(new Date());
		re.setDescription("测试数据");
		re.setHeader("ss");
		re.setIsEnable(false);
		re.setMemo("测试");
		re.setName("A1102");
		re.setPosition("A1030");
		re.setType("RA");
		re.setWarehouseCode("W0001");
		re.setId(4L);
		re.setLastUpdateDate(new Date());
		
		ResponseMessage msg = api.saveStorage(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());

		msg = api.saveStorage(re);
//		System.out.println("getStatus:"+msg.getStatus()+" getSuccess"+msg.getSuccess()+" msg"+msg.getMsg());
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());

	}

	@Test
	public void delete() {

		ResponseMessage msg = api.removeById(null);

		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());

		msg = api.removeById(139L);
//		System.out.println("status:"+msg.getStatus());
//		System.out.println("msg"+msg.getMsg());
//		Assert.assertFalse(msg.getSuccess());
//		Assert.assertEquals("60042", msg.getStatus());
		
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());

	}
	
	
}
