package com.wxs.mapper.common;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wxs.entity.common.TSubjectTypeDict;
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
public interface TSubjectTypeDictMapper extends BaseMapper<TSubjectTypeDict> {
    public List<Map<String,Object>> getSubjectTypeByParentCode(@Param("parentCode") String parentCode);

}