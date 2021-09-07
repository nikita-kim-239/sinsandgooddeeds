/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.controller;

import java.util.List;
import nikita.kim.model.User;
import nikita.kim.service.UserService;

/**
 *
 * @author Никита
 */
public class UserController {
    
    
    private UserService userService;
    
    
    public List<User> getAll()
        {
            return userService.getAll();
        }
    
    public User get(int id)
        {
            return userService.get(id);
        }
    
    public User create(User user)
        {
            return userService.create(user);
        }
    
    public void delete(int id)
        {
            userService.delete(id);
        }
    
    
    
}
