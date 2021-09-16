/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.model;

import java.util.List;

/**
 *
 * @author Никита
 */
public class User {
    
    
    private Integer id;
    
    private String name;
    
    private String login;
    
    private String password;
    
    private List<Act> acts;
    
  

    
    public User()
        {
        }   
    
    public User(Integer id,String name,String login,String password)
        {
            this.id=id;
            this.name=name;
            this.login=login;
            this.password=password;
        }
            
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the acts
     */
    public List<Act> getActs() {
        return acts;
    }

    /**
     * @param acts the acts to set
     */
    public void setActs(List<Act> acts) {
        this.acts = acts;
    }

    /**
     * @return the votesToHeaven
     */
    
    public Boolean isNew()
        {
            return id==null;
        }
    
    

    
}
