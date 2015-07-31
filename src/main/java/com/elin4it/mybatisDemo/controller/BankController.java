package com.elin4it.mybatisDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elin4it.mybatisDemo.pojo.Bank;
import com.elin4it.mybatisDemo.service.BankService;

@Controller
@RequestMapping("/bank")
public class BankController {
	@Autowired
	private BankService bankService;

	@RequestMapping("/queryAllBank")
	public void queryAllBank(ModelMap modelMap) throws Exception {
		List<Bank> banks = bankService.queryAllBank();
		System.out.println(banks);
	}
}
