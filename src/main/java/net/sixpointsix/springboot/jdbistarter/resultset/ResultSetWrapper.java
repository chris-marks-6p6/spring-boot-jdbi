package net.sixpointsix.springboot.jdbistarter.resultset;

import net.sixpointsix.springboot.jdbistarter.exception.ColumnNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class ResultSetWrapper {

    private static final Logger logger = LoggerFactory.getLogger(ResultSetWrapper.class);
    private final ResultSet resultSet;
    private final String prefix;

    public ResultSetWrapper(ResultSet resultSet, String prefix) {
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
    public UUID getUuid(String column) throws SQLException {
        return UUID.fromString(getString(column));
    }

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
    public LocalDateTime getLocalDateTime(String column) throws SQLException {
        return resultSet.getTimestamp(getColumn(column)).toLocalDateTime();
    }

    /**
     * Get the result as a bigint
     *
     * @param column to search
     * @return get big int value
     * @throws SQLException thrown if data is invalid
     */
    public BigInteger getBigInteger(String column) throws SQLException {
        long res = resultSet.getLong(getColumn(column));

        return BigInteger.valueOf(res);
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
                .orElseGet(() -> findColumn(prefix + "_" + name)
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
