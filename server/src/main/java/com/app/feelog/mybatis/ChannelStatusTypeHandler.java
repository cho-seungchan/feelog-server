package com.app.feelog.mybatis;

import com.app.feelog.domain.enumeration.ChannelStatus;
import com.app.feelog.domain.enumeration.DiaryStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(ChannelStatus.class)
public class ChannelStatusTypeHandler implements TypeHandler<ChannelStatus> {

    @Override
    public void setParameter(PreparedStatement ps, int i, ChannelStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode()); // "정상" 같은 문자열 저장
    }

    @Override
    public ChannelStatus getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)) {
            case "정상": return ChannelStatus.ACTIVE;
            case "삭제": return ChannelStatus.DELETED;
        }
        return null;
    }

    @Override
    public ChannelStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)) {
            case "정상": return ChannelStatus.ACTIVE;
            case "삭제": return ChannelStatus.DELETED;
        }
        return null;
    }

    @Override
    public ChannelStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)) {
            case "정상": return ChannelStatus.ACTIVE;
            case "삭제": return ChannelStatus.DELETED;
        }
        return null;
    }
}
