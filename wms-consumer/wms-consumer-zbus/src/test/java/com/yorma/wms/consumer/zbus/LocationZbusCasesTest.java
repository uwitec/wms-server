package com.yorma.wms.consumer.zbus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.Location;
import com.yorma.wms.entity.dto.LocationDto;
import com.yorma.wms.service.api.LocationService;

import io.zbus.rpc.bootstrap.ClientBootstrap;

public class LocationZbusCasesTest {

	static LocationService api;

	@SuppressWarnings("resource")
	@BeforeClass
	public static void before() throws Exception {
		ClientBootstrap clientBootstrap = new ClientBootstrap();

		clientBootstrap.serviceName("WMS-RPC-TEST");
		clientBootstrap.serviceAddress("172.31.1.176:15555");
		clientBootstrap.serviceToken("wms_rpc_test_service");
		api = clientBootstrap.createProxy(LocationService.class);
	}

	@Ignore
	public void add() {
		Location lo = new Location();
		lo.setBarcode("123");
//		lo.setCode("T0003");
		lo.setCreateBy("su");
		lo.setCreateDate(new Date());
		lo.setDescription("测试数据");
		lo.setHeight(new BigDecimal(9.0));
		lo.setIsEnable(false);
		lo.setKind("RACK");
		lo.setLength(new BigDecimal(8.5));
		lo.setLoadWeight(new BigDecimal(25));
		lo.setMemo("测试");
		lo.setStorageCode("S0001");
		lo.setTag("1");
		lo.setType("PT");
		lo.setVolume(new BigDecimal(255));
		lo.setWarehouseCode("W0001");
		lo.setWidth(new BigDecimal(7));

		ResponseMessage msg = api.saveLocation(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());

		msg = api.saveLocation(lo);
//		Assert.assertFalse(msg.getSuccess());
//		Assert.assertEquals("60001", msg.getStatus());
		
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());

	}

	@Ignore
	public void adds(){
		List<Location> beans=new ArrayList<>();
		Location lo = new Location();
		lo.setBarcode("123");
		lo.setCode("T00012");
		lo.setCreateBy("su");
		lo.setCreateDate(new Date());
		lo.setDescription("测试数据");
		lo.setHeight(new BigDecimal(9.0));
		lo.setIsEnable(false);
		lo.setKind("RACK");
		lo.setLength(new BigDecimal(8.5));
		lo.setLoadWeight(new BigDecimal(25));
		lo.setMemo("测试");
		lo.setStorageCode("S0001");
		lo.setTag("1");
		lo.setType("PT");
		lo.setVolume(new BigDecimal(255));
		lo.setWarehouseCode("W0002");
		lo.setWidth(new BigDecimal(14));
		Location lc = new Location();
		lc.setBarcode("123");
		lc.setCode("T00013");
		lc.setCreateBy("su");
		lc.setCreateDate(new Date());
		lc.setDescription("测试数据");
		lc.setHeight(new BigDecimal(9.0));
		lc.setIsEnable(false);
		lc.setKind("RACK");
		lc.setLength(new BigDecimal(8.5));
		lc.setLoadWeight(new BigDecimal(25));
		lc.setMemo("测试");
		lc.setStorageCode("S0001");
		lc.setTag("1");
		lc.setType("PT");
		lc.setVolume(new BigDecimal(255));
		lc.setWarehouseCode("W0002");
		lc.setWidth(new BigDecimal(7));
		beans.add(lo);
		beans.add(lc);
		
		ResponseMessage //msg = api.adds(null);
//		Assert.assertFalse(msg.getSuccess());
//		Assert.assertEquals("10003", msg.getStatus());

		msg = api.createBatch(beans);
//		Assert.assertTrue(msg.getSuccess());
//		Assert.assertNotNull(msg.getData());
		System.out.println("getData:"+msg.getData());
		System.out.println("getSuccess:"+msg.getSuccess()+" getStatus"+msg.getStatus());
		System.out.println("getMsg:"+msg.getMsg());
	}
	

	public void getLocation() {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("code=", "A102");
		map1.put("kind", "");
		ResponseMessage msg = api.getLocation(map1);
//		Assert.assertFalse(msg.getSuccess());
//		Assert.assertEquals("10003", msg.getStatus());
		
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());

//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("warehouseCode", null);
//		map.put("kind", "");
//		map.put("kind", "");
//		map.put("code", "A102");
//		msg = api.getLocation(map);
//		Assert.assertTrue(msg.getSuccess());
//		Assert.assertNotNull(msg.getData());

	}

	@Ignore
	public void getById() {
		long id = 12;
		ResponseMessage msg = api.find(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());
		
		msg=api.find(id);
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());
	}

	@Ignore
	public void update() {
		
		Location lo = new Location();
		lo.setBarcode("123");
		lo.setCode("A103");
		lo.setCreateBy("su");
		lo.setCreateDate(new Date());
		lo.setDescription("测试数据");
		lo.setHeight(new BigDecimal(9.0));
		lo.setIsEnable(false);
		lo.setKind("RACK");
		lo.setLength(new BigDecimal(8.5));
		lo.setLoadWeight(new BigDecimal(25));
		lo.setMemo("测试");
		lo.setStorageCode("S0001");
		lo.setTag("1");
		lo.setType("PT");
		lo.setVolume(new BigDecimal(255));
		lo.setWarehouseCode("W0001");
		lo.setWidth(new BigDecimal(7));
		lo.setStorageCode("A1002");
		lo.setLastUpdateBy("s");
		lo.setLastUpdateDate(new Date());
		lo.setId(12L);

		ResponseMessage msg = api.saveLocation(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());
		msg = api.saveLocation(lo);
//		System.out.println("msg："+msg.getMsg());
//		System.out.println("msg："+msg.getMsg());
//		System.out.println("getStatus"+msg.getStatus());
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());

	}

	@Test
	public void delete() {

		ResponseMessage msg = api.removeById(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());
		
		msg=api.removeById(1113L);
//		System.out.println("getSuccess:"+msg.getSuccess());
//		System.out.println("getMsg:"+msg.getMsg());
//		Assert.assertFalse(msg.getSuccess());
//		Assert.assertEquals("60042", msg.getStatus());
		
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());
	}

	@SuppressWarnings("unchecked")
	@Ignore
	public void getLocationAndWarehouse(){
		Map<String, Object> map=new HashMap<>();
		
		ResponseMessage message=api.getLocationAndWarehouse(map);
		
		for (LocationDto locationDto : (List<LocationDto>)message.getData()) {
			System.out.println("warehouse："+locationDto.getFullName());
			System.out.println("id："+locationDto.getId());
		}
	}
	
}
