package com.app.feelog.domain.enumeration;

public enum MemberNotificationPostReplyLike {
    SET("설정"),
    CLEAR("해제");

    private String code;

    private MemberNotificationPostReplyLike(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
