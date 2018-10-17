package net.sixpointsix.springboot.jdbistarter.argumentfactory;

import org.jdbi.v3.core.argument.Argument;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class BigIntArgumentFactoryTest {

    @Test
    public void getBigint() throws SQLException {
        int position = 1;
        PreparedStatement statement = mock(PreparedStatement.class);

        BigIntArgumentFactory factory = new BigIntArgumentFactory();
        Argument argument = factory.build(BigInteger.TEN, null);

        argument.apply(position, statement, null);

        verify(statement, times(1)).setLong(position, 10L);
    }
}