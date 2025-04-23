package com.app.feelog.mybatis;

import com.app.feelog.domain.enumeration.MemberTaskPoolStatus;
import com.app.feelog.domain.enumeration.MemberType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(MemberTaskPoolStatus.class)
public class MemberTaskPoolStatusHandler implements TypeHandler<MemberTaskPoolStatus>{

    @Override
    public void setParameter(PreparedStatement ps, int i, MemberTaskPoolStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public MemberTaskPoolStatus getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)){
            case "정상":
                return MemberTaskPoolStatus.ACTIVE;
            case "삭제":
                return MemberTaskPoolStatus.DELETED;
        }
        return null;
    }

    @Override
    public MemberTaskPoolStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)){
            case "정상":
                return MemberTaskPoolStatus.ACTIVE;
            case "삭제":
                return MemberTaskPoolStatus.DELETED;
        }
        return null;
    }

    @Override
    public MemberTaskPoolStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)){
            case "정상":
                return MemberTaskPoolStatus.ACTIVE;
            case "삭제":
                return MemberTaskPoolStatus.DELETED;
        }
        return null;
    }
}
