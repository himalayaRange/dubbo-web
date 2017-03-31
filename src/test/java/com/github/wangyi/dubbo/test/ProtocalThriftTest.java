package com.github.wangyi.dubbo.test;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.github.wangyi.commons.util.guava.ObjectsUtil;
import com.github.wangyi.dubbo.client.ThfirtClient;
import com.github.wangyi.dubbo.dto.Order;
import com.github.wangyi.dubbo.interfaces.HelloService;
import com.github.wangyi.dubbo.interfaces.WebserviceService;

/**
 * Thrift 协议测试
 * 参考DEMO:https://github.com/yankai913/dubbo-rpc-thrift 
 * <p>User: wangyi
 * <p>Date: 2016-11-21
 * <p>Version: 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:protocal/thrift/test-applicationContext.xml"})
public class ProtocalThriftTest {
	
	@Autowired
	private WebserviceService payService;
	
	@Autowired
	private com.github.wangyi.dubbo.interfaces.HelloService.Iface helloService;
	
	/**
	 * 通过配置文件customer.xml注入
	 */
	@Test
	public void testInject(){
		/*System.out.println(helloService);*/
		System.out.println(payService);
		String ret=(String)payService.pay("this is test");
		System.out.println(ret);
	}
	
	/**
	 * 多语言标准接口调用
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@Test
	public void simpleTest() throws IllegalArgumentException, IllegalAccessException{
		long start=System.currentTimeMillis();
		TTransport  transport = new TSocket("192.168.1.176",3030);
		TProtocol protocol = new TBinaryProtocol(new TFramedTransport((transport)));
		try {
			transport.open();
			HelloService.Client client = new HelloService.Client(protocol);
			for (int i = 0; i <10; i++) {
				try {
					String str = client.getString("hello" + i);
					System.out.println(str);
					TimeUnit.SECONDS.sleep(1);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			ThfirtClient protocol1= new ThfirtClient(protocol);
			Order order=new Order();
			order.setOrderNo(UUID.randomUUID().toString());
			order.setOrderName("huawei Mate9");
			order.setPrice(9999L);
			order.setCreateTime(new Date());
			Order curr=(Order)protocol1.pay(order);
			System.out.println(ObjectsUtil.stringhelper(curr));
			long end=System.currentTimeMillis();
			System.out.println("Thrift执行时间："+(end-start)+"ms");
		} catch (TTransportException e) {
			e.printStackTrace();
		}
		transport.close();
	}
}
