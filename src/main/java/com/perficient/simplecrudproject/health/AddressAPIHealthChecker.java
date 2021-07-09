package com.perficient.simplecrudproject.health;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.net.Socket;

@Component
@Slf4j
public class AddressAPIHealthChecker implements HealthContributor, HealthIndicator {
    private static final String URL = "https://random-data-api.com/api/address/random_address";

    @Override
    public Health health() {
        try (Socket socket = new Socket(new java.net.URL(URL).getHost(),80)) {
        } catch (Exception e1) {
            log.warn("Failed to connect to : {}",URL);
            return Health.down().withDetail("error", e1.getMessage()).build();
        }
        return Health.up().build();
    }
}
