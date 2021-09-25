/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import nikita.kim.model.User;
import nikita.kim.repository.UserRepository;
import nikita.kim.repository.VoteRepository;
import nikita.kim.util.SecurityUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private VoteRepository voteRepository;
    
    public boolean create(User user)
        {
            return userRepository.create(user.getName(),user.getLogin(),user.getPassword());
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
            
            
            List <User> users=userRepository.getAll();
            Map<String,String> loginsAndPasswords=getLoginsAndPasswords(users);
            Map<String,Integer> userIds=getLoginsAndIds(users);
            SecurityUtil.setCurrentUser(userIds.get(login));
            SecurityUtil.setVotesToHeaven(voteRepository.votesToHeaven( SecurityUtil.getCurrentUser()));
            SecurityUtil.setVotesToHell(voteRepository.votesToHell(userIds.get(login)));
            return (loginsAndPasswords.containsKey(login))&&(loginsAndPasswords.get(login).equals(DigestUtils.md5Hex(password)));
        }
    
    @Transactional
    public boolean register(String name,String login,String password)
        {
            List <User> users=userRepository.getAll();
            List<String> logins=getLogins(users);
            List<String> names=getNames(users);
            
            
            if((!logins.contains(login))&&(!names.contains(name))&&(password.length()>2))
                {  
                    return userRepository.create(name,login,DigestUtils.md5Hex(password));
                    
                }
            else
                return false;
            
        }
    
    public User get(int id)
        {
            return userRepository.getUserById(id);
        }
    
    public List<String> getLogins(List<User> users)
        {
            
            return users.stream().map(u->u.getLogin()).collect(Collectors.toList());
            
        }
    
    public List<String> getNames(List<User> users)
        {
            
            return users.stream().map(u->u.getName()).collect(Collectors.toList());
            
        }
    
    public Map<String,String> getLoginsAndPasswords(List<User> users)
        {
            return users.stream().collect(Collectors.toMap(User::getLogin,User::getPassword));
        }
    
    
    public Map<String,Integer> getLoginsAndIds(List<User> users)
        {
            return users.stream().collect(Collectors.toMap(User::getLogin,User::getId));
        }
}
