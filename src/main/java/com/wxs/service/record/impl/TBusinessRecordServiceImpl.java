package com.wxs.service.record.impl;

import com.wxs.entity.record.TBusinessRecord;
import com.wxs.mapper.record.TBusinessRecordMapper;
import com.wxs.service.record.ITBusinessRecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 机构不同业务的操作记录表 服务实现类
 * </p>
 *
 * @author wyh
 * @since 2018-01-05
 */
@Service
public class TBusinessRecordServiceImpl extends ServiceImpl<TBusinessRecordMapper, TBusinessRecord> implements ITBusinessRecordService {

    @Autowired
    private TBusinessRecordMapper businessRecordMapper;
    @Override
    public List<TBusinessRecord> getAllByStuId(Long studentId) {
        return businessRecordMapper.getAllByStuId(studentId);
    }

    @Override
    public Map<String, String> signInCourseLesson(Long taskId) {
        return businessRecordMapper.signInCourseLesson(taskId);
    }
}
