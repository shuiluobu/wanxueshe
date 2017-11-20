package com.wxs.front.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wxs.entity.customer.TStudent;
import com.wxs.service.customer.ITStudentService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wxs.core.bean.Rest;
import org.wxs.core.controller.CrudController;

/**
 * Created by wyh on 2017/11/20.
 */
@Controller
@RequestMapping("/student")
public class StudentContorller extends CrudController<TStudent,ITStudentService> {

    @Autowired
    private  ITStudentService studentService;

    @RequestMapping("/page")
    @ResponseBody
    public Rest page(@RequestParam(required = true,defaultValue = "1")Integer page,
                     @RequestParam(defaultValue="10")Integer limit, String keyword, Model model){
        try{
            System.out.println("limit:      "+limit);
            EntityWrapper<TStudent> ew = new EntityWrapper<TStudent>();
            if(StringUtils.isNotBlank(keyword)){
                ew.like("nickName",keyword);
            }
            Page<TStudent> pageData = studentService.selectPage(new Page<>(page,limit),ew);
            return Rest.okPageData(pageData.getRecords(),pageData.getTotal());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public String getViewName() {
        return "student";
    }

    @Override
    public String getModelName() {
        return "student";
    }
}
