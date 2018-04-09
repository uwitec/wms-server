package com.yorma.wms.consumer.zbus;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.PackInfomation;
import com.yorma.wms.entity.dto.PackInfomationDto;
import com.yorma.wms.service.api.PackInfomationService;

import io.zbus.rpc.bootstrap.ClientBootstrap;

public class PackInfomationZbusCasesTest {

	static PackInfomationService api;
	 
	@SuppressWarnings("resource")
	@BeforeClass
	public static void before(){
		ClientBootstrap clientBootstrap = new ClientBootstrap();

		clientBootstrap.serviceName("WMS-RPC-TEST");
		clientBootstrap.serviceAddress("10.10.10.171:15555");
		clientBootstrap.serviceToken("wms_rpc_test_service");
		api=clientBootstrap.createProxy(PackInfomationService.class);
	 }
	
	@SuppressWarnings("unchecked")
	@Ignore
	public void getByGoodsUUID(){
		ResponseMessage responseMessage=api.getPackInfomationByUUID("f9c8d33e-c572-4f76-aeee-0552d7217c4a");
		for (PackInfomationDto dto : (List<PackInfomationDto>)responseMessage.getData()) {
			System.out.println("UNIT1Name:"+dto.getUnit1Name());
			System.out.println("UNIT1:"+dto.getUnit1());
			System.out.println("UNIT2Name:"+dto.getUnit2Name());
			System.out.println("UNIT2:"+dto.getUnit2());
			System.out.println("UNIT3Name:"+dto.getUnit3Name());
			System.out.println("UNIT3:"+dto.getUnit3());
			System.out.println("UNIT4Name:"+dto.getUnit4Name());
			System.out.println("UNIT4:"+dto.getUnit4());
			System.out.println("UNIT5Name:"+dto.getUnit5Name());
			System.out.println("UNIT5:"+dto.getUnit5());
			System.out.println("departName:"+dto.getDepartName());
		}
//		Assert.assertTrue(responseMessage.getSuccess());
//		Assert.assertNotNull(responseMessage.getData());
//		responseMessage=api.getPackInfomationByUUID(null);
//		Assert.assertFalse(responseMessage.getSuccess());
//		Assert.assertEquals("10012", responseMessage.getStatus());
		
	}

	@Ignore
	public void getById(){
		ResponseMessage responseMessage=api.getPackInfomationById(21L);
		PackInfomationDto dto=(PackInfomationDto)responseMessage.getData();
		System.out.println("UNIT1Name:"+dto.getUnit1Name());
		System.out.println("UNIT1:"+dto.getUnit1());
		System.out.println("UNIT2Name:"+dto.getUnit2Name());
		System.out.println("UNIT2:"+dto.getUnit2());
		System.out.println("UNIT3Name:"+dto.getUnit3Name());
		System.out.println("UNIT3:"+dto.getUnit3());
		System.out.println("UNIT4Name:"+dto.getUnit4Name());
		System.out.println("UNIT4:"+dto.getUnit4());
		System.out.println("UNIT5Name:"+dto.getUnit5Name());
		System.out.println("UNIT5:"+dto.getUnit5());
		System.out.println("departName:"+dto.getDepartName());
	}
	
	@Test
	public void getPackInfomationByUUID(){
		ResponseMessage responseMessage=api.getPackInfomationIsDefaultByGoodsUUID("353789f4-1232-4731-b449-93696bd172a2");
		System.out.println("Dtat::"+responseMessage.getData());
	}
	@Test
	public void save(){
		PackInfomation packInfomation = new PackInfomation();
		packInfomation.setGoodsUuid("353789f4-1232-4731-b449-93696bd172a2");
		packInfomation.setUnit1("011");
		packInfomation.setQty1(10);
		packInfomation.setIsDefault(1);
		ResponseMessage responseMessage=api.save(packInfomation);
		System.out.println("Dtat::"+responseMessage.getData());
		
	}
	
}
