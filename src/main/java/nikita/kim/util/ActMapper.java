/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import nikita.kim.model.Act;
import nikita.kim.model.User;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Никита
 */
public class ActMapper implements RowMapper<Act>{
    public Act mapRow(ResultSet rs, int rowNum) throws SQLException {
      Act act = new Act();
      act.setId(rs.getInt("id"));
      act.setSin(rs.getBoolean("sin"));
      act.setActed(rs.getObject("acted",LocalDate.class));
      act.setDescription(rs.getString("description"));
      User user = new User();
      user.setId(rs.getInt("user_id"));
      act.setUser(user);
      return act;
      
   }
}
