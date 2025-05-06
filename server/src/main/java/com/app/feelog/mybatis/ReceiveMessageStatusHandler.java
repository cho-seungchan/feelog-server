package com.app.feelog.mybatis;

import com.app.feelog.domain.enumeration.ReceiveMessageStatus;
import com.app.feelog.domain.enumeration.ReplyStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(ReceiveMessageStatus.class)
public class ReceiveMessageStatusHandler implements TypeHandler<ReceiveMessageStatus>{

    @Override
    public void setParameter(PreparedStatement ps, int i, ReceiveMessageStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public ReceiveMessageStatus getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)){
            case "정상":
                return ReceiveMessageStatus.ACTIVE;
            case "삭제":
                return ReceiveMessageStatus.DELETE;
        }
        return null;
    }

    @Override
    public ReceiveMessageStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)){
            case "정상":
                return ReceiveMessageStatus.ACTIVE;
            case "삭제":
                return ReceiveMessageStatus.DELETE;
        }
        return null;
    }

    @Override
    public ReceiveMessageStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)){
            case "정상":
                return ReceiveMessageStatus.ACTIVE;
            case "삭제":
                return ReceiveMessageStatus.DELETE;
        }
        return null;
    }
}
