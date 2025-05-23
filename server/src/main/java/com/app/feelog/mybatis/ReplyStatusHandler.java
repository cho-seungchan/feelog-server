package com.app.feelog.mybatis;

import com.app.feelog.domain.enumeration.FaqStatus;
import com.app.feelog.domain.enumeration.ReplyStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(ReplyStatus.class)
public class ReplyStatusHandler implements TypeHandler<ReplyStatus>{

    @Override
    public void setParameter(PreparedStatement ps, int i, ReplyStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public ReplyStatus getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)){
            case "정상":
                return ReplyStatus.ACTIVE;
            case "삭제":
                return ReplyStatus.DELETED;
        }
        return null;
    }

    @Override
    public ReplyStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)){
            case "정상":
                return ReplyStatus.ACTIVE;
            case "삭제":
                return ReplyStatus.DELETED;
        }
        return null;
    }

    @Override
    public ReplyStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)){
            case "정상":
                return ReplyStatus.ACTIVE;
            case "삭제":
                return ReplyStatus.DELETED;
        }
        return null;
    }
}
