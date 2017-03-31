package com.github.wangyi.dubbo.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.rpc.RpcContext;

/**
 *   IP白名单
 * <p>User: wangyi
 * <p>Date: 2016-11-21
 * <p>Version: 1.0
 */
@Component
public class IpWhiteList {

	private  List<String> whiteList =new ArrayList<String>();
	
	private  List<Map<String,Boolean>> whiteListMap =new ArrayList<Map<String,Boolean>>();
	
	public List<String> getWhiteList() {
		return whiteList;
	}


	public void setWhiteList(List<String> whiteList) {
		this.whiteList = whiteList;
	}

	
	public List<Map<String, Boolean>> getWhiteMap() {
		return whiteListMap;
	}


	public void setWhiteMap(List<Map<String, Boolean>> whiteListMap) {
		this.whiteListMap = whiteListMap;
	}


	public IpWhiteList(){
		put("192.168.1.176");
	}
	

	/**
	 * 添加白名单
	 * @param whiteIP
	 */
	public void put(String whiteIP) {
	    Map<String,Boolean> curr= new HashMap<String,Boolean>();
	    curr.put(whiteIP, true);
	    this.whiteList.add(whiteIP);
		this.whiteListMap.add(curr);
	}
	
	/**
	 * 移除白名单
	 * @param whiteIP
	 */
	public void remove(String whiteIP){
		 Map<String,Boolean> curr= new HashMap<String,Boolean>();
		 curr.put(whiteIP, true);
		 this.whiteList.remove(whiteIP);
		 this.whiteListMap.remove(curr);
	}
	
	/**
	 * 设置黑名单
	 * @param backIp
	 */
	public void setBlackIP(String backIp){
		Map<String,Boolean> curr= new HashMap<String,Boolean>();
		 curr.put(backIp, true);
		 if(this.whiteListMap.contains(curr)){
			this.whiteListMap.remove(curr);
			curr.put(backIp, false);
			this.whiteListMap.add(curr);
		 }
	}
	
	/**
	 * 设置白名单
	 * @param backIp
	 */
	public void setWhiteIP(String whiteIP){
		Map<String,Boolean> curr= new HashMap<String,Boolean>();
		 curr.put(whiteIP, false);
		 if(this.whiteListMap.contains(curr)){
			this.whiteListMap.remove(curr);
			curr.put(whiteIP, true);
			this.whiteListMap.add(curr);
		 }
	}
	
	
	/**
	 * 白名单是否禁用
	 * @return
	 */
	public boolean isEnabled(){
		//获取远程上下文
		RpcContext context = RpcContext.getContext();
		String remoteHost = context.getRemoteHost();
		Map<String,Boolean> curr=new HashMap<String,Boolean>();
		curr.put(remoteHost, true);//远程IP在白名单
		return whiteListMap.contains(curr)?true:false;
	}
	
	/**
	 * 获取所有的白名单
	 * @return
	 */
	public List<String> getAllowedIps(){
		
		return this.whiteList;
	}
	
	
}
