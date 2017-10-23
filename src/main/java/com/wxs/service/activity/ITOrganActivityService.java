package com.wxs.service.activity;

import com.baomidou.mybatisplus.service.IService;
import com.wxs.entity.activity.TOrganActivity;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author skyer
 * @since 2017-09-29
 */
public interface ITOrganActivityService extends IService<TOrganActivity> {
	public List<TOrganActivity> getActivityOfOrgan(Long organId);
}
