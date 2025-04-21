package com.app.feelog.mybatis;

import com.app.feelog.domain.enumeration.MemberStatus;
import com.app.feelog.domain.enumeration.MemberType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(MemberStatus.class)
public class MemberStatusHandler implements TypeHandler<MemberStatus>{

    @Override
    public void setParameter(PreparedStatement ps, int i, MemberStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public MemberStatus getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)){
            case "정상":
                return MemberStatus.ACTIVE;
            case "중지":
                return MemberStatus.BANNED;
            case "탈퇴":
                return MemberStatus.WITHDRAWN;
        }
        return null;
    }

    @Override
    public MemberStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)){
            case "정상":
                return MemberStatus.ACTIVE;
            case "중지":
                return MemberStatus.BANNED;
            case "탈퇴":
                return MemberStatus.WITHDRAWN;
        }
        return null;
    }

    @Override
    public MemberStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)){
            case "정상":
                return MemberStatus.ACTIVE;
            case "중지":
                return MemberStatus.BANNED;
            case "탈퇴":
                return MemberStatus.WITHDRAWN;
        }
        return null;
    }
}
