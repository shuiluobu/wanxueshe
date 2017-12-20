package com.wxs.service.task;

import com.wxs.entity.task.TClock;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyh
 * @since 2017-12-20
 */
public interface ITClockService extends IService<TClock> {

    //根据 课时Id  获取 其下 所有的 签到
    public List<TClock> getAllByLessonId(Long lessonId);
	
}
