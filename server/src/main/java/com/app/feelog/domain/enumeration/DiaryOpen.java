package com.app.feelog.domain.enumeration;

public enum DiaryOpen {
    ALL("전체 공개"),
    SUBSCRIBE("구독자에게만 공개"),
    CLOSE("비공개");

    private String code;

    private DiaryOpen(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
