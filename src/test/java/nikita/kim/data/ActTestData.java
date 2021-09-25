/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.data;

import nikita.kim.model.Act;
import static java.time.LocalDate.of;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import static nikita.kim.data.UserTestData.user1;
import static nikita.kim.data.UserTestData.user2;
import static nikita.kim.data.UserTestData.user3;
import static nikita.kim.data.UserTestData.user4;
import static nikita.kim.data.UserTestData.user5;
import nikita.kim.model.User;


public class ActTestData {
    
    public static final int NOT_FOUND = 10;
    public static final int ACT1_ID = 100005;
    public static final int NEW_ACT_ID = 100015;
    public static final int UPDATED_ACT_ID = 100015;
    public static final Act act1 = new Act(ACT1_ID,true,of(2021,Month.SEPTEMBER,11),"Смотрел порно");
    public static final Act act2 = new Act(ACT1_ID+1,false,of(2021,Month.SEPTEMBER,12),"Сходил в храм на литургию");
    public static final Act act3 = new Act(ACT1_ID+2,true,of(2021,Month.SEPTEMBER,13),"Впал в блуд");
    public static final Act act4 = new Act(ACT1_ID+3,false,of(2021,Month.SEPTEMBER,14),"Подал милостыню нищему");
    public static final Act act5 = new Act(ACT1_ID+4,true,of(2021,Month.SEPTEMBER,15),"Ругался матом");
    public static final Act updatedAct=new Act(ACT1_ID,true,of(2014,Month.AUGUST,20),"Нахамил маме");
    public static final List <Act> acts=Arrays.asList(act1);
    public static final Act actToCreate=new Act(true,of(2021,Month.SEPTEMBER,16),"Играл в доту");
    public static final Act newAct = new Act(NEW_ACT_ID,true,of(2021,Month.SEPTEMBER,16),"Играл в доту");
    
    
}
