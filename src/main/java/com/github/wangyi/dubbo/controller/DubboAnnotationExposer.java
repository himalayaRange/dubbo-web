package com.github.wangyi.dubbo.controller;

import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.github.wangyi.dubbo.dto.Order;
import com.github.wangyi.dubbo.interfaces.annotation.AnnotationRpc;


/**
 *  Dubbo协议暴露的所有服务
 * <p>User: wangyi
 * <p>Date: 2016-11-16
 * <p>Version: 1.0
 */
@Component
public class DubboAnnotationExposer {
	
	@Reference(version="1.0.0")
	private AnnotationRpc annotationRpc;

	
	public String sayHello(String to){
	
		return annotationRpc.sayHello(to);
	}
	
	public String order(Order order){
		
		return JSON.toJSONString(order);
	}
	
}
