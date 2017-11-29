package com.wxs.front.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wxs.entity.course.TCourse;
import com.wxs.entity.course.TCourseCategory;
import com.wxs.entity.customer.TTeacher;
import com.wxs.entity.organ.TOrganization;
import com.wxs.service.course.ITCoursesService;
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
@RequestMapping("/course")
public class CoursesController  extends CrudController<TCourse,ITCoursesService> {

    //日志管理
    public static final Logger logger = Logger.getLogger(CoursesController.class);

    @ResponseBody
    @RequestMapping("/page")
    public Rest page(TCourse course){
        //mysql ,mapper.xml中不能加减，在这里处理,直接处理为 起始下标
        course.setPageStartIndex( (course.getPage()-1)*course.getLimit() );
        List<TCourse> list = getS().pageData(course);
        return Rest.okPageData(list,list.size());
    }

    /**
     * 到添加页面
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(Model model){
        //所有教育机构
        model.addAttribute("organizations",organizationService.selectList(new EntityWrapper<TOrganization>()));
        //所有课程类型
        model.addAttribute("courseCategorys",courseCategoryService.selectList(new EntityWrapper<TCourseCategory>()));
        return "course/add";
    }
    /**
     * 执行添加
     * @param course
     * @return
     */
    @RequestMapping("/doAdd")
    @ResponseBody
    public Rest doAdd(TCourse course){
        try{
            course.setCreateTime(new Date());
            courseService.insert(course);
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
        //所有教育机构
        model.addAttribute("organizations",organizationService.selectList(new EntityWrapper<TOrganization>()));
        //所有课程类型
        model.addAttribute("courseCategorys",courseCategoryService.selectList(new EntityWrapper<TCourseCategory>()));
        model.addAttribute("course",courseService.selectById(id));
        return "course/edit";
    }
    /**
     * 执行编辑
     * @param course
     * @return
     */
    @RequestMapping("/doEdit")
    @ResponseBody
    public Rest doEdit(TCourse course){
        try{
            courseService.updateById(course);
        }catch (Exception e){
            logger.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
        return Rest.ok("编辑成功!");
    }


    @Override
    public String getViewName() {
        return "course";
    }

    @Override
    public String getModelName() {
        return "course";
    }
}
