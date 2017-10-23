package com.wxs.service.customer;

import com.wxs.entity.customer.TTeacher;
import com.baomidou.mybatisplus.service.IService;

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
}
