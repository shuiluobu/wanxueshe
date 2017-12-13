package com.wxs.config;

import com.wxs.admin.intercptror.LoginInterceptor;
import com.wxs.admin.intercptror.ResourceInterceptor;
import com.wxs.admin.intercptror.WxInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 自定义spring mvc配置
 * 更多配置可以查看WebMvcConfigurerAdapter的类的API,因其是WebMvcConfigurer接口的实现，所以WebMvcConfigurer的API方法也可以用来配置MVC
 * 只是实现WebMvcConfigurer接口的话，要实现所有的方法，非常的麻烦
 * 所以还是推荐使用继承WebMvcConfigurerAdapter类来处理
 * @author Skyer
 * @date 2017年8月17日 下午4:01:10
 */
@Configuration
public class MyWebMvcConfig extends WebMvcConfigurerAdapter {

	@Autowired
	LoginInterceptor loginInterceptor;
	@Autowired
	ResourceInterceptor resourceInterceptor;
	@Autowired
	WxInterceptor wxInterceptor;

	/**
	 * 自定义拦截器
	 * @author skyer
	 * @date 2017年8月17日 下午4:01:46
	 * @see WebMvcConfigurerAdapter#addInterceptors(InterceptorRegistry)
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// addPathPatterns 用于添加拦截规则
	    // excludePathPatterns 用户排除拦截
		registry.addInterceptor(loginInterceptor).addPathPatterns("/**").
				excludePathPatterns("/login").excludePathPatterns("/app/**");
		registry.addInterceptor(wxInterceptor).addPathPatterns("/app/**");
		registry.addInterceptor(resourceInterceptor).addPathPatterns("/**");
		super.addInterceptors(registry);
	}

}
