package com.github.wangyi.dubbo.test;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.github.wangyi.dubbo.client.Task;
import com.github.wangyi.dubbo.client.ThfirtClient;
import com.github.wangyi.dubbo.interfaces.HelloService;

/**
 * 多线程客户端
 * <p>User: wangyi
 * <p>Date: 2016-11-22
 * <p>Version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:protocal/thrift/test-applicationContext.xml"})
public class MultiThreadDubboClient {

	@Test
	public void miltiThread() throws TTransportException{
		long start=System.currentTimeMillis();
		TTransport  transport = new TSocket("192.168.1.176",3030);
		TProtocol protocol = new TBinaryProtocol(new TFramedTransport((transport)));
		transport.open();
		HelloService.Client server = new HelloService.Client(protocol);
		ThfirtClient server2= new ThfirtClient(protocol);
		for(int i=0;i<5;i++){
			new Task(server,server2).start();
		}
		long end=System.currentTimeMillis();
		System.out.println("多线程客户端Thrift执行时间："+(end-start)+"ms");
	}

}
