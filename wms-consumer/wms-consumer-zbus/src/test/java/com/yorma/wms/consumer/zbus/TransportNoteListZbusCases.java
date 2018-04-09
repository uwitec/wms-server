package com.yorma.wms.consumer.zbus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.dto.DeliveryPackingListDto;
import com.yorma.wms.entity.vo.TransportNoteListVO;
import com.yorma.wms.service.api.TransportNoteListService;

import io.zbus.rpc.bootstrap.ClientBootstrap;

public class TransportNoteListZbusCases {

	static TransportNoteListService api;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void before(){
		ClientBootstrap clientBootstrap=new ClientBootstrap();
		clientBootstrap.serviceName("WMS-RPC-TEST");
		clientBootstrap.serviceAddress("172.31.1.176:15555");
		clientBootstrap.serviceToken("wms_rpc_test_service");
		api=clientBootstrap.createProxy(TransportNoteListService.class);
	}
	
	@Ignore
	public void add() {
		List<DeliveryPackingListDto> deliveryPackingListDtos=new ArrayList<>();
		DeliveryPackingListDto deliveryPackingListDto=new DeliveryPackingListDto();
		deliveryPackingListDto.setAnsUuid("bde9e404-398b-4b6b-9fe4-17f3b1366fac");
		deliveryPackingListDto.setCreateBy("s");
		deliveryPackingListDto.setCreateDate(new Date());
		deliveryPackingListDto.setDeliveryNoteNo("00001");
		deliveryPackingListDto.setDeliveryQty(50);
		deliveryPackingListDto.setStockUuid("2047d6da-6c4d-4b96-a25b-dfb1abe87c77");
		deliveryPackingListDto.setGoodsUuid("7a64aec0-bfd6-42b6-a4f9-c165d1ee6d68");
		deliveryPackingListDto.setPn("101");
		deliveryPackingListDto.setLocation("L0001");
		deliveryPackingListDto.setStorage("S0001");
		deliveryPackingListDto.setWarehouse("W0001");
		deliveryPackingListDto.setPackagingKey("101");
		deliveryPackingListDto.setPackagingNo("05310102");
		deliveryPackingListDto.setUuid("4d5a9c25-a4e9-4ec0-9e3c-5d127c8b0bae");
		
		deliveryPackingListDto.setSku(null);
		deliveryPackingListDto.setItem("");
		
		deliveryPackingListDto.setDepartCode("23");
		deliveryPackingListDto.setDepartName("广运隆");
		deliveryPackingListDtos.add(deliveryPackingListDto);
		ResponseMessage message=api.saveTransportNoteList(deliveryPackingListDtos, "S201711030003");
	
		Assert.assertTrue(message.getSuccess());
		Assert.assertNotNull(message.getData());
		
		
	}
	
	@Ignore
	public void getTransportNoteListDtoByTransportNoteNo(){
		ResponseMessage message=api.getTransportNoteListDtoByTransportNoteNo("S201711030003");
		Assert.assertTrue(message.getSuccess());
		Assert.assertNotNull(message.getData());
	}
	
	@Ignore
	public void removeTransportNoteListByUUID(){
		ResponseMessage message=api.removeTransportNoteListByUUID("9ee801df-e9e5-41e7-908a-145f7dae53b1");
		Assert.assertTrue(message.getSuccess());
		Assert.assertNotNull(message.getData());
	}
	
	@Test
	public void ListByTransportNoteNo(){
		ResponseMessage message=api.ListByTransportNoteNo("D201803150006");
		Assert.assertTrue(message.getSuccess());
		Assert.assertNotNull(message.getData());
		System.out.println((List<TransportNoteListVO>)message.getData());
	}
	

}
