package com.wxs.service.organ;

import com.wxs.entity.organ.TStudentGrouping;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 学生分组 服务类
 * </p>
 *
 * @author wyh
 * @since 2018-01-03
 */
public interface ITStudentGroupingService extends IService<TStudentGrouping> {

    //获取 某 学生 所属 的 所有分组
    List<TStudentGrouping> getAllByStuId(Long studentId);
	
}
