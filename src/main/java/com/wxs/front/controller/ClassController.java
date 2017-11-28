package com.wxs.front.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wxs.entity.course.TClass;
import com.wxs.entity.course.TCourse;
import com.wxs.entity.customer.TTeacher;
import com.wxs.entity.organ.TOrganization;
import com.wxs.service.course.ITClassService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wxs.core.bean.Rest;
import org.wxs.core.controller.CrudController;
import org.wxs.core.util.BaseUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  班级管理
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
@Controller
@RequestMapping("/tClass")
public class ClassController extends CrudController<TClass,ITClassService> {

    //日志管理
    public static final Logger logger = Logger.getLogger(ClassController.class);

    /**
     * //分页+混合条件 查询 班级
     * @return
     */
    @ResponseBody
    @RequestMapping("/page")
    public Rest page(TClass tClass){
        //mysql ,mapper.xml中不能加减，在这里处理,直接处理为 起始下标
        tClass.setPageStartIndex( (tClass.getPage()-1)*tClass.getLimit() );
        List<TClass> list = tClassService.pageData(tClass);
        return Rest.okPageData(list,list.size());
    }
    /**
     * 到添加页面
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(Model model){
        //所有老师
        model.addAttribute("teachers",teacherService.selectList(new EntityWrapper<TTeacher>()));
        //所有教育机构
        model.addAttribute("organizations",organizationService.selectList(new EntityWrapper<TOrganization>()));
        //所有课程
        model.addAttribute("courses",coursesService.selectList(new EntityWrapper<TCourse>()));
        return "tClass/add";
    }

    /**
     * 执行添加
     * @param tClass
     * @return
     */

    @RequestMapping("/doAdd")
    @ResponseBody
    public Rest doAdd(TClass tClass){
        try{
            tClass.setCreateTime(new Date());
            tClassService.insert(tClass);
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
        //所有老师
        model.addAttribute("teachers",teacherService.selectList(new EntityWrapper<TTeacher>()));
        //所有教育机构
        model.addAttribute("organizations",organizationService.selectList(new EntityWrapper<TOrganization>()));
        //所有课程
        model.addAttribute("courses",coursesService.selectList(new EntityWrapper<TCourse>()));
        model.addAttribute("tClass",tClassService.selectById(id));
        return "tClass/edit";
    }

    /**
     * 执行编辑
     * @param tClass
     * @return
     */

    @RequestMapping("/doEdit")
    @ResponseBody
    public Rest doEdit(TClass tClass){
        try{
            tClassService.updateById(tClass);
        }catch (Exception e){
            logger.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
        return Rest.ok("编辑成功!");
    }


    @Override
    public String getViewName() {
        return "tClass";
    }

    @Override
    public String getModelName() {
        return "tClass";
    }
}
