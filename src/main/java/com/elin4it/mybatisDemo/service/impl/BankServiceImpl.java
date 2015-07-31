package com.elin4it.mybatisDemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elin4it.mybatisDemo.mapper.BankMapper;
import com.elin4it.mybatisDemo.pojo.Bank;
import com.elin4it.mybatisDemo.service.BankService;

@Service
public class BankServiceImpl implements BankService {

	@Autowired
	private BankMapper bankMapper;

	public List<Bank> queryAllBank() throws Exception {
		// 查询条件设置为null表示无条件查询
		List<Bank> banks = bankMapper.selectByExample(null);
		return banks;
	}

}
