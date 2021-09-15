/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.service;

import java.util.List;
import nikita.kim.model.User;
import nikita.kim.repository.UserRepository;



public class UserService {
    
    
    private UserRepository userRepository;
    
    public User create(User user)
        {
            return userRepository.save(user);
        }
    
    public void delete(int id)
        {
            userRepository.delete(id);
        }
    
    public List<User> getAll()
        {
            return userRepository.getAll();
        }
    
    
    public User get(int id)
        {
            return userRepository.getUserById(id);
        }
}
