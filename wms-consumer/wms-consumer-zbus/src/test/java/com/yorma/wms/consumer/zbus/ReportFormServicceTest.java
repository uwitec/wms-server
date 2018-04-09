package com.yorma.wms.consumer.zbus;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.dto.MapParameters;
import com.yorma.wms.service.api.ReportFormServicce;

import io.zbus.rpc.bootstrap.ClientBootstrap;

public class ReportFormServicceTest {

	static ReportFormServicce reportFormServicce;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void before(){
		ClientBootstrap clientBootstrap=new ClientBootstrap();
		clientBootstrap.serviceName("WMS-RPC-TEST");
		clientBootstrap.serviceAddress("172.31.1.176:15555");
		clientBootstrap.serviceToken("wms_rpc_test_service");
		reportFormServicce=clientBootstrap.createProxy(ReportFormServicce.class);
	}
	
	
	@Ignore
	public void listReceivingNoteAsn(){
		MapParameters parameters = new MapParameters(1, 0, null);
		ResponseMessage responseMessage = reportFormServicce.listReceivingNoteAsn(parameters);
		Assert.assertFalse(responseMessage.getSuccess());
		Assert.assertEquals("10012", responseMessage.getStatus());
		parameters = new MapParameters(1, 10, null);
		responseMessage = reportFormServicce.listReceivingNoteAsn(parameters);
		Assert.assertTrue(responseMessage.getSuccess());
		Assert.assertNotNull(responseMessage.getData());
	}
	
	@Ignore
	public void listDeliveryNoteAsn(){
		MapParameters parameters = new MapParameters(1, 0, null);
		ResponseMessage responseMessage = reportFormServicce.listDeliveryNoteAsn(parameters);
		Assert.assertFalse(responseMessage.getSuccess());
		Assert.assertEquals("10012", responseMessage.getStatus());
		parameters = new MapParameters(1, 10, null);
		responseMessage = reportFormServicce.listDeliveryNoteAsn(parameters);
		Assert.assertTrue(responseMessage.getSuccess());
		Assert.assertNotNull(responseMessage.getData());
	}
	
	@Test
	public void listDeliveryPackingList(){
		Map<String, Object> map = new HashMap<>();
		map.put("deliveryPackingList.mu =", "");
		map.put("deliveryPackingList.pn =", "");
		MapParameters parameters = new MapParameters(1, 20, map);
		ResponseMessage responseMessage = reportFormServicce.listDeliveryPackingList(parameters);
		Assert.assertTrue(responseMessage.getSuccess());
		Assert.assertNotNull(responseMessage.getData());
		parameters = new MapParameters(0, 0, null);
		responseMessage = reportFormServicce.listDeliveryPackingList(parameters);
		Assert.assertTrue(responseMessage.getSuccess());
		Assert.assertNotNull(responseMessage.getData());
	}
	
	@Ignore
	public void listInAndOutOfStock(){
		MapParameters parameters = new MapParameters(0, 0, null);
		ResponseMessage responseMessage = reportFormServicce.listInAndOutOfStock(parameters);
		Assert.assertFalse(responseMessage.getSuccess());
		Assert.assertEquals("10012", responseMessage.getStatus());
		parameters = new MapParameters(0, 0, null);
		responseMessage = reportFormServicce.listInAndOutOfStock(parameters);
		Assert.assertTrue(responseMessage.getSuccess());
		Assert.assertNotNull(responseMessage.getData());
	}
	

}
