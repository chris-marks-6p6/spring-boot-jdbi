package net.sixpointsix.springboot.jdbistarter.resultset;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
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
    void optionalLocalDateTime() throws SQLException {
        LocalDateTime now = LocalDateTime.now();
        when(resultSet.findColumn("time")).thenThrow(SQLException.class);
        when(resultSet.findColumn("a_time")).thenReturn(1);
        when(resultSet.getTimestamp(1)).thenReturn(Timestamp.valueOf(now));

        assertEquals(now, wrapper.getOptionalLocalDateTime("time").get());
    }

    @Test
    void optionalLocalDateTimeNotPresent() throws SQLException {
        when(resultSet.findColumn("time")).thenThrow(SQLException.class);
        when(resultSet.findColumn("a_time")).thenThrow(SQLException.class);

        assertFalse(wrapper.getOptionalLocalDateTime("time").isPresent());
    }

    @Test
    void nullLocalDate() throws SQLException {
        when(resultSet.findColumn("time")).thenThrow(SQLException.class);
        when(resultSet.findColumn("a_time")).thenReturn(1);
        when(resultSet.getDate(1)).thenReturn(null);

        assertNull(wrapper.getLocalDate("time"));
    }

    @Test
    void localDate() throws SQLException {
        LocalDate now = LocalDate.now();
        when(resultSet.findColumn("time")).thenThrow(SQLException.class);
        when(resultSet.findColumn("a_time")).thenReturn(1);
        when(resultSet.getDate(1)).thenReturn(Date.valueOf(now));

        assertEquals(now, wrapper.getLocalDate("time"));
    }

    @Test
    void getInt() throws SQLException {
        when(resultSet.findColumn("value")).thenThrow(SQLException.class);
        when(resultSet.findColumn("a_value")).thenReturn(1);
        when(resultSet.getInt(1)).thenReturn(25);

        assertEquals(Integer.valueOf(25), wrapper.getInt("value"));
    }

    @Test
    void getFloat() throws SQLException {
        when(resultSet.findColumn("value")).thenThrow(SQLException.class);
        when(resultSet.findColumn("a_value")).thenReturn(1);
        when(resultSet.getFloat(1)).thenReturn(10f);

        assertEquals(Float.valueOf(10f), wrapper.getFloat("value"));
    }

    @Test
    void getLong() throws SQLException {
        when(resultSet.findColumn("value")).thenThrow(SQLException.class);
        when(resultSet.findColumn("a_value")).thenReturn(1);
        when(resultSet.getLong(1)).thenReturn(10L);

        assertEquals(Long.valueOf(10L), wrapper.getLong("value"));
    }

    @Test
    void getDouble() throws SQLException {
        when(resultSet.findColumn("value")).thenThrow(SQLException.class);
        when(resultSet.findColumn("a_value")).thenReturn(1);
        when(resultSet.getDouble(1)).thenReturn(10d);

        assertEquals(Double.valueOf(10d), wrapper.getDouble("value"));
    }

    @Test
    void getOptionalDouble() throws SQLException {
        when(resultSet.findColumn("value")).thenThrow(SQLException.class);
        when(resultSet.findColumn("a_value")).thenReturn(1);
        when(resultSet.getDouble(1)).thenReturn(10d);

        assertEquals(Double.valueOf(10d), wrapper.getOptionalDouble("value").get());
    }

    @Test
    void getOptionalDoubleNotPresent() throws SQLException {
        when(resultSet.findColumn("value")).thenThrow(SQLException.class);
        when(resultSet.findColumn("a_value")).thenThrow(SQLException.class);

        assertFalse(wrapper.getOptionalDouble("value").isPresent());
    }
}