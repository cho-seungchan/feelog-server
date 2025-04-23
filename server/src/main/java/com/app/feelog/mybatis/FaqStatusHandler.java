package com.app.feelog.mybatis;

import com.app.feelog.domain.enumeration.FaqStatus;
import com.app.feelog.domain.enumeration.NoticeStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(FaqStatus.class)
public class FaqStatusHandler implements TypeHandler<FaqStatus>{

    @Override
    public void setParameter(PreparedStatement ps, int i, FaqStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public FaqStatus getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)){
            case "정상":
                return FaqStatus.ACTIVE;
            case "삭제":
                return FaqStatus.DELETE;
        }
        return null;
    }

    @Override
    public FaqStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)){
            case "정상":
                return FaqStatus.ACTIVE;
            case "삭제":
                return FaqStatus.DELETE;
        }
        return null;
    }

    @Override
    public FaqStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)){
            case "정상":
                return FaqStatus.ACTIVE;
            case "삭제":
                return FaqStatus.DELETE;
        }
        return null;
    }
}
