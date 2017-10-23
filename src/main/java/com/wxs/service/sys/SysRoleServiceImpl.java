package com.wxs.service.sys;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wxs.entity.sys.SysRole;
import com.wxs.mapper.sys.SysRoleMapper;
import com.wxs.service.sys.impl.ISysRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author GaoJun.Zhou
 * @since 2017-06-30
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
	
}
