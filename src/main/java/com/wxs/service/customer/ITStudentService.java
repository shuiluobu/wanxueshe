package com.wxs.service.customer;

import com.wxs.entity.comment.TDynamic;
import com.wxs.entity.customer.TStudent;
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
public interface ITStudentService extends IService<TStudent> {
    List<Map<String,Object>> getStudentOfUser(Long userId);
    Map<String, Object> getMyCourses(Long userId,Integer isAll); //我的课程
    List<Map<String,Object>> isEndMyCourses(Long userId,Integer isEnd);
    Map<String, Object> saveMygrowth(List<String> mediaUrls, String mediaType, TDynamic dynamic, Long workId);
    public List<Map<String, Object>> queryStuInfoByUserId(Long userId);
    public Map<String,Object> saveStudent(TStudent student);
    Integer delStudent(Long studentId,Long userId);
    //根据 教育机构Id 和 学生名字 模糊搜索 学生
    List<TStudent> searchByName(String name,Long organId);
    //根据 教育机构Id  获取其下 所有学生
    List<TStudent> getAllByOrganId(Long organId);

    /**
     * 获取一个学省的基本信息
     * @param studentId
     * @return
     */
    Map<String,Object> getOneStudentInfoById(Long studentId);
}
