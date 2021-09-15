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
    
    Act save(Act act);
    
    boolean delete(int id);
    
    Act get(int id);
    
    List <Act> getAll();
    
    List <Act> getAllByUserId(Integer id);
}
