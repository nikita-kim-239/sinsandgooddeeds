/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.model;

import java.time.LocalDateTime;

/**
 *
 * @author Никита
 */
public class Vote {
    
    
    private Integer id;
   
    private LocalDateTime dateTime;
    
    private Integer votedUserId; 
    
    private Integer targetUserId;
    
    private boolean toHeaven;
    
    private boolean actual;
    
    
    public Vote (LocalDateTime dateTime,Boolean toHeaven,Integer votedUserId,Integer targetUserId)
        {
            this.dateTime=dateTime;
            this.toHeaven=toHeaven;
            this.votedUserId=votedUserId;
            this.targetUserId=targetUserId;
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
     * @return the dateTime
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * @param dateTime the dateTime to set
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * @return the votedUserId
     */
    public Integer getVotedUserId() {
        return votedUserId;
    }

    /**
     * @param votedUserId the votedUserId to set
     */
    public void setVotedUserId(Integer votedUserId) {
        this.votedUserId = votedUserId;
    }

    /**
     * @return the targerUserId
     */
    public Integer getTargetUserId() {
        return targetUserId;
    }

    /**
     * @param targerUserId the targerUserId to set
     */
    public void setTargetUserId(Integer targetUserId) {
        this.targetUserId = targetUserId;
    }

    /**
     * @return the toHeaven
     */
    public boolean isToHeaven() {
        return toHeaven;
    }

    /**
     * @param toHeaven the toHeaven to set
     */
    public void setToHeaven(boolean toHeaven) {
        this.toHeaven = toHeaven;
    }

    /**
     * @return the actual
     */
    public boolean isActual() {
        return actual;
    }

    /**
     * @param actual the actual to set
     */
    public void setActual(boolean actual) {
        this.actual = actual;
    }
    
    
    public Boolean isNew()
        {
            return id==null;
        }
    
}
