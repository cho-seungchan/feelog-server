package com.app.feelog.mybatis;

import com.app.feelog.domain.enumeration.MemberNotificationSubscribe;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(MemberNotificationSubscribe.class)
public class MemberNotificationSubscribeHandler implements TypeHandler<MemberNotificationSubscribe>{

    @Override
    public void setParameter(PreparedStatement ps, int i, MemberNotificationSubscribe parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public MemberNotificationSubscribe getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)){
            case "설정":
                return MemberNotificationSubscribe.ON;
            case "해지":
                return MemberNotificationSubscribe.OFF;
        }
        return null;
    }

    @Override
    public MemberNotificationSubscribe getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)){
            case "설정":
                return MemberNotificationSubscribe.ON;
            case "해지":
                return MemberNotificationSubscribe.OFF;
        }
        return null;
    }

    @Override
    public MemberNotificationSubscribe getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)){
            case "설정":
                return MemberNotificationSubscribe.ON;
            case "해지":
                return MemberNotificationSubscribe.OFF;
        }
        return null;
    }
}
