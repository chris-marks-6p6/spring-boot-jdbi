package net.sixpointsix.springboot.jdbistarter.provider;

import org.jdbi.v3.core.spi.JdbiPlugin;

/**
 * Jdbi plugin provider
 */
public interface JdbiPluginProvider {

    /**
     * Get a Jdbi Plugin
     * @return Get plugin
     */
    JdbiPlugin getPlugin();
}
