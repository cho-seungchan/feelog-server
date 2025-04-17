package com.app.feelog.domain.enumeration;

public enum ChallengeStatus {
    ACTIVE("정상"),
    DELETED("삭제");

    private String code;

    private ChallengeStatus(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
