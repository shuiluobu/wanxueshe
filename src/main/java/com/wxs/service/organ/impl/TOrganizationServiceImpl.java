package com.wxs.service.organ.impl;

import com.google.common.collect.Maps;
import com.wxs.entity.course.TStudentClass;
import com.wxs.entity.organ.TOrganization;
import com.wxs.mapper.course.TCoursesMapper;
import com.wxs.mapper.course.TStudentClassMapper;
import com.wxs.mapper.organ.TFllowOrganMapper;
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
 *  服务实现类
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
    public TStudentClassMapper studentClassMapper;

    @Autowired
    public TCoursesMapper coursesMapper;

    @Autowired
    public TFllowOrganMapper fllowOrganMapper; //关注机构表

    @Autowired
    private ITParentService parentService;


    /**
     * 机构概要
     * @param organId
     * @param userId
     * @return
     */
   public Map<String,Object> getOrganOutline(Long organId, Long userId){
       Map<String,Object> result = Maps.newHashMap();
       TOrganization organization = organizationMapper.selectById(organId);
       return buildOrganInfo(organization,userId);
   }

    @Override
    public List<TOrganization> getNearOrgans(double latitude, double longitude) {
       //先搜索5公里之内的机构
        return organizationMapper.getNearOrgans(latitude,longitude,5);
    }
    @Override
    public List<Map<String,Object>> getOrganFllowUserList(Long organId){
       List<Long> userIds = fllowOrganMapper.getFllowUserIdsOfOrganId(organId);
       return parentService.getFllowUsers(userIds);
    }

    public Map<String,Object> buildOrganInfo(TOrganization organization,Long userId){
        Map<String,Object> result = Maps.newHashMap();
       result.put("organId",organization.getId());
        result.put("organName",organization.getOrganName());
        result.put("logoImg",organization.getLogoImg());//logo头像
        result.put("smallIntroduce",organization.getIntroduce()); //小介绍，个性签名
        result.put("samllCount",smallCountOfOrgan(organization.getId())); //小统计
        result.put("ifFllow",fllowOrganMapper.getFllowByUserId(userId,organization.getId())==null?false:true); //是否关注
        result.put("leval",organization.getLeval());
        result.put("foundingTime", BaseUtil.toChinaDate(organization.getFoundingTime())); //成立时间
        result.put("address",organization.getAddress());
        result.put("organRemark",organization.getOrganRemark());
        result.put("telePhone",organization.getTelePhone()==null?organization.getMobilePhone():organization.getTelePhone());
        return result;
   }


    public String smallCountOfOrgan(Long organId){
       //获取机构的小统计，主要统计多少学员，多少人关注，多少课程
        int organFllowCount = fllowOrganMapper.getOrganFllowCount(organId);
        TStudentClass stuClass = new TStudentClass();
        stuClass.setOrganizationId(organId);
        int organStudentCount = studentClassMapper.getClassStudentCountByParam(stuClass);
        int organCourseCount = coursesMapper.getOrganCourseCount(organId);
        return  organFllowCount+"人关注，"+organStudentCount+"学员，"+organCourseCount+"课程";
   }
}