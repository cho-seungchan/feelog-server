package com.app.feelog.mybatis;

import com.app.feelog.domain.enumeration.DiaryStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;

@MappedTypes(DiaryStatus.class)
public class DiaryStatusTypeHandler implements TypeHandler<DiaryStatus> {

    @Override
    public void setParameter(PreparedStatement ps, int i, DiaryStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode()); // "정상" 같은 문자열 저장
    }

    @Override
    public DiaryStatus getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)) {
            case "정상": return DiaryStatus.ACTIVE;
            case "삭제": return DiaryStatus.DELETED;
        }
        return null;
    }

    @Override
    public DiaryStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)) {
            case "정상": return DiaryStatus.ACTIVE;
            case "삭제": return DiaryStatus.DELETED;
        }
        return null;
    }

    @Override
    public DiaryStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)) {
            case "정상": return DiaryStatus.ACTIVE;
            case "삭제": return DiaryStatus.DELETED;
        }
        return null;
    }
}
