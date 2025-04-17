package com.app.feelog.domain.enumeration;

public enum DiaryStatus {
    ACTIVE("정상"),
    DELETED("삭제");

    private String code;

    private DiaryStatus(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
