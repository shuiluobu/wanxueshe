package com.wxs.service.course;

import com.wxs.entity.course.TClassLesson;
import com.baomidou.mybatisplus.service.IService;
import com.wxs.mapper.course.TClassLessonMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
public interface ITClassLessonService extends IService<TClassLesson> {

	//分页+混合条件 查询课时
	List<TClassLesson> pageData(TClassLesson classLesson);

	public Map<String,Object> getOneClassLession(Long lessionId);
	public Map<String,Object> getOneClassLession(Long lessionId,Long userId);
}
