/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.repository.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import nikita.kim.model.Vote;
import nikita.kim.repository.VoteRepository;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcVoteRepository implements VoteRepository{

    
    private static final String JDBC_LOGIN="postgres";
    private static final String JDBC_PASSWORD="postgres";
    private static final String JDBC_URL="jdbc:postgresql://localhost:5432/sagd?characterEncoding=UTF-8";
    private static final String INSERT_VOTE_QUERY="insert into votes (time_of_vote,toheaven,user_id,target_user_id,actual) values (?,?,?,?,true)";
    private static final String UPDATE_VOTE_QUERY="update votes set actual=false where user_id=? and target_user_id=? and actual=true";
    
    
    @Override
    public Vote save(Vote vote) {
        
        
  
        Connection connection=null;
        
        try{
                        connection=DriverManager.getConnection(JDBC_URL,JDBC_LOGIN,JDBC_PASSWORD);
                        PreparedStatement pstmt = connection.prepareStatement(INSERT_VOTE_QUERY);
                        pstmt.setObject(1,vote.getDateTime());
                        pstmt.setBoolean(2,vote.isToHeaven());
                        pstmt.setInt(3,vote.getVotedUserId());
                        pstmt.setInt(4,vote.getTargetUserId());
                        pstmt.executeUpdate();
                        
                        }
                        catch(SQLException ex)
                        {
                            ex.printStackTrace();
                        }
        return vote;
        
    }
    
    @Override
    public void removeOldVote(int userId,int targetUserId) {
        
        
  
        Connection connection=null;
        
        try{
                        connection=DriverManager.getConnection(JDBC_URL,JDBC_LOGIN,JDBC_PASSWORD);
                        PreparedStatement pstmt = connection.prepareStatement(UPDATE_VOTE_QUERY);
                        
                        pstmt.setInt(1,userId);
                        pstmt.setInt(2,targetUserId);
                        pstmt.executeUpdate();
                        
                        }
                        catch(SQLException ex)
                        {
                            ex.printStackTrace();
                        }
        
        
    }
    
    
}
