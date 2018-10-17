package net.sixpointsix.springboot.jdbistarter.provider;

import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.guava.GuavaPlugin;

public class GuavaProvider implements JdbiPluginProvider {

    /**
     * Get a Jdbi Plugin
     *
     * @return Get plugin
     */
    @Override
    public JdbiPlugin getPlugin() {
        return new GuavaPlugin();
    }
}
