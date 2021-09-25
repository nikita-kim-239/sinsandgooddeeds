/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.repository.jpa;


import nikita.kim.config.JpaConfig;
import nikita.kim.data.AbstractActRepositoryTest;
import nikita.kim.repository.ActRepository;
import org.junit.BeforeClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class JpaActRepositoryTest extends AbstractActRepositoryTest{
    
    @BeforeClass
    public static void beforeClass()
        {
            context=new AnnotationConfigApplicationContext(JpaConfig.class);
            actRepository=context.getBean(ActRepository.class);
        }
    
}
