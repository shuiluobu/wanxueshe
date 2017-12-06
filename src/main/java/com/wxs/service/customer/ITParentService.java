package com.wxs.service.customer;

import com.wxs.entity.customer.TParent;
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
public interface ITParentService extends IService<TParent> {
    List<Map<String,Object>> getStudentByParent(Long parentId);
    List<Map<String,Object>> getParentFllowUserList(Long parentId);
    List<Map<String, Object>> getFllowUsers(List<Long> userIds);
}
