/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.model;

import java.time.LocalDate;

/**
 *
 * @author Никита
 */
public class Sin {
    
    
    private Integer id;
    
    private LocalDate date;
    
            
    
    private SinType sinType;
    
    private String description;

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

    /**
     * @return the sinType
     */
    public SinType getSinType() {
        return sinType;
    }

    /**
     * @param sinType the sinType to set
     */
    public void setSinType(SinType sinType) {
        this.sinType = sinType;
    }

    /**
     * @return the description
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
    
    
    
    
    
    
}
