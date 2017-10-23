package com.wxs.mapper.sys;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wxs.entity.sys.SysRoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 角色菜单关联表 Mapper 接口
 * </p>
 *
 * @author GaoJun.Zhou
 * @since 2017-06-30
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

	List<Map<String, Object>> selectAuthByRoleId(@Param("roleId") String roleId);

}