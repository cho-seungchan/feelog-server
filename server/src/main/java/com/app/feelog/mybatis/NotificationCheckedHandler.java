package com.app.feelog.mybatis;

import com.app.feelog.domain.enumeration.NotificationChecked;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(NotificationChecked.class)
public class NotificationCheckedHandler implements TypeHandler<NotificationChecked> {

    @Override
    public void setParameter(PreparedStatement ps, int i, NotificationChecked parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public NotificationChecked getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)) {
            case "읽음":
                return NotificationChecked.READ;
            case "안읽음":
                return NotificationChecked.UNREAD;
        }
        return null;
    }

    @Override
    public NotificationChecked getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)) {
            case "읽음":
                return NotificationChecked.READ;
            case "안읽음":
                return NotificationChecked.UNREAD;
        }
        return null;
    }

    @Override
    public NotificationChecked getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)) {
            case "읽음":
                return NotificationChecked.READ;
            case "안읽음":
                return NotificationChecked.UNREAD;
        }
        return null;
    }
}
