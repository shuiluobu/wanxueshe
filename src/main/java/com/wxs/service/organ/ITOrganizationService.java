package com.wxs.service.organ;

import com.wxs.entity.organ.TOrganization;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
public interface ITOrganizationService extends IService<TOrganization> {

    public Map<String,Object> getOrganOutline(Long organId,Long userId);

    public List<TOrganization> getNearOrgans(double latitude, double longitude );

    List<Map<String,Object>> getOrganFllowUserList(Long organId);
	
}
