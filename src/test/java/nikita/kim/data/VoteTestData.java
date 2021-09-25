/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.data;


import nikita.kim.model.Vote;

import java.time.LocalDateTime;
import java.time.Month;

/**
 *
 * @author Никита
 */
public class VoteTestData {
    public static final Integer USER2_ID=100002;
    public static final Vote voteToCreate=new Vote( LocalDateTime.of(2021, Month.SEPTEMBER,25,9,37,0),true,100002,100003,true);

}
