package com.wxs.companyWX.controller.organization;

import com.wxs.entity.organ.TOrganTask;
import com.wxs.entity.organ.TOrganTaskImg;
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

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    @Value("${web.upload-path}")
    private String imgUploadPath;
    @Value("${web.load-path}")
    private String imgLoadPath;
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


    @RequestMapping("/releaseClassComment")
    public Result releaseClassComment(Long taskId,String content,String imgs,Integer power){
        TOrganTask tempOrganTask = null;
        try{
            tempOrganTask = organTaskService.selectById(taskId);
            tempOrganTask.setContent(content);//点评内容
            tempOrganTask.setDoneTime(new Date());//完成时间
            tempOrganTask.setPower(power);//权限
            //保存图片
            uploadCommentImg(imgs,taskId,1);
            organTaskService.updateById(tempOrganTask);
            return Result.of("发布课堂评论成功!");
        }catch (Exception e){
            e.printStackTrace();
            log.error(BaseUtil.getExceptionStackTrace(e));
        }finally {
            tempOrganTask = null;//for gc
        }
        return null;
    }


    private void  uploadCommentImg(String imgs,Long taskId,Integer type){
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
                if(type == 1){
                    savePath += "classComment";
                    loadPath += "classComment";
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
                TOrganTaskImg organTaskImg = null;
                for(int i=0;i<arr.length;i++){
                    img = arr[i];
                    //当前时间 年月日
                    Calendar calendar = Calendar.getInstance();
                    String yMd = BaseUtil.toString(calendar.getTime(),"yyyyMMdd");
                    //文件名称
                    String curFileName = yMd + "_" + calendar.getTimeInMillis() + "_" + taskId+"_"+i+".png";
                    tempSavePath = savePath+"/"+curFileName;
                    tempLoadPath = loadPath+"/"+curFileName;
                    tempSavePath = tempSavePath.replace("/", FileSeparator);
                    BASE64Util.uploadImg(img,tempSavePath);
                    //插入数据库
                    organTaskImg = new TOrganTaskImg();
                    organTaskImg.setTaskId(taskId);
                    organTaskImg.setImgUrl(tempLoadPath);
                    organTaskImg.setCreateTime(new Date());
                    organTaskImgService.insert(organTaskImg);
                }
            }


        }catch (Exception e){
            log.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
    }

	
}
