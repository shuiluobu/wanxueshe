package com.wxs.service.task.impl;

import com.wxs.entity.task.TClock;
import com.wxs.mapper.task.TClockMapper;
import com.wxs.service.task.ITClockService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wyh
 * @since 2017-12-20
 */
@Service
public class TClockServiceImpl extends ServiceImpl<TClockMapper, TClock> implements ITClockService {

    @Autowired
    private TClockMapper clockMapper;
    @Override
    public List<TClock> getAllByLessonId(Long lessonId) {
        return clockMapper.getAllByLessonId(lessonId);
    }
}
