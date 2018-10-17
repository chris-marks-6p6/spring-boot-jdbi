package net.sixpointsix.springboot.jdbistarter.provider;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KotlinProviderTest {

    @Test
    void getProvider() {
        assertNotNull(new KotlinProvider().getPlugin());
    }

}