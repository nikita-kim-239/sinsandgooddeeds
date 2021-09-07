/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.repository;

import java.util.List;
import nikita.kim.model.User;

/**
 *
 * @author Никита
 */
public interface UserRepository 
{
     
    User save (User user);
    
    boolean delete(int id);
    
    User get(int id);
    
    List<User> getAll();
    
}
