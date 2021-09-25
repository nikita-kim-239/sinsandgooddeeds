/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.repository;

import java.util.List;
import nikita.kim.model.Act;

/**
 *
 * @author Никита
 */
public interface ActRepository {
    
    boolean create(Act act,Integer userId);
    
    boolean update(Act act,Integer userId);

    boolean delete(int id,int userId);
    
    Act get(Integer id,Integer userId);
    
    List <Act> getAll(int userId);
    
    
}
