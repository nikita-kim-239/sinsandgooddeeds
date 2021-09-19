package nikita.kim.data;

import java.util.Arrays;
import java.util.List;
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
    public static final int UPDATED_USER_ID = 100015;
    public static final User updated_user1=new User(100015,"user1","semen","0ef78b48343037bbdcf70abee62238b4");
    public static final User user1 = new User(100000,"user1","ivan","0ef78b48343037bbdcf70abee62238b4");
    public static final User user2 = new User(100001,"user2","petr","cae49840b6785f4074cb41dd21c47e22");
    public static final User user3 = new User(100002,"user3","sidor","fa6c34cebbd140b3cb53acb85856c7eb");
    public static final User user4 = new User(100003,"user4","fedor","2fa3c839cdfd57deaadc4f0f904813ba");
    public static final User user5 = new User(100004,"user5","evgen","997b9c7fbfbd6f7bf6899cfa75f5ff2e");
    public static final List <User> users=Arrays.asList(user1,user2,user3,user4,user5);
    public static final User newUser = new User(100015,"user5","fedot","8debb68cd703b70fff71375d2d4d014b");
}
