//BankService.java   
//2015年7月30日  上午10:18:59
package com.elin4it.mybatisDemo.service;

import java.util.List;

import com.elin4it.mybatisDemo.pojo.Bank;

public interface BankService {
	/**
	 * 获取所有银行信息（总行）
	 * 
	 * @return
	 */
	List<Bank> queryAllBank() throws Exception;

}
