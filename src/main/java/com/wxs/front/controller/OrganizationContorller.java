package com.wxs.front.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wxs.entity.organ.TOrganization;
import com.wxs.entity.sys.SysUser;
import com.wxs.service.customer.ITStudentService;
import com.wxs.service.organ.ITOrganizationService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.wxs.core.bean.Rest;
import org.wxs.core.controller.CrudController;
import org.wxs.core.util.BaseUtil;
import org.wxs.core.util.OsppyUtil;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wyh on 2017/11/20.
 */
@Controller
@RequestMapping("/organization")
public class OrganizationContorller extends CrudController<TOrganization,ITOrganizationService> {
    //日志管理
    public static final Logger logger = Logger.getLogger(OrganizationContorller.class);

    @Value("${web.upload-path}")
    private String imgUploadPath;
    @Value("${web.load-path}")
    private String imgLoadPath;

    @Autowired
    private  ITOrganizationService organizationService;

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "frontPages/organization/eduAgendaDetail";
    }

    @RequestMapping("/toTemp")
    public String toTemp(){
        return "frontPages/organization/signInDetail";
    }

    @RequestMapping("/page")
    @ResponseBody
    public Rest page(@RequestParam(required = true,defaultValue = "1")Integer page,
                     @RequestParam(defaultValue="10")Integer limit,
                     String organCode,String organName,String linkman,String mobilePhone,String leval,
                     Model model){
        try{
            EntityWrapper<TOrganization> ew = new EntityWrapper<TOrganization>();
            if(StringUtils.isNotBlank(organCode)){
                ew.eq("organCode",organCode.trim());
            }
            if(StringUtils.isNotBlank(organName)){
                ew.like("organName",organName.trim());
            }
            if(StringUtils.isNotBlank(linkman)){
                ew.eq("linkman",linkman.trim());
            }
            if(StringUtils.isNotBlank(mobilePhone)){
                ew.like("mobilePhone",mobilePhone.trim());
            }
            if(StringUtils.isNotBlank(leval)){
                ew.eq("leval",leval.trim());
            }
            Page<TOrganization> pageData = organizationService.selectPage(new Page<>(page,limit),ew);
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
        return "organization/add";
    }

    /**
     * 执行添加
     * @param organization
     * @return
     */

    @RequestMapping("/doAdd")
    @ResponseBody
    public Rest doAdd(TOrganization organization, HttpSession session){
        try{
            SysUser sysUser = (SysUser)session.getAttribute("session_user");
            organization.setCreateUser(Long.parseLong( sysUser.getId() ));
            organization.setCreateTime(new Date());
            organizationService.insert(organization);
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
        model.addAttribute("organization",organizationService.selectById(id));
        return "organization/edit";
    }

    /**
     * 执行编辑
     * @param organization
     * @return
     */

    @RequestMapping("/doEdit")
    @ResponseBody
    public Rest doEdit(TOrganization organization){
        try{
            organizationService.updateById(organization);
        }catch (Exception e){
            logger.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
        return Rest.ok("编辑成功!");
    }
    /**
     * 到编辑页面
     * @return
     */
    @RequestMapping("/toDetail")
    public String toDetail(String id,Model model){
        model.addAttribute("organization",organizationService.selectById(id));
        return "organization/detail";
    }
    /**
     * 上传头像图片
     * @param id
     * @return
     */

    @RequestMapping("/uploadLogoImg")
    @ResponseBody
    public Rest uploadLogoImg(
            @RequestParam(required = false,defaultValue = "") MultipartFile file,
            @RequestParam(required = false,defaultValue = "") String id,HttpSession session){
        String loadPath = "";
        try{
            //如果Id为空，即用户创建时候就上传图片就为空,  用 创建人id代替
            if(id.trim().equals("undefined")){
                SysUser sysUser = (SysUser)session.getAttribute("session_user");
                id = sysUser.getId().toString();
            }
            //文件后缀
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.indexOf(".")+1);
            //读取系统文件分隔符
            String FileSeparator = OsppyUtil.osSeparator();
            //文件保存根路径
            String savePath = imgUploadPath;
            //文件读取根路径
            loadPath = imgLoadPath;
            //文件夹-学生
            savePath += "organization";
            loadPath += "organization";
            //判断目录是否存在，不存在则创建
            savePath = savePath.replace("/", FileSeparator);
            File newFile = new File(savePath);
            if(!newFile.exists() || !newFile.isDirectory()){
                newFile.mkdirs();//会创建所有的目录
            }
            //当前时间 年月日
            Calendar calendar = Calendar.getInstance();
            String yMd = BaseUtil.toString(calendar.getTime(),"yyyyMMdd");
            //文件名称
            String curFileName = yMd + "_" + calendar.getTimeInMillis() + "_" + id+"."+suffix;
            savePath += "/"+curFileName;
            loadPath += "/"+curFileName;
            savePath = savePath.replace("/", FileSeparator);
            file.transferTo(new File(savePath));
        }catch (Exception e){
            logger.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
        return Rest.ok(loadPath);
    }

    @Override
    public String getViewName() {
        return "organization";
    }

    @Override
    public String getModelName() {
        return "organization";
    }
}
