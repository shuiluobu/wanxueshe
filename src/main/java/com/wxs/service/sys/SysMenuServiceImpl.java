package com.wxs.service.sys;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.base.Splitter;
import com.wxs.entity.sys.SysMenu;
import com.wxs.entity.sys.SysRoleMenu;
import com.wxs.mapper.sys.SysMenuMapper;
import com.wxs.mapper.sys.SysRoleMenuMapper;
import com.wxs.service.sys.impl.ISysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxs.core.util.BaseUtil;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author GaoJun.Zhou
 * @since 2017-06-30
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
	@Autowired
	private SysMenuMapper sysMenuMapper;

	/**
	 * 分配权限
	 */
	@Override
	public void updateAuth(String roleId, String menuIds) {
		// TODO Auto-generated method stub

		sysRoleMenuMapper.delete(new EntityWrapper<SysRoleMenu>().eq("roleId", roleId));
		if (StringUtils.isNotBlank(menuIds)) {
			List<String> menuIdList = Splitter.on(",").splitToList(menuIds);
			for (String menuId : menuIdList) {
				SysRoleMenu roleMenu = new SysRoleMenu();
				roleMenu.setId(BaseUtil.uuid());
				roleMenu.setMenuId(menuId);
				roleMenu.setRoleId(roleId);
				sysRoleMenuMapper.insert(roleMenu);
			}
		}

	}

	@Override
	public List<Map<String, Object>> selectMenuByUid(String uid, String pid) {
		// TODO Auto-generated method stub
		return sysMenuMapper.selectMenuByUid(uid, pid);
	}

	@Override
	public List<String> selectResourceByUid(String uid) {
		// TODO Auto-generated method stub
		return sysMenuMapper.selectResourceByUid(uid);
	}

}
