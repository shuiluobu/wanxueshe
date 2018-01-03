package com.wxs.service.organ;

import com.wxs.entity.organ.TStudentImpressTag;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 学生的印象标签 服务类
 * </p>
 *
 * @author wyh
 * @since 2018-01-03
 */
public interface ITStudentImpressTagService extends IService<TStudentImpressTag> {

    //获取某机构端 学生 的 所有 印象标签
    List<TStudentImpressTag> getAllByStuId(Long studentId);
}
