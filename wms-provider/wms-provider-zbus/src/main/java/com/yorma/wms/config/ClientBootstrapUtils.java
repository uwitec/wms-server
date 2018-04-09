package com.yorma.wms.config;

import io.zbus.rpc.bootstrap.ClientBootstrap;

public class ClientBootstrapUtils {

	
	public static ClientBootstrap getClientBootstrap() {
		ClientBootstrap clientBootstrap = new ClientBootstrap();

		clientBootstrap.serviceName("COMMON-WMS-RPC");
//		clientBootstrap.serviceAddress("10.10.10.171:15555");
		clientBootstrap.serviceAddress("172.31.1.176:15555");
//		clientBootstrap.serviceAddress("localhost:15555");
		clientBootstrap.serviceToken("common_wms_rpc_service");
		return clientBootstrap;
		
	}
	
}
