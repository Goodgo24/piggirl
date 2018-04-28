package com.joe.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.common.BaseServiceImpl;
import com.joe.dao.ExtraPayMapper;
import com.joe.pojo.ExtraPay;
import com.joe.pojo.ExtraPayExample;
import com.joe.service.ExtraPayService;
@Service("extraPaySerivce")
public class ExtraPayServiceImpl extends BaseServiceImpl<ExtraPay, ExtraPayExample> implements ExtraPayService{
	@Resource
	private ExtraPayMapper extraPayMapper;
	@Override
	public void initService() {
		this.baseMapper=extraPayMapper;
	}
	
}
