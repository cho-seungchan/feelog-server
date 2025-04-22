package com.app.feelog.mybatis;

import com.app.feelog.domain.enumeration.DiaryOpen;
import com.app.feelog.domain.enumeration.PostType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(PostType.class)
public class PostTypeHandler implements TypeHandler<PostType> {

    @Override
    public void setParameter(PreparedStatement ps, int i, PostType parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public PostType getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)) {
            case "포스트":
                return PostType.POST;
            case "응원글":
                return PostType.CHEERING;
        }
        return null;
    }

    @Override
    public PostType getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)) {
            case "포스트": return PostType.POST;
            case "응원글": return PostType.CHEERING;
        }
        return null;
    }

    @Override
    public PostType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)) {
            case "포스트": return PostType.POST;
            case "응원글": return PostType.CHEERING;
        }
        return null;
    }
}
