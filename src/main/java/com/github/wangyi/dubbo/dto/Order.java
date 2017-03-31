package com.github.wangyi.dubbo.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单entity
 * <p>User: wangyi
 * <p>Date: 2016-11-16
 * <p>Version: 1.0
 */
public class Order implements Serializable{

	private static final long serialVersionUID = 5273528949555924734L;

	private String orderNo;
	
	private String orderName;
	
	private Long price;
	
	private Date createTime;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
