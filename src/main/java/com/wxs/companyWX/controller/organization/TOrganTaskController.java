package com.wxs.companyWX.controller.organization;

import com.wxs.entity.organ.TOrganTask;
import com.wxs.service.organ.ITOrganTaskService;
import com.wxs.util.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxs.core.util.BaseUtil;

import java.util.ArrayList;
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

	
}
