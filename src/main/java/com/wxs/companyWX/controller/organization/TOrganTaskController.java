package com.wxs.companyWX.controller.organization;

import com.wxs.entity.comment.TDyimg;
import com.wxs.entity.comment.TDynamicmsg;
import com.wxs.entity.course.TCourse;
import com.wxs.entity.customer.TFrontUser;
import com.wxs.entity.organ.TOrganComment;
import com.wxs.entity.organ.TOrganTask;
import com.wxs.entity.organ.TOrganTaskImg;
import com.wxs.service.comment.ITDyimgService;
import com.wxs.service.comment.ITDynamicmsgService;
import com.wxs.service.course.ITClassService;
import com.wxs.service.course.ITCoursesService;
import com.wxs.service.organ.ITOrganCommentService;
import com.wxs.service.organ.ITOrganTaskImgService;
import com.wxs.service.organ.ITOrganTaskService;
import com.wxs.util.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxs.core.util.BASE64Util;
import org.wxs.core.util.BaseUtil;
import org.wxs.core.util.OsppyUtil;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

/**
 * <p>
 *  机构 教务待办 所属任务 前端控制器
 * </p>
 *
 * @author wyh
 * @since 2017-12-08
 */
@RestController
@RequestMapping("/cOrganTask")
public class TOrganTaskController {

    private static Logger log = Logger.getLogger(TOrganTaskController.class);
    @Autowired
    private ITOrganTaskService organTaskService;
    @Autowired
    private ITOrganTaskImgService organTaskImgService;
    @Autowired
    private ITOrganCommentService organCommentService;
    @Autowired
    private ITDynamicmsgService dynamicmsgService;
    @Autowired
    private ITDyimgService dyimgService;
    @Autowired
    private ITCoursesService coursesService;
    @Autowired
    private ITClassService classService;

    @Value("${web.upload-path}")
    private String imgUploadPath;
    @Value("${web.load-path}")
    private String imgLoadPath;

    /**
     * @Description : 获取任务详情
     * @return com.wxs.util.Result
     * @Author : wyh
     * @Creation Date : 10:42 2017/12/15
     * @Params : [taskId]
     **/
    @RequestMapping("/getByTaskId")
    public Result getByTaskId(Long taskId){

        Map resultMap = new HashMap();
        try{
            TOrganTask organTask = organTaskService.getDetailByTaskId(taskId);
            resultMap.put("organTask",organTask);
            //获取任务下图片
            List<TOrganTaskImg> imgList = organTaskImgService.getAllByTaskId(taskId);
            if(imgList.size()>0){
                resultMap.put("imgList",imgList);
            }
            //获取任务下评论
            List<TOrganComment> commentList = organCommentService.getAllById(taskId,2);
            if(commentList.size()>0){
                resultMap.put("commentList",commentList);
            }
            return Result.of(resultMap);
        }catch (Exception e){
            e.printStackTrace();
            log.error(BaseUtil.getExceptionStackTrace(e));
        }finally {
            resultMap = null;//for gc
        }
        return null;
    }

    /**
     * @Description : 根据 教务待办Id + 类型 + 状态  获取 其下 子任务
     * @return com.wxs.util.Result
     * @Author : wyh
     * @Creation Date : 10:01 2017/12/13
     * @Params : [agendaId, type, status]  // status 0:未完成,1:已完成，2：全部
     **/
    @RequestMapping("/getAllByAgendaId")
    public Result getAllByAgendaId(Long agendaId,Integer type,Integer status){
        List<Integer> statuss = new ArrayList<>();
        try{
            if(status == 2){
                statuss.add(0);
                statuss.add(1);
                statuss.add(2);
            }else{
                statuss.add(status);
            }
            return Result.of(organTaskService.getAllByAgendaId(agendaId,type,statuss));
        }catch (Exception e){
            e.printStackTrace();
            log.error(BaseUtil.getExceptionStackTrace(e));
        }finally {
            statuss = null;//for gc
        }
        return null;
    }
    /**
     * @Description : 签到
     * @return com.wxs.util.Result
     * @Author : wyh
     * @Creation Date : 10:48 2017/12/13
     * @Params : [taskId]
     **/
    @RequestMapping("/signIn")
    public Result signIn(Long taskId){
        TOrganTask organTask = organTaskService.selectById(taskId);
        try{
            organTask.setStatus(1);
            organTask.setDoneTime(new Date());
            organTaskService.updateById(organTask);
            return Result.of("签到成功!");
        }catch (Exception e){
            e.printStackTrace();
            log.error(BaseUtil.getExceptionStackTrace(e));
        }finally {
            organTask = null;//for gc
        }
        return null;
    }
    /**
     * @Description : 批量签到
     * @return com.wxs.util.Result
     * @Author : wyh
     * @Creation Date : 11:50 2017/12/13
     * @Params : [taskIds]
     **/
    @RequestMapping("/batchSignIn")
    public Result batchSignIn(String taskIds){
        TOrganTask tempOrganTask = null;
        String[] arr = taskIds.split(",");
        try{
            for(int i=0;i<arr.length;i++){
                tempOrganTask = organTaskService.selectById(Long.parseLong(arr[i]));
                tempOrganTask.setStatus(1);
                tempOrganTask.setDoneTime(new Date());
                organTaskService.updateById(tempOrganTask);
            }
            return Result.of("批量签到成功!");
        }catch (Exception e){
            e.printStackTrace();
            log.error(BaseUtil.getExceptionStackTrace(e));
        }finally {
            tempOrganTask = null;//for gc
            arr = null;
        }
        return null;
    }

    /**
     * @Description : 批量请假
     * @return com.wxs.util.Result
     * @Author : wyh
     * @Creation Date : 11:50 2017/12/13
     * @Params : [taskIds]
     **/
    @RequestMapping("/batchskForleave")
    public Result batchskForleave(String taskIds){
        TOrganTask tempOrganTask = null;
        String[] arr = taskIds.split(",");
        try{
            for(int i=0;i<arr.length;i++){
                tempOrganTask = organTaskService.selectById(Long.parseLong(arr[i]));
                tempOrganTask.setStatus(2);
                organTaskService.updateById(tempOrganTask);
            }
            return Result.of("批量请假成功!");
        }catch (Exception e){
            e.printStackTrace();
            log.error(BaseUtil.getExceptionStackTrace(e));
        }finally {
            tempOrganTask = null;//for gc
            arr = null;
        }
        return null;
    }

    /**
     * @Description : 发布课堂点评
     * @return com.wxs.util.Result
     * @Author : wyh
     * @Creation Date : 16:52 2017/12/15
     * @Params : [taskId, content, imgs, power]
     **/
    @RequestMapping("/releaseClassComment")
    public Result releaseClassComment(Long taskId, String content, String imgs, Integer power, HttpSession session){

        TOrganTask tempOrganTask = null;
        try{
            //点评任务
            tempOrganTask = organTaskService.selectById(taskId);
            Long courseId = tempOrganTask.getCourseId();
            //所属课程
            TCourse course = coursesService.selectById(tempOrganTask.getCourseId());
//            TFrontUser frontUser = (TFrontUser)session.getAttribute("fronUser");
//            Long userId = frontUser.getId();
            Long userId = 1l;
            //插入到动态表
            TDynamicmsg dynamicmsg = new TDynamicmsg();
            dynamicmsg.setContent(content);//动态内容
            dynamicmsg.setCreateTime(new Date());//创建时间
            dynamicmsg.setUserId(userId);//创建人Id
            dynamicmsg.setPower(power);//权限
            dynamicmsg.setDynamicType("课堂点评");//动态类型
            dynamicmsg.setCourseId(courseId);//所属课程Id
            dynamicmsg.setClassId(classService.getByCourseId(courseId).getId());//所属班级Id
            dynamicmsg.setClassLessonId(tempOrganTask.getClassLessonId());//所属课时Id
            dynamicmsg.setStudentId(tempOrganTask.getStudentId());//所属学生Id
            dynamicmsg.setOrganId(course.getOrganizationId());//所属教育机构Id
            //插入
            dynamicmsgService.insert(dynamicmsg);
            //更新点评任务
            tempOrganTask.setDynamicId(dynamicmsg.getId());
            tempOrganTask.setDoneTime(new Date());//完成时间
            tempOrganTask.setDoneTime(new Date());
            tempOrganTask.setStatus(1);
            organTaskService.updateById(tempOrganTask);
            //保存图片
            uploadCommentImg(imgs,dynamicmsg.getId(),1);
            return Result.of("发布课堂评论成功!");
        }catch (Exception e){
            e.printStackTrace();
            log.error(BaseUtil.getExceptionStackTrace(e));
        }finally {
            tempOrganTask = null;//for gc
        }
        return null;
    }


    private void  uploadCommentImg(String imgs,Long dynamicId,Integer type){
//        String loadPath = "";
        try{

            if(imgs.trim().length()>0){
                //读取系统文件分隔符
                String FileSeparator = OsppyUtil.osSeparator();
                //文件保存根路径
                String savePath = imgUploadPath;
                //文件读取根路径
                String loadPath = imgLoadPath;
                //文件夹
                //课堂点评
                if(type == 1){
                    savePath += "classComment";
                    loadPath += "classComment";
                //课堂点评 作业点评
                }else{
                    savePath += "workComment";
                    loadPath += "workComment";
                }
                //判断目录是否存在，不存在则创建
                savePath = savePath.replace("/", FileSeparator);
                File file = new File(savePath);
                if(!file.exists() || !file.isDirectory()){
                    file.mkdirs();//会创建所有的目录
                }
                String[] arr = imgs.split(",");
                String img = null;
                String tempSavePath = null;
                String tempLoadPath = null;
                TDyimg dyimg = null;
                for(int i=0;i<arr.length;i++){
                    img = arr[i];
                    //当前时间 年月日
                    Calendar calendar = Calendar.getInstance();
                    String yMd = BaseUtil.toString(calendar.getTime(),"yyyyMMdd");
                    //文件名称
                    String curFileName = yMd + "_" + calendar.getTimeInMillis() + "_" + dynamicId+"_"+i+".png";
                    tempSavePath = savePath+"/"+curFileName;
                    tempLoadPath = loadPath+"/"+curFileName;
                    tempSavePath = tempSavePath.replace("/", FileSeparator);
                    BASE64Util.uploadImg(img,tempSavePath);
                    //插入数据库
                    dyimg = new TDyimg();
                    dyimg.setDynamicId(dynamicId);
                    dyimg.setOriginalImgUrl(tempLoadPath);
                    dyimg.setCreateTime(new Date());
                    dyimgService.insert(dyimg);
                }
            }


        }catch (Exception e){
            log.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
    }

	
}
