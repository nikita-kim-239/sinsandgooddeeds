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
    
    private LocalDate date;
    
    private String description;
    
    public Act()
        {
        }
            
    public Act(LocalDate date,String description)
        {
            this.date=date;
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
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

  
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
    
}