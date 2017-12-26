package com.wxs.service.organ;

import com.wxs.entity.organ.TAgendaCompletionDict;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyh
 * @since 2017-12-26
 */
public interface ITAgendaCompletionDictService extends IService<TAgendaCompletionDict> {

    //获取所有的待办完成情况  字典
    List<TAgendaCompletionDict> getAll();
	
}
