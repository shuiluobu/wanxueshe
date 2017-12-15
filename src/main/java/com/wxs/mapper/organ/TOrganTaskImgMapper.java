package com.wxs.mapper.organ;

import com.wxs.entity.organ.TOrganTaskImg;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author wyh
 * @since 2017-12-14
 */
public interface TOrganTaskImgMapper extends BaseMapper<TOrganTaskImg> {

    //根据taskId获取其下所有图片
    @Select(" select * from t_organ_task_img where taskId = #{taskId}")
    @ResultMap("BaseResultMap")
    List<TOrganTaskImg> getAllByTaskId(@Param("taskId") long taskId);

}