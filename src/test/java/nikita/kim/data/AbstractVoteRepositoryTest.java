/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.data;

import static nikita.kim.data.VoteTestData.USER2_ID;

import nikita.kim.model.Vote;
import nikita.kim.repository.VoteRepository;

import static nikita.kim.data.VoteTestData.voteToCreate;
import static org.junit.Assert.assertEquals;
import org.junit.Test;




public abstract class AbstractVoteRepositoryTest extends AbstractRepositoryTest{
    
    protected static VoteRepository voteRepository;

    @Test 
    public void votesToHeaven()
        {
            assertEquals((long)2,(long)voteRepository.votesToHeaven(100002));
        }
    
    @Test
    public void save()
        {

            assertEquals(voteToCreate,voteRepository.save(voteToCreate));
        }


}
