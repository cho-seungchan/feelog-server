package com.app.feelog.domain.enumeration;

public enum DiaryReplyLikeStatus {
    ACTIVE("정상"),
    DELETED("삭제");

    private String code;

    private DiaryReplyLikeStatus(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
