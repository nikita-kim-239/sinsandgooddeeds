/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.repository.springjdbc;


import nikita.kim.config.SpringConfig;
import nikita.kim.data.AbstractVoteRepositoryTest;
import nikita.kim.repository.VoteRepository;
import org.junit.BeforeClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Никита
 */
public class SpringJdbcVoteRepositoryTest extends AbstractVoteRepositoryTest{
    
    @BeforeClass
    public static void beforeClass()
        {
            context=new AnnotationConfigApplicationContext(SpringConfig.class);
            voteRepository=context.getBean(VoteRepository.class);
        }
    
}
