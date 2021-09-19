/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import nikita.kim.model.User;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Никита
 */
public class GetAllUsersMapper implements RowMapper{

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        User user = new User();
        
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("nick"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setVotesToHeaven(rs.getInt("HeavenCount"));
        user.setVotesToHell(rs.getInt("HellCount"));
        return user;
        
        
        
    }
    
}
