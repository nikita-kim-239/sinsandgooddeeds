package nikita.kim.data;

import nikita.kim.model.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Никита
 */
public class UserTestData {
    
    public static final int NOT_FOUND = 10;
    public static final int USER1_ID = 100000;
    public static final int NEW_USER_ID = 100015;
    public static final User user1 = new User(100000,"user1","ivan","0ef78b48343037bbdcf70abee62238b4"); 
    public static final User newUser = new User(100015,"user5","fedot","8debb68cd703b70fff71375d2d4d014b");
}
