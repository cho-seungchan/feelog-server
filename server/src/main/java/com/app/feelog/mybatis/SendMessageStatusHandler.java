package com.app.feelog.mybatis;

import com.app.feelog.domain.enumeration.ReceiveMessageStatus;
import com.app.feelog.domain.enumeration.SendMessageStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(SendMessageStatus.class)
public class SendMessageStatusHandler implements TypeHandler<SendMessageStatus>{

    @Override
    public void setParameter(PreparedStatement ps, int i, SendMessageStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public SendMessageStatus getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)){
            case "정상":
                return SendMessageStatus.ACTIVE;
            case "삭제":
                return SendMessageStatus.DELETE;
        }
        return null;
    }

    @Override
    public SendMessageStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)){
            case "정상":
                return SendMessageStatus.ACTIVE;
            case "삭제":
                return SendMessageStatus.DELETE;
        }
        return null;
    }

    @Override
    public SendMessageStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)){
            case "정상":
                return SendMessageStatus.ACTIVE;
            case "삭제":
                return SendMessageStatus.DELETE;
        }
        return null;
    }
}
