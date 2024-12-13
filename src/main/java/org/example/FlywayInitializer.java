package org.example;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlywayInitializer {

    @Autowired
    public FlywayInitializer(Flyway flyway) {
        flyway.migrate(); // Выполнить миграции при старте
    }
}
