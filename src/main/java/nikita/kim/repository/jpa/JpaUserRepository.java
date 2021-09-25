/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.repository.jpa;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nikita.kim.model.User;
import nikita.kim.repository.UserRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
@Transactional(readOnly=true)
public class JpaUserRepository implements UserRepository{

    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    @Transactional
    public boolean create(String name,String login,String password) {
        User user=new User(null,name,login,password);
        em.persist(user);
        
        return user!=null;        
    }
    
    @Override
    public boolean update(User user) {
        em.merge(user);
        return user!=null;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery("User.Delete")
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public User getUserById(int id) {
         List<User> users = em.createNamedQuery("User.GetById", User.class)
                .setParameter(1, id)
                .getResultList();
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public List<User> getAll() {
       return em.createNamedQuery("User.GetAll", User.class).getResultList();
    }

    

    
    
}
