package com.yorma.wms.consumer.zbus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.ReceivingNoteAsn;
import com.yorma.wms.service.api.ReceivingNoteAsnService;

import io.zbus.rpc.bootstrap.ClientBootstrap;

public class ReceivingNoteAsnZbusCases {

	static ReceivingNoteAsnService api;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void before(){
		ClientBootstrap clientBootstrap = new ClientBootstrap();

		clientBootstrap.serviceName("WMS-RPC-TEST");
		clientBootstrap.serviceAddress("10.10.10.171:15555");
		clientBootstrap.serviceToken("wms_rpc_test_service");
		api=clientBootstrap.createProxy(ReceivingNoteAsnService.class);
	}
	
	@Ignore
	public void add() {
		ReceivingNoteAsn rea=new ReceivingNoteAsn();
		rea.setAcceptanceQty(50);
		rea.setCreateBy("s");
		rea.setCreateDate(new Date());
		rea.setExpectQty(50);
		rea.setGoodsUuid("7a64aec0-bfd6-42b6-a4f9-c165d1ee6d68");
		rea.setReceivingNoteNo("0531-102");
		rea.setMu("001");
		rea.setPn("");
		rea.setPackagingQty(2);
		rea.setBatchAttribute("");
		rea.setCurrency(null);
		
		ResponseMessage msg=api.save(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());
		
		msg=api.save(rea);
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());
//		System.out.println("getData:"+msg.getData());
	}
	
	@SuppressWarnings("unchecked")
	@Ignore
	public void get(){
		
		ResponseMessage msg=api.getReceivingNoteAsnByNoteNo("RK201710190005",20);
		
		for (ReceivingNoteAsn r : (List<ReceivingNoteAsn>) msg.getData()) {
			System.out.println("date:"+r.getCreateDate());
		}
//		Assert.assertTrue(msg.getSuccess());
//		Assert.assertNotNull(msg.getData());
//		System.out.println("getData:"+msg.getData());
	}
	
	@Ignore
	public void ById(){
		ResponseMessage msg=api.find(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());
		
		msg=api.find(5L);
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());
//		System.out.println("getData:"+msg.getData());
	}
	
	@Ignore
	public void update(){
		ReceivingNoteAsn rea=new ReceivingNoteAsn();
		rea.setAcceptanceQty(50);
		rea.setCreateBy("s");
		rea.setCreateDate(new Date());
		rea.setExpectQty(50);
		rea.setGoodsUuid("7a64aec0-bfd6-42b6-a4f9-c165d1ee6d68");
		rea.setReceivingNoteNo("0531-102");
		rea.setMu("001");
		rea.setPn("007");
		rea.setPackagingQty(10);
		rea.setBatchAttribute("");
		rea.setCurrency(null);
		rea.setUuid("c41c9f95-5987-4015-881f-334551db3951");
		rea.setId(5L);
		
		ResponseMessage msg=api.save(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());
		
		msg=api.save(rea);
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());
//		System.out.println("getData:"+msg.getData());
	}

	@Ignore
	public void delete(){
		ResponseMessage msg=api.removeById(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());
		
		msg=api.removeById(4L);
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());
	}
	
	
	@Ignore
	public void deletes(){
		ResponseMessage msg=api.delete("e48fe1a1-a4d6-4106-a0b9-2f05305e316d");
		
//		Assert.assertTrue(msg.getSuccess());
//		Assert.assertNotNull(msg.getData());
		System.out.println("getSuccess:"+msg.getSuccess());
	}
	
	@Ignore
	public void batchByAsn(){
		String asnUuid="03f18845-3777-4481-a95f-0c0d9b7cf099";
		List<ReceivingNoteAsn> asnList=new ArrayList<>();
		ReceivingNoteAsn rea=new ReceivingNoteAsn();
		rea.setAcceptanceQty(50);
		rea.setCreateBy("s");
		rea.setCreateDate(new Date());
		rea.setExpectQty(50);
		rea.setGoodsUuid("7a64aec0-bfd6-42b6-a4f9-c165d1ee6d68");
		rea.setReceivingNoteNo("0531-102");
		rea.setMu("001");
		rea.setPn("101");
		rea.setPackagingQty(5);
		rea.setBatchAttribute("123");
		rea.setCurrency(null);
		
		ReceivingNoteAsn rec=new ReceivingNoteAsn();
		rec.setAcceptanceQty(100);
		rec.setCreateBy("s");
		rec.setCreateDate(new Date());
		rec.setExpectQty(100);
		rec.setGoodsUuid("7a64aec0-bfd6-42b6-a4f9-c165d1ee6d68");
		rec.setReceivingNoteNo("0531-102");
		rec.setMu("002");
		rec.setPn("101");
		rec.setPackagingQty(20);
		rec.setBatchAttribute("123");
		rec.setCurrency(null);
		asnList.add(rea);
		asnList.add(rec);
		
		ResponseMessage msg=api.batchByAsn(asnUuid, asnList);
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());
	}
	
	@Test
	public void revokePacking(){
//		ResponseMessage responseMessage = api.revokePacking("ME1712280007");
//		Assert.assertTrue(responseMessage.getSuccess());
		
		
	}
	
	
}
