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
    
    private Integer targerUserId;
    
    private boolean toHeaven;
    
}
