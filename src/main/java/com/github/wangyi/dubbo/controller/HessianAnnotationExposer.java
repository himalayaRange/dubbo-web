package com.github.wangyi.dubbo.controller;

import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.github.wangyi.dubbo.interfaces.SearchService;


/**
 *  Hessian协议注解暴露的所有服务
 * <p>User: wangyi
 * <p>Date: 2016-11-16
 * <p>Version: 1.0
 */
@Component
public class HessianAnnotationExposer {
	

	@Reference(version="1.0.0",url="http://192.168.1.176:10080/com.github.wangyi.dubbo.interfaces.SearchService")
	private SearchService searchService;
	
	
	public String welcome(String to){
		
		return JSON.toJSONString(searchService.search(to, 50, 100));
	}
}
