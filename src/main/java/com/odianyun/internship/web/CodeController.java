package com.odianyun.internship.web;

import com.odianyun.internship.model.DTO.CodeDTO;
import com.odianyun.internship.model.VO.CodeVO;
import com.odianyun.internship.service.CodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("code")
public class CodeController {

    @Resource
    private CodeService codeService;

    @PostMapping("listByCategory")
    public List<CodeVO> listByCategory(@RequestBody CodeDTO dto) {
        if (StringUtils.isBlank(dto.getPool()) || StringUtils.isBlank(dto.getCategory())) {
            return Collections.EMPTY_LIST;
        }
        return codeService.listByCategory(dto.getPool(), dto.getCategory());
    }
    @PostMapping("getNameByCode")
    public String getNameByCode(@RequestBody CodeDTO dto) {
        if (StringUtils.isBlank(dto.getPool()) || StringUtils.isBlank(dto.getCategory()) ||  StringUtils.isBlank(dto.getCode())) {
            return null;
        }
        return codeService.getNameByCode(dto.getPool(), dto.getCategory(), dto.getCode());
    }
}
