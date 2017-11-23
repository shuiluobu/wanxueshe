package com.wxs.front.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wxs.entity.customer.TParent;
import com.wxs.service.customer.ITParentService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wxs.core.bean.Rest;
import org.wxs.core.controller.CrudController;
import org.wxs.core.util.BaseUtil;

import java.util.Date;

/**
 * Created by wyh on 2017/11/20.
 */
@Controller
@RequestMapping("/parent")
public class ParentController extends CrudController<TParent,ITParentService> {
    //日志管理
    public static final Logger logger = Logger.getLogger(ParentController.class);
    @Autowired
    private ITParentService parentService;

    @RequestMapping("/page")
    @ResponseBody
    public Rest page(@RequestParam(required = true,defaultValue = "1")Integer page,
                     @RequestParam(defaultValue="10")Integer limit,
                     String userId, String realName, String wxCode, String mobilePhone, String idCode, String sex,
                     Model model){
        try{
            EntityWrapper<TParent> ew = new EntityWrapper<TParent>();
            if(StringUtils.isNotBlank(userId)){
                ew.eq("userId",userId.trim());
            }
            if(StringUtils.isNotBlank(realName)){
                ew.eq("realName",realName.trim());
            }
            if(StringUtils.isNotBlank(wxCode)){
                ew.eq("wxCode",wxCode.trim());
            }
            if(StringUtils.isNotBlank(mobilePhone)){
                ew.eq("mobilePhone",mobilePhone.trim());
            }
            if(StringUtils.isNotBlank(idCode)){
                ew.eq("idCode",idCode.trim());
            }
            if(StringUtils.isNotBlank(sex)){
                ew.eq("sex",sex.trim());
            }
            Page<TParent> pageData = parentService.selectPage(new Page<>(page,limit),ew);
            return Rest.okPageData(pageData.getRecords(),pageData.getTotal());
        }catch (Exception e){
            logger.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 到添加页面
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "parent/add";
    }

    /**
     * 执行添加
     * @param parent
     * @return
     */

    @RequestMapping("/doAdd")
    @ResponseBody
    public Rest doAdd(TParent parent){
        try{
            parent.setCreateTime(new Date());
            parentService.insert(parent);
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
        model.addAttribute("parent",parentService.selectById(id));
        return "parent/edit";
    }

    /**
     * 执行编辑
     * @param parent
     * @return
     */

    @RequestMapping("/doEdit")
    @ResponseBody
    public Rest doEdit(TParent parent){
        try{
            parentService.updateById(parent);
        }catch (Exception e){
            logger.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
        return Rest.ok("编辑成功!");
    }
    @Override
    public String getViewName() {
        return "parent";
    }

    @Override
    public String getModelName() {
        return "parent";
    }
}
