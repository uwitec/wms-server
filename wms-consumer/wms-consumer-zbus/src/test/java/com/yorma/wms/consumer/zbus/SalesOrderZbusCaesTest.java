package com.yorma.wms.consumer.zbus;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.PurchaseDetails;
import com.yorma.wms.entity.SalesDetails;
import com.yorma.wms.entity.SalesOrder;
import com.yorma.wms.service.api.SalesDetailsService;
import com.yorma.wms.service.api.SalesOrderService;

import io.zbus.rpc.bootstrap.ClientBootstrap;

public class SalesOrderZbusCaesTest {

	static SalesOrderService api;
	static SalesDetailsService detailsService;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void before(){
		ClientBootstrap clientBootstrap = new ClientBootstrap();

		clientBootstrap.serviceName("WMS-RPC-TEST");
		clientBootstrap.serviceAddress("172.31.1.176:15555");
		clientBootstrap.serviceToken("wms_rpc_test_service");
		api=clientBootstrap.createProxy(SalesOrderService.class);
		detailsService = clientBootstrap.createProxy(SalesDetailsService.class);
	}
	@Ignore
	public void testadd() {
		SalesOrder order =new SalesOrder();
		order.setOwnerCode("847");
		order.setCreateBy("test");
		order.setCreateDate(new Date());
		order.setIsAudit(false);
		order.setIsDelivery(false);
		
		ResponseMessage message=api.save(order);
		Assert.assertTrue(message.getSuccess());
		Assert.assertNotNull(message.getData());
	}
	
	@Ignore
	public void testGet(){
		Map<String, Object> parameters = new HashMap<>();
		
		ResponseMessage message = api.getSalesOrderDto(parameters);
		Assert.assertTrue(message.getSuccess());
		Assert.assertNotNull(message.getData());
	}
	
	@Ignore
	public void testDelete(){
		ResponseMessage message = api.removeSalesOrderBySO("");
		Assert.assertTrue(message.getSuccess());
		Assert.assertNotNull(message.getData());
	}
	
	@Ignore
	public void testUpdate(){
		ResponseMessage message = api.updateIsAuditBySo("S201711150003", false);
		Assert.assertTrue(message.getSuccess());
		Assert.assertNotNull(message.getData());
	}
	
	@Ignore
	public void testGetSalesOrderAsDelivery(){
		ResponseMessage message = api.getSalesOrderAsDeliveryBySo("S201711150003");
		Assert.assertTrue(message.getSuccess());
		Assert.assertNotNull(message.getData());
	}
	
	@Test
	public void saveSalesOrderAndDetails(){
		SalesOrder salesOrder = new SalesOrder();
		salesOrder.setSo("CS201802270005");
		salesOrder.setCustomsCode("CS001");
		salesOrder.setOwnerCode("855");
		salesOrder.setConsigneeCode("851");
		salesOrder.setCreateBy("subiao");
		salesOrder.setOrderType("WS");
		salesOrder.setId(null);
		List<SalesDetails> salesDetailss = this.getSalseDetails("S201802270007");
		for (SalesDetails salesDetails : salesDetailss) {
			salesDetails.setOrderSo("CS201802270005");
			salesDetails.setId(null);
		}
		ResponseMessage responseMessage =api.saveSalesOrderAndDetails(salesOrder, salesDetailss);
		Assert.assertTrue(responseMessage.getSuccess());
//		Assert.assertFalse(responseMessage.getSuccess());
		System.out.println("msg:"+responseMessage.getMsg());
	}
	
	public List<SalesDetails> getSalseDetails(String so){
		ResponseMessage responseMessage = detailsService.getSalesDetailsByOrderSO(so);
		return (List<SalesDetails>)responseMessage.getData();
	}
	
}
