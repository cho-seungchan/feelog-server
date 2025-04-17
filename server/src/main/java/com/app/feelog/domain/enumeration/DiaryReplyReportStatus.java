package com.app.feelog.domain.enumeration;

public enum DiaryReplyReportStatus {
    ACTIVE("정상"),
    DELETED("삭제");

    private String code;

    private DiaryReplyReportStatus(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
