package com.wxs.admin.intercptror;

import com.wxs.util.WebUtil;
import org.springframework.stereotype.Component;
import org.wxs.core.anno.PassLogin;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 登录拦截器
 * Created by Gaojun.Zhou 2017年7月6日
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub

		if (handler instanceof HandlerMethod) {
			/**
			 * 登录验证
			 */
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			
			PassLogin passLogin = method.getAnnotation(PassLogin.class);
			if (passLogin != null) {
				//跳过登录拦截
				return true;
			}else{
				//登录验证
				if(request.getSession().getAttribute("session_user") == null){
					if (WebUtil.isAjax(request)) {
						throw new RuntimeException("您的登录已失效,请重新登录");
					} else {
						WebUtil.clearRedirectLogin(request, response);
						return false;
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
