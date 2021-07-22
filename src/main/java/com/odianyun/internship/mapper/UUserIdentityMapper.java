package com.odianyun.internship.mapper;

import com.odianyun.internship.model.PO.UUserIdentityPO;

/**
 * @author wxj
 * @date 2021/7/22 - 16:46
 */
public interface UUserIdentityMapper {
    String getUserLabel(Long userId);
    int updateUserLabel(UUserIdentityPO po);
}
