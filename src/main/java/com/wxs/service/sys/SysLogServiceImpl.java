package com.wxs.service.sys;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wxs.entity.sys.SysLog;
import com.wxs.entity.sys.SysUser;
import com.wxs.mapper.sys.SysLogMapper;
import com.wxs.service.sys.impl.ISysLogService;
import org.wxs.core.log.LogApi;
import org.wxs.core.log.LogBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author GaoJun.Zhou
 * @since 2017-06-30
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService,LogApi {

	@Autowired
    private HttpServletRequest request;
	
	@Override
	public void log(LogBean log) {
		// TODO Auto-generated method stub
		
		SysLog sysLog = new SysLog();
		sysLog.setClientIp(log.getClientIp());
		sysLog.setLogContent(log.getLogContent());
		sysLog.setLogTime(log.getLogTime());
		sysLog.setLogTitle(log.getLogTitle());
		sysLog.setOther(log.getOther());
		sysLog.setRequestMethod(log.getRequestMethod());
		sysLog.setRequestParams(log.getRequestParams());
		sysLog.setRequestUrl(log.getRequestUrl());
		sysLog.setUserName(((SysUser)request.getSession().getAttribute("session_user")).getUserName());
		this.insert(sysLog);
	}
	
}
