package com.github.wangyi.dubbo.interfaces;

import org.apache.thrift.TException;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.TServiceClientFactory;
import org.apache.thrift.protocol.TProtocol;

public class HelloService {

	/**
	 * 接口<同步>
	 */
	
	public interface Iface{
		
		public String getString(String str)throws org.apache.thrift.TException;
		
		public void sayHello(String str)throws  org.apache.thrift.TException;
		
	}
	
	
	/**
	 * 异步开放接口
	 * <p>User: wangyi
	 * <p>Date: 2016-11-22
	 * <p>Version: 1.0
	 */
	public interface AsyncIface{
		
		//public String getString(String str,AsyncMethodCallback<AsyncClient.>) 
	
	}
	
	/**
	 * Thrift Client接入
	 */
	public static class Client extends TServiceClient implements Iface{
		
		public static class Factory implements TServiceClientFactory<Client>{
			
			public Factory(){}

			@Override
			public Client getClient(TProtocol prot) {
				return new Client(prot);
			}

			@Override
			public Client getClient(TProtocol iprot, TProtocol oprot) {
				return new Client(iprot, oprot);
			}
			
		}

		public Client(TProtocol prot) {
			super(prot);
		}

		public Client(TProtocol iprot, TProtocol oprot) {
		      super(iprot, oprot);
		}
		
		@Override
		public String getString(String str) throws TException {
			return "client :"+str;
		}

		@Override
		public void sayHello(String str) throws TException {
			System.out.println("client :" +str);
		}
		
	}
}
