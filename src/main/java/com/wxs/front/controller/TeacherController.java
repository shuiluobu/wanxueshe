package com.wxs.front.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wxs.entity.customer.TTeacher;
import com.wxs.service.customer.ITTeacherService;
import com.wxs.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wxs.core.bean.Rest;
import org.wxs.core.controller.CrudController;
import org.wxs.core.util.BaseUtil;

import java.util.Date;

/**
 * Created by Administrator on 2017/10/20 0020.
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController extends CrudController<TTeacher, ITTeacherService> {

    //日志管理
    public static final Logger logger = Logger.getLogger(TeacherController.class);

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
            @RequestParam(defaultValue="10")Integer limit,
            String teacherCode,String organizationId,String realName,String mobilePhone,String idCode,String sex,
            Model model){

        EntityWrapper<TTeacher> ew = new EntityWrapper<TTeacher>();
        if(StringUtils.isNotBlank(teacherCode)){
            ew.eq("teacherCode",teacherCode);
        }
        if(StringUtils.isNotBlank(organizationId)){
            ew.eq("organizationId",organizationId);
        }
        if(StringUtils.isNotBlank(realName)){
            ew.eq("realName",realName);
        }
        if(StringUtils.isNotBlank(mobilePhone)){
            ew.eq("mobilePhone",mobilePhone);
        }
        if(StringUtils.isNotBlank(idCode)){
            ew.eq("idCode",idCode);
        }
        if(StringUtils.isNotBlank(sex)){
            ew.eq("sex",sex);
        }
        Page<TTeacher> pageData = teacherService.selectPage(new Page<TTeacher>(page, limit),ew);
        return Rest.okPageData(pageData.getRecords(),pageData.getTotal());

    }
    /**
     * 到添加页面
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "teacher/add";
    }

    /**
     * 执行添加
     * @param teacher
     * @return
     */

    @RequestMapping("/doAdd")
    @ResponseBody
    public Rest doAdd(TTeacher teacher){
        try{
            teacher.setCreateTime(new Date());
            teacherService.insert(teacher);
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
        model.addAttribute("teacher",teacherService.selectById(id));
        return "teacher/edit";
    }

    /**
     * 执行编辑
     * @param teacher
     * @return
     */

    @RequestMapping("/doEdit")
    @ResponseBody
    public Rest doEdit(TTeacher teacher){
        try{
            teacherService.updateById(teacher);
        }catch (Exception e){
            logger.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
        return Rest.ok("编辑成功!");
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
