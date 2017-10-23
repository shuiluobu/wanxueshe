package com.wxs.service.customer.impl;

import com.wxs.entity.customer.TFrontUser;
import com.wxs.mapper.customer.TFrontUserMapper;
import com.wxs.service.customer.ITFrontUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登录表 服务实现类
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
@Service
public class TFrontUserServiceImpl extends ServiceImpl<TFrontUserMapper, TFrontUser> implements ITFrontUserService {
	
}
