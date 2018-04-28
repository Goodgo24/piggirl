package com.joe.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.common.BaseServiceImpl;
import com.joe.dao.ExpressMapper;
import com.joe.pojo.Express;
import com.joe.pojo.ExpressExample;
import com.joe.service.ExpressService;
@Service("expressSerivce")
public class ExpressServiceImpl extends BaseServiceImpl<Express, ExpressExample> implements ExpressService{
	@Resource
	private ExpressMapper expressMapper;
	@Override
	public void initService() {
		this.baseMapper=expressMapper;
	}
	
}
