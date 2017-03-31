package com.github.wangyi.dubbo.test;

import java.util.Date;
import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.github.wangyi.commons.util.guava.ObjectsUtil;
import com.github.wangyi.dubbo.controller.DubboAnnotationExposer;
import com.github.wangyi.dubbo.dto.Order;
import com.github.wangyi.dubbo.interfaces.RpcService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:protocal/dubbo/test-applicationContext.xml"})
public class ProtocalDubboTest {

	@Autowired
	private RpcService rpcService;
	
	
	@Autowired
	private DubboAnnotationExposer  rpcExposer;
	
	@Test
	public void test(){
		String ret = rpcService.hello("汪谊");
		System.out.println(ret);
	}
	
	/**
	 * 传输对象时候对象必须序列化
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@Test
	public void order() throws IllegalArgumentException, IllegalAccessException{
		Order order=new Order();
		order.setOrderNo(UUID.randomUUID().toString());
		order.setOrderName("iphone7 plus");
		order.setPrice(6888L);
		order.setCreateTime(new Date());
		Order curr = rpcService.sendOrder(order);
		System.out.println(ObjectsUtil.stringhelper(curr));
	}
	
	@Test
	public void simpleAnnotation(){
		String ret= rpcExposer.sayHello("汪谊");
		System.out.println(ret);
	}
	
	@Test
	public void ObjectAnnotation(){
		Order order=new Order();
		order.setOrderNo(UUID.randomUUID().toString());
		order.setOrderName("iphone7 plus");
		order.setPrice(6888L);
		order.setCreateTime(new Date());
		String ret= rpcExposer.order(order);
		System.out.println(ret);
	}
	
}
