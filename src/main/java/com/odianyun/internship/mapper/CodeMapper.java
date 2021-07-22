package com.odianyun.internship.mapper;

import com.odianyun.internship.model.VO.CodeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CodeMapper {
    List<CodeVO> listByCategory(@Param("pool") String pool,@Param("category") String category);
}
