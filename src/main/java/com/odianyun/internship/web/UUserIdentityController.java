package com.odianyun.internship.web;

import com.odianyun.internship.model.DTO.UUserIdentityDTO;
import com.odianyun.internship.service.UUserIdentityService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author wxj
 * @date 2021/7/22 - 16:56
 */
@RestController
@RequestMapping("uUserIdentity")
public class UUserIdentityController {

    @Resource
    private UUserIdentityService uUserIdentityService;
    @GetMapping("listUserLabelById")
    public List<String>listUserLabelById(@RequestParam(
            name="userId",required=true)Long userId){
        return uUserIdentityService.listUserLabelById((userId));
    }
    @PostMapping("updateUserLabel")
    public void updateUserLabel(@RequestBody UUserIdentityDTO dto){
        if (dto.getUserId()==null||dto.getExtField1List()==null){
            return;
        }
        uUserIdentityService.updateUserLabel1(dto);
    }

}
