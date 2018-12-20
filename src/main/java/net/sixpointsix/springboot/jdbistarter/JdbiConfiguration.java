package net.sixpointsix.springboot.jdbistarter;

import net.sixpointsix.springboot.jdbistarter.argumentfactory.BigIntArgumentFactory;
import net.sixpointsix.springboot.jdbistarter.provider.*;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.argument.ArgumentFactory;
import org.jdbi.v3.core.kotlin.KotlinPlugin;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.guava.GuavaPlugin;
import org.jdbi.v3.jodatime2.JodaTimePlugin;
import org.jdbi.v3.postgres.PostgresPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.jdbi.v3.sqlobject.kotlin.KotlinSqlObjectPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.List;

public class JdbiConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(JdbiConfiguration.class);

    @Bean
    @ConditionalOnMissingBean(Jdbi.class)
    public Jdbi getJdbiWithPlugin(DataSource dataSource, List<JdbiPluginProvider> plugins, List<ArgumentFactory> argumentFactories) {
        Jdbi jdbi = Jdbi.create(dataSource);
        plugins
                .forEach(p -> {
                    JdbiPlugin plugin = p.getPlugin();
                    if(plugin == null) {
                        logger.debug("nul JDBI plugin skipped");
                        return;
                    }

                    logger.debug("Enabling {} Jdbi Plugin", plugin.getClass().getSimpleName());
                    jdbi.installPlugin(plugin);
                });

        argumentFactories
                .forEach(a -> {
                    logger.debug("Enabling {} Jdbi Argument Factory", a.getClass().getSimpleName());
                    jdbi.registerArgument(a);
                });

        logger.info("Jdbi enabled with {} plugins and {} argument factories", plugins.size(), argumentFactories.size());

        return jdbi;
    }

    @Bean
    public BigIntArgumentFactory getBigIntegerMapper() {
        return new BigIntArgumentFactory();
    }

    @Bean
    @ConditionalOnClass(SqlObjectPlugin.class)
    @ConditionalOnProperty(value = "6point6.jdbi.plugin.sqlobject", havingValue = "true", matchIfMissing = true)
    public SqlObjectProvider getSqlObjectPlugin() {
        return new SqlObjectProvider();
    }

    @Bean
    @ConditionalOnClass(GuavaPlugin.class)
    @ConditionalOnProperty(value = "6point6.jdbi.plugin.guava", havingValue = "true", matchIfMissing = true)
    public GuavaProvider getGuavaPlugin() {
        return new GuavaProvider();
    }

    @Bean
    @ConditionalOnClass(KotlinPlugin.class)
    @ConditionalOnProperty(value = "6point6.jdbi.plugin.kotlin", havingValue = "true", matchIfMissing = true)
    public KotlinProvider getKotlinPlugin() {
        return new KotlinProvider();
    }

    @Bean
    @ConditionalOnClass(KotlinSqlObjectPlugin.class)
    @ConditionalOnProperty(value = "6point6.jdbi.plugin.kotlinobject", havingValue = "true", matchIfMissing = true)
    public KotlinObjectProvider getKotlinObjectProvider() {
        return new KotlinObjectProvider();
    }

    @Bean
    @ConditionalOnClass(JodaTimePlugin.class)
    @ConditionalOnProperty(value = "6point6.jdbi.plugin.jodatime", havingValue = "true", matchIfMissing = true)
    public JodaTime2Provider getJodaTimeProvider() {
        return new JodaTime2Provider();
    }

    @Bean
    @ConditionalOnClass(PostgresPlugin.class)
    @ConditionalOnProperty(value = "6point6.jdbi.plugin.postgres", havingValue = "true", matchIfMissing = true)
    public PostgresPluginProvider getPostgresPlugin() {
        return new PostgresPluginProvider();
    }

    @Bean
    @ConditionalOnMissingBean(JdbiPluginProvider.class)
    public JdbiPluginProvider getNullProvider() {
        return () -> null;
    }
}
