package com.wxs.mapper.dictionary;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wxs.entity.dictionary.TCourseTypeDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author skyer
 * @since 2017-12-15
 */
public interface TCourseTypeDictMapper extends BaseMapper<TCourseTypeDict> {
    public List<Map<String,Object>> getCourseTypeByParentCode(@Param("parentCode") String parentCode);

}