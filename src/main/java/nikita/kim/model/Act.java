/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name = "acts", schema = "public")
@NamedQueries({
        @NamedQuery(name = "Act.GetAll", query = "SELECT a FROM Act a WHERE a.user.id=:userId"),
        @NamedQuery(name = "Act.Delete", query = "DELETE FROM Act a WHERE a.id=:id AND a.user.id=:userId"),
        @NamedQuery(name = "Act.GetById", query = "SELECT a FROM Act a WHERE a.user.id=:userId and a.id=:id")
        
 
})
public class Act extends AbstractBaseEntity{
    
    
    
    @Column(name = "sin", nullable = false)
    @NotNull
    private Boolean sin;
    
    
    @Column(name = "acted", nullable = false)
    @NotNull
    private LocalDate acted;
    
    
    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
    private String description;
    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;
    
    public Act()
        {
        }
            
    public Act(Boolean sin,LocalDate acted,String description)
        {   
            this.sin=sin;
            this.acted=acted;
            this.description=description;
        }
    
    public Act(Integer id,Boolean sin,LocalDate acted,String description)
        {   super(id);
            this.sin=sin;
            this.acted=acted;
            this.description=description;
        }

  
    public String getDescription() {
        return description;
    }

    
    public void setDescription(String description) {
        this.description = description;
    }

   
    public Boolean getSin() {
        return sin;
    }

    
    public void setSin(Boolean sin) {
        this.sin = sin;
    }
    
    
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    
    @Override
    public String toString()
        {
            return "Act{Id: "
                    +id+" Sin "
                    +sin+" Acted "
                    +acted+" Description "
                    +description+" }";
        }

    /**
     * @return the acted
     */
    public LocalDate getActed() {
        return acted;
    }

    /**
     * @param acted the acted to set
     */
    public void setActed(LocalDate acted) {
        this.acted = acted;
    }
    
}
