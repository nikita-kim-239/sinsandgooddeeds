/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.repository;

import nikita.kim.model.Vote;

/**
 *
 * @author Никита
 */
public interface VoteRepository {
    
    
    Vote save(Vote vote);
    
}
