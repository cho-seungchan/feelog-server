package com.app.feelog.domain.enumeration;

public enum ChallengeComplete {
    COMPLETED("완료"),
    ONGOING("진행중"),
    CANCEL("중단");

    private String code;

    private ChallengeComplete(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
