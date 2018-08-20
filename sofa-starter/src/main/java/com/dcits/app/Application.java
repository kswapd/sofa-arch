package com.dcits.app;

import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.dcits.services.HelloService;
import com.dcits.services.HelloServiceImpl;


/**
 * Created by kongxiangwen on 7/23/18 w:30.
 */
public class Application {
	private static final String protocolUrl="bolt://127.0.0.1:12200";
	private static final String protocol = "bolt";
	private static final int port=12200;
	public static void main(String args[]){
		/*RegistryConfig registryConfig = new RegistryConfig()
				.setProtocol("zookeeper")
				.setAddress("127.0.0.1:2181")*/
		StartProvider();
		StartConsumer();
	}


	public static void StartProvider()
	{
		ServerConfig serverConfig = new ServerConfig()
				.setProtocol(protocol) // 设置一个协议，默认bolt
				.setPort(port) // 设置一个端口，默认12200
				.setDaemon(false); // 非守护线程

		ProviderConfig<HelloService> providerConfig = new ProviderConfig<HelloService>()
				.setInterfaceId(HelloService.class.getName()) // 指定接口
				.setRef(new HelloServiceImpl()) // 指定实现
				.setServer(serverConfig); // 指定服务端

		providerConfig.export(); // 发布服务
	}

	public static void StartConsumer()
	{

		ConsumerConfig<HelloService> consumerConfig = new ConsumerConfig<HelloService>()
				.setInterfaceId(HelloService.class.getName()) // 指定接口
				.setProtocol(protocol) // 指定协议
				.setDirectUrl(protocolUrl); // 指定直连地址
		// 生成代理类
		HelloService helloService = consumerConfig.refer();
		while (true) {
			System.out.println(helloService.sayHello("world"));
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
			}
		}
	}
}
