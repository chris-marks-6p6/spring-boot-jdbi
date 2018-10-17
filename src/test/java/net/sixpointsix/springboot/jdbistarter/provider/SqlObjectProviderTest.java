package net.sixpointsix.springboot.jdbistarter.provider;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqlObjectProviderTest {

    @Test
    void getProvider() {
        assertNotNull(new SqlObjectProvider().getPlugin());
    }

}