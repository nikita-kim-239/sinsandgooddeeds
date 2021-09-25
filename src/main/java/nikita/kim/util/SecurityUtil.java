/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.util;

/**
 *
 * @author Никита
 */
public class SecurityUtil {
    
    private static Integer currentUser;
    
    private static Integer votesToHeaven;
    
    private static Integer votesToHell;
    
   
    public static Integer getCurrentUser() {
        return currentUser;
    }

    
    public static void setCurrentUser(Integer currentUser) {
        SecurityUtil.currentUser = currentUser;
    }

    
    public static Integer getVotesToHeaven() {
        return votesToHeaven;
    }

    
    public static void setVotesToHeaven(Integer votesToHeaven) {
        SecurityUtil.votesToHeaven = votesToHeaven;
    }

    
   
    public static Integer getVotesToHell() {
        return votesToHell;
    }

    
    public static void setVotesToHell(Integer votesToHell) {
        SecurityUtil.votesToHell = votesToHell;
    }
    
    
    
    
}
