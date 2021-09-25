/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.repository.jpa;

import nikita.kim.model.Vote;
import nikita.kim.repository.VoteRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JpaVoteRepository implements VoteRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Vote save(Vote vote) {
        em.persist(vote);
        return vote;
    }

    @Override
    @Transactional
    public void removeOldVote(int userId, int targetUserId) {
        em.createNamedQuery("Vote.removeOld").setParameter("userId", userId)
                .setParameter("targetUserId", targetUserId)
                .executeUpdate();
    }

    @Override
    public Integer votesToHeaven(int userId) {
        return em.createNamedQuery("Vote.toHeaven",Vote.class).setParameter("targetUserId",userId).getResultList().size();
    }

    @Override
    public Integer votesToHell(int userId) {
        return em.createNamedQuery("Vote.toHell",Vote.class).setParameter("targetUserId",userId).getResultList().size();
    }
    
}
