package com.yorma.wms.consumer.zbus;

import java.util.Date;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.DeliveryPackingList;
import com.yorma.wms.service.api.DeliveryPackingListService;

import io.zbus.rpc.bootstrap.ClientBootstrap;

public class DeliveryPackingListZbusCasesTest {

	static DeliveryPackingListService api;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void before(){
		ClientBootstrap clientBootstrap = new ClientBootstrap();

		clientBootstrap.serviceName("WMS-RPC-TEST");
		clientBootstrap.serviceAddress("10.10.10.171:15555");
		clientBootstrap.serviceToken("wms_rpc_test_service");
		api=clientBootstrap.createProxy(DeliveryPackingListService.class);
	}
	
	@Ignore
	public void add() {
		DeliveryPackingList l=new DeliveryPackingList();
		l.setAnsUuid("bde9e404-398b-4b6b-9fe4-17f3b1366fac");
		l.setCreateBy("s");
		l.setCreateDate(new Date());
		l.setDeliveryNoteNo("00001");
		l.setDeliveryQty(50);
		l.setStockUuid("2047d6da-6c4d-4b96-a25b-dfb1abe87c77");
		l.setGoodsUuid("7a64aec0-bfd6-42b6-a4f9-c165d1ee6d68");
		l.setPn("101");
		l.setLocation("L0001");
		l.setStorage("S0001");
		l.setWarehouse("W0001");
		l.setPackagingKey("101");
		l.setPackagingNo("05310102");
		
		l.setSku(null);
		l.setItem("");
		ResponseMessage msg=api.save(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());
		
		msg=api.save(l);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10003", msg.getStatus());
		
		l.setPalletNo("100");
		msg=api.save(l);
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());
		
//		System.out.println("getSuccess:"+msg.getSuccess());
//		System.out.println("getStatus:"+msg.getStatus());
//		System.out.println("getData:"+msg.getData());
	}
	
	@Test
	public void get(){
		
		
		ResponseMessage msg=api.getDeliveryPackingListBydeliveryNoteNo("CK201711150029");
		
//		Assert.assertFalse(msg.getSuccess());
//		Assert.assertEquals("10003", msg.getStatus());
		
		System.out.println("getSuccess:"+msg.getSuccess());
		System.out.println("getStatus:"+msg.getStatus());
		System.out.println("getData:"+msg.getData());
		System.out.println("MSG:"+msg.getMsg());
	}
	
	@Ignore
	public void ById(){
		ResponseMessage msg=api.find(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());
		
		msg=api.find(4L);
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());
		
//		System.out.println("getSuccess:"+msg.getSuccess());
//		System.out.println("getStatus:"+msg.getStatus());
//		System.out.println("getData:"+msg.getData());
	}

	@Ignore
	public void uodate(){
		DeliveryPackingList l=new DeliveryPackingList();
		l.setUuid("c31911b5-9f7c-4e10-a465-65f189505019");
		l.setAnsUuid("bde9e404-398b-4b6b-9fe4-17f3b1366fac");
		l.setCreateBy("ss");
		l.setCreateDate(new Date());
		l.setDeliveryNoteNo("101-105");
		l.setDeliveryQty(50);
		l.setStockUuid("2047d6da-6c4d-4b96-a25b-dfb1abe87c77");
		l.setGoodsUuid("7a64aec0-bfd6-42b6-a4f9-c165d1ee6d68");
		l.setPn("101");
		l.setLocation("L0001");
		l.setStorage("S0001");
		l.setWarehouse("W0001");
		l.setPackagingKey("101");
		l.setPackagingNo("05310102");
		l.setSku(null);
		l.setItem("");
		l.setId(4L);
		ResponseMessage msg=api.save(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());
		
		msg=api.save(l);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10003", msg.getStatus());
		
		l.setPalletNo("101");
		msg=api.save(l);
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());
		
//		System.out.println("getSuccess:"+msg.getSuccess());
//		System.out.println("getStatus:"+msg.getStatus());
//		System.out.println("getData:"+msg.getData());
	}
	
	@Ignore
	public void delete(){
		ResponseMessage msg=api.removeById(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());
		
		msg=api.removeById(3L);
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());
		
//		System.out.println("getSuccess:"+msg.getSuccess());
//		System.out.println("getStatus:"+msg.getStatus());
//		System.out.println("getData:"+msg.getData());
	}
	
}
