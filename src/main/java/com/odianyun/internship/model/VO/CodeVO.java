package com.odianyun.internship.model.VO;

import java.io.Serializable;

public class CodeVO implements Serializable {

    private static final long serialVersionUID = 4750402797496154986L;

    private String code;

    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
