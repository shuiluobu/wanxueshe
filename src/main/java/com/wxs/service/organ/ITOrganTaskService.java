package com.wxs.service.organ;

import com.wxs.entity.organ.TOrganTask;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyh
 * @since 2017-12-08
 */
public interface ITOrganTaskService extends IService<TOrganTask> {

    //根据 待办Id ,类型和完成状态  获取 其下 所有的 任务
    public List<TOrganTask> getAllByAgendaId(Long agendaId,Integer type,List<Integer> statuss);
    //根绝 taskId 获取其详情
    public TOrganTask getDetailByTaskId(Long taskId);

}
