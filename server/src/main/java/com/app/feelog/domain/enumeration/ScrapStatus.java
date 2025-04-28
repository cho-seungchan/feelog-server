package com.app.feelog.domain.enumeration;

public enum ScrapStatus {
    ACTIVE("정상"),
    DELETED("삭제");

    private String code;

    private ScrapStatus(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
