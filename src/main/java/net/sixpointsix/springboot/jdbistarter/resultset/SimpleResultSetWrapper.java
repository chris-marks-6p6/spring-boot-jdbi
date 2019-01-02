package net.sixpointsix.springboot.jdbistarter.resultset;

import net.sixpointsix.springboot.jdbistarter.exception.ColumnNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
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

    /**
     * Get an id
     *
     * @param column to search
     * @return id
     */
    @NotNull
    @Override
    public Optional<UUID> getOptionalUuid(String column) {
        try {
            return Optional.ofNullable(getUuid(column));
        } catch (SQLException|ColumnNotFoundException e) {
            logger.debug("Column {} not found", column, e);

            return Optional.empty();
        }
    }

    /**
     * Get a string value from a database
     * @param column to search
     * @return string value
     * @throws SQLException thrown if the data is invalid
     */
    @Override
    public String getString(String column) throws SQLException {
        return resultSet.getString(getColumn(column));
    }

    /**
     * Get a string value from a database
     *
     * @param column to search
     * @return string value
     */
    @NotNull
    @Override
    public Optional<String> getOptionalString(String column) {
        try {
            return Optional.ofNullable(getString(column));
        } catch (SQLException|ColumnNotFoundException e) {
            logger.debug("Column {} not found", column, e);

            return Optional.empty();
        }
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
     * Get a local date time
     *
     * @param column to search
     * @return local date time
     */
    @NotNull
    @Override
    public Optional<LocalDateTime> getOptionalLocalDateTime(String column) {
        try {
            return Optional.ofNullable(getLocalDateTime(column));
        } catch (SQLException|ColumnNotFoundException e) {
            logger.debug("Column {} not found", column, e);

            return Optional.empty();
        }
    }

    /**
     * Get a local date
     *
     * @param column to search
     * @return local date
     * @throws SQLException thrown if data is invalid
     */
    @Override
    @Nullable
    public LocalDate getLocalDate(String column) throws SQLException {
        final int col = getColumn(column);
        final Date dateTime = resultSet.getDate(col);

        if(dateTime != null) {
            return dateTime.toLocalDate();
        }

        return null;
    }

    /**
     * Get a local date
     *
     * @param column to search
     * @return local date
     */
    @NotNull
    @Override
    public Optional<LocalDate> getOptionalLocalDate(String column) {
        try {
            return Optional.ofNullable(getLocalDate(column));
        } catch (SQLException|ColumnNotFoundException e) {
            logger.debug("Column {} not found", column, e);

            return Optional.empty();
        }
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
     * Get an big int
     *
     * @param column to search
     * @return big int value
     */
    @Override
    public Optional<BigInteger> getOptionalBigInteger(String column) {
        try {
            return Optional.ofNullable(getBigInteger(column));
        } catch (SQLException|ColumnNotFoundException e) {
            logger.debug("Column {} not found", column, e);

            return Optional.empty();
        }
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
    public Integer getInt(String column) throws SQLException {
        return resultSet.getInt(getColumn(column));
    }

    /**
     * Get an int from the result set
     *
     * @param column column name
     * @return int value
     */
    @NotNull
    @Override
    public Optional<Integer> getOptionalInt(String column) {
        try {
            return Optional.ofNullable(getInt(column));
        } catch (SQLException|ColumnNotFoundException e) {
            logger.debug("Column {} not found", column, e);

            return Optional.empty();
        }
    }

    /**
     * Get a long from the result set
     * @param column column name
     * @return long value
     * @throws SQLException thrown if data is invalid
     */
    @Override
    public Long getLong(String column) throws SQLException {
        return resultSet.getLong(getColumn(column));
    }

    /**
     * Get a long value
     *
     * @param column column name
     * @return value
     */
    @Override
    public Optional<Long> getOptionalLong(String column) {
        try {
            return Optional.ofNullable(getLong(column));
        } catch (SQLException|ColumnNotFoundException e) {
            logger.debug("Column {} not found", column, e);

            return Optional.empty();
        }
    }

    /**
     * Get a float from the result set
     * @param column column name
     * @return float value
     * @throws SQLException thrown if data is invalid
     */
    @Override
    public Float getFloat(String column) throws SQLException {
        return resultSet.getFloat(getColumn(column));
    }

    /**
     * Get a float value
     *
     * @param column column name
     * @return float value
     */
    @Override
    public Optional<Float> getOptionalFloat(String column) {
        try {
            return Optional.ofNullable(getFloat(column));
        } catch (SQLException|ColumnNotFoundException e) {
            logger.debug("Column {} not found", column, e);

            return Optional.empty();
        }
    }

    /**
     * Get a double from the result set
     * @param column column name
     * @return double value
     * @throws SQLException thrown if data is invalid
     */
    @Override
    public Double getDouble(String column) throws SQLException {
        return resultSet.getDouble(getColumn(column));
    }

    /**
     * Get double value
     *
     * @param column column name
     * @return double value
     */
    @Override
    public Optional<Double> getOptionalDouble(String column) {
        try {
            return Optional.ofNullable(getDouble(column));
        } catch (SQLException|ColumnNotFoundException e) {
            logger.debug("Column {} not found", column, e);

            return Optional.empty();
        }
    }

    /**
     * Get a boolean from the result set
     * @param column column name
     * @return boolean value
     * @throws SQLException thrown if data is invalid
     */
    @Override
    public Boolean getBoolean(String column) throws SQLException {
        return resultSet.getBoolean(getColumn(column));
    }

    /**
     * Get boolean value
     *
     * @param column column name
     * @return boolean value
     */
    @Override
    public Optional<Boolean> getOptionalBoolean(String column) {
        try {
            return Optional.ofNullable(getBoolean(column));
        } catch (SQLException|ColumnNotFoundException e) {
            logger.debug("Column {} not found", column, e);

            return Optional.empty();
        }
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
        return findColumn(getPrefix() + "_" + name)
                .orElseGet(() -> findColumn(name)
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
