package com.github.wangyi.dubbo.client;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import com.github.wangyi.commons.util.guava.ObjectsUtil;
import com.github.wangyi.dubbo.dto.Order;
import com.github.wangyi.dubbo.interfaces.HelloService;
import com.github.wangyi.dubbo.interfaces.WebserviceService;

public class Task extends Thread{
		
	private  HelloService.Iface service;
	private  WebserviceService service2;
		
	public Task(HelloService.Iface service, WebserviceService  service2) {
		this.service = service;
		this.service2 = service2;
	}
		
	public void run() {
		for (int i = 0; i <10; i++) {
			try {
				String str = service.getString("hello" + i);
				System.out.println(str);
				Order order=new Order();
				order.setOrderNo(UUID.randomUUID().toString());
				order.setOrderName("huawei Mate9");
				order.setPrice(9999L);
				order.setCreateTime(new Date());
				Order curr=(Order)service2.pay(order);
				System.out.println(ObjectsUtil.stringhelper(curr));
				TimeUnit.SECONDS.sleep(1);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
