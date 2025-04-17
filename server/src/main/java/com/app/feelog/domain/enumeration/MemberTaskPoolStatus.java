package com.app.feelog.domain.enumeration;

public enum MemberTaskPoolStatus {
    ACTIVE("정상"),
    DELETED("삭제");

    private String code;

    private MemberTaskPoolStatus(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
