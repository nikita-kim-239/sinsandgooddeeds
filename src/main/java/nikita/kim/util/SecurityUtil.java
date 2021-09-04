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

    /**
     * @return the currentUser
     */
    public static Integer getCurrentUser() {
        return currentUser;
    }

    /**
     * @param currentUser the currentUser to set
     */
    public static void setCurrentUser(Integer currentUser) {
        SecurityUtil.currentUser = currentUser;
    }
    
    
    
}
