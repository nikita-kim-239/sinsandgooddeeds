/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.repository.jdbc;

import static java.nio.charset.StandardCharsets.UTF_8;
import static junit.framework.TestCase.assertEquals;
import nikita.kim.config.JdbcConfig;
import nikita.kim.config.SpringConfig;
import static nikita.kim.data.UserTestData.NEW_USER_ID;
import static nikita.kim.data.UserTestData.NOT_FOUND;
import static nikita.kim.data.UserTestData.UPDATED_USER_ID;
import static nikita.kim.data.UserTestData.USER1_ID;
import static nikita.kim.data.UserTestData.newUser;
import static nikita.kim.data.UserTestData.updated_user1;
import static nikita.kim.data.UserTestData.user1;
import static nikita.kim.data.UserTestData.users;
import nikita.kim.repository.UserRepository;
import org.junit.After;
import static org.junit.Assert.assertNotNull;

import static org.junit.Assert.assertNull;
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
             context=new AnnotationConfigApplicationContext(JdbcConfig.class);
             userRepository=context.getBean(JdbcUserRepository.class);
             dataSource=context.getBean(DriverManagerDataSource.class);
             ResourceDatabasePopulator tables = new ResourceDatabasePopulator();
             tables.setSqlScriptEncoding(UTF_8.name());
             tables.addScript(new ClassPathResource("db/initDB.sql"));
             tables.addScript(new ClassPathResource("db/populateDB.sql"));
             DatabasePopulatorUtils.execute(tables, dataSource);
        }
    
    
    @After
    public void after()
        {
             ResourceDatabasePopulator tables = new ResourceDatabasePopulator();
             tables.addScript(new ClassPathResource("db/cleanDB.sql"));            
             DatabasePopulatorUtils.execute(tables, dataSource);
        }
    
    
    
    @Test
    public void create()
        {
            assertEquals(newUser,userRepository.save(newUser));
            assertNotNull(userRepository.getUserById(NEW_USER_ID));
            assertEquals(newUser,userRepository.getUserById(NEW_USER_ID));
        }
    
    @Test
    public void update()
        {
            assertEquals(updated_user1,userRepository.save(updated_user1));
            assertNotNull(userRepository.getUserById(NEW_USER_ID));
            assertEquals(updated_user1,userRepository.getUserById(UPDATED_USER_ID));
        }
    
    
    @Test
    public void delete()
        {
            userRepository.delete(USER1_ID);
            assertNull(userRepository.getUserById(USER1_ID));
        }
    
    @Test
    public void deleteNotFound()
        {
            assertEquals(false,userRepository.delete(NOT_FOUND));
        }
    
    @Test
    public void getUserById()
        {
            assertEquals(user1,userRepository.getUserById(USER1_ID));
        }
    
    @Test
    public void getNotFound()
        {
            assertNull(userRepository.getUserById(NOT_FOUND));
        }
    
    
    @Test 
    public void getAll()
        {
            assertEquals(users,userRepository.getAll());
        }    
            
    
}
