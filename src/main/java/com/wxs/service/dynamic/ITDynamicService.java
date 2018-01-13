package com.wxs.service.dynamic;

import com.wxs.entity.comment.TDynamic;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
public interface ITDynamicService extends IService<TDynamic> {
	public List<Map<String,Object>> getDynamicmListByCourseId(Long loginUserId,Long couserId);
	public List<Map<String,Object>> getDynamicmListByTeacherId(Long loginUserId,Long teacherId);
	Map<String,Object> getNewestDynamicByOrganId(Long loginUserId,Long organId);
	List<Map<String,Object>> getMyFriendDynamicmList(Long loginUserId);
	List<Map<String, Object>> getOtherParentUserDynamicmList(Long loginUserId,Long userId);
	List<Map<String,Object>> getMyStudentDynamicmList(List<Long> studentId);
	List<Map<String,Object>> getFollowDynamicmList(Long loginUserId);
	List<Map<String,Object>> getNearByDynamicms(Long loginUserId,double latitude,double longitude);
	Map<String,Object> buildOneDynamic(Map<String,Object> dyn);
	Boolean saveComment(Long userId,Long dynamicId,String content);
	Map<String,Object> queryDynamicOfWork(Long dynamicId);
	List<Map<String,Object>> getDynamicDetailOfWork(Long loginUserId,String dynamicIds);

}
