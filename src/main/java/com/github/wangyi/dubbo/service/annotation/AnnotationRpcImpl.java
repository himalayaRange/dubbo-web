package com.github.wangyi.dubbo.service.annotation;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.wangyi.dubbo.interfaces.annotation.AnnotationRpc;

@Service(version="1.0.0")
public class AnnotationRpcImpl implements AnnotationRpc {

	@Override
	public String sayHello(String to) {
	
		return "欢迎使用dubbo注解:"+to;
	}

}
