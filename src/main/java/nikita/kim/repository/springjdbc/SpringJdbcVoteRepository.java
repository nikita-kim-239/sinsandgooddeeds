/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.repository.springjdbc;

import nikita.kim.model.Vote;
import nikita.kim.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class SpringJdbcVoteRepository implements VoteRepository{

    
    private final JdbcTemplate jdbcTemplate;
    
    private final SimpleJdbcInsert insertUser;
    
    
    @Autowired
    public SpringJdbcVoteRepository(JdbcTemplate jdbcTemplate) {
       
        this.insertUser = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("votes")
                .usingGeneratedKeyColumns("id");    
        this.jdbcTemplate = jdbcTemplate;
        
        
    }
    
    @Override
    public Vote save(Vote vote) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", vote.getId())
                .addValue("time_of_vote", vote.getDateTime())
                .addValue("toheaven", vote.isToHeaven())
                .addValue("user_id", vote.getVotedUserId())
                .addValue("target_user_id",vote.getTargetUserId())
                .addValue("actual", true);
                ;

        if (vote.isNew()) {
            Number newKey = insertUser.executeAndReturnKey(map);
            vote.setId(newKey.intValue());
        } 
        
        return vote;
    }

    @Override
    public void removeOldVote(int userId, int targetUserId) {
        jdbcTemplate.update("update votes set actual=false where user_id=? and target_user_id=? and actual=true", userId,targetUserId);
    }

    @Override
    public Integer votesToHeaven(int userId) {
        
        return jdbcTemplate.queryForObject("select count(*) from votes where actual=true and toheaven=true and target_user_id=?",new Object[]{userId},Integer.class);
    }

    @Override
    public Integer votesToHell(int userId) {
         return jdbcTemplate.queryForObject("select count(*) from votes where actual=true and toheaven=false and target_user_id=?",new Object[]{userId},Integer.class);
    }
    
}
