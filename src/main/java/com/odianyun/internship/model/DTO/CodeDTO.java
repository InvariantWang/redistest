package com.odianyun.internship.model.DTO;

import java.io.Serializable;


public class CodeDTO implements Serializable {

    private static final long serialVersionUID = 4006165897521621894L;

    private String code;

    private String pool;

    private String category;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPool() {
        return pool;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
