package com.wxs.service.comment;

import com.wxs.entity.comment.TDynamicmsg;
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
public interface ITDynamicmsgService extends IService<TDynamicmsg> {
	public List<Map<String,Object>> getDynamicmListByCourseId(Long loginUserId,Long couserId);
	public List<Map<String,Object>> getDynamicmListByTeacherId(Long loginUserId,Long teacherId);
	List<Map<String,Object>> getMyFriendDynamicmList(Long loginUserId);
	List<Map<String, Object>> getOtherParentUserDynamicmList(Long loginUserId,Long userId);
	List<Map<String,Object>> getMyStudentDynamicmList(List<Long> studentId);
	List<Map<String,Object>> getFollowTeacherDynamicmList(Long loginUserId);
	Map<String,Object> buildOneDynamic(Map<String,Object> dyn);
	Boolean saveComment(Long userId,Long dynamicId,String content);
}
