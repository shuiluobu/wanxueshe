package com.wxs.service.sys;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wxs.entity.sys.SysUserRole;
import com.wxs.mapper.sys.SysUserRoleMapper;
import com.wxs.service.sys.impl.ISysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author GaoJun.Zhou
 * @since 2017-06-30
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {
	
}
