package com.app.feelog.domain.enumeration;

public enum PostType {
    POST("포스트"),
    CHEERING("응원글");

    private String code;

    private PostType(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
