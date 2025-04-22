package com.app.feelog.mybatis;

import com.app.feelog.domain.enumeration.MemberNotificationMessage;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(MemberNotificationMessage.class)
public class MemberNotificationMessageHandler implements TypeHandler<MemberNotificationMessage>{

    @Override
    public void setParameter(PreparedStatement ps, int i, MemberNotificationMessage parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public MemberNotificationMessage getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)){
            case "설정":
                return MemberNotificationMessage.ON;
            case "해지":
                return MemberNotificationMessage.OFF;
        }
        return null;
    }

    @Override
    public MemberNotificationMessage getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)){
            case "설정":
                return MemberNotificationMessage.ON;
            case "해지":
                return MemberNotificationMessage.OFF;
        }
        return null;
    }

    @Override
    public MemberNotificationMessage getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)){
            case "설정":
                return MemberNotificationMessage.ON;
            case "해지":
                return MemberNotificationMessage.OFF;
        }
        return null;
    }
}
