package com.wxs.admin.intercptror;

import com.wxs.entity.sys.SysUser;
import com.wxs.service.sys.impl.ISysMenuService;
import com.wxs.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wxs.core.anno.Resource;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 资源拦截器
 * @author Gaojun.Zhou
 * @date 2016年12月15日 下午2:35:27
 */
@Component
public class ResourceInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private ISysMenuService sysMenuService;

	@Override
	public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Resource rce =  handlerMethod.getMethodAnnotation(Resource.class);
			if(rce != null){
				
				String uid = ((SysUser)request.getSession().getAttribute("session_user")).getId();
				List<String> resAll = sysMenuService.selectResourceByUid(uid);
				
				if(resAll.contains(rce.value())){
					return true;
				}
				
				if (WebUtil.isAjax(request)) {
					throw new RuntimeException("illegalAccess，无访问权限");
				} else {
					request.setAttribute("url",request.getRequestURL());
					request.getRequestDispatcher("/error/illegalAccess").forward(request, response);
					return false;
				}
			}
		}
		return true;
	}
}
