package com.wxs.service.sys;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wxs.entity.sys.SysSetting;
import com.wxs.mapper.sys.SysSettingMapper;
import com.wxs.service.sys.impl.ISysSettingService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统设置表 服务实现类
 * </p>
 *
 * @author GaoJun.Zhou
 * @since 2017-06-30
 */
@Service
public class SysSettingServiceImpl extends ServiceImpl<SysSettingMapper, SysSetting> implements ISysSettingService {
	
}
