package ru.test.poll.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfiguration {

    @Bean
    public SpringLiquibase liquibase(@Qualifier("pollDataSource") DataSource pollDataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibase/LiquibaseChangeLog.xml");
        liquibase.setDataSource(pollDataSource);
        return liquibase;
    }
}
