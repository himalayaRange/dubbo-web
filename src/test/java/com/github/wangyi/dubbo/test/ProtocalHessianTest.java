package com.github.wangyi.dubbo.test;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.caucho.hessian.client.HessianProxyFactory;
import com.github.wangyi.dubbo.dto.Order;
import com.github.wangyi.dubbo.interfaces.SearchService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:protocal/hessian/test-applicationContext.xml"})
public class ProtocalHessianTest {
	
	@Autowired
	private SearchService searchService;
	
	private static final  String serviceURL="http://192.168.1.176:10080/dubbo-web/http_dubbo/hessian/search";
	
	/**
	 * 使用Dubbo进行调用
	 */
	@Test
	public void test(){
		String ret =searchService.search("星座财富", 50, 100);
		System.out.println(ret);
	}
	
	/**
	 * 面向多语言编程：使用Hessian代理标准接口进行处理
	 * @throws MalformedURLException
	 */
	@Test
	public void simpleTest() throws MalformedURLException{
		HessianProxyFactory factory = new HessianProxyFactory();
		SearchService exposer = (SearchService)factory.create(SearchService.class,serviceURL);
		String ret = exposer.search("热门财富", 10, 20);
		System.out.println(ret);
	}
	
	/**
	 * 各种序列化框架性能测试：
	 *  kryo--143ms
		fst--250ms
		protobuf--252ms
		hessian2 --280ms
		jackson--264ms
		java--355ms
		thrift--141ms
	 * @throws MalformedURLException
	 */
	@Test
	public void ObjectTest() throws MalformedURLException{
		long start=System.currentTimeMillis();
		HessianProxyFactory factory = new HessianProxyFactory();
		SearchService exposer = (SearchService)factory.create(SearchService.class,serviceURL);
		Order order=new Order();
		order.setOrderNo(UUID.randomUUID().toString());
		order.setOrderName("iphone8 plus");
		order.setPrice(9000L);
		order.setCreateTime(new Date());
		String ret = exposer.receive(order);
		System.out.println(ret);
		long end=System.currentTimeMillis();
		System.out.println("执行时间："+(end-start)+"ms");
		
	}
}
