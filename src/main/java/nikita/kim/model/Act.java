/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Никита
 */
public class Act implements Serializable{
    
    
    private Integer id;
    
    private Boolean sin;
    
    private LocalDate acted;
    
    private String description;
    
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
        {
            this.id=id;
            this.sin=sin;
            this.acted=acted;
            this.description=description;
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
     * @return the date
     */
   

  
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the sin
     */
    public Boolean getSin() {
        return sin;
    }

    /**
     * @param sin the sin to set
     */
    public void setSin(Boolean sin) {
        this.sin = sin;
    }
    
    public boolean isNew()
        {
            return id==null;
        }
    
    
    @Override
    public String toString()
        {
            return "{Id: "+id+" Sin "+sin+" Acted "+acted+" Description "+description+" }";
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
