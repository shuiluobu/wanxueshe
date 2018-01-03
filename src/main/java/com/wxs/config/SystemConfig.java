package com.wxs.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix="system")
@PropertySource("classpath:config/systemConfig.properties")
public class SystemConfig {
	
	private String maxCount;
	private String minCount;
	private String keyPreFix = null;
	public String getMaxCount() {
		return maxCount;
	}
	public void setMaxCount(String maxCount) {
		this.maxCount = maxCount;
	}
	public String getMinCount() {
		return minCount;
	}
	public void setMinCount(String minCount) {
		this.minCount = minCount;
	}

	public String getKeyPreFix() {
		return keyPreFix;
	}

	public void setKeyPreFix(String keyPreFix) {
		this.keyPreFix = keyPreFix;
	}

	@Override
	public String toString() {
		return "SystemConfig [maxCount=" + maxCount + ", minCount=" + minCount + "]";
	}
	
	

}
