package com.app.feelog.domain.enumeration;

public enum MemberChallengeStatus {
    ACTIVE("정상"),
    DELETED("삭제");

    private String code;

    private MemberChallengeStatus(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
