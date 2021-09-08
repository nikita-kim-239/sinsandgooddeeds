/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim;

import nikita.kim.config.SpringConfig;
import nikita.kim.repository.ActRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Никита
 */
public class SpringMain {
    
    public static void main(String[] args)
        {
            ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
            
            ActRepository actRepository = ctx.getBean(ActRepository.class);
            
            System.out.println(actRepository.getAll());
        
        }  
}
