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

import com.yorma.common.entity.dto.ResponseData;
import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.DeliveryNote;
import com.yorma.wms.entity.DeliveryNoteAsn;
import com.yorma.wms.service.api.DeliveryNoteAsnService;
import com.yorma.wms.service.api.DeliveryNoteService;

import io.zbus.rpc.bootstrap.ClientBootstrap;

public class DeliveryNoteZbusCasesTest {

	static DeliveryNoteService api;
	static DeliveryNoteAsnService asnapi;

	@SuppressWarnings("resource")
	@BeforeClass
	public static void before() {
		ClientBootstrap clientBootstrap = new ClientBootstrap();

		clientBootstrap.serviceName("WMS-RPC-TEST");
		clientBootstrap.serviceAddress("172.31.1.176:15555");
		clientBootstrap.serviceToken("wms_rpc_test_service");
		api = clientBootstrap.createProxy(DeliveryNoteService.class);
		asnapi = clientBootstrap.createProxy(DeliveryNoteAsnService.class);
	}

	@Ignore
	public void add() {
		DeliveryNote dn = new DeliveryNote();
		dn.setCreateBy("s");
		dn.setCreateDate(new Date());

		dn.setOwnerCode("23");
		dn.setStatus(1);
		dn.setDeliveryType("301");

		dn.setPacks("");
		dn.setBillNo(null);
		

		 ResponseMessage msg=api.save(null);
		 Assert.assertFalse(msg.getSuccess());
		 Assert.assertEquals("10004", msg.getStatus());;

		 msg=api.save(dn);
		 Assert.assertFalse(msg.getSuccess());
		 Assert.assertEquals("10003", msg.getStatus());
		 
		 dn.setWarehouse("W0001");
		 msg = api.save(dn);
		 Assert.assertTrue(msg.getSuccess());
		 Assert.assertNotNull(msg.getData());

		System.out.println("getSuccess:" + msg.getSuccess());
		System.out.println("getStatus:" + msg.getStatus());
		System.out.println("getData:" + msg.getData());
	}

	@Test
	public void getTest() {
		Map<String, Object> map = new HashMap<>();
//		map.put("deliveryNote.delivery_Note_No=", "0531-1");
		 map.put("deliveryNote.owner_Code=", "851");
//		 map.put("deliveryNote.CONSIGNEE_CODE=", "531");
//		 map.put("deliveryNote.PAYMENT_CODE=", "400");
//		 map.put("", "");
//		 map.put("", "");

		ResponseMessage msg = api.getDeliveryNote(map,1,10);
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());

		// map.put("deliveryType=", "301");
		// msg=api.getDeliveryNote(map);
		// Assert.assertFalse(msg.getSuccess());
		// Assert.assertEquals("10003", msg.getStatus());

		System.out.println("getSuccess:" + msg.getSuccess());
		System.out.println("getStatus:" + msg.getStatus());
		ResponseData<DeliveryNote> responseData = (ResponseData<DeliveryNote>)msg.getData();
		System.out.println("Data:"+responseData.getList().size());
		
	}

	@Ignore
	public void ById() {
		ResponseMessage msg = api.find(23L);
		// Assert.assertFalse(msg.getSuccess());
		// Assert.assertEquals("10004", msg.getStatus());
		//
		// msg=api.find(3L);
		// Assert.assertTrue(msg.getSuccess());
		// Assert.assertNotNull(msg.getData());

		System.out.println("getSuccess:" + msg.getSuccess());
		System.out.println("getStatus:" + msg.getStatus());
		System.out.println("getData:" + msg.getData());
	}

	@Ignore
	public void update() {
		DeliveryNote dn = new DeliveryNote();
		dn.setCreateBy("s");
		dn.setCreateDate(new Date());
		dn.setDeliveryNoteNo("101-105");
		dn.setOwnerCode("23");
		dn.setStatus(1);
		dn.setDeliveryType("301");

		dn.setPacks("");
		dn.setBillNo(null);
		dn.setId(3L);

		ResponseMessage msg = api.save(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());

		msg = api.save(dn);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10003", msg.getStatus());

		dn.setWarehouse("W0001");
		msg = api.save(dn);
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());

		// System.out.println("getSuccess:"+msg.getSuccess());
		// System.out.println("getStatus:"+msg.getStatus());
		// System.out.println("getData:"+msg.getData());
	}

	@Ignore
	public void removeById() {
		ResponseMessage msg = api.removeById(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());

		msg = api.removeById(2L);
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());

		// System.out.println("getSuccess:"+msg.getSuccess());
		// System.out.println("getStatus:"+msg.getStatus());
		// System.out.println("getData:"+msg.getData());
	}

	@Ignore
	public void upStatus() {
		ResponseMessage msg = api.updateStatus("00005", 30);
		System.out.println("getSuccess:" + msg.getSuccess());
		Assert.assertTrue(msg.getSuccess());
		System.out.println("getStatus:" + msg.getStatus());
		System.out.println("getData:" + msg.getData());
	}

	@Ignore
	public void delete() {
		ResponseMessage msg = api.delete("CK201710120027");
		System.out.println("getSuccess:" + msg.getSuccess());
//		Assert.asstTrue(msg.getSuccess());
		System.out.println("getStatus:" + msg.getStatus());
		System.out.println("getData:" + msg.getData());
	}
	/**
	 * 保存出库单及明细
	 */
	@Ignore
	public void saveDeliveryNoteAndAsn(){
		DeliveryNote deliveryNote = this.getdeliveryNoteByid(770L);
//		deliveryNote.setId(null);
		
		List<DeliveryNoteAsn> asns = new ArrayList<>();
		DeliveryNoteAsn asn = this.getdeliveryNoteAsnByid(740L);
//		asn.setId(null);
		asns.add(asn);
		ResponseMessage msg = api.saveDeliveryNoteAndAsn(deliveryNote, asns);
		System.out.println("getSuccess:" + msg.getSuccess());
//		Assert.asstTrue(msg.getSuccess());
		System.out.println("getStatus:" + msg.getStatus());
		System.out.println("getData:" + msg.getData());
		
		
	}
	
	
	
	public DeliveryNote getdeliveryNoteByid(Long id){
		return (DeliveryNote)api.find(id).getData();
	}
	public DeliveryNoteAsn getdeliveryNoteAsnByid(Long id){
		return (DeliveryNoteAsn)asnapi.find(id).getData();
	}
	
	
	
}
