package com.github.wangyi.dubbo.protocal;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.protocol.AbstractProxyProtocol;
import com.facebook.nifty.client.FramedClientConnector;
import com.facebook.nifty.core.NettyServerTransport;
import com.facebook.nifty.core.ThriftServerDef;
import com.facebook.nifty.core.ThriftServerDefBuilder;
import com.facebook.swift.codec.ThriftCodecManager;
import com.facebook.swift.service.ThriftClientManager;
import com.facebook.swift.service.ThriftServiceProcessor;
import com.google.common.net.HostAndPort;

public class SwiftProtocol extends AbstractProxyProtocol {
	
	public static final int DEFAULT_PORT = 40880;

    public static final String NAME = "thrift";

	@Override
	public int getDefaultPort() {
		return DEFAULT_PORT;
	}

	@Override
	protected <T> Runnable doExport(T impl, Class<T> type, URL url)
			throws RpcException {
		//此处没有ThriftEventHandler
		
		ThriftServiceProcessor processor = new ThriftServiceProcessor(new ThriftCodecManager(),null,impl);
        
        ThriftServerDef serverDef = new ThriftServerDefBuilder().listen(url.getPort()).withProcessor(processor).build();  

        final NettyServerTransport server = new NettyServerTransport(serverDef);  

        server.start();  

		return new Runnable() {
            public void run() {
                try {
                	server.stop();
                } catch (Throwable e) {
                    logger.warn(e.getMessage(), e);
                }
            }
        };
	}

	@Override
	protected <T> T doRefer(Class<T> type, URL url) throws RpcException {
		FramedClientConnector connector = new FramedClientConnector(HostAndPort.fromParts(url.getHost(), url.getPort()));
		try {
			ThriftClientManager clientManager = new ThriftClientManager();
			T ref = clientManager.createClient(connector, type).get();
			
			return ref;
		} catch (Exception e) {
			 throw new RpcException( "Fail to create remoting client for service(" + url + "): " + e.getMessage(), e );
		}
	}
}
