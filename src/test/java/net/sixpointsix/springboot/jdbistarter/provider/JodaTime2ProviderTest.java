package net.sixpointsix.springboot.jdbistarter.provider;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JodaTime2ProviderTest {

    @Test
    void getProvider() {
        assertNotNull(new JodaTime2Provider().getPlugin());
    }

}