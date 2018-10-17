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
        ResultSetWrapper rsw = new ResultSetWrapper(rs, prefix);

        return map(rsw);
    }
}
