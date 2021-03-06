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

    public List<Map<String,Object>> getNearOrgans(double latitude, double longitude );

    List<Map<String,Object>> getOrganFllowUserList(Long organId,Long LoginUserId);

    List<Map<String, Object>> queryOrganByLikeName(String organName);

    List<Map<String,Object>> getFollowOrganInfoByUserId(Long userId);

    List<Map<String,Object>> choicenessPhotos(Long organId,int page,int rows);

    Map<String, Object> followOrgan(Long organId, Long loginUserId);

    Map<String, Object> unFollowOrgan(Long organId, Long loginUserId);
	
}
