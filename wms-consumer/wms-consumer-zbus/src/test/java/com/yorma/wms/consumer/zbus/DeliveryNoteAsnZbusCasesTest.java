package com.yorma.wms.consumer.zbus;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.DeliveryNoteAsn;
import com.yorma.wms.entity.dto.DeliveryNoteAsnDto;
import com.yorma.wms.service.api.DeliveryNoteAsnService;

import io.zbus.rpc.bootstrap.ClientBootstrap;

public class DeliveryNoteAsnZbusCasesTest {

	static DeliveryNoteAsnService api;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void before(){
		ClientBootstrap clientBootstrap = new ClientBootstrap();

		clientBootstrap.serviceName("WMS-RPC-TEST");
		clientBootstrap.serviceAddress("172.31.1.176:15555");
		clientBootstrap.serviceToken("wms_rpc_test_service");
		api=clientBootstrap.createProxy(DeliveryNoteAsnService.class);
	}
	
	/**
	 * 根据盘点单号查询盘点单明细
	 */
	@Ignore
	public void add() {
		DeliveryNoteAsn dna=new DeliveryNoteAsn();
		dna.setCreateBy("s");
		dna.setCreateDate(new Date());
		dna.setDeliveryNoteNo("RK201709155");
		dna.setGoodsUuid("9db96915-f9cd-4b6b-92a3-2c20d21e7273");
		dna.setMu("101");
		dna.setPn("159159");
		dna.setExpectQty(50);
		dna.setBatchAttribute(null);
		dna.setCurrency("");
		
		ResponseMessage msg=api.save(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());
		
		msg=api.save(dna);
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());
		
//		System.out.println("getSuccess:"+msg.getSuccess());
//		System.out.println("getStatus:"+msg.getStatus());
//		System.out.println("getData:"+msg.getData());
	}

	/**
	 * 根据收货单号查询出货明细Dto
	 */
	@SuppressWarnings("unchecked")
	@Ignore
	public void get(){
		
		ResponseMessage msg=api.getDeliveryNoteAsnByNoteNo(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10012", msg.getStatus());
		msg=api.getDeliveryNoteAsnByNoteNo("CK201710110001");
//		Assert.assertTrue(msg.getSuccess());
//		Assert.assertNotNull(msg.getData());
		
		System.out.println("getSuccess:"+msg.getSuccess());
		System.out.println("getStatus:"+msg.getStatus());
		System.out.println("getData:"+msg.getData());
		for (DeliveryNoteAsnDto ddto : (List<DeliveryNoteAsnDto>)msg.getData()) {
			System.out.println(ddto.getDeliveryQtySum());
		}
	}
	
	/**
	 * 根据主键(Id)查询对象
	 */
	@Ignore
	public void ById(){
		ResponseMessage msg=api.find(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());
		
		msg=api.find(3L);
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());
		
//		System.out.println("getSuccess:"+msg.getSuccess());
//		System.out.println("getStatus:"+msg.getStatus());
//		System.out.println("getData:"+msg.getData());
	}
	
	/**
	 * 修改操作
	 */
	@Ignore
	public void update(){
		DeliveryNoteAsn dna=new DeliveryNoteAsn();
		dna.setUuid("7d7f8242-ee9c-43b7-899f-6deecbfa13c9");
		dna.setCreateBy("s");
		dna.setCreateDate(new Date());
		dna.setDeliveryNoteNo("101-105");
		dna.setGoodsUuid("7a64aec0-bfd6-42b6-a4f9-c165d1ee6d68");
		dna.setMu("102");
		dna.setPn("102");
		dna.setExpectQty(50);
		dna.setId(3L);
		dna.setBatchAttribute(null);
		dna.setCurrency("");
		
		ResponseMessage msg=api.save(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());
		
		msg=api.save(dna);
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());
		
//		System.out.println("getSuccess:"+msg.getSuccess());
//		System.out.println("getStatus:"+msg.getStatus());
//		System.out.println("getData:"+msg.getData());
	}
	
	/**
	 * 保存操作 
	 */
	@Ignore
	public void delete(){
		ResponseMessage msg=api.removeById(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());
		
		msg=api.removeById(2L);
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());
		
//		System.out.println("getSuccess:"+msg.getSuccess());
//		System.out.println("getStatus:"+msg.getStatus());
//		System.out.println("getData:"+msg.getData());
	}
	
	/**
	 * 根据明细UUID查询明细信息
	 */
	@Ignore
	public void getByUuid(){
		ResponseMessage msg=api.getDeliveryNoteAsnByUUID("");
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10012", msg.getStatus());
		msg=api.getDeliveryNoteAsnByUUID("36a3596a-e984-43d4-9082-baf5ff375092");
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());
		
		System.out.println("getSuccess:"+msg.getSuccess());
		System.out.println("getStatus:"+msg.getStatus());
		System.out.println("getData:"+msg.getData());
	}
	
	/**
	 * 根据单号查询相同商品合并后的明细（暂定）
	 */
	@Ignore
	public void getDeliveryNoteAsnMergeByDeliveryNoteNo(){
		
		ResponseMessage message=api.getDeliveryNoteAsnMergeByDeliveryNoteNo("");
		Assert.assertFalse(message.getSuccess());
		Assert.assertEquals("10012", message.getStatus());
		message=api.getDeliveryNoteAsnMergeByDeliveryNoteNo("CK201711060001");
		
		Assert.assertTrue(message.getSuccess());
		Assert.assertNotNull(message.getData());
		
	}
	
	/**
	 * 根据明细uuid合并明细信息
	 */
	@Ignore
	public void getDeliveryNoteAsnMergeByAsnUUID(){
		List<String> asnUuids = new ArrayList<>();
		asnUuids.add("a3f9b1f1-d2c8-42cc-a6b2-c4c7ade19c22");
		ResponseMessage message=api.getDeliveryNoteAsnMergeByAsnUUID(asnUuids);
		Assert.assertTrue(message.getSuccess());
		
		System.out.println("getSuccess:"+message.getSuccess());
		System.out.println("getStatus:"+message.getStatus());
		System.out.println("getData:"+message.getData());
	}
	
	/**
	 * 判断单条或多条明细是否能分配和多条明细是否符合合并条件
	 */
	@Test
	public void judgeDeliveryNoteAsn(){
		List<String> asnUuids = new ArrayList<>();
		asnUuids.add("25f88a36-ac64-42df-aac0-51c9e0acc6f5");
		asnUuids.add("45dcdf58-22a8-417a-87d7-48efc884ff7b");
		asnUuids.add("7da4537d-5354-49ea-a338-6b494e2d593d");
		ResponseMessage message=api.judgeDeliveryNoteAsn(asnUuids);
		Assert.assertTrue(message.getSuccess());
		
		System.out.println("getSuccess:"+message.getSuccess());
		System.out.println("getStatus:"+message.getStatus());
		System.out.println("getData:"+message.getData());
	}
	
}
