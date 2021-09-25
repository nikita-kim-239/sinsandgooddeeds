/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.model;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@NamedQueries({
        @NamedQuery(name = "User.Delete", query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = "User.GetById", query = "SELECT u FROM User u where u.id=?1"),
        @NamedQuery(name = "User.GetAll", query = "SELECT u FROM User u"),
})
@Entity
@Table(name = "users", schema = "public")
public class User extends AbstractNamedEntity{
    
    

    @Column(name = "login", nullable = false)
    @NotBlank
    @Size(min = 5, max = 100)
    private String login;
    
    
    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 100)
    private String password;
    
    
    @OneToMany(targetEntity=Act.class,fetch=FetchType.EAGER,mappedBy="user", orphanRemoval = true)
    private List<Act> acts;




    public User()
        {
        }   
    
    public User(Integer id,String name,String login,String password)
        {
            super(id,name);
            this.login=login;
            this.password=password;
        }
            
    
    
    public Integer getId() {
        return id;
    }

    
    public void setId(Integer id) {
        this.id = id;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public String getLogin() {
        return login;
    }

    
    public void setLogin(String login) {
        this.login = login;
    }

    
    public String getPassword() {
        return password;
    }

    
    public void setPassword(String password) {
        this.password = password;
    }

    
   
    
   
    
   
    
    @Override
    public String toString()
        {
            return "User with id="+id+
                    ", name="+name+
                    ", login="+login+
                    ", password="
                    +password+".";
        }

    
    public List<Act> getActs() {
        return acts;
    }


    public void setActs(List<Act> acts) {
        this.acts = acts;
    }

    
    
}
