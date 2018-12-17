package net.sixpointsix.springboot.jdbistarter.resultset;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface ResultSetWrapper {

    /**
     * Get an id
     *
     * @param column to search
     * @return id
     * @throws SQLException  thrown if data is invalid
     */
    UUID getUuid(String column) throws SQLException;

    /**
     * Get an id
     *
     * @param column to search
     * @return id
     */
    @NotNull
    Optional<UUID> getOptionalUuid(String column);

    /**
     * Get a string value from a database
     * @param column to search
     * @return string value
     * @throws SQLException thrown if the data is invalid
     */
    String getString(String column) throws SQLException;

    /**
     * Get a string value from a database
     * @param column to search
     * @return string value
     */
    @NotNull
    Optional<String> getOptionalString(String column);

    /**
     * Get a local date time
     *
     * @param column to search
     * @return local date time
     * @throws SQLException thrown if data is invalid
     */
    @Nullable
    LocalDateTime getLocalDateTime(String column) throws SQLException;

    /**
     * Get a local date time
     *
     * @param column to search
     * @return local date time
     */
    @NotNull
    Optional<LocalDateTime> getOptionalLocalDateTime(String column);

    /**
     * Get a local date
     *
     * @param column to search
     * @return local date
     * @throws SQLException thrown if data is invalid
     */
    @Nullable
    LocalDate getLocalDate(String column) throws SQLException;

    /**
     * Get a local date
     *
     * @param column to search
     * @return local date
     */
    @NotNull
    Optional<LocalDate> getOptionalLocalDate(String column);

    /**
     * Get an big int
     * @param column to search
     * @return big int value
     * @throws SQLException thrown if data is invalid
     */
    BigInteger getBigInteger(String column) throws SQLException;

    /**
     * Get an big int
     * @param column to search
     * @return big int value
     */
    Optional<BigInteger> getOptionalBigInteger(String column);

    /**
     * Get the original result set for further processing
     * @return result set
     */
    ResultSet getResultSet();

    /**
     * Get an int from the result set
     * @param column column name
     * @return int value
     * @throws SQLException thrown if data is invalid
     */
    Integer getInt(String column) throws SQLException;

    /**
     * Get an int from the result set
     * @param column column name
     * @return int value
     */
    @NotNull
    Optional<Integer> getOptionalInt(String column);

    /**
     * Get a long value
     * @param column column name
     * @return value
     * @throws SQLException thrown if data is invalid
     */
    Long getLong(String column) throws SQLException;

    /**
     * Get a long value
     * @param column column name
     * @return value
     */
    Optional<Long> getOptionalLong(String column);

    /**
     * Get a float value
     * @param column column name
     * @return float value
     * @throws SQLException thrown if data is invalid
     */
    Float getFloat(String column) throws SQLException;

    /**
     * Get a float value
     * @param column column name
     * @return float value
     */
    Optional<Float> getOptionalFloat(String column);

    /**
     * Get double value
     * @param column column name
     * @return double value
     * @throws SQLException thrown if data is invalid
     */
    Double getDouble(String column) throws SQLException;

    /**
     * Get double value
     * @param column column name
     * @return double value
     */
    Optional<Double> getOptionalDouble(String column);

    /**
     * Get a boolean from the result set
     * @param column column name
     * @return boolean value
     * @throws SQLException thrown if data is invalid
     */
    Boolean getBoolean(String column) throws SQLException;

    /**
     * Get boolean value
     *
     * @param column column name
     * @return boolean value
     */
    Optional<Boolean> getOptionalBoolean(String column);
}
