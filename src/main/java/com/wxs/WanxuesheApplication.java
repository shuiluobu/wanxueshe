package com.wxs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;


// 该 @SpringBootApplication 注解等价于以默认属性使用:
// @Configuration
// @EnableAutoConfiguration
// @ComponentScan
@MapperScan("com.wxs.mapper")
@SpringBootApplication
@EnableCaching(proxyTargetClass = true) // 开启缓存功能
public class WanxuesheApplication extends SpringBootServletInitializer implements CommandLineRunner {
	// 入口
	public static void main(String[] args) {
		SpringApplication.run(WanxuesheApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WanxuesheApplication.class);
	}

	// springboot运行后此方法首先被调用
	// 实现CommandLineRunner抽象类中的run方法
	@Override
	public void run(String... args) throws Exception {
		System.out.println("springboot启动完成！");
	}
}