/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.config;

import javax.sql.DataSource;
import jdk.internal.loader.Resource;
import nikita.kim.repository.jdbc.JdbcActRepository;
import nikita.kim.repository.jdbc.JdbcUserRepository;
import nikita.kim.repository.jdbc.JdbcVoteRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;



@Configuration
public class SpringConfig {
    
    @Value("classpath:db/initDB.sql")
    private Resource initDB;



    @Value("classpath:db/populateDB.sql")
    private Resource populateDB;
    
    
    @Bean
    public JdbcUserRepository userRepository()
        {
            return new JdbcUserRepository();
        }
    
    @Bean
    public JdbcActRepository actRepository()
        {
            return new JdbcActRepository();
        }
    
    @Bean
    public JdbcVoteRepository voteRepository()
        {
            return new JdbcVoteRepository();
        }
    
    
    @Bean
    public DriverManagerDataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/sinsandgooddeeds");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        DatabasePopulatorUtils.execute(createDatabasePopulator(), dataSource);
        return dataSource;
    }
    
    
    private DatabasePopulator createDatabasePopulator() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setContinueOnError(true);
        databasePopulator.addScript(new ClassPathResource("db/initDB.sql"));
        databasePopulator.addScript(new ClassPathResource("db/populateDB.sql"));
        return databasePopulator;
    }

    
    
    
}
