package com.github.wangyi.dubbo.interfaces;

/**
 * rpc search interface 
 * <p>User: wangyi
 * <p>Date: 2016-11-16
 * <p>Version: 1.0
 */
public interface SearchService {

	String search(String key,int start,int end);
	
	String receive(Object obj);
	
}
