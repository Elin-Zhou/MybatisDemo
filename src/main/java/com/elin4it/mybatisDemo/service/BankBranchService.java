package com.elin4it.mybatisDemo.service;

import java.util.List;

import com.elin4it.mybatisDemo.pojo.BankBranch;

public interface BankBranchService {
	/**
	 * 根据银行编号，省份编号，市区编号获取所有分行总数
	 * 
	 * @param bankCode
	 * @param provinceCode
	 * @param cityCode
	 * @param bankBranch
	 * @return
	 */
	int queryBankBranchByBankCodeSum(String bankCode, String provinceCode,
			String cityCode, String bankBranch);

	/**
	 * 根据银行编号，省份编号，市区编号获取所有分行信息
	 * 
	 * @param bankCode
	 * @param provinceCode
	 * @param cityCode
	 * @param bankBranch
	 * @return
	 */
	public List<BankBranch> queryBankBranchByBankCode(String bankCode,
			String provinceCode, String cityCode, String bankBranch,
			int currentPage, int size);

}
