package com.beyondsphere.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.beyondsphere.dao.TemplateDAO;
import com.beyondsphere.entity.OCVM;
import com.beyondsphere.helper.SessionHelper;

/**
 * @author luogan
 * 2015年5月19日
 * 下午2:18:48
 */
@Component("TemplateDAO")
public class TemplateDAOImpl implements TemplateDAO {

	@Resource
	private SessionHelper sessionHelper;

	@Override
	public int countByUserId(int userId, String search) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OCVM> getOnePageByUserId(int userId, int page, int limit,
			String search) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
