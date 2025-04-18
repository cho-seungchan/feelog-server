package com.app.feelog.mybatis;

import com.app.feelog.domain.enumeration.MemberNotificationPostReply;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(MemberNotificationPostReply.class)
public class MemberNotificationPostReplyHandler implements TypeHandler<MemberNotificationPostReply>{

    @Override
    public void setParameter(PreparedStatement ps, int i, MemberNotificationPostReply parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public MemberNotificationPostReply getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)){
            case "설정":
                return MemberNotificationPostReply.SET;
            case "해지":
                return MemberNotificationPostReply.CLEAR;
        }
        return null;
    }

    @Override
    public MemberNotificationPostReply getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)){
            case "설정":
                return MemberNotificationPostReply.SET;
            case "해지":
                return MemberNotificationPostReply.CLEAR;
        }
        return null;
    }

    @Override
    public MemberNotificationPostReply getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)){
            case "설정":
                return MemberNotificationPostReply.SET;
            case "해지":
                return MemberNotificationPostReply.CLEAR;
        }
        return null;
    }
}
