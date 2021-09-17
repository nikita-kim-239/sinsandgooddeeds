/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.service;

import nikita.kim.model.Vote;
import nikita.kim.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class VoteService {
    
    @Autowired
    private VoteRepository voteRepository;
    
    
    public void vote(Vote vote)
        {
            
            
            voteRepository.removeOldVote(vote.getVotedUserId(), vote.getTargetUserId());
            voteRepository.save(vote);
        }
    
    
}
