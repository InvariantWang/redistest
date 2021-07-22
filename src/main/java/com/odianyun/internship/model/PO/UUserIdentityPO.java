package com.odianyun.internship.model.PO;

/**
 * @author wxj
 * @date 2021/7/22 - 16:46
 */
public class UUserIdentityPO {
    private Long userId;
    private String extField1list;

    public Long getUserId( ) {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId=userId;
    }

    public String getextField1list(String s) {
        return extField1list;
    }

    public void setextField1list(String extField1list) {
        this.extField1list=extField1list;
    }
}
