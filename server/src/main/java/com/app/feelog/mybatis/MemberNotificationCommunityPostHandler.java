package com.app.feelog.mybatis;

import com.app.feelog.domain.enumeration.MemberNotificationCommunityPost;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(MemberNotificationCommunityPost.class)
public class MemberNotificationCommunityPostHandler implements TypeHandler<MemberNotificationCommunityPost>{

    @Override
    public void setParameter(PreparedStatement ps, int i, MemberNotificationCommunityPost parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public MemberNotificationCommunityPost getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)){
            case "설정":
                return MemberNotificationCommunityPost.ON;
            case "해지":
                return MemberNotificationCommunityPost.OFF;
        }
        return null;
    }

    @Override
    public MemberNotificationCommunityPost getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)){
            case "설정":
                return MemberNotificationCommunityPost.ON;
            case "해지":
                return MemberNotificationCommunityPost.OFF;
        }
        return null;
    }

    @Override
    public MemberNotificationCommunityPost getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)){
            case "설정":
                return MemberNotificationCommunityPost.ON;
            case "해지":
                return MemberNotificationCommunityPost.OFF;
        }
        return null;
    }
}
