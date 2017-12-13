package com.wxs.admin.intercptror;

import com.wxs.cache.ICache;
import com.wxs.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.wxs.core.anno.PassLogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 微信拦截器
 * Created by skyer 2017年12月12日
 */
@Component
public class WxInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private ICache cache;

	@Override
	public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub

		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();

			PassLogin passLogin = method.getAnnotation(PassLogin.class);
			if (passLogin != null) {
				//跳过登录拦截
				return true;
			}
				//登录验证
				if(request.getParameter("sessionId") == null){
					if (WebUtil.isAjax(request)) {
						throw new RuntimeException("您的登录已失效,请重新登录");
					} else {
						String sessionKey = cache.getCache(request.getParameter("sessionId"));
						if(sessionKey==null){
							return false; //session失效了，需要重新授权
						} else {
							return true;
						}

					}
				}
				
			}

		/**
		 * 通过拦截
		 */
		return true;
	}

}
