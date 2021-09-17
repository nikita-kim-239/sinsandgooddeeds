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
    
    private Integer votesToHeaven;
    
    private Integer votesToHell;
    
  

    
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
    
    @Override
    public boolean equals(Object o)
        {   
            
            if (o == this) {
            return true;
            }
             if (!(o instanceof User)) {
            return false;
            }
             
            User u=(User)o; 
            return id.equals(u.getId())&&name.equals(u.getName())&&login.equals(u.getLogin())&&password.equals(u.getPassword());
        }
    
    @Override
    public String toString()
        {
            return "User with id="+id+", name="+name+", login="+login+", password="+password+".";
        }

    /**
     * @return the votesToHeaven
     */
    public Integer getVotesToHeaven() {
        return votesToHeaven;
    }

    /**
     * @param votesToHeaven the votesToHeaven to set
     */
    public void setVotesToHeaven(Integer votesToHeaven) {
        this.votesToHeaven = votesToHeaven;
    }

    /**
     * @return the votesToHell
     */
    public Integer getVotesToHell() {
        return votesToHell;
    }

    /**
     * @param votesToHell the votesToHell to set
     */
    public void setVotesToHell(Integer votesToHell) {
        this.votesToHell = votesToHell;
    }
    
}
