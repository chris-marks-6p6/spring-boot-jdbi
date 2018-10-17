package net.sixpointsix.springboot.jdbistarter.provider;

import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.postgres.PostgresPlugin;

public class PostgresPluginProvider implements JdbiPluginProvider {
    /**
     * Get a Jdbi Plugin
     *
     * @return Get plugin
     */
    @Override
    public JdbiPlugin getPlugin() {
        return new PostgresPlugin();
    }
}
