package com.app.feelog.mybatis;

import com.app.feelog.domain.enumeration.DiaryOpen;
import com.app.feelog.domain.enumeration.PostStatus;
import com.app.feelog.domain.enumeration.PostType;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(PostStatus.class)
public class PostStatusHandler implements TypeHandler<PostStatus> {

    @Override
    public void setParameter(PreparedStatement ps, int i, PostStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public PostStatus getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)) {
            case "정상":
                return PostStatus.ACTIVE;
            case "삭제":
                return PostStatus.DELETED;
        }
        return null;
    }

    @Override
    public PostStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)) {
            case "정상": return PostStatus.ACTIVE;
            case "삭제": return PostStatus.DELETED;
        }
        return null;
    }

    @Override
    public PostStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)) {
            case "정상": return PostStatus.ACTIVE;
            case "삭제": return PostStatus.DELETED;
        }
        return null;
    }

    @JsonCreator
    public static PostStatus forCode(String code){
        for(PostStatus postStatus : PostStatus.values()){
            if(postStatus.getCode().equals(code)){
                return postStatus;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 값");
    }
}
