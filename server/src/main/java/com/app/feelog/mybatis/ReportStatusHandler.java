package com.app.feelog.mybatis;

import com.app.feelog.domain.enumeration.ChannelPostScrapStatus;
import com.app.feelog.domain.enumeration.ReportStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(ReportStatus.class)
public class ReportStatusHandler implements TypeHandler<ReportStatus>{

    @Override
    public void setParameter(PreparedStatement ps, int i, ReportStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public ReportStatus getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)){
            case "정상":
                return ReportStatus.ACTIVE;
            case "삭제":
                return ReportStatus.DELETED;
        }
        return null;
    }

    @Override
    public ReportStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)){
            case "정상":
                return ReportStatus.ACTIVE;
            case "삭제":
                return ReportStatus.DELETED;
        }
        return null;
    }

    @Override
    public ReportStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)){
            case "정상":
                return ReportStatus.ACTIVE;
            case "삭제":
                return ReportStatus.DELETED;
        }
        return null;
    }
}
