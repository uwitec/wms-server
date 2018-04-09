package com.yorma.wms.consumer.zbus;

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
import com.yorma.wms.entity.StockTransfer;
import com.yorma.wms.entity.StockTransferList;
import com.yorma.wms.service.api.StockTransferService;

import io.zbus.rpc.bootstrap.ClientBootstrap;

public class StockTransferZbusCasesTest {

	static StockTransferService api;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void before(){
		ClientBootstrap clientBootstrap = new ClientBootstrap();

		clientBootstrap.serviceName("WMS-RPC-TEST");
		clientBootstrap.serviceAddress("172.31.1.176:15555");
		clientBootstrap.serviceToken("wms_rpc_test_service");
		api=clientBootstrap.createProxy(StockTransferService.class);
	}
	
	@Ignore
	public void testgetNo() {
		ResponseMessage message = api.getStockTransferByTransferNo("T201712180001");
		Assert.assertTrue(message.getSuccess());
		Assert.assertNotNull(message.getData());
	}
	
	@Ignore
	public void testSave(){
		List<StockTransferList> transferLists= new ArrayList<>();
		transferLists.add(new StockTransferList(null, null, "T201712180001", "10203", "6a055af3-2c9a-4d33-ab9c-31de38582475", "西瓜", "W0001", "W0112", "S0001", "S0112", "L0001", "L0001", 1, "122","df5822e7-1e62-45c1-aebe-4ead0a58bd66"));
		transferLists.add(new StockTransferList(null, null, "T201712180001", "10203", "bb484861-1ddf-4d60-aadf-a29a273e9fc1", "CESHI", "W0001", "W0112", "S0128", "S0112", "L0262", "L0001", 2, "122","d71b89dd-e9b1-42d1-9836-8adde04135a3"));
		ResponseMessage message =api.saveStockTransFerAndList(new StockTransfer(null, "T201712180001", "ss", new Date(), null, null, "846", "s", new Date(), null, "W0112", "W0112", false), transferLists);
		
		System.out.println("success:"+message.getSuccess());
		System.out.println("data:"+message.getData());
		System.out.println("Status:"+message.getStatus());
		System.out.println("Msg:"+message.getMsg());
	}
	
	
	@Test
	public void testget() {
		Map<String, Object> map= new HashMap<String,Object>();
//		map.put("TRANSFER_NO=", "T201712130001");
//		map.put("OWNER_CODE=", "700");
		ResponseMessage message = api.getStockTransfer(map);
		Assert.assertTrue(message.getSuccess());
		Assert.assertNotNull(message.getData());
	}

	/**
	 * 撤销移库
	 */
	@Ignore
	public void testrevokeStockTransferByTransferNo(){
		ResponseMessage message = api.revokeStockTransferByTransferNo("T201712180001");
//		Assert.assertTrue(message.getSuccess());
//		Assert.assertNotNull(message.getData());
		
		System.out.println("success:"+message.getSuccess());
		System.out.println("data:"+message.getData());
		System.out.println("Status:"+message.getStatus());
		System.out.println("Msg:"+message.getMsg());
	}
	
	/**
	 * 根据商品UUID、仓库查询库存储位和库区、主单位
	 */
	@Ignore
	public void getLocationAndStorage(){
		//查询库存储位和库区
		ResponseMessage message = api.getStockStorageAndLocationByGoodsUUIDAndWarehouse("6a055af3-2c9a-4d33-ab9c-31de38582475", "W0001","L");
//		Assert.assertTrue(message.getSuccess());
//		Assert.assertNotNull(message.getData());
		
		System.out.println("success:"+message.getSuccess());
		System.out.println("data:"+message.getData());
		System.out.println("Status:"+message.getStatus());
		System.out.println("Msg:"+message.getMsg());
		
		//查主单位
//		message = api.getStockMuByGoodsUUIDAndWarehouse("6a055af3-2c9a-4d33-ab9c-31de38582475", "W0001", "S0001", "L0001");
//		System.out.println("~~~~~~~~MU~~~~~~~~~");
//		System.out.println("success:"+message.getSuccess());
//		System.out.println("data:"+message.getData());
//		System.out.println("Status:"+message.getStatus());
//		System.out.println("Msg:"+message.getMsg());
	}
	
	@Ignore
	public void stockTransfer(){
		List<StockTransferList> stockTransferLists = new ArrayList<>();
		
		ResponseMessage responseMessage = api.stockTransfer(stockTransferLists, "s");
		Assert.assertFalse(responseMessage.getSuccess());
		Assert.assertEquals("10004", responseMessage.getStatus());
		
		stockTransferLists.add(new StockTransferList(null, null, "T201712180001", "10203", "6a055af3-2c9a-4d33-ab9c-31de38582475", "西瓜", "W0001", "W0112", "S0001", "S0112", "L0001", "L0001", 1, "122","df5822e7-1e62-45c1-aebe-4ead0a58bd66"));
		stockTransferLists.add(new StockTransferList(null, null, "T201712180001", "10203", "bb484861-1ddf-4d60-aadf-a29a273e9fc1", "CESHI", "W0001", "W0112", "S0128", "S0112", "L0262", "L0001", 2, "122","d71b89dd-e9b1-42d1-9836-8adde04135a3"));
		responseMessage = api.stockTransfer(stockTransferLists, "");
		Assert.assertFalse(responseMessage.getSuccess());
		Assert.assertEquals("10012", responseMessage.getStatus());
		
		responseMessage = api.stockTransfer(stockTransferLists, "s");
		Assert.assertTrue(responseMessage.getSuccess());
		Assert.assertNotNull(responseMessage.getData());
		
	}
	
}
