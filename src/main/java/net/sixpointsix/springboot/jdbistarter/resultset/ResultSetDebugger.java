package net.sixpointsix.springboot.jdbistarter.resultset;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Debug the result set
 */
public class ResultSetDebugger {

    /**
     * Get the columns from a result set
     * @param rs result set
     * @return List of column
     * @throws SQLException
     */
    public static List<String> getColumns(ResultSet rs) throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();
        List<String> cols = new ArrayList<>();

        for(int i = 1; i < meta.getColumnCount(); i++) {
            cols.add(meta.getColumnName(i));
        }

        return cols;
    }

    /**
     * Get the columns from a result set
     * @param rsw result set wrapper
     * @return List of column
     * @throws SQLException
     */
    public static List<String> getColumns(ResultSetWrapper rsw) throws SQLException {
        return getColumns(rsw.getResultSet());
    }
}
