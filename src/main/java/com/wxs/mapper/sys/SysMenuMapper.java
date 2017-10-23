package com.wxs.mapper.sys;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wxs.entity.sys.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 菜单表 Mapper 接口
 * </p>
 *
 * @author GaoJun.Zhou
 * @since 2017-06-30
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

	List<Map<String, Object>> selectMenuByUid(@Param("uid") String uid, @Param("pid") String pid);

	List<String> selectResourceByUid(@Param("uid") String uid);
	
}