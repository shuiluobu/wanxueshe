package com.wxs.companyWX.controller.record;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wxs.entity.comment.TDynamic;
import com.wxs.entity.comment.TDynamicImg;
import com.wxs.entity.organ.TOrganTask;
import com.wxs.entity.organ.TStudentImpressTag;
import com.wxs.entity.record.TBusinessRecord;
import com.wxs.service.common.IDictionaryService;
import com.wxs.service.dynamic.ITDynamicImgService;
import com.wxs.service.dynamic.ITDynamicService;
import com.wxs.service.organ.ITOrganTaskService;
import com.wxs.service.organ.ITStudentImpressTagService;
import com.wxs.service.record.ITBusinessRecordService;
import com.wxs.util.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxs.core.util.BaseUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/5.
 */
@RestController
@RequestMapping("/cBusinessRecord")
public class CBusinessRecordController {

    private static Logger log = Logger.getLogger(CBusinessRecordController.class);

    @Autowired
    private ITBusinessRecordService businessRecordService;
    @Autowired
    private IDictionaryService dictionaryService;
    @Autowired
    private ITOrganTaskService organTaskService;
    @Autowired
    private ITStudentImpressTagService studentImpressTagService;
    @Autowired
    private ITDynamicService dynamicService;
    @Autowired
    private ITDynamicImgService dynamicImgService;

    @RequestMapping("/studentRecord")
    public Result myOrganAgenda(Long studentId){
        Map resultMap = new HashMap();
        try{
            List<TBusinessRecord> records = businessRecordService.getAllByStuId(studentId);
            Map<String,String> typeMap = dictionaryService.getBusinessRecordType();
            List<TBusinessRecord> list = new ArrayList<>();
            for(TBusinessRecord br : records){
                //签到
                if(br.getBusinessType().trim().equals("SIGN_IN")){
                    //课程-课时 名称
                    br.setClName(businessRecordService.signInCourseLesson(br.getBusinessId()).get("clName"));
                }
                //课堂点评
                if(br.getBusinessType().trim().equals("CLASS_COMMENT")){
                    //对应的 点评任务
                    Long taskId = br.getBusinessId();
                    TOrganTask ot = organTaskService.selectById(taskId);
                    //课程-课时 名称
                    br.setClName(businessRecordService.signInCourseLesson(taskId).get("clName"));
                    //点评下标签
                    EntityWrapper<TStudentImpressTag>  ew = new EntityWrapper<>();
                    ew.eq("studentId",studentId);
                    ew.eq("lessonId",ot.getLessonId());
                    List<TStudentImpressTag> tagList = studentImpressTagService.selectList(ew);
                    br.setImpressTags(tagList);
                    //动态内容
                    TDynamic dynamic = dynamicService.selectById(ot.getBusinessId());
                    br.setDynamicContent(dynamic.getContent());
                    //动态下图片
                    List<TDynamicImg> imgList = dynamicImgService.getAllByDynamicId(ot.getBusinessId());
                    br.setDynamicImgs(imgList);
                }
                //记录类型
//                br.setBusinessType(dictionaryService.getBusinessRecordType().get(br.getBusinessType()));
                list.add(br);
            }

            return Result.of(list);
        }catch (Exception e){
            e.printStackTrace();
            log.error(BaseUtil.getExceptionStackTrace(e));
        }

        return null;
    }
}
