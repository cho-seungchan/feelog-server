package com.app.feelog.domain.enumeration;

public enum CommonChallengeStatus {
    ACTIVE("정상"),
    DELETED("삭제");

    private String code;

    private CommonChallengeStatus(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
