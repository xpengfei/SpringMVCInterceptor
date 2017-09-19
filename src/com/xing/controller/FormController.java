package com.xing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FormController{

	@RequestMapping(value="/{formName}")
	 public String loginForm(@PathVariable String formName){
		// 动态页面跳转
		return formName;
	}

}

