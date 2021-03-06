package com.oncecloud.manager.Impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.oncecloud.manager.AccountManager;
import com.oncecloud.service.AccountService;

@Service("accountManager")
public class AccountManagerImpl implements AccountManager {
	
	@Resource
	private AccountService accountService;
	
	public Map<String, Object> getInfrastructureInfos() {
		return accountService.getInfrastructureInfos();
	}

}
