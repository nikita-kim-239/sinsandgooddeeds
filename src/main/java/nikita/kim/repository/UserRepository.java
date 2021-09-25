/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.repository;


import java.util.List;
import java.util.Map;
import nikita.kim.model.User;

/**
 *
 * @author Никита
 */
public interface UserRepository 
{
    
    
    
    boolean create (String name,String login,String password);
    
    boolean update(User user);
    
    boolean delete(int id);
    
    User getUserById(int id);
    
    List<User> getAll();
    
   
    
}
