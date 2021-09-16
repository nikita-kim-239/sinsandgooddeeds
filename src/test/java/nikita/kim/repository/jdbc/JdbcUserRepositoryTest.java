/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.repository.jdbc;

import nikita.kim.data.UserTestData;
import nikita.kim.config.SpringConfig;
import static nikita.kim.data.UserTestData.NOT_FOUND;
import static nikita.kim.data.UserTestData.USER1_ID;
import nikita.kim.repository.UserRepository;
import org.junit.After;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import org.junit.Before;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;


/**
 *
 * @author Никита
 */
public class JdbcUserRepositoryTest {
    
    private static AnnotationConfigApplicationContext context;
    private static UserRepository userRepository;
    private static DriverManagerDataSource dataSource;
    @Before
    public  void before()
        {
             context=new AnnotationConfigApplicationContext(SpringConfig.class);
             userRepository=context.getBean(JdbcUserRepository.class);
             dataSource=context.getBean(DriverManagerDataSource.class);
             ResourceDatabasePopulator tables = new ResourceDatabasePopulator();
             tables.addScript(new ClassPathResource("db/initDB.sql"));
             tables.addScript(new ClassPathResource("db/populateDB.sql"));
             DatabasePopulatorUtils.execute(tables, dataSource);
        }
    
    
    
    
    
    @Test
    public void delete()
        {
            userRepository.delete(USER1_ID);
            assertNull(userRepository.getUserById(USER1_ID));
        }
    
    @Test
    public void deleteNotFound() throws Exception {
        assertThrows(RuntimeException.class, () -> userRepository.delete(NOT_FOUND));
    }
    
}
