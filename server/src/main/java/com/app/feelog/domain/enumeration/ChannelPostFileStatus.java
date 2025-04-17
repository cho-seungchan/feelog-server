package com.app.feelog.domain.enumeration;

public enum ChannelPostFileStatus {
    ACTIVE("정상"),
    DELETED("삭제");

    private String code;

    private ChannelPostFileStatus(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
