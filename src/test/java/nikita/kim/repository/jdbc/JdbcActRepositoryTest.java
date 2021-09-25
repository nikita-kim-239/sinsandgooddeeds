/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.repository.jdbc;

import nikita.kim.config.JdbcConfig;
import nikita.kim.data.AbstractActRepositoryTest;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Никита
 */
public class JdbcActRepositoryTest extends AbstractActRepositoryTest{
    
    @BeforeClass
    public static void beforeClass()
        {
            context=new AnnotationConfigApplicationContext(JdbcConfig.class);
            actRepository=context.getBean(JdbcActRepository.class);
        }

    
}
