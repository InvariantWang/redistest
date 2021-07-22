package com.odianyun.internship.service;

import com.odianyun.internship.model.DTO.UUserIdentityDTO;

import java.util.List;

/**
 * @author wxj
 * @date 2021/7/22 - 16:54
 */
public interface UUserIdentityService {
    List<String> listUserLabelById(Long userId);
    void updateUserLabel1(UUserIdentityDTO dto);
}
