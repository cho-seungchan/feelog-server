package com.app.feelog.domain.enumeration;

public enum ChallengeDiaryStatus {
    ACTIVE("정상"),
    DELETED("삭제");

    private String code;

    private ChallengeDiaryStatus(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
