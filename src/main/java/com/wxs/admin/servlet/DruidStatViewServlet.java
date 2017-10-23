package com.wxs.admin.servlet;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * druid数据源servlet
 * 
 * @author skyer
 * @date 2017年8月17日 下午5:29:34
 */
@WebServlet(urlPatterns = "/druid/*", initParams = { @WebInitParam(name = "allow", value = "192.168.1.179,127.0.0.1"),
		@WebInitParam(name = "deny", value = "192.168.1.73"), @WebInitParam(name = "loginUsername", value = "admin"),
		@WebInitParam(name = "loginPassword", value = "123456"), @WebInitParam(name = "resetEnable", value = "false")})
public class DruidStatViewServlet extends StatViewServlet{

	private static final long serialVersionUID = 1L;

}
