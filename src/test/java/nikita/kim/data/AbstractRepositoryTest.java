/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.data;

import static java.nio.charset.StandardCharsets.UTF_8;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;


public abstract class AbstractRepositoryTest {
    
    protected static AnnotationConfigApplicationContext context;
    protected static DriverManagerDataSource dataSource;
    
    @Before
    public  void before()
        {
             
             
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
    
    @AfterClass
    public static void afterClass()
        {

            context.close();
        }
    
}
