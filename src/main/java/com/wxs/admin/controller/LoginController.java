package com.wxs.admin.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wxs.entity.sys.SysUser;
import com.wxs.service.sys.impl.ISysUserService;
import com.wxs.util.WebUtil;
import org.apache.commons.lang3.StringUtils;
import org.wxs.core.anno.PassLogin;
import org.wxs.core.bean.Rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 标准的Rest接口,实例控制器
 * Created by Gaojun.Zhou 2017年6月8日
 */
@Controller
public class LoginController{   
	
	@Autowired
    private ISysUserService sysUserService;
	
	/**
	 * 登录
	 * @return
	 */
	@PassLogin
	@GetMapping("/login")
	public String login(){
		return "login";
		
	}
	
	/**
	 * 执行登录
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 */
	@PassLogin
	@ResponseBody
	@PostMapping("/doLogin")
	public Rest doLogin(String username,String password,HttpServletRequest request){
		
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
			throw new RuntimeException("用户名或密码不能为空");
		}
		SysUser user = sysUserService.selectOne(new EntityWrapper<SysUser>().eq("userName",username).eq("password", WebUtil.MD5(password)));
		if(user == null){
			throw new RuntimeException("用户名或密码错误");
		}
		if(user.getUserState() == -1){
			throw new RuntimeException("该用户已锁定,请联系超级管理员");
		}
		
		request.getSession().setAttribute("session_user",user);
		return Rest.ok("登录成功");
		
	}
	
	/**
	 * 注销
	 * @return
	 */
	@PassLogin
	@GetMapping("/logout")
    public String logout(HttpServletRequest request){
		request.getSession().invalidate();
		return "redirect:login";
    }
}
