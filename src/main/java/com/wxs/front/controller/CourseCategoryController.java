package com.wxs.front.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wxs.entity.course.TCourseCategory;
import com.wxs.entity.organ.TOrganization;
import com.wxs.entity.sys.SysUser;
import com.wxs.service.course.ITCourseCategoryService;
import org.apache.log4j.Logger;
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
import java.util.List;

/**
 * Created by wyh on 2017/11/28.
 */
@Controller
@RequestMapping("/courseCategory")
public class CourseCategoryController extends CrudController<TCourseCategory,ITCourseCategoryService> {

    //日志管理
    public static final Logger logger = Logger.getLogger(CourseCategoryController.class);

    @Value("${web.upload-path}")
    private String imgUploadPath;
    @Value("${web.load-path}")
    private String imgLoadPath;

    @RequestMapping("/page")
    @ResponseBody
    public Rest page(TCourseCategory courseCategory){
        try{
            //mysql ,mapper.xml中不能加减，在这里处理,直接处理为 起始下标
            courseCategory.setPageStartIndex( (courseCategory.getPage()-1)*courseCategory.getLimit() );
            List<TCourseCategory> list = getS().pageData(courseCategory);
            return Rest.okPageData(list,list.size());
        }catch (Exception e){
            logger.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 到编辑页面
     * @return
     */
    @RequestMapping("/toDetail")
    public String toDetail(String id,Model model){
        //所有教育机构
        TCourseCategory courseCategory = courseCategoryService.selectById(id);
        courseCategory.setOrganName(organizationService.selectById(courseCategory.getOrganId()).getOrganName());
        model.addAttribute("courseCategory",courseCategory);
        return "courseCategory/detail";
    }

    /**
     * 到添加页面
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(Model model){
        //所有教育机构
        model.addAttribute("organizations",organizationService.selectList(new EntityWrapper<TOrganization>()));
        return "courseCategory/add";
    }
    /**
     * 执行添加
     * @param courseCategory
     * @return
     */
    @RequestMapping("/doAdd")
    @ResponseBody
    public Rest doAdd(TCourseCategory courseCategory){
        try{
            courseCategory.setCreateTime(new Date());
            courseCategoryService.insert(courseCategory);
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
        model.addAttribute("courseCategory",courseCategoryService.selectById(id));
        return "courseCategory/edit";
    }
    /**
     * 执行编辑
     * @param courseCategory
     * @return
     */
    @RequestMapping("/doEdit")
    @ResponseBody
    public Rest doEdit(TCourseCategory courseCategory){
        try{
            courseCategoryService.updateById(courseCategory);
        }catch (Exception e){
            logger.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
        return Rest.ok("编辑成功!");
    }

    /**
     * 上传头像图片
     * @param id
     * @return
     */

    @RequestMapping("/uploadCover")
    @ResponseBody
    public Rest uploadCover(
            @RequestParam(required = false,defaultValue = "") MultipartFile file,
            @RequestParam(required = false,defaultValue = "") String id, HttpSession session){
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
            //文件夹-课程分类
            savePath += "courseCategory";
            loadPath += "courseCategory";
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
        return "courseCategory";
    }

    @Override
    public String getModelName() {
        return "courseCategory";
    }
}
