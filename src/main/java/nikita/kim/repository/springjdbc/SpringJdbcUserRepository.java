/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.repository.springjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nikita.kim.model.User;
import nikita.kim.repository.UserRepository;
import nikita.kim.util.GetAllUsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;



@Repository
public class SpringJdbcUserRepository implements UserRepository{

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);
    
    private final JdbcTemplate jdbcTemplate;
    
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    private final SimpleJdbcInsert insertUser;
    
    @Autowired
    public SpringJdbcUserRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertUser = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    
    
    @Override
    public User save(User user) {
         MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("nick", user.getName())
                .addValue("login", user.getLogin())
                .addValue("password", user.getPassword())
                ;

        if (user.isNew()) {
            Number newKey = insertUser.executeAndReturnKey(map);
            user.setId(newKey.intValue());
        } else if (namedParameterJdbcTemplate.update(
                "UPDATE users SET name=:nick, login:=, password=:password, " +
                        "WHERE id=:id", map) == 0) {
            return null;
        }
        return user;
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
         return jdbcTemplate.query("select distinct U.id,U.nick,U.login,U.password,(Select count(*) from votes where target_user_id=U.id and toheaven=true and actual=true group by target_user_id) as HeavenCount,(Select count(*) from votes where target_user_id=U.id and toheaven=false and actual=true group by target_user_id) as HellCount from users as U left join votes as V on U.id=V.target_user_id", new GetAllUsersMapper());
    }

    @Override
    public Map<String, String> getLoginsAndPasswords() {
        
       Map<String,String> result=jdbcTemplate.query("select login,password from users", new ResultSetExtractor<Map <String,String> >(){
            @Override
            public Map<String,String> extractData(ResultSet rs) throws SQLException,DataAccessException {
                Map<String,String> result= new HashMap<>();
                while(rs.next()){
                    result.put(rs.getString("login"),rs.getString("password"));
                }
                return result;
            }
            }   
       ); 
       return result;       
    }

    @Override
    public Map<String, Integer> getLoginsAndIds() {
        Map<String,Integer> result=jdbcTemplate.query("select login,id from users", new ResultSetExtractor<Map <String,Integer> >(){
            @Override
            public Map<String,Integer> extractData(ResultSet rs) throws SQLException,DataAccessException {
                Map<String,Integer> result= new HashMap<>();
                while(rs.next()){
                    result.put(rs.getString("login"),rs.getInt("id"));
                }
                return result;
            }
            }   
       ); 
       return result; 
    }

    @Override
    public List<String> getNames() {
        List<String> result=jdbcTemplate.query("select nick from users", new ResultSetExtractor<List<String> >(){
            @Override
            public List<String> extractData(ResultSet rs) throws SQLException,DataAccessException {
                List<String> result= new ArrayList<>();
                while(rs.next()){
                    result.add(rs.getString("nick"));
                }
                return result;
            }
            }   
       ); 
       return result;
    }

    @Override
    public List<String> getLogins() {
       List<String> result=jdbcTemplate.query("select login from users", new ResultSetExtractor<List<String> >(){
            @Override
            public List<String> extractData(ResultSet rs) throws SQLException,DataAccessException {
                List<String> result= new ArrayList<>();
                while(rs.next()){
                    result.add(rs.getString("login"));
                }
                return result;
            }
            }   
       ); 
       return result;
    }
    
}
