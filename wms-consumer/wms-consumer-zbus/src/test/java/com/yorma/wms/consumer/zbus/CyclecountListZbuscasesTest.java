package com.yorma.wms.consumer.zbus;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.CyclecountList;
import com.yorma.wms.service.api.CyclecountListService;

import io.zbus.rpc.bootstrap.ClientBootstrap;

public class CyclecountListZbuscasesTest {

	static CyclecountListService api;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void before(){
		ClientBootstrap clientBootstrap = new ClientBootstrap();

		clientBootstrap.serviceName("WMS-RPC-TEST");
		clientBootstrap.serviceAddress("10.10.10.171:15555");
		clientBootstrap.serviceToken("wms_rpc_test_service");
		api=clientBootstrap.createProxy(CyclecountListService.class);
	}
	
	/**
	 * 保存操作
	 */
	@Test
	public void testsave() {
		
		CyclecountList cyclecountList = new CyclecountList(); 
		cyclecountList.setCyclecountNo("C201712060001");
		cyclecountList.setGoodsName("可乐");
		
		ResponseMessage responseMessage = api.save(cyclecountList);
//		Assert.assertTrue(responseMessage.getSuccess());
//		Assert.assertNotNull(responseMessage.getData());
		
		System.out.println("getSuccess:"+responseMessage.getSuccess());
		System.out.println("getStatus:"+responseMessage.getStatus());
		System.out.println("getData:"+responseMessage.getData());
	}
	
	/**
	 * 根据盘点单号查询盘点单明细
	 */
	@Ignore
	public void getBycyclecountNo(){
		ResponseMessage responseMessage = api.getCyclecountListByCyclecountNo("");
		Assert.assertFalse(responseMessage.getSuccess());
		Assert.assertEquals("10012", responseMessage.getStatus());
		responseMessage = api.getCyclecountListByCyclecountNo("CS001");
		Assert.assertTrue(responseMessage.getSuccess());
		Assert.assertNotNull(responseMessage.getData());
	}

}
