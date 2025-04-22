package com.app.feelog.mybatis;

import com.app.feelog.domain.enumeration.MemberNotificationPostLike;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(MemberNotificationPostLike.class)
public class MemberNotificationPostLikeHandler implements TypeHandler<MemberNotificationPostLike>{

    @Override
    public void setParameter(PreparedStatement ps, int i, MemberNotificationPostLike parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public MemberNotificationPostLike getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)){
            case "설정":
                return MemberNotificationPostLike.ON;
            case "해지":
                return MemberNotificationPostLike.OFF;
        }
        return null;
    }

    @Override
    public MemberNotificationPostLike getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)){
            case "설정":
                return MemberNotificationPostLike.ON;
            case "해지":
                return MemberNotificationPostLike.OFF;
        }
        return null;
    }

    @Override
    public MemberNotificationPostLike getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)){
            case "설정":
                return MemberNotificationPostLike.ON;
            case "해지":
                return MemberNotificationPostLike.OFF;
        }
        return null;
    }
}
