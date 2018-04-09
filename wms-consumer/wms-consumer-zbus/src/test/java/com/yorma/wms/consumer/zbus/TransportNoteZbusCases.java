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
import com.yorma.wms.entity.TransportNote;
import com.yorma.wms.entity.TransportNoteList;
import com.yorma.wms.entity.dto.TransportNoteDto;
import com.yorma.wms.service.api.TransportNoteListService;
import com.yorma.wms.service.api.TransportNoteService;

import io.zbus.rpc.bootstrap.ClientBootstrap;


public class TransportNoteZbusCases {

	static TransportNoteService api;
	static TransportNoteListService listapi;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void before(){
		ClientBootstrap clientBootstrap = new ClientBootstrap();
		clientBootstrap.serviceName("WMS-RPC-TEST");
		clientBootstrap.serviceAddress("172.31.1.176:15555");
		clientBootstrap.serviceToken("wms_rpc_test_service");
		api=clientBootstrap.createProxy(TransportNoteService.class);
		listapi=clientBootstrap.createProxy(TransportNoteListService.class);
	}
	
	@Ignore
	public void add() {
		TransportNote transportNote=new TransportNote();
		transportNote.setWarehouse("W0001");
		transportNote.setCreateBy("s");
		transportNote.setCreateDate(new Date());
		transportNote.setTransportCode("23");
		transportNote.setTruckNumber("鲁AK47DADADA");
		transportNote.setTruckType("jeep");
		transportNote.setStatus(10);
		
		ResponseMessage message=api.save(transportNote);
		Assert.assertTrue(message.getSuccess());
		Assert.assertNotNull(message.getData());
		
	}
	
	@Ignore
	public void get(){
		Map<String, Object> parameters=new HashMap<>();
		parameters.put("status=", 20);
		ResponseMessage message =api.getTransportNoteDto(parameters);
//		Assert.assertTrue(message.getSuccess());
//		Assert.assertNotNull(message.getData());
		System.out.println("data:"+message.getData());
		
	}
	
	@Ignore
	public void update(){
		TransportNote transportNote=new TransportNote();
		transportNote.setWarehouse("W0001");
		transportNote.setCreateBy("s");
		transportNote.setCreateDate(new Date());
		transportNote.setTransportCode("23");
		transportNote.setTruckNumber("鲁AK47DADADA");
		transportNote.setTruckType("jeep");
		transportNote.setStatus(10);
		
		transportNote.setId(1L);
		transportNote.setLastUpdateBy("s");
		transportNote.setLastUpdateDate(new Date());
		ResponseMessage message=api.save(transportNote);
		Assert.assertTrue(message.getSuccess());
		Assert.assertNotNull(message.getData());
	}
	
	@Ignore
	public void delete(){
		ResponseMessage message =api.removeTransportNoteByTransportNoteNo("S201711030001");
		Assert.assertTrue(message.getSuccess());
		Assert.assertNotNull(message.getData());
	}

	@Ignore
	public void getTransportNoteDtoByNoteNo(){
		ResponseMessage message=api.getTransportNoteDtoByTransportNoteNo("S201711060009");
		Assert.assertTrue(message.getSuccess());
		Assert.assertNotNull(message.getData());
	}
	
	@Test//分页
	public void getTransportNoteDto(){
		Map<String, Object> parameters = new HashMap<String, Object>();
		ResponseMessage message=api.getTransportNoteDto(parameters, 1, 10);
		Assert.assertTrue(message.getSuccess());
		Assert.assertNotNull(message.getData());
		ResponseData<TransportNoteDto> data = (ResponseData<TransportNoteDto>) message.getData(); 
		Assert.assertEquals(8, data.getTotalRow());
		Assert.assertEquals(1, data.getPageNumber());
		Assert.assertNotNull(data.getList());
	}
	
	/*
	 * 根据id获取配送运输表头及表体
	 */
	public TransportNote getTransportNoteById(Long id){
		return (TransportNote)api.find(id).getData();
	}
	public TransportNoteList getTransportNoteListById(Long id){
		return (TransportNoteList)listapi.find(id).getData();
	}
	
}
