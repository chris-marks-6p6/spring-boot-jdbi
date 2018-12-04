package net.sixpointsix.springboot.jdbistarter.resultset;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

abstract public class AbstractRowMapper<T> implements RowMapper<T> {

    /**
     * Prefix if used
     */
    protected final String prefix;

    public AbstractRowMapper() {
        this(null);
    }

    public AbstractRowMapper(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Map the result set with a wrapper
     * @param rsw Result set wrapper
     * @return Model
     * @throws SQLException If the request is invalid
     */
    public abstract T map(ResultSetWrapper rsw) throws SQLException;

    @Override
    public T map(ResultSet rs, StatementContext ctx) throws SQLException {
        ResultSetWrapper rsw = getResultSetWrapper(rs, getPrefix());

        return map(rsw);
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
     * Get the result set
     *
     * This method can be overridden to allow a custom result set wrapper to be
     * used
     *
     * @param resultSet SQL result set
     * @return Result set wrapper
     */
    protected ResultSetWrapper getResultSetWrapper(ResultSet resultSet, String prefix) {
        return new SimpleResultSetWrapper(resultSet, prefix);
    }
}
