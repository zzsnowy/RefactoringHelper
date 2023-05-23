package com.zz.edrt.eddetect.mapper;

import com.zz.edrt.eddetect.domain.SmellDetectRule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SmellDetectRuleMapper {
    int deleteByPrimaryKey(String name);

    int insert(SmellDetectRule record);

    SmellDetectRule selectByPrimaryKey(String name);

    List<SmellDetectRule> selectAll();

    int updateByPrimaryKey(SmellDetectRule record);
}