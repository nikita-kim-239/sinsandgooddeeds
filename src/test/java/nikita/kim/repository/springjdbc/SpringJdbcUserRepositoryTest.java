/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.repository.springjdbc;

import nikita.kim.config.SpringConfig;
import nikita.kim.data.AbstractUserRepositoryTest;
import org.junit.BeforeClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SpringJdbcUserRepositoryTest extends AbstractUserRepositoryTest{
    
    @BeforeClass
    public static void beforeClass()
        {
            context=new AnnotationConfigApplicationContext(SpringConfig.class);
            userRepository=context.getBean(SpringJdbcUserRepository.class);
        }
    
}
