package com.yorma.wms.consumer.zbus;


//import com.yorma.wms.entity.Product;
//import com.yorma.wms.service.api.ProductService;


import io.zbus.mq.Broker;
import io.zbus.rpc.RpcInvoker;

public class ZBusClient {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			/*
			 * Broker broker = new ZbusBroker("127.0.0.1:8080"); RpcFactory
			 * factory = new RpcFactory(broker); // directly
			 * 
			 * // HelloService helloService = //
			 * factory.getService(HelloService.class); //
			 * System.out.println(helloService.sayHello("张三"));
			 * 
			 * ProductService productService =
			 * factory.getService(ProductService.class); Search search = new
			 * Search(); search.addFilterEqual("name", "梨子");
			 * System.out.println(productService.search(search));
			 * 
			 * broker.close();
			 */

			Broker broker = new Broker("172.31.1.167:15555");

			RpcInvoker rpc = new RpcInvoker(broker, "WMS-RPC");

			// Way 1) Raw request
			/*
			 * Request req = new Request(); req.setMethod("plus");
			 * req.setParams(new Object[]{1,2});
			 * 
			 * Response res = rpc.invokeSync(req); System.out.println(res);
			 */

			// asynchronous call
			/*
			 * rpc.invokeAsync(req, new ResultCallback<Response>() {
			 * 
			 * @Override public void onReturn(Response result) { Integer res =
			 * (Integer)result.getResult(); System.out.println(res); } });
			 */

			// Way 2) More abbreviated
			/*
			 * int result = rpc.invokeSync(Integer.class, "plus", 1, 2);
			 * System.out.println(result);
			 */

			// Way 3) Strong typed proxy

			

//			 ProductService api=rpc.createProxy(ProductService.class);

//			GoodsService api=rpc.createProxy(GoodsService.class);
			
//			RpcGoodsZbusCases.getByGoodsId();
			

			/*
			 * Search search = new Search(); search.addFilterEqual("name",
			 * "梨子"); System.out.println(api.search(search));
			 */

			broker.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * public static void saveProduct(RpcFactory factory) { Product product =
	 * new Product(); product.setName("橘子"); product.setStock(11);
	 * product.setStatus(true); product.setCreatedDate(new Date());
	 * product.setInfo("我是通过zbus服务添加进来的，是通过ORM框架memory添加的"); // ProductService
	 * productService = // factory.getService(ProductService.class); // boolean
	 * flag = productService.saveProduct(product); //
	 * System.out.println(String.format("添加数据状态为:%s", flag)); }
	 */
}
