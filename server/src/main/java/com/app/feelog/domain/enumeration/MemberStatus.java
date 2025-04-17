package com.app.feelog.domain.enumeration;

public enum MemberStatus {
    ACTIVE("정상"),
    BANNED("중지"),
    WITHDRAWN("탈퇴");

    private String code;

    private MemberStatus(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
