package com.yorma.wms.consumer.zbus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.yorma.common.entity.dto.Parameter;
import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.PurchaseDetails;
import com.yorma.wms.entity.PurchaseOrder;
import com.yorma.wms.service.api.PurchaseDetailsService;
import com.yorma.wms.service.api.PurchaseOrderService;

import io.zbus.rpc.bootstrap.ClientBootstrap;

public class PurchaseOrderZbusCasesTest {

	static PurchaseOrderService api;
	static PurchaseDetailsService detailsService;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void bofore(){
		ClientBootstrap clientBootstrap = new ClientBootstrap();

		clientBootstrap.serviceName("WMS-RPC-TEST");
		clientBootstrap.serviceAddress("172.31.1.176:15555");
		clientBootstrap.serviceToken("wms_rpc_test_service");
		api=clientBootstrap.createProxy(PurchaseOrderService.class);
		detailsService= clientBootstrap.createProxy(PurchaseDetailsService.class);
	}
	
	@Ignore
	public void testadd() {
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setOwnerCode("847");
		purchaseOrder.setCreateBy("test");
		purchaseOrder.setCreateDate(new Date());
		purchaseOrder.setIsAudit(false);
		purchaseOrder.setIsReceiving(false);
		purchaseOrder.setOrderType("WS");
		
		ResponseMessage message=api.save(purchaseOrder);
		Assert.assertTrue(message.getSuccess());
		Assert.assertNotNull(message.getData());
	}
	
	@Ignore
	public void testGet(){
		List<Parameter> parameters = new ArrayList<>();
		Parameter parameter = new Parameter();
		parameter.setField("PurchaseOrder.po");
		parameter.setValue("P201711140006");
		parameters.add(parameter);

		
		ResponseMessage message=api.searchParameter(parameters);
		Assert.assertTrue(message.getSuccess());
		Assert.assertNotNull(message.getData());
	}
	@Ignore
	public void testDelete(){
		ResponseMessage message=api.removePurchaseOrderByOrderPo("201711140005");
		Assert.assertTrue(message.getSuccess());
		Assert.assertNotNull(message.getData());
	}
	
	@Ignore
	public void testGetPurchaseOrderAsReceiving(){
		ResponseMessage message = api.getPurchaseOrderAsReceivingByPo("P201711140007");
		Assert.assertTrue(message.getSuccess());
		Assert.assertNotNull(message.getData());
	}
	
	@Test
	public void savePurchaseOrderAndDetails(){
	
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setPo("CS201802270005");
		purchaseOrder.setCustomsCode("CS001");
		purchaseOrder.setOwnerCode("855");
		purchaseOrder.setCreateBy("subiao");
		purchaseOrder.setOrderType("WS");
		purchaseOrder.setId(null);
		List<PurchaseDetails> purchaseDetailss = this.getPurchaseDetails("P201802270004");
		for (PurchaseDetails purchaseDetails : purchaseDetailss) {
			purchaseDetails.setOrderPo("CS201802270005");
			purchaseDetails.setId(null);
		}
		ResponseMessage responseMessage = api.savePurchaseOrderAndDetails(purchaseOrder, purchaseDetailss);
//		Assert.assertTrue(responseMessage.getSuccess());
		Assert.assertFalse(responseMessage.getSuccess());
		System.out.println("msg:"+responseMessage.getMsg());
	}

	@SuppressWarnings("unchecked")
	public List<PurchaseDetails> getPurchaseDetails(String po){
		ResponseMessage responseMessage = detailsService.getPurchaseDetailsByOrderPo(po);
		return (List<PurchaseDetails>)responseMessage.getData();
	}
	
}
