package com.wxs.companyWX.controller.organization;

import com.wxs.entity.comment.TDyimg;
import com.wxs.entity.comment.TDynamicmsg;
import com.wxs.entity.comment.TLike;
import com.wxs.entity.course.TClass;
import com.wxs.entity.course.TCourse;
import com.wxs.entity.organ.TOrganComment;
import com.wxs.entity.organ.TOrganTask;
import com.wxs.entity.task.TClock;
import com.wxs.service.comment.ITDyimgService;
import com.wxs.service.comment.ITDynamicmsgService;
import com.wxs.service.comment.ITLikeService;
import com.wxs.service.course.ITClassLessonService;
import com.wxs.service.course.ITClassService;
import com.wxs.service.course.ITCoursesService;
import com.wxs.service.organ.ITOrganCommentService;
import com.wxs.service.organ.ITOrganTaskService;
import com.wxs.service.task.ITClockService;
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
public class COrganTaskController {

    private static Logger log = Logger.getLogger(COrganTaskController.class);
    @Autowired
    private ITOrganTaskService organTaskService;
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
    @Autowired
    private ITLikeService likeService;
    @Autowired
    private ITClassLessonService classLessonService;
    @Autowired
    private ITClockService clockService;

    @Value("${web.upload-path}")
    private String imgUploadPath;
    @Value("${web.load-path}")
    private String imgLoadPath;

    /**
     * @Description : 获取任务详情
     * @return com.wxs.util.Result
     * @Author : wyh
     * @Creation Date : 10:42 2017/12/15
     * @Params : [taskId, userId]
     **/
    @RequestMapping("/getByTaskId")
    public Result getByTaskId(Long taskId,Long userId){

        Map resultMap = new HashMap();
        try{
            TOrganTask organTask = organTaskService.getDetailByTaskId(taskId);
            // 课堂作业，获取规定完成时间 属于 星期几
//            Date shouldDoneTime = organTask.getShouldDoneTime();
//            if(shouldDoneTime != null){
//                resultMap.put("dayOfWeek","星期" + BaseUtil.getWeekOfDate(shouldDoneTime));
//            }
            resultMap.put("organTask",organTask);
            //任务内容，在动态表
            TDynamicmsg dynamicmsg = dynamicmsgService.selectById(organTask.getBusinessId());
            if(dynamicmsg != null){
                resultMap.put("dynamicmsg",dynamicmsg);
                //是否已点赞
                TLike  like = likeService.getOneByDUId(dynamicmsg.getId(),userId);
                if(like != null && like.getStatus() == 1){
                    resultMap.put("like",like);
                }
                //动态下所有点赞人用户名
                List<TLike> likeList = likeService.getAllByDynamicId(dynamicmsg.getId());
                if(likeList.size() > 0){
                    String likeUserNames = "";
                    for(int i=0;i<likeList.size();i++){
                        if(i == likeList.size() -1){
                            likeUserNames += likeList.get(i).getCreateUserName();
                        }else{
                            likeUserNames += likeList.get(i).getCreateUserName()+",";
                        }
                    }
                    resultMap.put("likeUserNames",likeUserNames);
                }
            }
            //获取任务下图片
            List<TDyimg> imgList = dyimgService.getAllByDynamicId(organTask.getBusinessId());
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

            if( type == 1){
                //签到的 未完成 包括 未签到和请假的
                if(status == 0){
                    statuss.add(0);
                    statuss.add(2);
                }
                if(status == 1){
                    statuss.add(1);
                }
                if(status == 2){
                    statuss.add(0);
                    statuss.add(1);
                    statuss.add(2);
                }
            }else{
                if(status == 2){
                    statuss.add(0);
                    statuss.add(1);
                }else{
                    statuss.add(status);
                }

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
            //修改任务表
            Date doneTime = new Date(); //完成时间
            organTask.setStatus(1);
            organTask.setDoneTime(doneTime);
            organTaskService.updateById(organTask);
            //插入到 签到表
            //所属班级
            TClass tClass = classService.getByCourseId(organTask.getCourseId());
            TClock clock = new TClock();
            clock.setClassId(tClass.getId());
            clock.setLessonId(organTask.getLessonId());
            clock.setStudentId(organTask.getStudentId());
            clock.setOrganizationId(tClass.getOrganId());
            clock.setType(1);
            clock.setCreateTime(doneTime);
            clockService.insert(clock);
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
        Date doneTime = new Date(); //完成时间
        TClass tempClass = null;
        TClock tempClock = null;
        try{
            for(int i=0;i<arr.length;i++){
                tempOrganTask = organTaskService.selectById(Long.parseLong(arr[i]));
                tempOrganTask.setStatus(1);
                tempOrganTask.setDoneTime(doneTime);
                organTaskService.updateById(tempOrganTask);
                //插入到 签到表
                //所属班级
                tempClass = classService.getByCourseId(tempOrganTask.getCourseId());
                tempClock = new TClock();
                tempClock.setClassId(tempClass.getId());
                tempClock.setLessonId(tempOrganTask.getLessonId());
                tempClock.setStudentId(tempOrganTask.getStudentId());
                tempClock.setOrganizationId(tempClass.getOrganId());
                tempClock.setType(1);
                tempClock.setCreateTime(doneTime);
                clockService.insert(tempClock);
            }
            return Result.of("批量签到成功!");
        }catch (Exception e){
            e.printStackTrace();
            log.error(BaseUtil.getExceptionStackTrace(e));
        }finally {
            tempOrganTask = null;//for gc
            arr = null;
            doneTime = null;
            tempClass = null;
            tempClock = null;
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
        Date doneTime = new Date(); //完成时间
        TClass tempClass = null;
        TClock tempClock = null;
        try{
            for(int i=0;i<arr.length;i++){
                tempOrganTask = organTaskService.selectById(Long.parseLong(arr[i]));
                tempOrganTask.setStatus(2);
                tempOrganTask.setDoneTime(new Date());
                organTaskService.updateById(tempOrganTask);
                //插入到 签到表
                //所属班级
                tempClass = classService.getByCourseId(tempOrganTask.getCourseId());
                tempClock = new TClock();
                tempClock.setClassId(tempClass.getId());
                tempClock.setLessonId(tempOrganTask.getLessonId());
                tempClock.setStudentId(tempOrganTask.getStudentId());
                tempClock.setOrganizationId(tempClass.getOrganId());
                tempClock.setType(3);
                tempClock.setCreateTime(doneTime);
                clockService.insert(tempClock);
            }
            return Result.of("批量请假成功!");
        }catch (Exception e){
            e.printStackTrace();
            log.error(BaseUtil.getExceptionStackTrace(e));
        }finally {
            tempOrganTask = null;//for gc
            arr = null;
            doneTime = null;
            tempClass = null;
            tempClock = null;
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
            dynamicmsg.setClassLessonId(tempOrganTask.getLessonId());//所属课时Id
            dynamicmsg.setStudentId(tempOrganTask.getStudentId());//所属学生Id
            dynamicmsg.setOrganId(course.getOrganizationId());//所属教育机构Id
            //插入
            dynamicmsgService.insert(dynamicmsg);
            //更新点评任务
            tempOrganTask.setBusinessId(dynamicmsg.getId());
            tempOrganTask.setStatus(1);//状态修改为完成
            tempOrganTask.setDoneTime(new Date());
            organTaskService.updateById(tempOrganTask);
            //保存图片
            List<Long> dynamicmsgIds = new ArrayList<>();
            dynamicmsgIds.add(dynamicmsg.getId());
            uploadCommentImg(imgs,dynamicmsgIds,1);
            return Result.of("发布课堂评论成功!");
        }catch (Exception e){
            e.printStackTrace();
            log.error(BaseUtil.getExceptionStackTrace(e));
        }finally {
            tempOrganTask = null;//for gc
        }
        return null;
    }


    private void  uploadCommentImg(String imgs,List<Long> dynamicIds,Integer type){
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
                }
                //作业发布
                if(type == 2){
                    savePath += "jobPublish";
                    loadPath += "jobPublish";
                }
                //课堂点评 作业点评
                if(type == 3){
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
                    String curFileName = yMd + "_" + calendar.getTimeInMillis() + "_"+i+".png";
                    tempSavePath = savePath+"/"+curFileName;
                    tempLoadPath = loadPath+"/"+curFileName;
                    tempSavePath = tempSavePath.replace("/", FileSeparator);
                    BASE64Util.uploadImg(img,tempSavePath);
                    for( int j=0;j<dynamicIds.size();j++){
                        //插入数据库
                        dyimg = new TDyimg();
                        dyimg.setDynamicId(dynamicIds.get(j));
                        dyimg.setOriginalImgUrl(tempLoadPath);
                        dyimg.setCreateTime(new Date());
                        dyimgService.insert(dyimg);
                    }
                }
            }
        }catch (Exception e){
            log.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
    }
    /**
     * @Description : 点赞与取消点赞
     * @return com.wxs.util.Result
     * @Author : wyh
     * @Creation Date : 10:17 2017/12/18
     * @Params : [dynamicId, userId, userName]
     **/
    @RequestMapping("/likeOne")
    public Result likeOne(Long dynamicId,Long userId,String userName,Integer status){


        try{
            TLike tempLike = likeService.getOneByDUId(dynamicId,userId);
            //取消点赞
            if(tempLike != null){
                tempLike.setStatus(status);
                likeService.updateById(tempLike);
                String resultStr = null;
                if(status == 1){
                    resultStr = "点赞成功!";
                }else{
                    resultStr = "已取消点赞!";
                }
                return Result.of(resultStr);
            }else{
                TLike like = new TLike();
                like.setDynamicId(dynamicId);
                like.setCreateUserId(userId);
                like.setCreateUserName(userName);
                like.setCreateTime(new Date());
                likeService.insert(like);
                return Result.of("点赞成功!");
            }
        }catch (Exception e){
            log.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description : 发布作业选择 学员时,获取的信息 ，学员头像，Id,名字，课时名称，点评下评论数
     * @return com.wxs.util.Result
     * @Author : wyh
     * @Creation Date : 16:02 2017/12/18
     * @Params : [agendaId]
     **/
    @RequestMapping("/selectJobToDetail")
    public Result selectJobToDetail(Long agendaId){
        Map resultMap = new HashMap();
        try {
            //获取所有  未完成的  作业发布任务
            List<Integer> statuss = new ArrayList<>();
            statuss.add(0);
            List<TOrganTask> organTasks = organTaskService.getAllByAgendaId(agendaId,3,statuss);
            if(organTasks.size() >0){
                //获取课时名称
                String classLessonName = classLessonService.selectById(organTasks.get(0).getLessonId()).getLessonName();
                resultMap.put("classLessonName",classLessonName);
                //学生Id,头像
                List<Map> list = new ArrayList<>();
                List<TOrganComment> tempList = null;
                TOrganTask tempOrganTask  = null;
                for(int i=0;i<organTasks.size();i++){
                    Map map = new HashMap();
                    map.put("studentId",organTasks.get(i).getStudentId());
                    map.put("studentName",organTasks.get(i).getStudentName());
                    map.put("studentHeadImg",organTasks.get(i).getStudentHeadImg());
                    //获取对应的点评任务
                    tempOrganTask = organTaskService.getOneByASId(agendaId,organTasks.get(i).getStudentId(),2);
                    tempList = organCommentService.getAllById(tempOrganTask.getId(),2);
                    map.put("commentNum",tempList.size());
                    list.add(map);
                }
                resultMap.put("list",list);
            }
            return Result.of(resultMap);
        }catch (Exception e){
            log.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping("/publishJob")
    public Result publishJob(Long fronUserId,Long agendaId,String studentIds,String content,String imgs,String shouldDoneTime){
        try{
            String[] idArr = studentIds.split(",");
            String tempStudentId = null;
            TOrganTask tempOrganTask = null;
            TDynamicmsg tempDynamicmsg = null;
            List<Long> dynamicmsgIds = new ArrayList<>();
            for( int i=0;i<idArr.length;i++){
                tempStudentId = idArr[i];
                //发布任务
                tempOrganTask = organTaskService.getOneByASId(agendaId,Long.parseLong(tempStudentId),3);
                Long courseId = tempOrganTask.getCourseId();
                //所属课程
                TCourse course = coursesService.selectById(tempOrganTask.getCourseId());
//            TFrontUser frontUser = (TFrontUser)session.getAttribute("fronUser");
//            Long userId = frontUser.getId();
                Long userId = 1l;
                //插入到动态表
                tempDynamicmsg = new TDynamicmsg();
                tempDynamicmsg.setContent(content);//动态内容
                tempDynamicmsg.setCreateTime(new Date());//创建时间
                tempDynamicmsg.setUserId(userId);//创建人Id
                tempDynamicmsg.setPower(0);//权限 -公开
                tempDynamicmsg.setDynamicType("课堂作业");//动态类型
                tempDynamicmsg.setCourseId(courseId);//所属课程Id
                tempDynamicmsg.setClassId(classService.getByCourseId(courseId).getId());//所属班级Id
                tempDynamicmsg.setClassLessonId(tempOrganTask.getLessonId());//所属课时Id
                tempDynamicmsg.setStudentId(tempOrganTask.getStudentId());//所属学生Id
                tempDynamicmsg.setOrganId(course.getOrganizationId());//所属教育机构Id
                //插入动态
                dynamicmsgService.insert(tempDynamicmsg);
                dynamicmsgIds.add(tempDynamicmsg.getId());
                //更新发布任务
                tempOrganTask.setBusinessId(tempDynamicmsg.getId());//动态Id
//                tempOrganTask.setShouldDoneTime(BaseUtil.toDate(shouldDoneTime,"yyyy-MM-dd HH:mm"));//作业需完成时间
                tempOrganTask.setStatus(1);//状态修改为完成
                tempOrganTask.setDoneTime(new Date());
                organTaskService.updateById(tempOrganTask);
            }
            //保存图片
            uploadCommentImg(imgs,dynamicmsgIds,2);
            return Result.of("发布任务成功!");
        }catch (Exception e){
            log.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
        return null;
    }

	
}
