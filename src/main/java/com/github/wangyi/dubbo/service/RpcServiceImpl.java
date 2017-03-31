/**  
 * @author wangyi
 * @date 2016-7-6 上午11:54:44 
 * @version V1.0   
 */ 
package com.github.wangyi.dubbo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.wangyi.dubbo.dto.Order;
import com.github.wangyi.dubbo.interfaces.RpcService;



/**
 * @author wangyi
 * @date 2016-7-6 上午11:54:44 
 */

@Service(version="1.0.0")
public class RpcServiceImpl implements RpcService{

	@Override
	public String hello(String name) {
		
		return "您好！欢迎您："+name;
	}

	@Override
	public String connection(String host, int post) {
		
		return "连接成功！";
	}

	@Override
	public Order sendOrder(Order order) {
		order.setPrice(order.getPrice()+1000);
		return order;
	}


}
