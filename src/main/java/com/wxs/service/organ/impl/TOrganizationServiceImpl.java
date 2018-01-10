package com.wxs.service.organ.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wxs.entity.comment.TDynamicImg;
import com.wxs.entity.course.TStudentCourse;
import com.wxs.entity.organ.TOrganization;
import com.wxs.mapper.course.TClassCoursesMapper;
import com.wxs.mapper.course.TStudentCourseMapper;
import com.wxs.mapper.dynamic.TDynamicImgMapper;
import com.wxs.mapper.organ.TFollowOrganMapper;
import com.wxs.mapper.organ.TOrganizationMapper;
import com.wxs.service.customer.ITParentService;
import com.wxs.service.organ.ITOrganizationService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxs.core.util.BaseUtil;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
@Service
public class TOrganizationServiceImpl extends ServiceImpl<TOrganizationMapper, TOrganization> implements ITOrganizationService {
    @Autowired
    public TOrganizationMapper organizationMapper;

    @Autowired
    public TStudentCourseMapper studentCourseMapper;

    @Autowired
    public TClassCoursesMapper coursesMapper;

    @Autowired
    public TFollowOrganMapper fllowOrganMapper; //关注机构表

    @Autowired
    public TDynamicImgMapper dynamicImgMapper;

    @Autowired
    private ITParentService parentService;

    /**
     * 机构概要
     *
     * @param organId
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> getOrganOutline(Long organId, Long userId) {
        TOrganization organization = organizationMapper.selectById(organId);
        return buildOrganInfo(organization, userId);
    }

    @Override
    public List<Map<String,Object>> getNearOrgans(double latitude, double longitude) {
        //先搜索5公里之内的机构
        List<TOrganization> organs = organizationMapper.getNearOrgans(latitude, longitude, 5);
        return bean2MapList(organs);
    }

    @Override
    public List<Map<String, Object>> getOrganFllowUserList(Long organId, Long loginUserId) {
        List<Long> userIds = fllowOrganMapper.getFllowUserIdsOfOrganId(organId);
        return parentService.getFllowUsers(userIds, loginUserId);
    }

    @Override
    public List<Map<String, Object>> queryOrganByLikeName(String organName) {
        return organizationMapper.queryOrganByLikeName(organName);
    }

    public Map<String, Object> buildOrganInfo(TOrganization organization, Long userId) {
        Map<String, Object> result = Maps.newHashMap();
        result.put("organId", organization.getId());
        result.put("organName", organization.getOrganName());
        result.put("logoImg", organization.getLogoImg());//logo头像
        result.put("smallIntroduce", organization.getIntroduce()); //小介绍，个性签名
        result.put("samllCount", smallCountOfOrgan(organization.getId())); //小统计
        TStudentCourse stuClass = new TStudentCourse();
        stuClass.setOrganizationId(organization.getId());
        stuClass.setUserId(userId);
        int organStudentCount = studentCourseMapper.getClassStudentCountByParam(stuClass);
        result.put("isParkIn", organStudentCount > 0 ? 1 : 0);
        result.put("ifFllow", fllowOrganMapper.getFllowByUserId(userId, organization.getId()) == null ? 0 : 1); //是否关注
        result.put("leval", organization.getLeval()==1?"已认证":"");
        result.put("foundingTime", BaseUtil.toChinaDate(organization.getFoundingTime())); //成立时间
        result.put("address", organization.getAddress());
        result.put("organRemark", organization.getOrganRemark());
        result.put("telePhone", organization.getTelePhone() == null ? organization.getMobilePhone() : organization.getTelePhone());
        return result;
    }
    @Override
    public List<Map<String,Object>> getFollowOrganInfoByUserId(Long userId){
        List<TOrganization> organs = fllowOrganMapper.getFollowOrganByUser(userId);
        return bean2MapList(organs);
    }

    public List<Map<String,Object>> bean2MapList(List<TOrganization> organs){
        List<Map<String,Object>> mapList = Lists.newArrayList();
        organs.stream().forEach(bean->{
            Map<String,Object> map = Maps.newHashMap();
            map.put("organId",bean.getId());
            map.put("organName",bean.getOrganName());
            map.put("leval",bean.getLeval()==1?"已认证":"");
            map.put("logoImg",bean.getLogoImg());
            map.put("samllCount",smallCountOfOrgan(bean.getId()));
            mapList.add(map);
        });
        return mapList;
    }
    @Override
    public List<Map<String,Object>> choicenessPhotos(Long organId,int page,int rows){
        int offset = (page-1) * rows;
        return  dynamicImgMapper.getChoicenessPhotosByOrganId(organId,offset,rows);
    }


    public String smallCountOfOrgan(Long organId) {
        //获取机构的小统计，主要统计多少学员，多少人关注，多少课程
        int organFllowCount = fllowOrganMapper.getOrganFllowCount(organId);
        TStudentCourse stuClass = new TStudentCourse();
        stuClass.setOrganizationId(organId);
        int organStudentCount = studentCourseMapper.getClassStudentCountByParam(stuClass);
        int organCourseCount = coursesMapper.getOrganCourseCount(organId);
        return organFllowCount + "人关注，" + organStudentCount + "学员，" + organCourseCount + "课程";
    }
}