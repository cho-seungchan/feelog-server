package com.app.feelog.mybatis;

import com.app.feelog.domain.enumeration.DiaryNameOpen;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;

@MappedTypes(DiaryNameOpen.class)
public class DiaryNameOpenTypeHandler implements TypeHandler<DiaryNameOpen> {

    @Override
    public void setParameter(PreparedStatement ps, int i, DiaryNameOpen parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public DiaryNameOpen getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)){
            case "닉네임":
                return DiaryNameOpen.KNOWN;
            case "비공개(익명)":
                return DiaryNameOpen.UNKNOWN;
        }
        return null;
    }


    @Override
    public DiaryNameOpen getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)){
            case "닉네임":
                return DiaryNameOpen.KNOWN;
            case "비공개(익명)":
                return DiaryNameOpen.UNKNOWN;
        }
        return null;
    }


    @Override
    public DiaryNameOpen getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)){
            case "닉네임":
                return DiaryNameOpen.KNOWN;
            case "비공개(익명)":
                return DiaryNameOpen.UNKNOWN;
        }
        return null;
    }

}
