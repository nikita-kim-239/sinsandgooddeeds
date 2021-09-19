/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.repository.springjdbc;

import java.util.List;
import nikita.kim.model.Act;
import nikita.kim.model.User;
import nikita.kim.repository.ActRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class SpringJdbcActRepository implements ActRepository{

    private static final BeanPropertyRowMapper<Act> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Act.class);
    
    private final JdbcTemplate jdbcTemplate;
    
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    private final SimpleJdbcInsert insertUser;
    
    
    @Autowired
    public SpringJdbcActRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
       
        this.insertUser = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("acts")
                .usingGeneratedKeyColumns("id");    
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        
    }
    
    @Override
    public Act save(Act act, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", act.getId())
                .addValue("sin", act.getSin())
                .addValue("acted", act.getActed())
                .addValue("description", act.getDescription())
                .addValue("user_id",userId);
                ;

        if (act.isNew()) {
            Number newKey = insertUser.executeAndReturnKey(map);
            act.setId(newKey.intValue());
        } else if (namedParameterJdbcTemplate.update(
                "UPDATE acts SET sin=:sin, acted:=acted,description=:description " +
                        "WHERE id=:id", map) == 0) {
            return null;
        }
        return act;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM acts WHERE id=? and user_id=?", id,userId) != 0;
    }

    @Override
    public Act get(int id, int userId) {
        List<Act> acts = jdbcTemplate.query("SELECT * FROM users WHERE id=? and user_id=?", ROW_MAPPER, id,userId);
        return DataAccessUtils.singleResult(acts);
    }

    @Override
    public List<Act> getAll(int userId) {
       return jdbcTemplate.query("select * from acts where user_id=?",ROW_MAPPER,userId); 
    }
    
}
