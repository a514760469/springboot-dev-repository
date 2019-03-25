package com.cplh.gis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.cplh.gis.entity.Department;

@Mapper
public interface DepartmentMapper {

	@Select("SELECT * FROM department WHERE id = #{id}")
	Department getDeptById(Integer id);

}
