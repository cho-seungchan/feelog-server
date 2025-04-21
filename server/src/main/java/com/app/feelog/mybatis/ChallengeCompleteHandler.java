package com.app.feelog.mybatis;

import com.app.feelog.domain.enumeration.ChallengeComplete;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(ChallengeComplete.class)
public class ChallengeCompleteHandler implements TypeHandler<ChallengeComplete>{

    @Override
    public void setParameter(PreparedStatement ps, int i, ChallengeComplete parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public ChallengeComplete getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)){
            case "완료":
                return ChallengeComplete.COMPLETED;
            case "진행중":
                return ChallengeComplete.ONGOING;
            case "중단":
                return ChallengeComplete.CANCELD;
        }
        return null;
    }

    @Override
    public ChallengeComplete getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)){
            case "완료":
                return ChallengeComplete.COMPLETED;
            case "진행중":
                return ChallengeComplete.ONGOING;
            case "중단":
                return ChallengeComplete.CANCELD;
        }
        return null;
    }

    @Override
    public ChallengeComplete getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)){
            case "완료":
                return ChallengeComplete.COMPLETED;
            case "진행중":
                return ChallengeComplete.ONGOING;
            case "중단":
                return ChallengeComplete.CANCELD;
        }
        return null;
    }
}
