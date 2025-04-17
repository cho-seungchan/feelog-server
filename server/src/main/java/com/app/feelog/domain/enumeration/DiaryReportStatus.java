package com.app.feelog.domain.enumeration;

public enum DiaryReportStatus {
    ACTIVE("정상"),
    DELETED("삭제");

    private String code;

    private DiaryReportStatus(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
