package com.app.feelog.mybatis;

import com.app.feelog.domain.enumeration.DiaryOpen;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;

@MappedTypes(DiaryOpen.class)
public class DiaryOpenTypeHandler implements TypeHandler<DiaryOpen> {

    @Override
    public void setParameter(PreparedStatement ps, int i, DiaryOpen parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public DiaryOpen getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)) {
            case "전체 공개":
                return DiaryOpen.ALL;
            case "구독자에게만 공개":
                return DiaryOpen.SUBSCRIBE;
            case "비공개":
                return DiaryOpen.CLOSE;
        }
        return null;
    }

    @Override
    public DiaryOpen getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)) {
            case "전체 공개": return DiaryOpen.ALL;
            case "구독자에게만 공개": return DiaryOpen.SUBSCRIBE;
            case "비공개": return DiaryOpen.CLOSE;
        }
        return null;
    }

    @Override
    public DiaryOpen getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)) {
            case "전체 공개": return DiaryOpen.ALL;
            case "구독자에게만 공개": return DiaryOpen.SUBSCRIBE;
            case "비공개": return DiaryOpen.CLOSE;
        }
        return null;
    }
}
