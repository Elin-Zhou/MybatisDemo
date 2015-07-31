package com.elin4it.mybatisDemo.mapper;

import java.util.Map;

public interface BankBranchCustomMapper {
	/**
	 * 根据银行编号，省份编号，市区编号获取所有分行总数
	 * 
	 * @param map
	 * @return
	 */
	int QUERY_BANK_BRANCH_BY_BANK_CODE_SUM(Map map);

	/**
	 * 根据银行编号，省份编号，市区编号获取所有分行信息
	 * 
	 * @param map
	 * @return
	 */
	int QUERY_BANK_BRANCH_BY_BANK_CODE(Map map);
}
