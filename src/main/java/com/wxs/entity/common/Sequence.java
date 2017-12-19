package com.wxs.entity.common;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author skyer
 * @since 2017-12-19
 */
public class Sequence extends Model<Sequence> {

    private static final long serialVersionUID = 1L;

	private String name;
	@TableField("current_value")
	private Long currentValue;
	private Integer increment;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(Long currentValue) {
		this.currentValue = currentValue;
	}

	public Integer getIncrement() {
		return increment;
	}

	public void setIncrement(Integer increment) {
		this.increment = increment;
	}

	@Override
	protected Serializable pkVal() {
		return this.name;
	}

}
