package com.joe.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.common.BaseServiceImpl;
import com.joe.dao.PiggirlUserMapper;
import com.joe.pojo.PiggirlUser;
import com.joe.pojo.PiggirlUserExample;
import com.joe.service.PiggirlUserService;
@Service("piggirlUserSerivce")
public class PiggirlUserServiceImpl extends BaseServiceImpl<PiggirlUser, PiggirlUserExample> implements PiggirlUserService{
	@Resource
	private PiggirlUserMapper piggirlUserMapper;
	@Override
	public void initService() {
		this.baseMapper=piggirlUserMapper;
	}
	
}
