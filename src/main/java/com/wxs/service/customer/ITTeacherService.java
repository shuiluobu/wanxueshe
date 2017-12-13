package com.wxs.service.customer;

import com.wxs.entity.customer.TTeacher;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
public interface ITTeacherService extends IService<TTeacher> {
    public Optional<Map> getTeacharInfoById(Long tId);
    public Map<String,Object> getTeacherOutline(Long teacherId,Long userId) ;
    public List<Map<String,Object>> getOrganFllowUserList(Long organId,Long loginUserId);
    //根据用户给Id 获取老师信息
    public TTeacher getByUserId(Long userId);
}
