package com.wxs.admin.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger-ui配置
 * Created by Gaojun.Zhou 2017年6月19日
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				//.apis(RequestHandlerSelectors.basePackage("com.vacomall.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return null;
//		return new ApiInfoBuilder().title("Alex轻量级权限管理框架")
//				.termsOfServiceUrl("http://blog.jdoop.cn/")
//				.description("springmvc + swagger2 轻量级权限管理框架")
//				.contact(new Contact("JamesZhou", "http://blog.jdoop.cn/", "gaojun.zhou@qq.com"))
//				.version("1.0.0")
//				.build();
	}
}
