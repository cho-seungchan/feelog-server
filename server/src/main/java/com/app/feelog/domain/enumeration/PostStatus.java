package com.app.feelog.domain.enumeration;

public enum PostStatus {
    ACTIVE("정상"),
    DELETED("삭제");

    private String code;

    private PostStatus(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
