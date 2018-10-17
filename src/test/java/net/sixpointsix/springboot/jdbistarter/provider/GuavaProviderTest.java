package net.sixpointsix.springboot.jdbistarter.provider;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuavaProviderTest {

    @Test
    void getProvider() {
        assertNotNull(new GuavaProvider().getPlugin());
    }
}