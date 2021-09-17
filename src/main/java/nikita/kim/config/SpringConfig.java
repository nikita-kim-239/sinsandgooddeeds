/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.config;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.jdbc.datasource.DriverManagerDataSource;




@Configuration
@ComponentScan("nikita.kim")
public class SpringConfig {
    
//    @Value("classpath:db/initDB.sql")
//    private Resource initDB;
//
//
//
//    @Value("classpath:db/populateDB.sql")
//    private Resource populateDB;
    
    
  
    
    
    @Bean
    public DriverManagerDataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/sagd");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        
        
        return dataSource;
    }
    
    
//    private DatabasePopulator createDatabasePopulator() {
//        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
//        databasePopulator.setContinueOnError(true);
//        databasePopulator.addScript(new ClassPathResource("db/initDB.sql"));
//        databasePopulator.addScript(new ClassPathResource("db/populateDB.sql"));
//        return databasePopulator;
//    }

    
    
    
}
