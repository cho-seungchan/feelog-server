package com.app.feelog.domain.enumeration;

public enum ChannelStatus {
    ACTIVE("정상"),
    DELETED("삭제");

    private String code;

    private ChannelStatus(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
