package com.github.wangyi.dubbo.service;

import org.apache.thrift.TException;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.wangyi.dubbo.interfaces.HelloService;

@Service(version="1.0.0")
public class HelloServicesImpl implements HelloService.Iface{

	@Override
	public String getString(String str) throws TException {
		return "rpc request return  :"+str;
	}

	@Override
	public void sayHello(String str) throws TException {
		System.out.println("rpc request:"+str);
	}

}
