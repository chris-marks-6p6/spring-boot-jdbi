package net.sixpointsix.springboot.jdbistarter.resultset;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResultSetWrapperTest {

    ResultSet resultSet;
    ResultSetWrapper wrapper;

    @BeforeEach
    void setUp() {
        resultSet = mock(ResultSet.class);
        wrapper = new SimpleResultSetWrapper(resultSet, "a");
    }

    @Test
    void nullLocalDateTime() throws SQLException {
        when(resultSet.findColumn("time")).thenThrow(SQLException.class);
        when(resultSet.findColumn("a_time")).thenReturn(1);
        when(resultSet.getTimestamp(1)).thenReturn(null);

        assertNull(wrapper.getLocalDateTime("time"));
    }

    @Test
    void localDateTime() throws SQLException {
        LocalDateTime now = LocalDateTime.now();
        when(resultSet.findColumn("time")).thenThrow(SQLException.class);
        when(resultSet.findColumn("a_time")).thenReturn(1);
        when(resultSet.getTimestamp(1)).thenReturn(Timestamp.valueOf(now));

        assertEquals(now, wrapper.getLocalDateTime("time"));
    }

    @Test
    void getInt() throws SQLException {
        when(resultSet.findColumn("value")).thenThrow(SQLException.class);
        when(resultSet.findColumn("a_value")).thenReturn(1);
        when(resultSet.getInt(1)).thenReturn(25);

        assertEquals(25, wrapper.getInt("value"));
    }

    @Test
    void getFloat() throws SQLException {
        when(resultSet.findColumn("value")).thenThrow(SQLException.class);
        when(resultSet.findColumn("a_value")).thenReturn(1);
        when(resultSet.getFloat(1)).thenReturn(10f);

        assertEquals(10f, wrapper.getFloat("value"));
    }

    @Test
    void getLong() throws SQLException {
        when(resultSet.findColumn("value")).thenThrow(SQLException.class);
        when(resultSet.findColumn("a_value")).thenReturn(1);
        when(resultSet.getLong(1)).thenReturn(10L);

        assertEquals(10L, wrapper.getLong("value"));
    }

    @Test
    void getDouble() throws SQLException {
        when(resultSet.findColumn("value")).thenThrow(SQLException.class);
        when(resultSet.findColumn("a_value")).thenReturn(1);
        when(resultSet.getDouble(1)).thenReturn(10d);

        assertEquals(10d, wrapper.getDouble("value"));
    }
}