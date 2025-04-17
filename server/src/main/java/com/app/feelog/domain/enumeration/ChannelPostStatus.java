package com.app.feelog.domain.enumeration;

public enum ChannelPostStatus {
    ACTIVE("정상"),
    DELETED("삭제");

    private String code;

    private ChannelPostStatus(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
