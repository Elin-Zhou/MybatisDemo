package com.elin4it.mybatisDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elin4it.mybatisDemo.service.BankBranchService;

@Controller
@RequestMapping("/bankBranch")
public class BankBranchController {
	@Autowired
	private BankBranchService bankBranchService;

}
