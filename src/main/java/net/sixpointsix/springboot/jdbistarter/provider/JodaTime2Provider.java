package net.sixpointsix.springboot.jdbistarter.provider;

import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.jodatime2.JodaTimePlugin;

public class JodaTime2Provider implements JdbiPluginProvider {
    /**
     * Get a Jdbi Plugin
     *
     * @return Get plugin
     */
    @Override
    public JdbiPlugin getPlugin() {
        return new JodaTimePlugin();
    }
}
