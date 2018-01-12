package com.wxs.companyWX.controller.course;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wxs.entity.course.TClass;
import com.wxs.entity.course.TClassLesson;
import com.wxs.service.common.IDictionaryService;
import com.wxs.service.course.ITClassLessonService;
import com.wxs.service.course.ITClassService;
import com.wxs.util.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxs.core.util.BaseUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/28.
 */
@RestController
@RequestMapping("/cClass")
public class CClassController {
    private static Logger log = Logger.getLogger(CClassController.class);
    @Autowired
    private ITClassService classService;
    @Autowired
    private ITClassLessonService classLessonService;
    @Autowired
    private IDictionaryService dictionaryService;
    /**
     * @Description : 根据 班级名称，机构Id,类型(我的班级,不限-所属机构的),用户Id  搜索 班级
     * @return com.wxs.util.Result
     * @Author : wyh
     * @Creation Date : 10:01 2017/12/28
     * @Params : [name, organId, type, userId]
     **/
    @RequestMapping("/searchByName")
    public Result searchByName(String name, Long organId,Integer type,Long userId){
        try{
            return Result.of(classService.searchByName(name,organId,type,userId));
        }catch (Exception e){
            log.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @Description :  教师Id+机构Id+班级类型+班级名称  混合搜索班级
     * @return com.wxs.util.Result
     * @Author : wyh
     * @Creation Date : 17:14 2018/1/10
     * @Params : [teacherId]
     **/
    @RequestMapping("/searchClass")
    public Result searchClass(Long teacherId,Long organId,String className,String classType){

        try{
            EntityWrapper<TClass> ew = new EntityWrapper<>();
            ew.eq("teacherId",teacherId);
            List<TClass> classList = classService.searchClass(teacherId,organId,className,classType);
            if(classList.size() >0){
                Map<String,String> classTypeMap = dictionaryService.getClassType();
                TClassLesson classLesson = null;
                for(TClass temp : classList){
                    temp.setClassType(classTypeMap.get(temp.getClassType()));
                    if(temp.getNextLessonId() != null){
                        classLesson = classLessonService.selectById(temp.getNextLessonId());
                        temp.setNextLessonName(classLesson.getLessonName());
                        temp.setNextLessonSTime(classLesson.getBeginTime());
                        temp.setNextLessonETime(classLesson.getEndTime());
                    }
                }
            }
            return Result.of(classList);
        }catch (Exception e){
            log.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
        return null;
    }
}
