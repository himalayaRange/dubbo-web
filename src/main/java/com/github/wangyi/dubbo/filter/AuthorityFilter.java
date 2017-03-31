package com.github.wangyi.dubbo.filter;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.RpcResult;


/**
 *    RPC权限认证
 * <p>User: wangyi
 * <p>Date: 2016-11-21
 * <p>Version: 1.0
 */
public class AuthorityFilter implements Filter{

	private static final Logger LOG = LoggerFactory.getLogger(AuthorityFilter.class);
	
	private IpWhiteList  ipWhiteList ;
	
	//dubbo通过setter方式自动注入  
    public void setIpWhiteList(IpWhiteList ipWhiteList) {  
        this.ipWhiteList = ipWhiteList;  
    }  
    
	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation)
			throws RpcException {
		if(!ipWhiteList.isEnabled()){
			LOG.info("白名单禁用");
			RpcResult rpcResult = new RpcResult();
			rpcResult.setValue("您没有权限访问！");
			return rpcResult;
		}
		String clientIp = RpcContext.getContext().getRemoteHost();
		LOG.info("访问ip为{}",clientIp);
		List<String> allowedIps =ipWhiteList.getAllowedIps();
		if(allowedIps.contains(clientIp)){
			 return invoker.invoke(invocation); 
		}else{
			RpcResult rpcResult = new RpcResult();
			rpcResult.setValue("您没有权限访问！");
			return rpcResult;
		}
	}

}
