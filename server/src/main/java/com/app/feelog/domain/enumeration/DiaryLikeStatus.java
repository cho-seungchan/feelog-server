package com.app.feelog.domain.enumeration;

public enum DiaryLikeStatus {
    ACTIVE("정상"),
    DELETED("삭제");

    private String code;

    private DiaryLikeStatus(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
