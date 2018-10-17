package net.sixpointsix.springboot.jdbistarter.provider;

import org.jdbi.v3.core.kotlin.KotlinPlugin;
import org.jdbi.v3.core.spi.JdbiPlugin;

public class KotlinProvider implements JdbiPluginProvider {
    /**
     * Get a Jdbi Plugin
     *
     * @return Get plugin
     */
    @Override
    public JdbiPlugin getPlugin() {
        return new KotlinPlugin();
    }
}
