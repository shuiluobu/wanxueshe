package com.wxs.front.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wxs.entity.course.TClassLesson;
import com.wxs.entity.course.TCourse;
import com.wxs.entity.customer.TTeacher;
import com.wxs.service.course.ITClassLessonService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wxs.core.bean.Rest;
import org.wxs.core.controller.CrudController;
import org.wxs.core.util.BaseUtil;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
@Controller
@RequestMapping("/classLesson")
public class ClassLessonController extends CrudController<TClassLesson,ITClassLessonService> {


    //日志管理
    public static final Logger logger = Logger.getLogger(CoursesController.class);

    @ResponseBody
    @RequestMapping("/page")
    public Rest page(TClassLesson classLesson){
        //mysql ,mapper.xml中不能加减，在这里处理,直接处理为 起始下标
        classLesson.setPageStartIndex( (classLesson.getPage()-1)*classLesson.getLimit() );
        List<TClassLesson> list = getS().pageData(classLesson);
        return Rest.okPageData(list,list.size());
    }

    /**
     * 到添加页面
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(Model model){
        //所有课程
        model.addAttribute("courses",courseService.selectList(new EntityWrapper<TCourse>()));
        //所有老师
        model.addAttribute("teachers",teacherService.selectList(new EntityWrapper<TTeacher>()));
        return "classLesson/add";
    }
    /**
     * 执行添加
     * @param classLesson
     * @return
     */
    @RequestMapping("/doAdd")
    @ResponseBody
    public Rest doAdd(TClassLesson classLesson){
        try{
            classLesson.setCreateTime(new Date());
            classLessonService.insert(classLesson);
        }catch (Exception e){
            logger.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
        return Rest.ok("添加成功!");
    }

    /**
     * 到编辑页面
     * @return
     */
    @RequestMapping("/toEdit")
    public String toEdit(String id,Model model){
        //所有课程
        model.addAttribute("courses",courseService.selectList(new EntityWrapper<TCourse>()));
        //所有老师
        model.addAttribute("teachers",teacherService.selectList(new EntityWrapper<TTeacher>()));
        model.addAttribute("classLesson",getS().selectById(id) );
        return "classLesson/edit";
    }
    /**
     * 执行编辑
     * @param classLesson
     * @return
     */
    @RequestMapping("/doEdit")
    @ResponseBody
    public Rest doEdit(TClassLesson classLesson){
        try{
            classLessonService.updateById(classLesson);
        }catch (Exception e){
            logger.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
        return Rest.ok("编辑成功!");
    }

    @Override
    public String getViewName() {
        return "classLesson";
    }

    @Override
    public String getModelName() {
        return "classLesson";
    }
}
