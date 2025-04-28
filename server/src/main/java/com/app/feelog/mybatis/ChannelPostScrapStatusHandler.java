package com.app.feelog.mybatis;

import com.app.feelog.domain.enumeration.ChannelPostScrapStatus;
import com.app.feelog.domain.enumeration.FaqStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(ChannelPostScrapStatus.class)
public class ChannelPostScrapStatusHandler implements TypeHandler<ChannelPostScrapStatus>{

    @Override
    public void setParameter(PreparedStatement ps, int i, ChannelPostScrapStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public ChannelPostScrapStatus getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)){
            case "정상":
                return ChannelPostScrapStatus.ACTIVE;
            case "삭제":
                return ChannelPostScrapStatus.DELETED;
        }
        return null;
    }

    @Override
    public ChannelPostScrapStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)){
            case "정상":
                return ChannelPostScrapStatus.ACTIVE;
            case "삭제":
                return ChannelPostScrapStatus.DELETED;
        }
        return null;
    }

    @Override
    public ChannelPostScrapStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)){
            case "정상":
                return ChannelPostScrapStatus.ACTIVE;
            case "삭제":
                return ChannelPostScrapStatus.DELETED;
        }
        return null;
    }
}
