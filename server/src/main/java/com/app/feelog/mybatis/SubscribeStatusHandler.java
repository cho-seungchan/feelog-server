package com.app.feelog.mybatis;

import com.app.feelog.domain.enumeration.FaqStatus;
import com.app.feelog.domain.enumeration.SubscribeStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(SubscribeStatus.class)

public class SubscribeStatusHandler implements TypeHandler<SubscribeStatus> {

    @Override
    public void setParameter(PreparedStatement ps, int i, SubscribeStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public SubscribeStatus getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)) {
            case "정상":
                return SubscribeStatus.ACTIVE;
            case "삭제":
                return SubscribeStatus.DELETE;
        }
        return null;
    }

    @Override
    public SubscribeStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)) {
            case "정상": return SubscribeStatus.ACTIVE;
            case "삭제": return SubscribeStatus.DELETE;
        }
        return null;
    }

    @Override
    public SubscribeStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)) {
            case "정상": return SubscribeStatus.ACTIVE;
            case "삭제": return SubscribeStatus.DELETE;
        }
        return null;
    }
}
