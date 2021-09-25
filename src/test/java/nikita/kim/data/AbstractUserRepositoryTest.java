/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.data;


import static junit.framework.TestCase.assertEquals;
import static nikita.kim.data.UserTestData.NEW_USER_ID;
import static nikita.kim.data.UserTestData.NOT_FOUND;
import static nikita.kim.data.UserTestData.USER1_ID;
import static nikita.kim.data.UserTestData.newUser;
import static nikita.kim.data.UserTestData.updated_user1;
import static nikita.kim.data.UserTestData.user1;
import static nikita.kim.data.UserTestData.users;
import nikita.kim.model.User;
import nikita.kim.repository.UserRepository;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;



public abstract class AbstractUserRepositoryTest extends AbstractRepositoryTest{
    
    protected static UserRepository userRepository;

    @Test
    public void create()
        {
            
            
            assertTrue(userRepository.create("user5","fedot","8debb68cd703b70fff71375d2d4d014b"));
            assertNotNull(userRepository.getUserById(NEW_USER_ID));
            assertEquals(newUser,userRepository.getUserById(NEW_USER_ID));
        }
    
    @Test
    public void update()
        {
            User actualUser= new User(USER1_ID,"user1","semen","0ef78b48343037bbdcf70abee62238b4");
            assertTrue(userRepository.update(actualUser));
            assertNotNull(userRepository.getUserById(USER1_ID));
            assertEquals(updated_user1,userRepository.getUserById(USER1_ID));
        }
    
    
    
    
    @Test
    public void delete()
        {
            userRepository.delete(USER1_ID);
            assertNull(userRepository.getUserById(USER1_ID));
        }
    
    @Test
    public void deleteNotFound()
        {
            assertEquals(false,userRepository.delete(NOT_FOUND));
        }
    
    @Test
    public void getUserById()
        {
            assertEquals(user1,userRepository.getUserById(USER1_ID));
        }
    
    @Test
    public void getNotFound()
        {
            assertNull(userRepository.getUserById(NOT_FOUND));
        }
    
    
    @Test 
    public void getAll()
        {
            assertEquals(users,userRepository.getAll());
        }    
          
}
