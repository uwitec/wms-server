package com.yorma.wms.consumer.zbus;

import java.util.Date;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.SalesDetails;
import com.yorma.wms.service.api.SalesDetailsService;

import io.zbus.rpc.bootstrap.ClientBootstrap;

public class SalesDetailsZbusCasesTest {

	static SalesDetailsService api;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void before(){
		ClientBootstrap clientBootstrap = new ClientBootstrap();

		clientBootstrap.serviceName("WMS-RPC-TEST");
		clientBootstrap.serviceAddress("10.10.10.171:15555");
		clientBootstrap.serviceToken("wms_rpc_test_service");
		api=clientBootstrap.createProxy(SalesDetailsService.class);
	}
	
	@Ignore
	public void testadd() {
		SalesDetails salesDetails = new SalesDetails();
		salesDetails.setSo("S201711150003");
		salesDetails.setGoodsUuid("ab4ad5cc-f07a-4bf8-b29d-3538497790f2");
		salesDetails.setIsQualifd(true);
		salesDetails.setCreateBy("test");
		salesDetails.setCreateDate(new Date());
		
		ResponseMessage message=api.save(salesDetails);
		Assert.assertTrue(message.getSuccess());
		Assert.assertNotNull(message.getData());
//		System.out.println("Success:"+message.getSuccess());
//		System.out.println("statuis:"+message.getStatus());
//		System.out.println("msg:"+message.getStatus());
		
	}
	
	@Test
	public void testGet(){
		ResponseMessage message=api.getSalesDetailsByOrderSO("");
		Assert.assertTrue(message.getSuccess());
		Assert.assertNotNull(message.getData());
//		System.out.println("Success:"+message.getSuccess());
//		System.out.println("statuis:"+message.getStatus());
//		System.out.println("msg:"+message.getStatus());
	}
	@Test
	public void getSalesDetailsAsAsn(){
		ResponseMessage message=api.getSalesDetailsAsDerliveryAsnByOrderSO("");
		Assert.assertTrue(message.getSuccess());
		Assert.assertNotNull(message.getData());
	}
	
}
