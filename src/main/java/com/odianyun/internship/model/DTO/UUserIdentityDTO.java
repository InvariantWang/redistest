package com.odianyun.internship.model.DTO;

import java.io.Serializable;
import java.util.List;

/**
 * @author wxj
 * @date 2021/7/22 - 16:45
 */
public class UUserIdentityDTO implements Serializable {
  private Long userId;
  private List<String> extField1List;

    public Long getUserId( ) {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId=userId;
    }

    public List<String> getExtField1List( ) {
        return extField1List;
    }

    public void setExtField1List(List<String> extField1List) {
        this.extField1List=extField1List;
    }
}
