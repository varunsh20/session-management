package com.session.db;

import com.zaxxer.hikari.HikariDataSource;

import jakarta.activation.DataSource;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HikariMonitor {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void checkDataSource() {
        if (!(dataSource instanceof HikariDataSource)) {
            throw new IllegalStateException("DataSource is not HikariCP!");
        }
    }

    @Scheduled(fixedDelay = 10000)
    public void monitor() {
        HikariDataSource hikari = (HikariDataSource) dataSource;
        System.out.println("Hikari Pool Status - Active: " + hikari.getHikariPoolMXBean().getActiveConnections() +
                ", Idle: " + hikari.getHikariPoolMXBean().getIdleConnections() +
                ", Waiting: " + hikari.getHikariPoolMXBean().getThreadsAwaitingConnection());
    }
}