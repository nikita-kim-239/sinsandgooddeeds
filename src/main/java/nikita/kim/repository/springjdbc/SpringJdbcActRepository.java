/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.repository.springjdbc;

import java.util.List;
import nikita.kim.model.Act;
import nikita.kim.repository.ActRepository;
import nikita.kim.util.ActMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SpringJdbcActRepository implements ActRepository{

    private static final BeanPropertyRowMapper<Act> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Act.class);
    
    private final JdbcTemplate jdbcTemplate;
    
    
    
    
    @Autowired
    public SpringJdbcActRepository(JdbcTemplate jdbcTemplate) {
       
           
        this.jdbcTemplate = jdbcTemplate;
        
        
    }
    
    @Override
    public boolean create(Act act, Integer userId) {
        return (jdbcTemplate.update(
                "insert into acts (sin,acted,description,user_id) values (?,?,?,?)",act.getSin(),act.getActed(),act.getDescription(),userId) != 0); 
    }
    
    @Override
    public boolean update(Act act, Integer userId) {
        return (jdbcTemplate.update(
                "update acts set sin=?,acted=?,description=? where id=?",act.getSin(),act.getActed(),act.getDescription(),act.getId()) != 0); 
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM acts WHERE id=? and user_id=?", id,userId) != 0;
    }

    @Override
    public Act get(Integer id, Integer userId) {
        List<Act> acts = jdbcTemplate.query("SELECT * FROM acts WHERE id=? and user_id=?", new ActMapper(), id,userId);
        return DataAccessUtils.singleResult(acts);
    }

    @Override
    public List<Act> getAll(int userId) {
       return jdbcTemplate.query("select * from acts where user_id=?",new ActMapper(),userId); 
    }
    
}
