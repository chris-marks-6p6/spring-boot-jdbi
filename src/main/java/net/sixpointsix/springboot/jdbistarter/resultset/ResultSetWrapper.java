package net.sixpointsix.springboot.jdbistarter.resultset;

import org.jetbrains.annotations.Nullable;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public interface ResultSetWrapper {
    UUID getUuid(String column) throws SQLException;

    String getString(String column) throws SQLException;

    @Nullable
    LocalDateTime getLocalDateTime(String column) throws SQLException;

    BigInteger getBigInteger(String column) throws SQLException;

    ResultSet getResultSet();

    int getInt(String column) throws SQLException;

    long getLong(String column) throws SQLException;

    float getFloat(String column) throws SQLException;

    double getDouble(String column) throws SQLException;
}
