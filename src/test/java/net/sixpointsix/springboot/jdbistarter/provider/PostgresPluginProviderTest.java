package net.sixpointsix.springboot.jdbistarter.provider;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostgresPluginProviderTest {

    @Test
    void getProvider() {
        assertNotNull(new PostgresPluginProvider().getPlugin());
    }

}