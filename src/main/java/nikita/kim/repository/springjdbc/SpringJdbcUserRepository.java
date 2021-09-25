/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.repository.springjdbc;


import java.util.List;

import nikita.kim.model.User;
import nikita.kim.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;



@Repository
public class SpringJdbcUserRepository implements UserRepository{

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);
    
    private final JdbcTemplate jdbcTemplate;
    
    
    
    @Autowired
    public SpringJdbcUserRepository(JdbcTemplate jdbcTemplate) {
        

        this.jdbcTemplate = jdbcTemplate;
        
    }
    
    
    @Override
    public boolean create(String name,String login,String password) {   
        return (jdbcTemplate.update(
                "INSERT INTO users (nick,login,password) VALUES (?,?,?)",name,login,password) != 0); 
    }
    
    @Override
    public boolean update(User user) {   
        return (jdbcTemplate.update(
                "UPDATE  users SET nick=?,login=?,password=? WHERE id=?",user.getName(),user.getLogin(),user.getPassword(),user.getId()) != 0); 
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id) != 0;
    }

    @Override
    public User getUserById(int id) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE id=?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public List<User> getAll() {
         return jdbcTemplate.query("select * from users",ROW_MAPPER);
    }

   
    
}
