package com.joe.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.common.BaseServiceImpl;
import com.joe.dao.GoodsMapper;
import com.joe.pojo.Goods;
import com.joe.pojo.GoodsExample;
import com.joe.service.GoodsService;
@Service("goodsSerivce")
public class GoodsServiceImpl extends BaseServiceImpl<Goods, GoodsExample> implements GoodsService{
	@Resource
	private GoodsMapper goodsMapper;
	@Override
	public void initService() {
		this.baseMapper=goodsMapper;
	}
	
}
