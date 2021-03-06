/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.config;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.jdbc.datasource.DriverManagerDataSource;




@Configuration
@ComponentScan({"nikita.kim.repository.springjdbc","nikita.kim.service"})
public class SpringConfig {
    
    
    
  
    
    
    @Bean
    public DriverManagerDataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/sagd");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        
        
        return dataSource;
    }
    
    
    @Bean
    public JdbcTemplate getJdbcTemplate()
        {
            return new JdbcTemplate(getDataSource());
        }
    
    @Bean
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate()
        {
            return new NamedParameterJdbcTemplate(getDataSource());
        }

    
    
    
}
