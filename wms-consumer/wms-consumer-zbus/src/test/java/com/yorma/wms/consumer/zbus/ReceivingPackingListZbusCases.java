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
import com.yorma.wms.entity.ReceivingPackingList;
import com.yorma.wms.service.api.ReceivingPackingListService;

import io.zbus.rpc.bootstrap.ClientBootstrap;

public class ReceivingPackingListZbusCases {

	static ReceivingPackingListService api;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void before(){
		ClientBootstrap clientBootstrap=new ClientBootstrap();
		
		clientBootstrap.serviceName("WMS-RPC-TEST");
		clientBootstrap.serviceAddress("10.10.10.171:15555");
		clientBootstrap.serviceToken("wms_rpc_test_service");
		api=clientBootstrap.createProxy(ReceivingPackingListService.class);
	}
	
	@Test
	public void add() {
		ReceivingPackingList rpl=new ReceivingPackingList();
//		rpl.setId(1329L);
//		rpl.setUuid("334cef24-ef78-4c29-b2ef-1ed44df3e5d3");
//		rpl.setPackagingNo("PK1710190002");
		rpl.setAnsUuid("4cae0745-2880-47dc-aaae-e9733e216a59");
		rpl.setGoodsUuid("7a64aec0-bfd6-42b6-a4f9-c165d1ee6d68");
		rpl.setPn("007");
		rpl.setCreateBy("s");
		rpl.setCreateDate(new Date());
		rpl.setEntryQty(5);
		rpl.setLocation("L0002");
		rpl.setPackagingKey("5");
		rpl.setStorage("S0001");
		rpl.setWarehouse("W0001");
		rpl.setReceivingNoteNo("RK201712260021");
		rpl.setIsQualifd(false);
		rpl.setPalletUnit("托");
		ReceivingPackingList rpl1=new ReceivingPackingList();
//		rpl1.setAnsUuid("4cae0745-2880-47dc-aaae-e9733e216a59");
		rpl1.setGoodsUuid("71a27cd9-d365-4776-a408-9da9e9e62236");
		rpl1.setPn("007");
		rpl1.setCreateBy("s");
		rpl1.setCreateDate(new Date());
		rpl1.setEntryQty(5);
		rpl1.setLocation("L0002");
		rpl1.setPackagingKey("5");
		rpl1.setStorage("S0001");
		rpl1.setWarehouse("W0001");
		rpl1.setReceivingNoteNo("RK201712260021");
		rpl1.setIsQualifd(false);
		rpl1.setPalletUnit("托");
//		rpl1.setId(1330L);
//		rpl1.setUuid("2751d84e-f343-426e-831f-34d0cc4f63cf");
//		rpl1.setPackagingNo("PK1710190003");
		List<ReceivingPackingList> PLlist=new ArrayList<>();
		PLlist.add(rpl);
		PLlist.add(rpl1);
		List<String> asnUuids = new ArrayList<>();
		asnUuids.add("9cce5dd6-5609-4437-a589-99fc984280d6");
		ResponseMessage msg=api.saveBatch(PLlist);
//		msg=api.save(null);
//		Assert.assertFalse(msg.getSuccess());
//		Assert.assertEquals("10004", msg.getStatus());
		
//		Assert.assertTrue(msg.getSuccess());
//		Assert.assertNotNull(msg.getData());
		System.out.println("getSuccess:"+msg.getSuccess());
		System.out.println("getStatus:"+msg.getStatus());
		System.out.println("getData:"+msg.getData());
		
	}
	
	@SuppressWarnings("unchecked")
	@Ignore
	public void get(){
//		ResponseMessage msg=api.getReceivingPackingListByNoteNo("RK201710120011",30,null,new Date());
		ResponseMessage msg=api.getReceivingPackingListByNoteNo("RK201710120011");
		
		if (msg.getSuccess()) {
			if (msg.getData()== null) {
				System.out.println("null");
			}else {
				for (ReceivingPackingList r : (List<ReceivingPackingList>) msg.getData()) {
					System.out.println("date:"+r.getCreateDate());
				}
			}
		}else {
			System.out.println("status:"+msg.getStatus());
			System.out.println("msg:"+msg.getMsg());
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
		ReceivingPackingList rpl=new ReceivingPackingList();
		rpl.setUuid("41308dde-183d-46d1-80ba-f7fdbd433c52");
		rpl.setAnsUuid("4cae0745-2880-47dc-aaae-e9733e216a59");
		rpl.setGoodsUuid("7a64aec0-bfd6-42b6-a4f9-c165d1ee6d68");
		rpl.setPn("007");
		rpl.setCreateBy("s");
		rpl.setCreateDate(new Date());
		rpl.setEntryQty(50);
		rpl.setLocation("L0001");
		rpl.setPackagingKey("5");
		rpl.setPackagingNo("0531-101");
		rpl.setStorage("S0001");
		rpl.setWarehouse("W0001");
		rpl.setPalletNo("0531-1011");
		rpl.setReceivingNoteNo("0531-201");
		rpl.setIsQualifd(false);
		rpl.setPalletNo("0023");
		rpl.setPalletUnit("托");
		rpl.setId(5L);
		
		ResponseMessage msg=api.save(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());
		
		msg=api.save(rpl);
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
//		System.out.println("getData:"+msg.getData());
	}
	@Ignore
	public void oneSaveBatch() throws Exception{
		ReceivingPackingList rpl=new ReceivingPackingList();
//		rpl.setId(1329L);
//		rpl.setUuid("334cef24-ef78-4c29-b2ef-1ed44df3e5d3");
//		rpl.setPackagingNo("PK1710190002");
		rpl.setAnsUuid("c1a93af1-6a48-4244-9814-62f586aa5ab6");
		rpl.setGoodsUuid("7a64aec0-bfd6-42b6-a4f9-c165d1ee6d68");
		rpl.setPn("007");
		rpl.setCreateBy("s");
		rpl.setCreateDate(new Date());
		rpl.setEntryQty(50);
		rpl.setLocation("L0002");
		rpl.setPackagingKey("5");
		rpl.setStorage("S0001");
		rpl.setWarehouse("W0001");
		rpl.setReceivingNoteNo("RK201710240007");
		rpl.setIsQualifd(false);
		rpl.setPalletUnit("托");
		ReceivingPackingList rpl1=new ReceivingPackingList();
		rpl1.setAnsUuid("c1a93af1-6a48-4244-9814-62f586aa5ab6");
		rpl1.setGoodsUuid("7a64aec0-bfd6-42b6-a4f9-c165d1ee6d68");
		rpl1.setPn("007");
		rpl1.setCreateBy("s");
		rpl1.setCreateDate(new Date());
		rpl1.setEntryQty(50);
		rpl1.setLocation("L0002");
		rpl1.setPackagingKey("5");
		rpl1.setStorage("S0001");
		rpl1.setWarehouse("W0001");
		rpl1.setReceivingNoteNo("RK201710240007");
		rpl1.setIsQualifd(false);
		rpl1.setPalletUnit("托");
//		rpl1.setId(1330L);
//		rpl1.setUuid("2751d84e-f343-426e-831f-34d0cc4f63cf");
//		rpl1.setPackagingNo("PK1710190003");
		List<ReceivingPackingList> PLlist=new ArrayList<>();
		PLlist.add(rpl);
		PLlist.add(rpl1);
		ReceivingNoteAsn noteAsn=new ReceivingNoteAsn();
		noteAsn.setAcceptanceQty(0);
		noteAsn.setCreateBy("s");
		//2017-10-24 15:06:15
		noteAsn.setCreateDate(new Date());
		noteAsn.setExpectQty(50);
		noteAsn.setGoodsUuid("246cc720-eb24-45a5-be6b-1380928e36c2");
		noteAsn.setReceivingNoteNo("RK201710240007");
		noteAsn.setMu("001");
		noteAsn.setPn("007");
		noteAsn.setPackagingQty(10);
		noteAsn.setBatchAttribute("");
		noteAsn.setCurrency(null);
		noteAsn.setUuid("c41c9f95-5987-4015-881f-334551db3951");
		noteAsn.setId(5L);
		List<ReceivingNoteAsn> receivingNoteAsns=new ArrayList<>();
		receivingNoteAsns.add(noteAsn);
		
		ResponseMessage message=api.oneSaveBatch(PLlist, receivingNoteAsns);
		System.out.println("getSuccess:"+message.getSuccess());
		System.out.println("getStatus:"+message.getStatus());
		System.out.println("getData:"+message.getData());
	}
	
}
