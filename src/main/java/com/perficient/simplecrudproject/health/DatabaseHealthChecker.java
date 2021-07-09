package com.perficient.simplecrudproject.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class DatabaseHealthChecker implements HealthContributor, HealthIndicator {
    private DataSource ds;

    public DatabaseHealthChecker(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Health health() {
        try(Connection conn = ds.getConnection();){
            Statement stmt = conn.createStatement();
            stmt.execute("select 1");
        } catch (SQLException ex) {
            return Health.outOfService().withException(ex).build();
        }
        return Health.up().build();
    }
}
