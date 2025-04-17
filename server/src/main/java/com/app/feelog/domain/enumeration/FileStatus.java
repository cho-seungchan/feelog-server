package com.app.feelog.domain.enumeration;

public enum FileStatus {
    ACTIVE("정상"),
    DELETED("삭제");

    private String code;

    private FileStatus(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
