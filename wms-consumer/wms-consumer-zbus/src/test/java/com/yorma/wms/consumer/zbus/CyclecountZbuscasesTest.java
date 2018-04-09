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
import com.yorma.wms.entity.Cyclecount;
import com.yorma.wms.entity.CyclecountList;
import com.yorma.wms.service.api.CyclecountService;

import io.zbus.rpc.bootstrap.ClientBootstrap;

public class CyclecountZbuscasesTest {

	static CyclecountService api;

	@SuppressWarnings("resource")
	@BeforeClass
	public static void before() {
		ClientBootstrap clientBootstrap = new ClientBootstrap();

		clientBootstrap.serviceName("WMS-RPC-TEST");
		clientBootstrap.serviceAddress("172.31.1.176:15555");
		clientBootstrap.serviceToken("wms_rpc_test_service");
		api = clientBootstrap.createProxy(CyclecountService.class);
	}

	@Ignore
	public void testsave() {

		Cyclecount cyclecount = new Cyclecount();
		cyclecount.setCreateBy("s");
		cyclecount.setCyclecountName("s");
		cyclecount.setCyclecountTime(new Date());

		ResponseMessage responseMessage = api.save(cyclecount);
		// Assert.assertTrue(responseMessage.getSuccess());
		// Assert.assertNotNull(responseMessage.getData());

		System.out.println("getSuccess:" + responseMessage.getSuccess());
		System.out.println("getStatus:" + responseMessage.getStatus());
		System.out.println("getData:" + responseMessage.getData());
	}

	/**
	 * 根据条件盘点库存
	 * 
	 * @return List<盘点明细>
	 */
	@Ignore
	public List<CyclecountList> getCyclecountList() {
		Map<String, Object> parames = new HashMap<>();
		parames.put("warehouse=", "W0125");
		parames.put("pn=", "102");
		ResponseMessage responseMessage = api.getStockCyclecount(parames);

		Assert.assertTrue(responseMessage.getSuccess());
		Assert.assertNotNull(responseMessage.getData());
		List<CyclecountList> cyclecountLists = (List<CyclecountList>) responseMessage.getData();
		System.out.println("getCyclecountList:" + cyclecountLists);
		return cyclecountLists;

	}

	/**
	 * 保存盘点单及明细
	 */
	@Ignore
	public void saveCyclecount() {
		Cyclecount cyclecount = new Cyclecount();
		cyclecount.setCreateBy("s");
		cyclecount.setCyclecountName("s");
		cyclecount.setCyclecountTime(new Date());
		cyclecount.setWarehouse("W0125");
		cyclecount.setPn("102");
		cyclecount.setCyclecountNo("CS001");
		List<CyclecountList> cyclecountLists = this.getCyclecountList();
		for (CyclecountList cyclecountList : cyclecountLists) {
			cyclecountList.setCyclecountNo("CS001");

		}
		ResponseMessage responseMessage = api.saveCyclecount(cyclecount, cyclecountLists);
		Assert.assertTrue(responseMessage.getSuccess());
		Assert.assertNotNull(responseMessage.getData());

		System.out.println("Success:" + responseMessage.getSuccess());
		System.out.println("Status:" + responseMessage.getStatus());
		System.out.println("msg:" + responseMessage.getMsg());
	}

	/**
	 * 根据盘点单号调整库存
	 */
	@Ignore
	public void adjustmentStock() {
		ResponseMessage responseMessage = api.adjustmentStock("", "s");
		Assert.assertFalse(responseMessage.getSuccess());
		Assert.assertEquals("10012", responseMessage.getStatus());
		responseMessage = api.adjustmentStock("CS001", "s");
		Assert.assertTrue(responseMessage.getSuccess());

		System.out.println("Success:" + responseMessage.getSuccess());
		System.out.println("Status:" + responseMessage.getStatus());
		System.out.println("msg:" + responseMessage.getMsg());
	}

	/**
	 * 根据盘点单号查询盘点单表头信息
	 */
	@Ignore
	public void getCyclecount() {
		ResponseMessage responseMessage = api.getCyclecountBycyclecountNo("");
		Assert.assertFalse(responseMessage.getSuccess());
		Assert.assertEquals("10012", responseMessage.getStatus());
		responseMessage = api.getCyclecountBycyclecountNo("CS001");
		Assert.assertTrue(responseMessage.getSuccess());
		Assert.assertNotNull(responseMessage.getData());
		System.out.println("getData:" + responseMessage.getData());
	}

	/**
	 * 根据盘点单号删除盘点单表头信息
	 */
	@Ignore
	public void delete() {
		ResponseMessage responseMessage = api.removeCyclecountBycyclecountNo("");
		Assert.assertFalse(responseMessage.getSuccess());
		Assert.assertEquals("10012", responseMessage.getStatus());
		responseMessage = api.removeCyclecountBycyclecountNo("CS001");
		Assert.assertTrue(responseMessage.getSuccess());
	}

	/**
	 * 根据盘点单号查询盘点单表头信息
	 */
	@Ignore
	public void getBycyclecountNo() {
		ResponseMessage responseMessage = api.getCyclecountBycyclecountNo("");
		Assert.assertFalse(responseMessage.getSuccess());
		Assert.assertEquals("10012", responseMessage.getStatus());
		responseMessage = api.getCyclecountBycyclecountNo("CS001");
		Assert.assertTrue(responseMessage.getSuccess());
		Assert.assertNotNull(responseMessage.getData());
	}

	/**
	 * 根据条件盘点库存（区分盘点条件）
	 */
	@Ignore
	public void listCyclecountList() {
		Map<String, Object> parames = new HashMap<>();
		ResponseMessage responseMessage = api.listStockCyclecount(parames, "packingKey");
		Assert.assertTrue(responseMessage.getSuccess());
		Assert.assertNotNull(responseMessage.getData());
		
		responseMessage = api.listStockCyclecount(parames, "mu");
		Assert.assertTrue(responseMessage.getSuccess());
		Assert.assertNotNull(responseMessage.getData());
	}
	
	@Test
	public void listCyclecount(){
		Map<String, Object> parames = new HashMap<>();
		ResponseMessage responseMessage = api.getCyclecount(parames, 10, 1);
		Assert.assertTrue(responseMessage.getSuccess());
		Assert.assertNotNull(responseMessage.getData());
	}

}
