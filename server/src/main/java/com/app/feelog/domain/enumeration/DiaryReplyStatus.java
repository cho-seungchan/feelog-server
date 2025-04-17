package com.app.feelog.domain.enumeration;

public enum DiaryReplyStatus {
    ACTIVE("정상"),
    DELETED("삭제");

    private String code;

    private DiaryReplyStatus(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
