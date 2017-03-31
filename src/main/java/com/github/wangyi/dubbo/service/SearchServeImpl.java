package com.github.wangyi.dubbo.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.wangyi.dubbo.dto.Order;
import com.github.wangyi.dubbo.interfaces.SearchService;

/**
 *  提供方使用Dubbo封装的Hessian协议，实现rpc服务
 *  描述:rpc search service
 *  <p>User: wangyi
 *  <p>Date: 2016-11-16
 *  <p>Version: 1.0
 */

@Service(version="1.0.0")
public class SearchServeImpl implements SearchService{

	private static final Logger LOG = LoggerFactory.getLogger(SearchServeImpl.class);
	
	@Override
	public String search(String key,int start,int end) {
		
		LOG.info("Hessian.SolrSearchServer.search....");
		return "key:"+key+",从"+start+"到"+end+"条记录";
	}

	@Override
	public String receive(Object obj) {
		Order order = (Order)obj;
		return JSON.toJSONString(order);
	}

}
