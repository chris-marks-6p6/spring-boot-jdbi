package net.sixpointsix.springboot.jdbistarter.argumentfactory;

import org.jdbi.v3.core.argument.AbstractArgumentFactory;
import org.jdbi.v3.core.argument.Argument;
import org.jdbi.v3.core.argument.ArgumentFactory;
import org.jdbi.v3.core.config.ConfigRegistry;

import java.math.BigInteger;
import java.sql.Types;

public class BigIntArgumentFactory extends AbstractArgumentFactory<BigInteger> {

    /**
     * Constructs an {@link ArgumentFactory} for type {@code T}.
     */
    public BigIntArgumentFactory() {
        super(Types.BIGINT);
    }

    /**
     * Produce an argument object for the given value.
     *
     * @param value  the value to convert to an argument
     * @param config the config registry
     * @return an {@link Argument} for the given {@code value}.
     */
    @Override
    protected Argument build(BigInteger value, ConfigRegistry config) {
        return (position, statement, ctx) -> statement.setLong(position, value.longValue());
    }
}
