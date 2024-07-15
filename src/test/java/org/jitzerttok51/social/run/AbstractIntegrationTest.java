package org.jitzerttok51.social.run;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

public abstract class AbstractIntegrationTest {

    private static PostgreSQLContainer<?> POSTGRES_SQL_CONTAINER;

    @BeforeAll
    public static void setupDB() {
        POSTGRES_SQL_CONTAINER = new PostgreSQLContainer<>("postgres:14")
                .withDatabaseName("test_db");
        POSTGRES_SQL_CONTAINER.start();

    }

    @DynamicPropertySource
    public static void overrideTestProperties(DynamicPropertyRegistry registry) {

        System.out.println(POSTGRES_SQL_CONTAINER.getJdbcUrl());
        System.out.println(POSTGRES_SQL_CONTAINER.getUsername());
        System.out.println(POSTGRES_SQL_CONTAINER.getPassword());

        registry.add("spring.datasource.url", POSTGRES_SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES_SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRES_SQL_CONTAINER::getPassword);
    }

    @AfterAll
    public static void destroyDB() {
        POSTGRES_SQL_CONTAINER.stop();
    }
}
