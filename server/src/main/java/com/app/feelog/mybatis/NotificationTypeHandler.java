package com.app.feelog.mybatis;

import com.app.feelog.domain.enumeration.NotificationType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(NotificationType.class)
public class NotificationTypeHandler implements TypeHandler<NotificationType> {

    @Override
    public void setParameter(PreparedStatement ps, int i, NotificationType parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public NotificationType getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)) {
            case "SUBSCRIBE":
                return NotificationType.SUBSCRIBE;
            case "COMMUNITY_POST":
                return NotificationType.COMMUNITY_POST;
            case "POST_REPLY":
                return NotificationType.POST_REPLY;
            case "POST_REPLY_LIKE":
                return NotificationType.POST_REPLY_LIKE;
            case "POST_LIKE":
                return NotificationType.POST_LIKE;
            case "RECEIVE_MESSAGE":
                return NotificationType.RECEIVE_MESSAGE;
        }
        return null;
    }

    @Override
    public NotificationType getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)) {
            case "SUBSCRIBE":
                return NotificationType.SUBSCRIBE;
            case "COMMUNITY_POST":
                return NotificationType.COMMUNITY_POST;
            case "POST_REPLY":
                return NotificationType.POST_REPLY;
            case "POST_REPLY_LIKE":
                return NotificationType.POST_REPLY_LIKE;
            case "POST_LIKE":
                return NotificationType.POST_LIKE;
            case "RECEIVE_MESSAGE":
                return NotificationType.RECEIVE_MESSAGE;
        }
        return null;
    }

    @Override
    public NotificationType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)) {
            case "SUBSCRIBE":
                return NotificationType.SUBSCRIBE;
            case "COMMUNITY_POST":
                return NotificationType.COMMUNITY_POST;
            case "POST_REPLY":
                return NotificationType.POST_REPLY;
            case "POST_REPLY_LIKE":
                return NotificationType.POST_REPLY_LIKE;
            case "POST_LIKE":
                return NotificationType.POST_LIKE;
            case "RECEIVE_MESSAGE":
                return NotificationType.RECEIVE_MESSAGE;
        }
        return null;
    }
}