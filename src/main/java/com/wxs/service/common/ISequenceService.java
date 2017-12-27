package com.wxs.service.common;

import java.util.Date;

/**
 * Created by Administrator on 2017/12/19 0019.
 */
public interface ISequenceService {

   public String getCourseCode(Long userId,String type);
   public String getCourseClassCode(Long userId);
   String getOrganCode(Long userId,String type);
}
