package com.wxs.service.customer;

import com.wxs.entity.customer.TFrontUser;
import com.baomidou.mybatisplus.service.IService;
import com.wxs.entity.customer.TWxUser;
import org.wxs.core.util.BaseUtil;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户登录表 服务类
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
public interface ITFrontUserService extends IService<TFrontUser> {

    public TWxUser saveUserByWx(String wxUserInfo,String sessionId);

    public List<Map<String,Object>> getUserFriends(Long userId);
	
}
