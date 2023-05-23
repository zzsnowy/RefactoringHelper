package com.zz.edrt.projectmanage.mapper;

import com.zz.edrt.edrtcommon.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ProjectMapper {
    int deleteByPrimaryKey(String name);

    int insert(Project record);

    Project selectByPrimaryKey(String name);

    List<Project> selectAll();

    int updateByPrimaryKey(Project record);
}