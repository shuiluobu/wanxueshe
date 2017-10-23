package com.wxs.service.sys;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wxs.entity.sys.SysRoleMenu;
import com.wxs.mapper.sys.SysRoleMenuMapper;
import com.wxs.service.sys.impl.ISysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色菜单关联表 服务实现类
 * </p>
 *
 * @author GaoJun.Zhou
 * @since 2017-06-30
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

	@Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
	 
	@Override
	public List<Map<String, Object>> selectAuthByRoleId(String id) {
		// TODO Auto-generated method stub
		return sysRoleMenuMapper.selectAuthByRoleId(id);
	}
	
}
