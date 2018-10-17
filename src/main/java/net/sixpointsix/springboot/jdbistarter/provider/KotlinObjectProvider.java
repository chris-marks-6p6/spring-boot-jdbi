package net.sixpointsix.springboot.jdbistarter.provider;

import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.kotlin.KotlinSqlObjectPlugin;

public class KotlinObjectProvider implements JdbiPluginProvider {
    /**
     * Get a Jdbi Plugin
     *
     * @return Get plugin
     */
    @Override
    public JdbiPlugin getPlugin() {
        return new KotlinSqlObjectPlugin();
    }
}
