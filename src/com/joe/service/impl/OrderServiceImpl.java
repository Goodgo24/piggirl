package com.joe.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.common.BaseServiceImpl;
import com.joe.dao.OrderMapper;
import com.joe.pojo.Order;
import com.joe.pojo.OrderExample;
import com.joe.service.OrderService;
@Service("orderSerivce")
public class OrderServiceImpl extends BaseServiceImpl<Order, OrderExample> implements OrderService{
	@Resource
	private OrderMapper orderMapper;
	@Override
	public void initService() {
		this.baseMapper=orderMapper;
	}
	
}
