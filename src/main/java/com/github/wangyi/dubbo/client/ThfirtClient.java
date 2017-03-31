package com.github.wangyi.dubbo.client;

import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TProtocol;
import com.github.wangyi.dubbo.interfaces.WebserviceService;

/**
 * Thrift Client
 * <p>User: wangyi
 * <p>Date: 2016-11-22
 * <p>Version: 1.0
 */
public class ThfirtClient extends TServiceClient implements WebserviceService{

	public ThfirtClient(TProtocol prot) {
		super(prot);
	}
	
	public ThfirtClient(TProtocol iprot, TProtocol oprot) {
		super(iprot, oprot);
	}

	@Override
	public Object pay(Object obj) {
		return obj;
	}

}
