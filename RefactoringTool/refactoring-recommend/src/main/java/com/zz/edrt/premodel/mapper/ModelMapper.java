package com.zz.edrt.premodel.mapper;

import com.zz.edrt.premodel.domain.Model;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ModelMapper {
    int deleteByPrimaryKey(String title);

    int insert(Model record);

    Model selectByPrimaryKey(String title);

    List<Model> selectAll();

    int updateByPrimaryKey(Model record);
}