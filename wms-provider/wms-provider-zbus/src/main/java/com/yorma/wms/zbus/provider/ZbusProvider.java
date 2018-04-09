package com.yorma.wms.zbus.provider;


import com.yorma.wms.dao.memory.impl.DeliveryNoteAsnDaoImpl;
import com.yorma.wms.dao.memory.impl.DeliveryPackingListDaoImpl;
import com.yorma.wms.dao.memory.impl.ReceivingNoteAsnDaoImpl;
import com.yorma.wms.dao.memory.impl.StockDaoImpl;
import com.yorma.wms.service.impl.CyclecountListServiceImplZbus;
import com.yorma.wms.service.impl.CyclecountServiceImplZbus;
import com.yorma.wms.service.impl.DeliveryNoteAsnServiceImplZbus;
import com.yorma.wms.service.impl.DeliveryNoteServiceImplZbus;
import com.yorma.wms.service.impl.DeliveryPackingListServiceImplZbus;
import com.yorma.wms.service.impl.GoodsServiceImplZbus;
import com.yorma.wms.service.impl.LocationServiceImplZbus;
import com.yorma.wms.service.impl.PackInfomationServiceImplZbus;
import com.yorma.wms.service.impl.PurchaseDetailsServiceImplZbus;
import com.yorma.wms.service.impl.PurchaseOrderServiceImplZbus;
import com.yorma.wms.service.impl.ReceivingNoteAsnServiceImplZbus;
import com.yorma.wms.service.impl.ReceivingNoteServiceImplZbus;
import com.yorma.wms.service.impl.ReceivingPackingListServiceImplZbus;
import com.yorma.wms.service.impl.ReportFormServicceImpl;
import com.yorma.wms.service.impl.SalesDetailsServiceImplZbus;
import com.yorma.wms.service.impl.SalesOrderServiceImplZbus;
import com.yorma.wms.service.impl.StockServiceImplZbus;
import com.yorma.wms.service.impl.StockTransferListServiceImplZbus;
import com.yorma.wms.service.impl.StockTransferServiceImplZbus;
import com.yorma.wms.service.impl.StorageServiceImplZbus;
import com.yorma.wms.service.impl.TransportNoteListServiceImplZbus;
import com.yorma.wms.service.impl.TransportNoteServiceImplZbus;
import com.yorma.wms.service.impl.WarehouseServiceImplZbus;

import io.zbus.rpc.bootstrap.ServiceBootstrap;

public class ZbusProvider {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		/*
		 * RpcProcessor processor = new RpcProcessor(); processor.addModule(new
		 * HelloServiceImplZbus()); processor.addModule(new
		 * ProductServiceImplZbus());
		 * 
		 * ServiceConfig config = new ServiceConfig(); config.serverPort = 8080;
		 * config.messageProcessor = processor;
		 * 
		 * Service svc = new Service(config); svc.start();
		 */

		ServiceBootstrap serviceBootstrap=new ServiceBootstrap();
		
//		RpcProcessor processor = new RpcProcessor();
		// processor.addModule(new LocationServiceImpl(new LocationDaoImpl()));
		// processor.addModule(new RegionServiceImpl(new RegionDaoImpl()));

		
		serviceBootstrap.addModule(new GoodsServiceImplZbus());
		serviceBootstrap.addModule(new LocationServiceImplZbus());
		serviceBootstrap.addModule(new StorageServiceImplZbus());
		serviceBootstrap.addModule(new StockServiceImplZbus());
		serviceBootstrap.addModule(new WarehouseServiceImplZbus());
		serviceBootstrap.addModule(new ReceivingNoteServiceImplZbus());
		serviceBootstrap.addModule(new ReceivingNoteAsnServiceImplZbus());
		serviceBootstrap.addModule(new ReceivingPackingListServiceImplZbus());
		serviceBootstrap.addModule(new DeliveryNoteServiceImplZbus());
		serviceBootstrap.addModule(new DeliveryNoteServiceImplZbus());
		serviceBootstrap.addModule(new DeliveryNoteAsnServiceImplZbus());
		serviceBootstrap.addModule(new DeliveryPackingListServiceImplZbus());
		serviceBootstrap.addModule(new PackInfomationServiceImplZbus());
		serviceBootstrap.addModule(new TransportNoteServiceImplZbus());
		serviceBootstrap.addModule(new TransportNoteListServiceImplZbus());
		serviceBootstrap.addModule(new PurchaseOrderServiceImplZbus());
		serviceBootstrap.addModule(new PurchaseDetailsServiceImplZbus());
		serviceBootstrap.addModule(new SalesOrderServiceImplZbus());
		serviceBootstrap.addModule(new SalesDetailsServiceImplZbus());
		serviceBootstrap.addModule(new CyclecountServiceImplZbus());
		serviceBootstrap.addModule(new CyclecountListServiceImplZbus());
		serviceBootstrap.addModule(new StockTransferServiceImplZbus());
		serviceBootstrap.addModule(new StockTransferListServiceImplZbus());
		serviceBootstrap.addModule(new ReportFormServicceImpl(new StockDaoImpl(), new ReceivingNoteAsnDaoImpl(), new DeliveryNoteAsnDaoImpl(), new DeliveryPackingListDaoImpl()));
		
		
		serviceBootstrap.serviceName("WMS-RPC-TEST");
		serviceBootstrap.serviceAddress("172.31.1.176:15555");
		serviceBootstrap.serviceToken("wms_rpc_test_service");
//		serviceBootstrap.serviceName("WMS-RPC");
//		serviceBootstrap.serviceAddress("localhost:15555");
//		serviceBootstrap.serviceToken("wms_rpc_service");
		
		serviceBootstrap.start();
		
		
		
//		Broker broker = new Broker("10.10.10.171:15555");
//		 Broker broker = new Broker("localhost:15555");
		
//		broker.addTracker("localhost:15555", "ssl/zbus.crt");
//		ConsumerConfig config = new ConsumerConfig();
//		config.setBroker(broker);
//		config.setTopic("WMS-RPC");
//		config.setTopic("WMS-RPC-TEST");
//		config.setMessageHandler(processor);

//		Consumer consumer = new Consumer(config);
//
//		consumer.start();

	}
}
