/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "votes", schema = "public")
@NamedQueries({
        @NamedQuery(name = "Vote.removeOld", query = "UPDATE  Vote v SET v.actual=false WHERE v.votedUserId=:userId AND v.targetUserId=:targetUserId"),
        @NamedQuery(name = "Vote.toHeaven", query = "SELECT v FROM Vote v where v.actual=true and v.targetUserId=:targetUserId and v.toHeaven=true"),
        @NamedQuery(name = "Vote.toHell", query = "SELECT v FROM Vote v where v.actual=true and v.targetUserId=:targetUserId and v.toHeaven=false")

})
public class Vote extends AbstractBaseEntity{



    @Column(name = "time_of_vote", nullable = false)
    @NotNull
    private LocalDateTime dateTime;


    @Column(name="user_id",nullable = false)
    @NotNull
    private Integer votedUserId;

    @Column(name="target_user_id",nullable = false)
    @NotNull
    private Integer targetUserId;


    @Column(name = "toheaven", nullable = false)
    @NotNull
    private boolean toHeaven;


    @Column(name = "actual", nullable = false)
    @NotNull
    private boolean actual;
    
    public Vote()
        {
        }
    
    
    public Vote (LocalDateTime dateTime,Boolean toHeaven,Integer votedUserId,Integer targetUserId,Boolean actual)
        {
            this.dateTime=dateTime;
            this.toHeaven=toHeaven;
            this.votedUserId=votedUserId;
            this.targetUserId=targetUserId;
            this.actual=actual;
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


    public void setTargetUserId(Integer targetUserId) {
        this.targetUserId = targetUserId;
    }


    public boolean isToHeaven() {
        return toHeaven;
    }


    public void setToHeaven(boolean toHeaven) {
        this.toHeaven = toHeaven;
    }


    public boolean isActual() {
        return actual;
    }


    public void setActual(boolean actual) {
        this.actual = actual;
    }
    
    
    
    
}
