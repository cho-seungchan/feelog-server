package com.app.feelog.mybatis;

import com.app.feelog.domain.enumeration.MemberNotificationPostReply;
import com.app.feelog.domain.enumeration.MemberNotificationPostReplyLike;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(MemberNotificationPostReplyLike.class)
public class MemberNotificationPostReplyLikeHandler implements TypeHandler<MemberNotificationPostReplyLike>{

    @Override
    public void setParameter(PreparedStatement ps, int i, MemberNotificationPostReplyLike parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public MemberNotificationPostReplyLike getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)){
            case "설정":
                return MemberNotificationPostReplyLike.ON;
            case "해지":
                return MemberNotificationPostReplyLike.OFF;
        }
        return null;
    }

    @Override
    public MemberNotificationPostReplyLike getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)){
            case "설정":
                return MemberNotificationPostReplyLike.ON;
            case "해지":
                return MemberNotificationPostReplyLike.OFF;
        }
        return null;
    }

    @Override
    public MemberNotificationPostReplyLike getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)){
            case "설정":
                return MemberNotificationPostReplyLike.ON;
            case "해지":
                return MemberNotificationPostReplyLike.OFF;
        }
        return null;
    }
}
