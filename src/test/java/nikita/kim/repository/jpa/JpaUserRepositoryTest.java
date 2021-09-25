/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.repository.jpa;


import nikita.kim.config.JpaConfig;
import nikita.kim.data.AbstractUserRepositoryTest;
import nikita.kim.repository.UserRepository;
import org.junit.BeforeClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 *
 * @author Никита
 */
public class JpaUserRepositoryTest extends AbstractUserRepositoryTest{
    
    @BeforeClass
    public static void beforeClass()
        {
            context=new AnnotationConfigApplicationContext(JpaConfig.class);
            userRepository=context.getBean(UserRepository.class);
        }
    
}
