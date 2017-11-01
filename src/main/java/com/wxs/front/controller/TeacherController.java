package com.wxs.front.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wxs.entity.customer.TStudent;
import com.wxs.entity.customer.TTeacher;
import com.wxs.entity.sys.SysMenu;
import com.wxs.entity.sys.SysUser;
import com.wxs.service.customer.ITTeacherService;
import com.wxs.service.sys.impl.ISysMenuService;
import com.wxs.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wxs.core.anno.Resource;
import org.wxs.core.bean.Rest;
import org.wxs.core.controller.CrudController;

/**
 * Created by Administrator on 2017/10/20 0020.
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController extends CrudController<TTeacher, ITTeacherService> {
    @Autowired
    private ITTeacherService teacherService;

    @RequestMapping(value = "/teacherOne/{tid}")
    public Result teacherOne(@PathVariable("tid") Long tid){
        //老师基本信息
        return Result.of(teacherService.getTeacharInfoById(tid));
    }

    @ResponseBody
    @RequestMapping("/page")
    public Rest page(
            @RequestParam(required = true,defaultValue="1") Integer page,
            @RequestParam(defaultValue="10")Integer size, String keyword, Model model){

        EntityWrapper<TTeacher> ew = new EntityWrapper<TTeacher>();
        if(StringUtils.isNotBlank(keyword)){
            ew.like("realName", keyword);
        }
        Page<TTeacher> pageData = teacherService.selectPage(new Page<TTeacher>(page, size),ew);

        return Rest.okPageData(pageData.getRecords(),pageData.getPages());

    }


    @Override
    public String getViewName() {
        // TODO Auto-generated method stub
        return "teacher";
    }

    @Override
    public String getModelName() {
        // TODO Auto-generated method stub
        return "teacher";
    }

}
