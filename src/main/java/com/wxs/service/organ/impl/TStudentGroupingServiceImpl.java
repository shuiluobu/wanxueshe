package com.wxs.service.organ.impl;

import com.wxs.entity.organ.TStudentGrouping;
import com.wxs.mapper.organ.TStudentGroupingMapper;
import com.wxs.service.organ.ITStudentGroupingService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 学生分组 服务实现类
 * </p>
 *
 * @author wyh
 * @since 2018-01-03
 */
@Service
public class TStudentGroupingServiceImpl extends ServiceImpl<TStudentGroupingMapper, TStudentGrouping> implements ITStudentGroupingService {


    @Autowired
    private TStudentGroupingMapper studentGroupingMapper;
    @Override
    public List<TStudentGrouping> getAllByStuId(Long studentId) {
        return studentGroupingMapper.getAllByStuId(studentId);
    }
}
