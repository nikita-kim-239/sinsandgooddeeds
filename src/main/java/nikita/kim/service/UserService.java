/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.service;

import java.util.List;
import java.util.Map;
import nikita.kim.model.User;
import nikita.kim.repository.UserRepository;
import nikita.kim.util.SecurityUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    
    @Autowired
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
    
    
    public boolean autorize(String login,String password)
        {
            Map<String,String> users=userRepository.getLoginsAndPasswords();
            Map<String,Integer> userIds=userRepository.getLoginsAndIds();
            SecurityUtil.setCurrentUser(userIds.get(login));
            return (users.containsKey(login))&&(users.get(login).equals(DigestUtils.md5Hex(password)));
        }
    
    public boolean register(String name,String login,String password)
        {
            List<String> logins=userRepository.getLogins();
            List<String> names=userRepository.getNames();
            User user=new User();
            user.setName(name);
            user.setLogin(login);
            user.setPassword(password);
            user.setVotesToHeaven(0);
            user.setVotesToHell(0);
            if((!logins.contains(login))&&(!names.contains(name))&&(password.length()>2))
                {  
                    userRepository.save(user);
                    return true;
                }
            else
                return false;
            
        }
    
    public User get(int id)
        {
            return userRepository.getUserById(id);
        }
}
