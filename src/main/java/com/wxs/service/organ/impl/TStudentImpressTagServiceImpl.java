package com.wxs.service.organ.impl;

import com.wxs.entity.organ.TStudentImpressTag;
import com.wxs.mapper.organ.TStudentImpressTagMapper;
import com.wxs.service.organ.ITStudentImpressTagService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 学生的印象标签 服务实现类
 * </p>
 *
 * @author wyh
 * @since 2018-01-03
 */
@Service
public class TStudentImpressTagServiceImpl extends ServiceImpl<TStudentImpressTagMapper, TStudentImpressTag> implements ITStudentImpressTagService {

    @Autowired
    private TStudentImpressTagMapper studentImpressTagMapper;
    @Override
    public List<TStudentImpressTag> getAllByStuId(Long studentId) {
        return studentImpressTagMapper.getAllByStuId(studentId);
    }
}
