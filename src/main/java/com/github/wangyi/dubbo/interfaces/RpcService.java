/**  
 * @author wangyi
 * @date 2016-7-6 上午11:53:36 
 * @version V1.0   
 */ 
package com.github.wangyi.dubbo.interfaces;

import com.github.wangyi.dubbo.dto.Order;


/**
 * @author wangyi
 * @date 2016-7-6 上午11:53:36 
 */
public interface  RpcService {
	
	public String hello(String name); 
	
	public String connection(String host,int post);
	
	public Order sendOrder(Order order);
	
}
