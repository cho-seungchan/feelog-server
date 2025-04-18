package com.app.feelog.domain.enumeration;

public enum DiaryNameOpen {
    UNKNOWN("비공개(익명)"),
    KNOWN("닉네임");


    private String code;

    private DiaryNameOpen(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
