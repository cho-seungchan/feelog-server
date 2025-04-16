package com.app.feelog.mybatis;

import com.app.feelog.domain.enumeration.MemberType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(MemberType.class)
public class MemberTypeHandler implements TypeHandler<MemberType>{

    @Override
    public void setParameter(PreparedStatement ps, int i, MemberType parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public MemberType getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)){
            case "일반 회원":
                return MemberType.MEMBER;
            case "관리자":
                return MemberType.ADMIN;
        }
        return null;
    }

    @Override
    public MemberType getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)){
            case "일반 회원":
                return MemberType.MEMBER;
            case "관리자":
                return MemberType.ADMIN;
        }
        return null;
    }

    @Override
    public MemberType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)){
            case "일반 회원":
                return MemberType.MEMBER;
            case "관리자":
                return MemberType.ADMIN;
        }
        return null;
    }
}
