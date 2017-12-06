package com.wxs.service.message.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wxs.entity.message.TRemindMessage;
import com.wxs.mapper.message.TRemindMessageMapper;
import com.wxs.service.message.ITRemindMessageService;
import org.springframework.stereotype.Service;
import org.wxs.core.util.BaseUtil;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author skyer
 * @since 2017-11-27
 */
@Service
public class TRemindMessageServiceImpl extends ServiceImpl<TRemindMessageMapper, TRemindMessage> implements ITRemindMessageService {
	@Override
    public List<Map<String,Object>> getRemindMsgByFromUid(Long userId){
        EntityWrapper<TRemindMessage> wrapper = new EntityWrapper();
        wrapper.eq("fromUserId",userId);
        wrapper.eq("status",0);
        wrapper.eq("readStatus",0);//未读
        List<Map<String,Object>> maps = this.selectMaps(wrapper);
        try{
            maps.stream().forEach(map ->{
                Map<String,Object> messageContent = BaseUtil.parseJson(map.get("messageContent").toString(),Map.class);
                map.put("messageContent",messageContent);
            });
        } catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public List<Map<String,Object>> getRemindMsgByToUid(Long userId){
        EntityWrapper<TRemindMessage> wrapper = new EntityWrapper();
        wrapper.eq("userId",userId);
        wrapper.eq("status",0);
        wrapper.eq("readStatus",0);//未读
        List<Map<String,Object>> maps = this.selectMaps(wrapper);
        try{
            maps.stream().forEach(map ->{
                Map<String,Object> messageContent = BaseUtil.parseJson(map.get("messageContent").toString(),Map.class);
                map.put("messageContent",messageContent);
            });
        } catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
}
