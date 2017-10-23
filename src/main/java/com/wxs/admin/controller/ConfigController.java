package com.wxs.admin.controller;

import com.wxs.config.SystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 读取系统配置文件
 * @author skyer
 * @date 2017年8月17日 下午2:48:27
 */
@RestController
@RequestMapping(value="systemConfig")
public class ConfigController {
	
	@Autowired
	private SystemConfig systemConfig;
	
	/**
	 * 查询系统配置文件
	 * @author SongZhangLiang
	 * @date 2017年8月17日 下午2:52:31
	 * @return
	 */
	@RequestMapping(value="")
	public String querySystemConfig() {
		return "系统配置文件参数："+systemConfig.toString();
	}

	
	
}
