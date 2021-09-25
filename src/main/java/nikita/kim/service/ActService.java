/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.service;

import nikita.kim.model.Act;
import nikita.kim.repository.ActRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActService {


    @Autowired
    private ActRepository actRepository;
    
    
    public void create(Act act,int userId)
        {
            actRepository.create(act, userId);
        }
    
    
}
