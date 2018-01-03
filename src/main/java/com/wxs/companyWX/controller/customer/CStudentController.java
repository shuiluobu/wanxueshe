package com.wxs.companyWX.controller.customer;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wxs.entity.customer.TTeacher;
import com.wxs.entity.organ.TOrganParent;
import com.wxs.entity.organ.TOrganStudent;
import com.wxs.entity.organ.TStudentImpressTag;
import com.wxs.service.course.ITStudentCourseService;
import com.wxs.service.customer.ITTeacherService;
import com.wxs.service.organ.ITOrganParentService;
import com.wxs.service.organ.ITOrganStudentService;
import com.wxs.service.organ.ITStudentGroupingService;
import com.wxs.service.organ.ITStudentImpressTagService;
import com.wxs.util.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxs.core.util.BaseUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/25.
 */
@RestController
@RequestMapping("/cStudent")
public class CStudentController {

    private static Logger log = Logger.getLogger(CStudentController.class);
    @Autowired
    private ITOrganStudentService organStudentService;
    @Autowired
    private ITOrganParentService organParentService;
    @Autowired
    private ITStudentCourseService studentCourseService;
    @Autowired
    private ITStudentImpressTagService studentImpressTagService;
    @Autowired
    private ITStudentGroupingService studentGroupingService;
    @Autowired
    private ITTeacherService teacherService;

    /**
     * @Description : 根据学生姓名  搜索学生
     * @return com.wxs.util.Result
     * @Author : wyh
     * @Creation Date : 16:08 2017/12/25
     * @Params : [name,organId]
     **/
    @RequestMapping("/searchByName")
    public Result searchByName(String name,Long organId){

        try{
            return Result.of(organStudentService.searchByName(name,organId));
        }catch (Exception e){
            log.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description : 根据 教育机构Id 获取其下 所有学员
     * @return com.wxs.util.Result
     * @Author : wyh
     * @Creation Date : 16:10 2017/12/27
     * @Params : [organId]
     **/
    @RequestMapping("/getAllByOrganId")
    public Result getAllByOrganId(Long organId){

        try{
            return Result.of(organStudentService.getAllByOrganId(organId));
        }catch (Exception e){
            log.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description : 学生资料页面 -获取学生的信息
     * @return com.wxs.util.Result
     * @Author : wyh
     * @Creation Date : 18:00 2018/1/3
     * @Params : [studentId]
     **/
    @RequestMapping("/studentInfo")
    public Result studentInfo(Long studentId){
        Map resultMap = new HashMap();

        try{
            //学生
            TOrganStudent organStudent = organStudentService.selectById(studentId);
            resultMap.put("student",organStudent);
            //课程顾问 老师 姓名
            TTeacher tTeacher = teacherService.selectById(organStudent.getAdvisorTeacherId());
            if(tTeacher != null){
                resultMap.put("advisorTeacher",tTeacher.getRealName());
            }
            //家长，可能是两个
            resultMap.put("parent",organParentService.getAllByStuId(studentId));
            //在读 课程 数
            resultMap.put("courseNum",studentCourseService.getAllByOStudentId(studentId).size());
            //印象标签
            EntityWrapper<TStudentImpressTag> wrapper = new EntityWrapper<>();
            wrapper.setSqlSelect("tagDesc,sum(studentId) num");
            wrapper.eq("studentId",studentId);
            wrapper.groupBy("tagDesc");
            List<Map<String,Object>> tagList = studentImpressTagService.selectMaps(wrapper);
            resultMap.put("impressTag",tagList);
            //所属 分组标签 个数
            resultMap.put("groupLabelNum",studentGroupingService.getAllByStuId(studentId).size());

        }catch (Exception e){
            log.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
        return Result.of(resultMap);
    }
    /**
     * @Description : 删除单个机构学生
     * @return com.wxs.util.Result
     * @Author : wyh
     * @Creation Date : 18:02 2018/1/3
     * @Params : [studentId]
     **/
    @RequestMapping("/delOrganStudent")
    public Result delOrganStudent(Long studentId){

        try{
            organStudentService.deleteById(studentId);

        }catch (Exception e){
            log.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
        return Result.of("删除学生成功!");
    }
}
