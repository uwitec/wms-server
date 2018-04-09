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

import com.yorma.common.entity.dto.ResponseData;
import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.ReceivingNote;
import com.yorma.wms.entity.ReceivingNoteAsn;
import com.yorma.wms.service.api.ReceivingNoteService;

import io.zbus.rpc.bootstrap.ClientBootstrap;

public class ReceivingNoteZbusCasesTest {

	static ReceivingNoteService api;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void before(){
		ClientBootstrap clientBootstrap = new ClientBootstrap();

		clientBootstrap.serviceName("WMS-RPC-TEST");
		clientBootstrap.serviceAddress("172.31.1.176:15555");
		clientBootstrap.serviceToken("wms_rpc_test_service");
		api=clientBootstrap.createProxy(ReceivingNoteService.class);
	}
	
	@Ignore
	public void add() {
		ReceivingNote rn=new ReceivingNote();
		rn.setAmount(new BigDecimal(10000.5));
		rn.setBillNo("0531-101");
		rn.setCreateBy("s");
//		rn.setCreateDate(new Date());
		rn.setCurrency("元");
		rn.setExpeateArrivalDate(new Date());
		rn.setGrossWeight(new BigDecimal(500));
		rn.setLastUpdateBy("");
		rn.setOwnerCode("26");
		rn.setPacks(null);
		
		rn.setReceivingType("快进快出");
		rn.setWarehouse("W0001");
		rn.setStatus(1);
		
//		ResponseMessage msg=api.save(null);
//		Assert.assertFalse(msg.getSuccess());
//		Assert.assertEquals("10004", msg.getStatus());
		
		ResponseMessage	msg=api.save(rn);
		
//		Assert.assertTrue(msg.getSuccess());
//		Assert.assertNotNull(msg.getData());
		
		System.out.println("getSuccess:"+msg.getSuccess());
		System.out.println("getStatus:"+msg.getStatus());
		System.out.println("getData:"+msg.getData());
	}
	
	
	@Test
	public void getTest(){
//		Map<String, Object> m=new HashMap<>();
//		m.put("warehouse", "W0001");
//		m.put("receivingNoteNo", "");
//		m.put("receivingType", null);
//		
//		ResponseMessage msg=api.getReceivingNote(m);
//		Assert.assertFalse(msg.getSuccess());
//		Assert.assertEquals("10003", msg.getStatus());
		
		Map<String, Object> map=new HashMap<>();
		Map<String, Object> map1=new HashMap<>();
//		map.put("invoice_no  LIKE", null);
//		map.put("order_no  LIKE","");
//		map.put("warehouse=", "W0002");
//		map.put("receiving_Type=", null);
//		map.put("status=", 1);
//		map.put("r.create_By=", "");
//		map.put("owner_Code=", "23");
//		map.put("r.shipping_Code=", "");
//		map.put("r.payment_Code=", "");
//		map.put("r.receiving_Note_No like","%0531%");
//		map.put("bill_No", "102");
//		map.put("h.operate_time<=", "2017-08-31 08:31:48");
//		map1.put("r.warehouse =", "");
//		map1.put("r.create_Date<=", "2017-09-13 14:03:22");
//		map1.put("r.CREATE_DATE>=", "2017-08-23 15:42:53");
//		map.put("expeate_Arrival_Date<=", "2017-08-25 15:42:53");
//		map.put("expeate_Arrival_Date>=", "2017-08-23 15:42:53");
		
		ResponseMessage msg=api.getReceivingNote(map,map1,1,10);
		
		
//		System.out.println("getSuccess:"+msg.getSuccess());
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());
		ResponseData<ReceivingNote> responseData = (ResponseData<ReceivingNote>)msg.getData();
		System.out.println("Data:"+responseData.getList().size());
	}

	@Ignore
	public void ById(){
		ResponseMessage msg=api.find(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());
		
		msg=api.find(5L);
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());
	}
	
	@Ignore
	public void update(){
		ReceivingNote rn=new ReceivingNote();
//		rn.setAmount(new BigDecimal(10000.5));
//		rn.setBillNo("0531-102");
		rn.setCreateBy("s");
//		rn.setCreateDate(new Date());
//		rn.setCurrency("元");
//		rn.setExpeateArrivalDate(new Date());
//		rn.setGrossWeight(new BigDecimal(500));
//		rn.setLastUpdateBy("");
//		rn.setOwnerCode("23");
//		rn.setPacks(null);
//		rn.setReceivingNoteNo("0531-122");
//		rn.setReceivingType("快进快出");
//		rn.setWarehouse("");
//		rn.setStatus(1);
		rn.setId(141L);
		
		ResponseMessage 
//		msg=api.save(null);
//		Assert.assertFalse(msg.getSuccess());
//		Assert.assertEquals("10004", msg.getStatus());
		
		msg=api.save(rn);
//		Assert.assertTrue(msg.getSuccess());
//		Assert.assertNotNull(msg.getData());
		System.out.println("Status:"+msg.getStatus());
		System.out.println("Msg:"+msg.getMsg());
		
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
	public void upstatus(){
		ResponseMessage msg=api.updateStatus("0531-2", 20);
		System.out.println("getSuccess:"+msg.getSuccess());
		System.out.println("getData:"+msg.getData());
		System.out.println("getStatus:"+msg.getStatus());
		System.out.println("getMsg:"+msg.getMsg());
	}
	
	@Ignore
	public void deletes(){
		ResponseMessage msg=api.delete("0531-103");
		System.out.println("getSuccess3:"+msg.getSuccess());
		System.out.println("getData3:"+msg.getData());
		System.out.println("getStatus3:"+msg.getStatus());
	}
	
	/**
	 * 保存入库单及明细
	 */
	@Ignore
	public void saveReceivingNoteAndAsn(){
		ReceivingNote rn=new ReceivingNote();
		rn.setAmount(new BigDecimal(10000.5));
		rn.setBillNo("0531-101");
		rn.setCreateBy("s");
		rn.setCreateDate(new Date());
		rn.setCurrency("502");
		rn.setExpeateArrivalDate(new Date());
		rn.setGrossWeight(new BigDecimal(500));
		rn.setOwnerCode("26");
		rn.setReceivingType("301");
		rn.setWarehouse("W0001");
		rn.setStatus(10);
		rn.setReceivingNoteNo("RK201801100003");
		rn.setId(927L);
		List<ReceivingNoteAsn> asns = new ArrayList<>();
		ReceivingNoteAsn asn =new ReceivingNoteAsn();
		asn.setCreateBy("ss");
		asn.setExpectQty(1);
		asn.setGoodsUuid("123");
		asn.setIsQualifd(false);
		asn.setMu("011");
		asn.setPn("12");
		asn.setPackagingQty(1);
		asn.setAcceptanceQty(1);
		asn.setStatus(0);
		asn.setId(1935L);
		asn.setCreateDate(new Date());
		asn.setReceivingNoteNo("RK201801100003");
		asn.setUuid("1b01ba41-8de8-47f7-a25d-9d2b550d2054");
		asns.add(asn);
		
		ResponseMessage message=api.saveReceivingNoteAndAsn(rn, asns);
		System.out.println("getSuccess:"+message.getSuccess());
		System.out.println("getData:"+message.getData());
		System.out.println("getStatus:"+message.getStatus());
		System.out.println("getMsg:"+message.getMsg());
		
		
	}
	
	
}
