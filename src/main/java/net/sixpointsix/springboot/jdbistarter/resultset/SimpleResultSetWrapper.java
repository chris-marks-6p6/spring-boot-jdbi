package net.sixpointsix.springboot.jdbistarter.resultset;

import net.sixpointsix.springboot.jdbistarter.exception.ColumnNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Basic result set wrapper
 */
public class SimpleResultSetWrapper implements ResultSetWrapper {

    private static final Logger logger = LoggerFactory.getLogger(SimpleResultSetWrapper.class);
    private final ResultSet resultSet;
    private final String prefix;

    public SimpleResultSetWrapper(ResultSet resultSet, String prefix) {
        this.resultSet = resultSet;
        this.prefix = prefix;
    }

    /**
     * Get an id
     *
     * @param column to search
     * @return id
     * @throws SQLException  thrown if data is invalid
     */
    @Override
    public UUID getUuid(String column) throws SQLException {
        return UUID.fromString(getString(column));
    }

    @Override
    public String getString(String column) throws SQLException {
        return resultSet.getString(getColumn(column));
    }

    /**
     * Get a local date time
     *
     * @param column to search
     * @return local date time
     * @throws SQLException thrown if data is invalid
     */
    @Override
    @Nullable
    public LocalDateTime getLocalDateTime(String column) throws SQLException {
        final int col = getColumn(column);
        final Timestamp dateTime = resultSet.getTimestamp(col);

        if(dateTime != null) {
            return dateTime.toLocalDateTime();
        }

        return null;
    }

    /**
     * Get the result as a bigint
     *
     * @param column to search
     * @return get big int value
     * @throws SQLException thrown if data is invalid
     */
    @Override
    public BigInteger getBigInteger(String column) throws SQLException {
        long res = resultSet.getLong(getColumn(column));

        return BigInteger.valueOf(res);
    }

    /**
     * Get the raw result set
     * @return raw result set
     */
    @Override
    public ResultSet getResultSet() {
        return resultSet;
    }

    /**
     * Get an int from the result set
     * @param column column name
     * @return int value
     * @throws SQLException thrown if data is invalid
     */
    @Override
    public int getInt(String column) throws SQLException {
        return resultSet.getInt(getColumn(column));
    }

    /**
     * Get a long from the result set
     * @param column column name
     * @return long value
     * @throws SQLException thrown if data is invalid
     */
    @Override
    public long getLong(String column) throws SQLException {
        return resultSet.getLong(getColumn(column));
    }

    /**
     * Get a float from the result set
     * @param column column name
     * @return float value
     * @throws SQLException thrown if data is invalid
     */
    @Override
    public float getFloat(String column) throws SQLException {
        return resultSet.getFloat(getColumn(column));
    }

    /**
     * Get a double from the result set
     * @param column column name
     * @return double value
     * @throws SQLException thrown if data is invalid
     */
    @Override
    public double getDouble(String column) throws SQLException {
        return resultSet.getDouble(getColumn(column));
    }

    /**
     * Get the prefix for the columns
     *
     * This method can be overridden if the prefix is not set in the constructor
     *
     * @return prefix
     */
    protected String getPrefix() {
        return prefix;
    }

    /**
     * Get the column name with a prefix
     *
     * @param name column to search
     * @return name of the column in the query
     */
    @NotNull
    private int getColumn(String name) {
        return findColumn(name)
                .orElseGet(() -> findColumn(getPrefix() + "_" + name)
                        .orElseThrow(() -> new ColumnNotFoundException("Column " + name + " is not in result set with prefix " + prefix)));
    }

    /**
     * Get the column id if one exists
     *
     * @param name name to search for
     * @return column id
     */
    private Optional<Integer> findColumn(String name) {
        try {
            return Optional.of(resultSet.findColumn(name));
        } catch (SQLException e) {
            logger.trace("Column {} not found", name, e);

            return Optional.empty();
        }
    }
}
