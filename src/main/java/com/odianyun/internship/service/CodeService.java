package com.odianyun.internship.service;

import com.odianyun.internship.model.VO.CodeVO;

import java.util.List;

public interface CodeService {

    List<CodeVO> listByCategory(String pool,String category);

    String getNameByCode(String pool,String category,String code);


}
