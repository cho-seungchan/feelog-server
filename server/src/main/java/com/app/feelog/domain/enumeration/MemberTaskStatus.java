package com.app.feelog.domain.enumeration;

public enum MemberTaskStatus {
    ACTIVE("정상"),
    DELETED("삭제");

    private String code;

    private MemberTaskStatus(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
