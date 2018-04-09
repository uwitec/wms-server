package com.yorma.wms.consumer.zbus;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.service.api.StockTransferListService;

import io.zbus.rpc.bootstrap.ClientBootstrap;

public class StockTransferListZbusCasesTest {

	static StockTransferListService api;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void before(){
		ClientBootstrap clientBootstrap = new ClientBootstrap();

		clientBootstrap.serviceName("WMS-RPC-TEST");
		clientBootstrap.serviceAddress("10.10.10.171:15555");
		clientBootstrap.serviceToken("wms_rpc_test_service");
		api=clientBootstrap.createProxy(StockTransferListService.class);
	}
	
	@Test
	public void testgetNo() {
		ResponseMessage message = api.getStockTransferListByTransferNo("T201712150001");
		Assert.assertTrue(message.getSuccess());
		Assert.assertNotNull(message.getData());
	}

}
