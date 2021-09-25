/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.repository.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nikita.kim.model.Act;
import nikita.kim.model.User;
import nikita.kim.repository.ActRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly=true)
public class JpaActRepository implements ActRepository{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    @Transactional
    public boolean create(Act act,Integer userId) {
        act.setUser(em.getReference(User.class,userId));
        
        em.persist(act);
        
        return (act!=null)&&(get(act.getId(),userId)!=null);
        
               
    }

    @Override
    
    public boolean update(Act act,Integer userId) {
            act.setUser(em.find(User.class,userId));        
            em.merge(act);
            return (act!=null)&&(get(act.getId(),userId)!=null);
          
    }
    
    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery("Act.Delete")
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Act get(Integer id, Integer userId) {
        List<Act> acts = em.createNamedQuery("Act.GetById", Act.class)
                .setParameter("userId", userId)
                .setParameter("id",id)
                .getResultList();
        return DataAccessUtils.singleResult(acts);
    }

    @Override
    public List<Act> getAll(int userId) {
                return em.createNamedQuery("Act.GetAll", Act.class)
                .setParameter("userId", userId)
                .getResultList();
    }
    
}
