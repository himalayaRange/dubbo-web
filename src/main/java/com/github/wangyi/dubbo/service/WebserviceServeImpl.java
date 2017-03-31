package com.github.wangyi.dubbo.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.wangyi.dubbo.interfaces.WebserviceService;

/**
 *  提供方使用Dubbo封装的Webservice协议，实现rpc服务
 *  实例：银行充值接口
 *  <p>User: wangyi
 *  <p>Date: 2016-11-16
 *  <p>Version: 1.0
 */

@Service(version="1.0.0")
public class WebserviceServeImpl implements WebserviceService{

	private static final Logger LOG = LoggerFactory.getLogger(WebserviceServeImpl.class);


	@Override
	public Object pay(Object obj) {
		LOG.info("rpc 充值接口...");
		return obj;
	}

}
