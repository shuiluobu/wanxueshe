package com.wxs.service.sys;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wxs.entity.sys.SysUser;
import com.wxs.entity.sys.SysUserRole;
import com.wxs.mapper.sys.SysUserMapper;
import com.wxs.service.sys.impl.ISysUserRoleService;
import com.wxs.service.sys.impl.ISysUserService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxs.core.util.BaseUtil;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author GaoJun.Zhou
 * @since 2017-06-30
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

	@Autowired
    private ISysUserRoleService sysUserRoleService;
	
	@Override
	public void addUser(SysUser user, String[] roleIds) {
		// TODO Auto-generated method stub
		this.insert(user);
		if(!ArrayUtils.isEmpty(roleIds)){
			for(String rid : roleIds){
				SysUserRole ur = new SysUserRole();
				ur.setRoleId(rid);
				ur.setId(BaseUtil.uuid());
				ur.setUserId(user.getId());
				sysUserRoleService.insert(ur);
			}
			
		}
	}

	@Override
	public void updateUser(SysUser user, String[] roleIds) {
		// TODO Auto-generated method stub
		this.updateById(user);
		sysUserRoleService.delete(new EntityWrapper<SysUserRole>().eq("userId",user.getId()));
		if(!ArrayUtils.isEmpty(roleIds)){
			for(String rid : roleIds){
				SysUserRole ur = new SysUserRole();
				ur.setRoleId(rid);
				ur.setId(BaseUtil.uuid());
				ur.setUserId(user.getId());
				sysUserRoleService.insert(ur);
			}
			
		}
		
	}
	
	@Override
	public void deleteUser(String[] id) {
		// TODO Auto-generated method stub
		if(!ArrayUtils.isEmpty(id)){
			for(String d : id){
				this.deleteById(d);
				sysUserRoleService.delete(new EntityWrapper<SysUserRole>().eq("userId",d));
			}
		}
	}
	
}
