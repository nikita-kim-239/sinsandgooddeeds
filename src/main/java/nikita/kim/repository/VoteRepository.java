
package nikita.kim.repository;

import nikita.kim.model.Vote;

/**
 *
 * @author Никита
 */
public interface VoteRepository {
    
    
    Vote save(Vote vote);
     
    void removeOldVote(int userId,int targetUserId);
    
    Integer votesToHeaven(int userId);
    
    Integer votesToHell(int userId);
    
}
