package com.elin4it.mybatisDemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elin4it.mybatisDemo.mapper.BankBranchCustomMapper;
import com.elin4it.mybatisDemo.mapper.BankBranchMapper;
import com.elin4it.mybatisDemo.pojo.BankBranch;
import com.elin4it.mybatisDemo.pojo.BankBranchExample;
import com.elin4it.mybatisDemo.service.BankBranchService;

@Service
public class BankBranchServiceImpl implements BankBranchService {

	@Autowired
	private BankBranchMapper bankBranchMapper;
	@Autowired
	private BankBranchCustomMapper bankBranchCustomMapper;

	// public int queryBankBranchByBankCodeSum(String bankCode,
	// String provinceCode, String cityCode, String bankBranch) {
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("bankCode", bankCode);
	// map.put("provinceCode", provinceCode);
	// map.put("cityCode", cityCode);
	// map.put("bankBranch", bankBranch);
	//
	// int count = bankBranchCustomMapper
	// .QUERY_BANK_BRANCH_BY_BANK_CODE_SUM(map);
	//
	// return count;
	// }

	public int queryBankBranchByBankCodeSum(String bankCode,
			String provinceCode, String cityCode, String bankBranch) {
		// 设置查询条件
		BankBranchExample bankBranchExample = new BankBranchExample();
		BankBranchExample.Criteria criteria = bankBranchExample
				.createCriteria();

		criteria.andBankCodeEqualTo(bankCode);
		criteria.andProvinceCodeEqualTo(provinceCode);
		criteria.andCityCodeEqualTo(cityCode);
		criteria.andNameEqualTo(bankBranch);

		// 执行查询操作
		int count = bankBranchMapper.countByExample(bankBranchExample);
		return count;
	}

	public List<BankBranch> queryBankBranchByBankCode(String bankCode,
			String provinceCode, String cityCode, String bankBranch,
			int currentPage, int size) {
		// 设置查询条件
		BankBranchExample bankBranchExample = new BankBranchExample();
		BankBranchExample.Criteria criteria = bankBranchExample
				.createCriteria();

		criteria.andBankCodeEqualTo(bankCode);
		criteria.andProvinceCodeEqualTo(provinceCode);
		criteria.andCityCodeEqualTo(cityCode);
		criteria.andNameEqualTo(bankBranch);

		// 执行查询操作
		List<BankBranch> bankBranchs = bankBranchMapper
				.selectByExample(bankBranchExample);
		return bankBranchs;
	}
}
