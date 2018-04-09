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
import com.yorma.wms.entity.Stock;
import com.yorma.wms.entity.dto.StockDto;
import com.yorma.wms.entity.vo.StockVO;
import com.yorma.wms.service.api.StockService;

import io.zbus.rpc.bootstrap.ClientBootstrap;

public class StockZbusCasesTest {

	static StockService api;

	@SuppressWarnings("resource")
	@BeforeClass
	public static void before() throws Exception {
		ClientBootstrap clientBootstrap = new ClientBootstrap();

		clientBootstrap.serviceName("WMS-RPC-TEST");
		clientBootstrap.serviceAddress("172.31.1.176:15555");
		clientBootstrap.serviceToken("wms_rpc_test_service");
		api = clientBootstrap.createProxy(StockService.class);
	}

	@Ignore
	public void testadd() {
		Stock st = new Stock();

		st.setAllocatedStock(0);
		st.setAmounts(new BigDecimal(10));
		st.setBarcode("05310034556");
		st.setCurrency("元");
		st.setEmsNo("455610030531");
		st.setEntryDate(new Date());
		st.setEntryType("快进快出");

		st.setGoodsUuid("9459c695-fab2-47e3-a88e-3046a7996fe4");
		st.setInStock(100);
		st.setIsQualifed(false);
		st.setItem("1");
		st.setLocation("A1002");
		st.setStorage("A102");
		st.setWarehouse("A");
		st.setMu("2");
		st.setOwnerCode("23");
		st.setPlUuid("2b744d84-b98f-492a-9863-574dde50d395");
		st.setPackagingNo("203");
		st.setPreAllocationStock(0);
		st.setPn("101");
		st.setEntryQty(100);

		ResponseMessage 
//		msg = api.save(null);
//		Assert.assertFalse(msg.getSuccess());
//		Assert.assertEquals("10004", msg.getStatus());

		msg = api.save(st);

		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());
	}

	@Ignore
	public void testGet() {
		Map<String, Object> ma = new HashMap<String, Object>();
		 ma.put("barcode", null);
		 ma.put("currency", "元");
		 ma.put("pn=", "");
		 ma.put("owner_code=", 23);
		ResponseMessage msg = api.getStock(ma,1,10);
		 Assert.assertFalse(msg.getSuccess());
		 Assert.assertEquals("10003", msg.getStatus());
		
		 Map<String, Object> map=new HashMap<String, Object>();
//		 map.put("barcode", null);
//		 map.put("currency", "元");
//		 map.put("pn", "");
		// map.put("item", 0);
		 msg=api.getStock(map,1,10);
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());
		ResponseData<Stock> responseData = (ResponseData<Stock>)msg.getData();
		System.out.println("Data:"+responseData.getList().size());
	}
	
	@Ignore
	public void getStockQtyTest(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(" goods_uuid=", "bb484861-1ddf-4d60-aadf-a29a273e9fc1");
		ResponseMessage msg = api.getStockQty(map);
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());
		StockVO stockVO = (StockVO)msg.getData();
		System.out.println("总在库量"+stockVO.getSumInStock());
		System.out.println("总预分配量"+stockVO.getSumPreAllocationStock());
		System.out.println("总已分配量"+stockVO.getSumAllocationStock());
		System.out.println("总包装数"+stockVO.getSumPackagingQty());
		System.out.println("可用库存量"+stockVO.getSumQty());
	}
	
	@SuppressWarnings("unchecked")
	@Ignore
	public void getStock() {
		//查询可分配库存
		ResponseMessage msg = api.getStockByGoodsUUID("25b4c62b-195b-4d09-8872-c51e973f7b37","82bc569c-c827-49a8-bb85-e9d8ab48febd","W0001");
		List<StockDto> stockDtos=(List<StockDto>)msg.getData();
		for (StockDto s : stockDtos) {
			System.out.println(s.getUuid());
		}
		System.out.println(stockDtos.size());
		System.out.println("success:"+msg.getSuccess());
		System.out.println("data:"+msg.getData());
		System.out.println("status:"+msg.getStatus());
		System.out.println("msg:"+msg.getMsg());

		
//		//自动计算分配库存
//		msg=api.getSelfMotionStock(null, null,null);
//		Assert.assertFalse(msg.getSuccess());
//		Assert.assertEquals("10012", msg.getStatus());
//		
//		ResponseMessage	msg=api.getSelfMotionStock("3da2191c-9b2b-4c4c-bd55-264086b5974e", "fb38d38b-2e4b-463a-b863-fae4a5d374a1",false,"wooo91");
//		Assert.assertTrue(msg.getSuccess());
//		Assert.assertNotNull(msg.getData());
		
		
//		List<StockDto> lisd=(List<StockDto>) msg.getData();
//		for (StockDto s : lisd) {
//			System.out.println("s："+s.getId());
//			System.out.println("DeliveryQty："+s.getDeliveryQty());
//		}
//		System.out.println("Data:"+msg.getData());
	}

	@Ignore
	public void byId() {
		ResponseMessage msg = api.find(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());

		msg = api.find(3L);
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getMsg());

	}

	@Ignore
	public void update() {
		Stock st = new Stock();
		st.setAllocatedStock(50);
		st.setAmounts(new BigDecimal(10));
		st.setBarcode("05310034556");
		st.setCurrency("元");
		st.setEmsNo("455610030531");
		st.setEntryDate(new Date());
		st.setEntryType("快进快出");

		st.setGoodsUuid("9459c695-fab2-47e3-a88e-3046a7996fe4");
		st.setInStock(100);
		st.setIsQualifed(false);
		st.setItem("1");
		st.setLocation("A1002");
		st.setStorage("A102");
		st.setWarehouse("A");
		st.setMu("2");
		st.setOwnerCode("23");
		st.setPackagingNo("条码");
		// st.setPackagingGrossWeight(new BigDecimal(500));

		st.setPreAllocationStock(100);
		st.setPn("料号");
		st.setEntryQty(50);
		st.setWarehouse("W0001");
		st.setId(3L);
		st.setUuid("47163c22-c83b-4716-8607-df6c324c7879");

		ResponseMessage msg = api.save(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());

		msg = api.save(st);
		// System.out.println("getSuccess“"+msg.getSuccess());
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());

	}

	@Ignore
	public void delete() {
		ResponseMessage msg = api.removeById(null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());

		msg = api.removeById(2L);
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());
	}

	
	@Ignore
	public void upPR() {
		List<StockDto> lisd=new ArrayList<>();
		StockDto sd=new StockDto();
		sd.setUuid("a229986e-0b5f-42ee-9d70-83c38a46702d");
		sd.setDeliveryQty(2);
		sd.setGoodsUuid("353789f4-1232-4731-b449-93696bd172a2");
		sd.setPlCreateBy("s");
		
//		StockDto sd1=new StockDto();
//		sd1.setUuid("cbad4475-d5e0-4060-9ca5-45b73efb04a6");
//		sd1.setDeliveryQty(0);
//
//		sd1.setPlCreateBy("s");
		
		lisd.add(sd);
//		lisd.add(sd1);
		
		ResponseMessage 
//		msg=api.updatePreAllocationStock(null);
//		Assert.assertFalse(msg.getSuccess());
//		Assert.assertEquals("10004", msg.getStatus());
		
		msg=api.updatePreAllocationStock(lisd, null, true);
//		Assert.assertTrue(msg.getSuccess());
//		Assert.assertNotNull(msg.getData());
		System.out.println("getSuccess:"+msg.getSuccess());
		System.out.println("getData:"+msg.getData());
		System.out.println("getStatus:"+msg.getStatus());
		System.out.println("getMsg:"+msg.getMsg());
	}
	
	@Ignore
	public void upAL(){
		
		List<String> deliveryPackingListUuids=new ArrayList<>();
		deliveryPackingListUuids.add("7e43d3c9-2f37-444c-866a-426ccf87b40d");
		
//		responseMessage=api.updateallocatedStock(null);
//		Assert.assertFalse(responseMessage.getSuccess());
//		Assert.assertEquals("10012", responseMessage.getStatus());
		
		ResponseMessage responseMessage=api.updateAllocatedStock(deliveryPackingListUuids);
//		Assert.assertTrue(responseMessage.getSuccess());
//		Assert.assertNotNull(responseMessage.getData());
		System.out.println("success:"+responseMessage.getSuccess());
		System.out.println("data:"+responseMessage.getData());
		System.out.println("Status:"+responseMessage.getStatus());
		System.out.println("Msg:"+responseMessage.getMsg());
	}

	@Ignore
	public void upIn(){
		List<String> deliveryPackingListUuids=new ArrayList<>();
		deliveryPackingListUuids.add("7e43d3c9-2f37-444c-866a-426ccf87b40d");
		

//		responseMessage=api.updateallocatedStock(null);
//		Assert.assertFalse(responseMessage.getSuccess());
//		Assert.assertEquals("10012", responseMessage.getStatus());
		ResponseMessage responseMessage=api.updateInstock(deliveryPackingListUuids,"0");
//		Assert.assertTrue(responseMessage.getSuccess());
//		Assert.assertNotNull(responseMessage.getData());
		System.out.println("success:"+responseMessage.getSuccess());
		System.out.println("data:"+responseMessage.getData());
		System.out.println("Status:"+responseMessage.getStatus());
		System.out.println("Msg:"+responseMessage.getMsg());
	}
	
	@Ignore
	public void addBeatchBystock(){
		List<Stock> stockList=new ArrayList<>();
		
		Stock st = new Stock();
		st.setAllocatedStock(0);
		st.setAmounts(new BigDecimal(10));
		st.setBarcode("05310034556");
		st.setCurrency("元");
		st.setEmsNo("455610030531");
		st.setEntryDate(new Date());
		st.setEntryType("快进快出");

		st.setGoodsUuid("009520d5-a2fc-4c41-8a3e-9763356ad079");
		st.setInStock(100);
		st.setIsQualifed(true);
		st.setItem("1");
		st.setLocation("A10002");
		st.setStorage("A10002");
		st.setWarehouse("A10001");
		st.setMu("2");
		st.setOwnerCode("23");
		st.setPlUuid("5a5bb625-83a5-4b2a-9d9f-1ac9c566bba9");
		st.setPackagingNo("203");
		st.setPreAllocationStock(0);
		st.setPn("101");
		st.setEntryQty(100);
		Stock st1 = new Stock();
		st1.setAllocatedStock(0);
		st1.setAmounts(new BigDecimal(10));
		st1.setBarcode("05310034556");
		st1.setCurrency("元");
		st1.setEmsNo("455610030531");
		st1.setEntryDate(new Date());
		st1.setEntryType("快进快出");
		st1.setGoodsUuid("009520d5-a2fc-4c41-8a3e-9763356ad079");
		st1.setInStock(100);
		st1.setIsQualifed(true);
		st1.setItem("1");
		st1.setLocation("A10002");
		st1.setStorage("A10002");
		st1.setWarehouse("A10001");
		st1.setMu("2");
		st1.setOwnerCode("23");
		st1.setPlUuid("bfca046b-b6a6-486d-950d-18751ef56791");
		st1.setPackagingNo("203");
		st1.setPreAllocationStock(0);
		st1.setPn("101");
		st1.setEntryQty(100);
		stockList.add(st);
		stockList.add(st1);

		ResponseMessage msg=api.addBatchByStockAndUpdateStatus(stockList, "RK2017091410");
		Assert.assertTrue(msg.getSuccess());
		Assert.assertNotNull(msg.getData());
		
		msg=api.addBatchByStockAndUpdateStatus(null, "RK2017091410");
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10004", msg.getStatus());
		msg=api.addBatchByStockAndUpdateStatus(stockList, null);
		Assert.assertFalse(msg.getSuccess());
		Assert.assertEquals("10012", msg.getStatus());
		
//		System.out.println("success:"+msg.getSuccess());
//		System.out.println("data:"+msg.getData());
//		System.out.println("Status:"+msg.getStatus());
//		System.out.println("Msg:"+msg.getMsg());
	}
	
	@SuppressWarnings("unchecked")
	@Ignore
	public void getStockStatistics(){
		Map<String, Object> map=new HashMap<>();
		ResponseMessage message=api.getStockStatistics(map);
//		System.out.println("success:"+message.getSuccess());
//		System.out.println("data:"+message.getData());
//		System.out.println("Status:"+message.getStatus());
//		System.out.println("Msg:"+message.getMsg());
		for (StockDto stockDto : (List<StockDto>) message.getData()) {
			System.out.println("DepartName:"+stockDto.getDepartName()+"!!GoodsName:"+stockDto.getGoodsName());
		}
	}
	
	@Ignore
	public void createBatchByStockAndReceiving(){
		Stock stock=new Stock();
		stock.setOwnerCode("23");
		stock.setGoodsUuid("353789f4-1232-4731-b449-93696bd172a2");
		stock.setIsQualifed(true);
		stock.setPackagingKey("1-2-4/件-箱-托");
		stock.setPackagingQty(new BigDecimal(2.5));
		stock.setManufactureLotNo("1");
		stock.setReceivingLotNo("1");
		stock.setEmsNo("007");
		stock.setItem("001");
		stock.setWarehouse("W0001");
		stock.setStorage("S0001");
		stock.setLocation("L0001");
		stock.setMu("011");
		stock.setAmounts(new BigDecimal(3.0));
		stock.setCurrency("502");
		stock.setEntryQty(10);
		stock.setPreAllocationStock(0);
		stock.setAllocatedStock(0);
		stock.setExpirationDate(new Date());
		List<Stock> params=new ArrayList<>();
		params.add(stock);
		
		ResponseMessage message=api.createBatchByStockAndReceiving(params);
		System.out.println("success:"+message.getSuccess());
		System.out.println("data:"+message.getData());
		System.out.println("Status:"+message.getStatus());
		System.out.println("Msg:"+message.getMsg());
	}
	
	/**
	 * 移库操作
	 */
	@Ignore
	public void stockTransfer(){
		
		ResponseMessage responseMessage = api.stockTransfer("T201712180024");
		
		System.out.println("success:"+responseMessage.getSuccess());
		System.out.println("data:"+responseMessage.getData());
		System.out.println("Status:"+responseMessage.getStatus());
		System.out.println("Msg:"+responseMessage.getMsg());
	}
	
	@Ignore
	public void listStockVO(){
		Map<String, Object> params = new HashMap<>();
		params.put("goods_uuid = ", "bb484861-1ddf-4d60-aadf-a29a273e9fc1");
		params.put("in_stock <> ", 0);
		ResponseMessage responseMessage = api.listStockVO(params);
		System.out.println("Data:"+responseMessage.getData());
		for (StockVO stockVO : (List<StockVO>)responseMessage.getData()) {
			System.out.println("getPackQtyCount:"+stockVO.getPackQtyCount());
			System.out.println("getInStockCount:"+stockVO.getInStockCount());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
	}
	
	@Test
	public void getStockStatisticsa(){
		Map<String, Object> params = new HashMap<>();
		params.put("stock.pn =", "6996");
		ResponseMessage responseMessage = api.getStockStatistics(params, 1, 30);
		Assert.assertTrue(responseMessage.getSuccess());
		Assert.assertNotNull(responseMessage.getData());
		System.out.println("success:"+responseMessage.getSuccess());
		ResponseData<?> responseData = (ResponseData<?>) responseMessage.getData();
		System.out.println("counts:"+responseData.getTotalRow());
		System.out.println("page:"+responseData.getTotalPage());
	}
	
}
