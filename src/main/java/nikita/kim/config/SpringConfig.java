/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.config;

import nikita.kim.repository.jdbc.JdbcActRepository;
import nikita.kim.repository.jdbc.JdbcUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class SpringConfig {
    
    @Bean
    public JdbcUserRepository userRepository()
        {
            return new JdbcUserRepository();
        }
    
}
